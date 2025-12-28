package com.voxelengine.world.gen;

import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;

import java.util.Random;

/**
 * Handles the generation of the world terrain.
 * Uses fractal noise to create continents, oceans, and hills.
 */
public class TerrainGenerator {

    private final Noise noise;
    private final TreeGenerator treeGenerator;
    private final long seed;

    public TerrainGenerator(long seed) {
        this.seed = seed;
        this.noise = new Noise(seed);
        this.treeGenerator = new TreeGenerator();
    }

    /**
     * Generates Fractal Brownian Motion (FBM) noise.
     * Combines multiple octaves of noise for detail.
     */
    private float getNoise(float x, float z, int octaves, float frequency, float amplitude) {
        float total = 0;
        float max = 0;
        for (int i = 0; i < octaves; i++) {
            total += noise.noise(x * frequency, 0, z * frequency) * amplitude;
            max += amplitude;
            frequency *= 2.0f;
            amplitude *= 0.5f;
        }
        // Normalize roughly to 0..1 range
        return (total / max + 1.0f) * 0.5f;
    }

    /**
     * Generates the base terrain for a chunk (Stone, Dirt, Water, etc.).
     *
     * @param chunk The chunk to generate.
     */
    public void generate(Chunk chunk) {
        int cx = chunk.chunkX * 16;
        int cz = chunk.chunkZ * 16;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int wx = cx + x;
                int wz = cz + z;

                // 1. Continent Noise (Large scale, determines Ocean vs Land)
                float continent = getNoise(wx, wz, 2, 0.003f, 1.0f); 
                
                // 2. Detail Noise (Hills/Roughness)
                float detail = getNoise(wx, wz, 4, 0.01f, 1.0f);

                // 3. Calculate Height
                float threshold = 0.5f;
                int worldHeight;
                int seaLevel = 60;

                if (continent < threshold) {
                    // OCEAN: Smooth curve approaching threshold
                    float depth = (threshold - continent) / threshold; // 1.0 (Deep) to 0.0 (Shore)
                    worldHeight = seaLevel - (int)(depth * 30); // Max depth 30 blocks
                } else {
                    // LAND: Hills blended by continent factor
                    float heightFactor = (continent - threshold) / (1.0f - threshold); // 0.0 (Shore) to 1.0 (Inland)
                    
                    float finalHeight = heightFactor * 40.0f; // Base land rise
                    finalHeight += heightFactor * detail * 20.0f; // Add hill detail
                    
                    worldHeight = seaLevel + (int)finalHeight;
                }

                // Fill Column
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    if (y == 0) {
                        chunk.setBlock(x, y, z, Block.BEDROCK);
                    } else if (y < worldHeight - 3) {
                        chunk.setBlock(x, y, z, Block.STONE);
                    } else if (y < worldHeight) {
                        // Underwater/Beach is Sand, otherwise Dirt
                        if (worldHeight <= seaLevel + 1) {
                            chunk.setBlock(x, y, z, Block.SAND);
                        } else {
                            chunk.setBlock(x, y, z, Block.DIRT);
                        }
                    } else if (y == worldHeight) {
                        // Top Soil
                        if (worldHeight <= seaLevel + 1) {
                            chunk.setBlock(x, y, z, Block.SAND); // Beach
                        } else {
                            chunk.setBlock(x, y, z, Block.GRASS);
                        }
                    } else if (y > worldHeight && y <= seaLevel) {
                        chunk.setBlock(x, y, z, Block.WATER); // Fill ocean
                    }
                }
            }
        }
        chunk.isPopulated = true;
    }

    /**
     * Populates the chunk with features like trees (decorators).
     *
     * @param chunk The chunk to populate.
     * @param world The world reference.
     */
    public void populate(Chunk chunk, World world) {
        // Deterministic Random per chunk (Trees appear same every reload)
        long chunkSeed = seed + (long)chunk.chunkX * 341873128712L + (long)chunk.chunkZ * 132897987541L;
        Random rng = new Random(chunkSeed);

        // Try to place trees
        int attempts = 4;
        for (int i = 0; i < attempts; i++) {
            int x = rng.nextInt(16);
            int z = rng.nextInt(16);
            int wx = chunk.worldX + x;
            int wz = chunk.worldZ + z;

            // Forest Noise (Biomes)
            // If noise > 0.6, Dense Forest. If < 0.45, Plains.
            float forestNoise = getNoise(wx, wz, 1, 0.01f, 1.0f);
            
            if (forestNoise < 0.45f) continue;
            
            // Higher chance in dense forest areas
            if (forestNoise > 0.6f || rng.nextInt(10) == 0) {
                // Find surface
                int y = 255;
                while (y > 0 && chunk.getBlockLocal(x, y, z) == Block.AIR) {
                    y--;
                }

                // Only plant on grass above water
                if (chunk.getBlockLocal(x, y, z) == Block.GRASS && y >= 60) {
                    treeGenerator.generateTree(world, rng, wx, y + 1, wz);
                }
            }
        }
    }
}