package com.voxelengine.world.gen;

import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TerrainGenerator {

    private static final int PREVIEW_SIZE = 1024;
    private final Noise noise;
    private final long seed;
    private final boolean[][] oceanMap; 
    private final TreeGenerator treeGenerator;

    public TerrainGenerator(long seed) {
        this.seed = seed;
        this.noise = new Noise(seed);
        this.oceanMap = generateOceanMap(); 
        this.treeGenerator = new TreeGenerator();
    }

    private float getFractalHeight(float x, float z) {
        float total = 0;
        float frequency = 0.004f; 
        float amplitude = 1.0f;
        float maxValue = 0;
        
        for(int i=0; i<3; i++) {
            total += (float)noise.noise(x * frequency, 0, z * frequency) * amplitude;
            maxValue += amplitude;
            amplitude *= 0.5f; 
            frequency *= 2.0f; 
        }
        
        return (total / maxValue + 1.0f) * 0.5f;
    }

    private boolean[][] generateOceanMap() {
        boolean[][] isOcean = new boolean[PREVIEW_SIZE][PREVIEW_SIZE];
        float[][] heightMap = new float[PREVIEW_SIZE][PREVIEW_SIZE];

        for (int x = 0; x < PREVIEW_SIZE; x++) {
            for (int z = 0; z < PREVIEW_SIZE; z++) {
                float n = (float) noise.noise(x * 0.005, 0, z * 0.005);
                heightMap[x][z] = (n + 1) / 2.0f; 
            }
        }

        float waterLevel = 0.4f;
        boolean[][] visited = new boolean[PREVIEW_SIZE][PREVIEW_SIZE];
        Random rng = new Random(seed);

        for (int x = 0; x < PREVIEW_SIZE; x++) {
            for (int z = 0; z < PREVIEW_SIZE; z++) {
                if (!visited[x][z] && heightMap[x][z] < waterLevel) {
                    Queue<Point> queue = new LinkedList<>();
                    LinkedList<Point> basin = new LinkedList<>();
                    queue.add(new Point(x, z));
                    visited[x][z] = true;

                    while (!queue.isEmpty()) {
                        Point p = queue.poll();
                        basin.add(p);

                        int[] dx = {1, -1, 0, 0};
                        int[] dz = {0, 0, 1, -1};

                        for (int i = 0; i < 4; i++) {
                            int nx = p.x + dx[i];
                            int nz = p.z + dz[i];
                            if (nx >= 0 && nx < PREVIEW_SIZE && nz >= 0 && nz < PREVIEW_SIZE) {
                                if (!visited[nx][nz] && heightMap[nx][nz] < waterLevel) {
                                    visited[nx][nz] = true;
                                    queue.add(new Point(nx, nz));
                                }
                            }
                        }
                    }

                    if (rng.nextFloat() < 0.60f) {
                        for (Point p : basin) {
                            isOcean[p.x][p.z] = true;
                        }
                    }
                }
            }
        }
        return isOcean;
    }

    // 1. Terrain Shape Pass (Writes to Chunk only)
    public void generate(Chunk chunk) {
        int cx = chunk.chunkX * 16;
        int cz = chunk.chunkZ * 16;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int wx = cx + x;
                int wz = cz + z;

                int mapX = Math.abs(wx) % PREVIEW_SIZE;
                int mapZ = Math.abs(wz) % PREVIEW_SIZE;

                float h = getFractalHeight(wx, wz);
                boolean isOcean = oceanMap[mapX][mapZ];
                
                float threshold = 0.4f;
                int seaLevel = 60;
                int worldHeight;

                if (h < threshold) {
                    worldHeight = seaLevel - (int)((threshold - h) * 30);
                } else {
                    worldHeight = seaLevel + (int)((h - threshold) * 40);
                }
                
                if (worldHeight >= seaLevel && isOcean) worldHeight = seaLevel - 1;

                boolean isBeach = false;
                if (!isOcean && h >= threshold && h < threshold + 0.05) {
                    isBeach = true;
                }

                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    if (y == 0) {
                        chunk.setBlock(x, y, z, Block.BEDROCK);
                    } else if (y < worldHeight - 3) {
                        chunk.setBlock(x, y, z, Block.STONE);
                    } else if (y < worldHeight) {
                        if (isBeach || (isOcean && y < seaLevel + 2)) {
                            chunk.setBlock(x, y, z, Block.SAND);
                        } else {
                            chunk.setBlock(x, y, z, Block.DIRT);
                        }
                    } else if (y == worldHeight) {
                        if (isOcean && y <= seaLevel) {
                            chunk.setBlock(x, y, z, Block.SAND);
                        } else if (isBeach) {
                            chunk.setBlock(x, y, z, Block.SAND);
                        } else {
                            chunk.setBlock(x, y, z, Block.GRASS);
                        }
                    } else if (y > worldHeight && y <= seaLevel && isOcean) {
                        chunk.setBlock(x, y, z, Block.WATER); 
                    }
                }
            }
        }
        chunk.isPopulated = true;
    }
    
    // 2. Population Pass (Places Trees - modifies World)
    // Called by ChunkManager AFTER adding chunk to world
    public void populate(Chunk chunk, World world) {
        Random rng = new Random(seed + (long)chunk.chunkX * 341873128712L + (long)chunk.chunkZ * 132897987541L);
        
        // Try to place a few trees
        int treeAttempts = 5; 
        for(int i=0; i<treeAttempts; i++) {
            int x = rng.nextInt(16);
            int z = rng.nextInt(16);
            int wx = chunk.worldX + x;
            int wz = chunk.worldZ + z;
            
            // Find surface
            int y = 255;
            while(y > 0 && chunk.getBlockLocal(x, y, z) == Block.AIR) {
                y--;
            }
            
            // Tree conditions: On Grass, Above Water
            if (chunk.getBlockLocal(x, y, z) == Block.GRASS && y > 60) {
                // Place tree
                treeGenerator.generateTree(world, wx, y + 1, wz);
            }
        }
    }

    public void exportPreview(String path) {
        // (Optional preview logic)
    }

    private static class Point {
        int x, z;
        Point(int x, int z) { this.x = x; this.z = z; }
    }
}