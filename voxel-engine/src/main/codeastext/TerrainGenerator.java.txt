package com.voxelengine.world.gen;

import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Implements the full terrain algorithm:
 * Perlin Noise -> Basins/Oceans -> Decoration -> Strata.
 */
public class TerrainGenerator {

    private static final int PREVIEW_SIZE = 1024;
    private final Noise noise;
    private final long seed;
    private final boolean[][] oceanMap; // Global map for consistency in this implementation context

    // In a real infinite world, we wouldn't pre-generate a 1024x1024 ocean map,
    // we would use a biome noise generator.
    // However, the spec asks to "Generate a 1024x1024 Perlin noise field... Identify basins... classify."
    // We will do this logically.

    public TerrainGenerator(long seed) {
        this.seed = seed;
        this.noise = new Noise(seed);
        this.oceanMap = generateOceanMap(); // Simulating the global basin analysis
    }

    /**
     * Pre-computes ocean basins based on the prompt's requirements for the 1024 region.
     * Uses Seed-consistent RNG.
     */
    private boolean[][] generateOceanMap() {
        boolean[][] isOcean = new boolean[PREVIEW_SIZE][PREVIEW_SIZE];
        float[][] heightMap = new float[PREVIEW_SIZE][PREVIEW_SIZE];

        // 1. Generate base elevation
        for (int x = 0; x < PREVIEW_SIZE; x++) {
            for (int z = 0; z < PREVIEW_SIZE; z++) {
                float n = (float) noise.noise(x * 0.005, 0, z * 0.005);
                heightMap[x][z] = (n + 1) / 2.0f; // Normalize 0..1
            }
        }

        // 2. Identify Basins (Flood fill connected components < waterLevel)
        float waterLevel = 0.4f;
        boolean[][] visited = new boolean[PREVIEW_SIZE][PREVIEW_SIZE];
        Random rng = new Random(seed);

        for (int x = 0; x < PREVIEW_SIZE; x++) {
            for (int z = 0; z < PREVIEW_SIZE; z++) {
                if (!visited[x][z] && heightMap[x][z] < waterLevel) {
                    // Found a basin. Flood fill to find extent.
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

                    // 3. Randomly assign 60% of basins as oceans
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

    public void generate(Chunk chunk) {
        int cx = chunk.chunkX * 16;
        int cz = chunk.chunkZ * 16;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int wx = cx + x;
                int wz = cz + z;

                // Map infinite coordinates to our 1024 logic map (wrapping for simplicity or clamping)
                // For a "real" engine we'd use noise, but here we use the map as requested.
                // We'll wrap the 1024 map to make the world infinite repeating.
                int mapX = Math.abs(wx) % PREVIEW_SIZE;
                int mapZ = Math.abs(wz) % PREVIEW_SIZE;

                // Base Height
                float n = (float) noise.noise(wx * 0.005, 0, wz * 0.005);
                float h = (n + 1) / 2.0f; // 0..1

                // Mountain modification
                if (h > 0.8) {
                    h = 0.8f + (float) Math.pow((h - 0.8) * 5, 2) * 0.2f; // Exponential
                }

                int worldHeight = (int) (h * 128) + 64; // Scale
                boolean isOcean = oceanMap[mapX][mapZ];
                int waterLevel = 64 + (int)(0.4 * 128); // Roughly matches the map logic

                // Decorate Edges (Sand)
                boolean isBeach = false;
                if (!isOcean && h < 0.45) {
                    // Check neighbors in map for ocean
                    // Simple check: if close to water level
                    isBeach = true;
                }

                if (isOcean) {
                    worldHeight = (int)(h * 100); // Deep ocean
                }

                // Fill Column
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    if (y == 0) {
                        chunk.setBlock(x, y, z, Block.BEDROCK);
                    } else if (y < worldHeight - 4) {
                        chunk.setBlock(x, y, z, Block.STONE);
                    } else if (y < worldHeight - 1) {
                        chunk.setBlock(x, y, z, isBeach || isOcean ? Block.SAND : Block.DIRT);
                    } else if (y == worldHeight) {
                        if (isOcean) {
                            chunk.setBlock(x, y, z, Block.SAND);
                        } else if (isBeach) {
                            chunk.setBlock(x, y, z, Block.SAND);
                        } else {
                            chunk.setBlock(x, y, z, Block.GRASS);
                        }
                    } else if (y > worldHeight && y <= 60 && isOcean) {
                        chunk.setBlock(x, y, z, Block.WATER); // Fill water for oceans
                    }
                }
            }
        }
        chunk.isPopulated = true;
    }

    public void exportPreview(String path) {
        BufferedImage img = new BufferedImage(PREVIEW_SIZE, PREVIEW_SIZE, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < PREVIEW_SIZE; x++) {
            for (int z = 0; z < PREVIEW_SIZE; z++) {
                if (oceanMap[x][z]) {
                    img.setRGB(x, z, Color.BLUE.getRGB());
                } else {
                    float n = (float) noise.noise(x * 0.005, 0, z * 0.005);
                    float h = (n + 1) / 2.0f;
                    int gray = (int)(h * 255);
                    if (h > 0.8) img.setRGB(x, z, Color.WHITE.getRGB()); // Peak
                    else img.setRGB(x, z, new Color(gray, (int)(gray*1.2)%255, gray).getRGB());
                }
            }
        }
        try {
            File output = new File(path);
            output.getParentFile().mkdirs();
            ImageIO.write(img, "png", output);
            System.out.println("Exported worldgen preview to " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Point {
        int x, z;
        Point(int x, int z) { this.x = x; this.z = z; }
    }
}