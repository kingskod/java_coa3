package com.voxelengine.world.gen;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;

import java.util.Random;

public class TreeGenerator {

    public void generateTree(World world, Random random, int x, int y, int z) {
        int height = 4 + random.nextInt(3); // 4 to 6 blocks tall

        // 1. Trunk
        for (int i = 0; i < height; i++) {
            // Metadata 0 = Up/Down orientation for Logs
            safeSetBlock(world, x, y + i, z, Block.LOG, (byte)0);
        }

        // 2. Leaves (Sphere-ish)
        int leafStart = y + height - 2;
        int leafEnd = y + height + 1;
        
        for (int ly = leafStart; ly <= leafEnd; ly++) {
            int distanceToTop = ly - (y + height);
            int radius = 2; 
            if (distanceToTop >= 0) radius = 1; // Taper top

            for (int lx = x - radius; lx <= x + radius; lx++) {
                for (int lz = z - radius; lz <= z + radius; lz++) {
                    
                    // Round corners
                    int dx = Math.abs(lx - x);
                    int dz = Math.abs(lz - z);
                    if (dx == radius && dz == radius && (ly != leafEnd || random.nextBoolean())) continue;

                    // Don't replace the trunk
                    if (lx == x && lz == z && ly < y + height) continue;

                    // Place leaves if air
                    if (world.getBlock(lx, ly, lz) == Block.AIR) {
                        safeSetBlock(world, lx, ly, lz, Block.LEAVES, (byte)0);
                    }
                }
            }
        }
    }

    private void safeSetBlock(World world, int x, int y, int z, Block block, byte meta) {
        // Only set if the chunk is loaded to prevent cascading generation lag/crashes
        if (world.isLoaded(x, z)) {
            world.setBlock(x, y, z, block, meta);
        }
    }
}