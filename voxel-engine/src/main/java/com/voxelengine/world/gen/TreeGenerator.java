package com.voxelengine.world.gen;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;

import java.util.Random;

public class TreeGenerator {

    public void generateTree(World world, int x, int y, int z) {
        // Simple Oak Tree
        int height = 4 + new Random().nextInt(3); // 4 to 6 blocks tall

        // 1. Trunk
        for (int i = 0; i < height; i++) {
            safeSetBlock(world, x, y + i, z, Block.LOG);
        }

        // 2. Leaves
        // Generate a rough sphere/ellipsoid at the top
        int leafStart = y + height - 2;
        int leafEnd = y + height + 1;
        int radius = 2;

        for (int ly = leafStart; ly <= leafEnd; ly++) {
            int yOffset = ly - (y + height); // Distance from top
            
            // Radius shrinks at top
            int currentRadius = radius;
            if (ly == leafEnd) currentRadius = 1; // Top tip
            
            for (int lx = x - currentRadius; lx <= x + currentRadius; lx++) {
                for (int lz = z - currentRadius; lz <= z + currentRadius; lz++) {
                    
                    // Circular check (Manhattan or Euclidean)
                    // Simple "corners cut" logic:
                    int dx = Math.abs(lx - x);
                    int dz = Math.abs(lz - z);
                    
                    // Skip corners to make it rounder
                    if (dx == currentRadius && dz == currentRadius && ly != leafEnd) continue;
                    
                    // Don't overwrite the trunk
                    if (lx == x && lz == z && ly < y + height) continue;
                    
                    // Place leaf if air
                    if (world.getBlock(lx, ly, lz) == Block.AIR) {
                        safeSetBlock(world, lx, ly, lz, Block.LEAVES);
                    }
                }
            }
        }
    }

    // Safety wrapper: Only place if chunk is loaded to avoid infinite gen loops
    private void safeSetBlock(World world, int x, int y, int z, Block block) {
        if (world.isLoaded(x, z)) {
            world.setBlock(x, y, z, block);
        }
    }
}