package com.voxelengine.world.light;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Handles SkyLight and BlockLight propagation using BFS.
 */
public class LightingEngine {
    
    private final World world;
    private final Queue<LightNode> lightQueue = new LinkedList<>();

    public LightingEngine(World world) {
        this.world = world;
    }

    /**
     * Called when a block changes. 
     * Handles both adding light (placing torch) and removing light (breaking torch/placing block).
     */
    public void updateBlock(int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        
        // Case 1: We placed a light source (Torch, Lava, etc.)
        if (block.isLightSource()) {
            world.setBlockLight(x, y, z, block.getLightLevel());
            lightQueue.add(new LightNode(x, y, z, block.getLightLevel()));
            propagateBlockLight();
        } 
        // Case 2: We placed an opaque block that blocks existing light
        else if (!block.isTransparent()) {
            removeLight(x, y, z);
        }
        // Case 3: We broke a block (Air). 
        // This is complex: we need to check neighbors to see if light flows IN.
        else {
            // Check neighbors and add them to queue if they have light
            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                int nz = z + d[2];
                int level = world.getBlockLight(nx, ny, nz);
                if (level > 0) {
                    lightQueue.add(new LightNode(nx, ny, nz, level));
                }
                // Also check sky light
                int sky = world.getSkyLight(nx, ny, nz);
                if (sky > 0) {
                    lightQueue.add(new LightNode(nx, ny, nz, sky));
                }
            }
            propagateBlockLight();
            propagateSkyLight();
        }
    }

    public void removeLight(int x, int y, int z) {
        // 1. Queue initial removal
        int val = world.getBlockLight(x, y, z);
        if (val == 0) return;

        Queue<LightNode> removeQueue = new LinkedList<>();
        Queue<LightNode> relightQueue = new LinkedList<>();

        removeQueue.add(new LightNode(x, y, z, val));
        world.setBlockLight(x, y, z, 0);

        // 2. Flood Darken
        while (!removeQueue.isEmpty()) {
            LightNode node = removeQueue.poll();
            
            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            for (int[] d : dirs) {
                int nx = node.x + d[0];
                int ny = node.y + d[1];
                int nz = node.z + d[2];
                
                int neighborLevel = world.getBlockLight(nx, ny, nz);
                
                if (neighborLevel != 0 && neighborLevel < node.val) {
                    // This neighbor was likely lit by 'node', so darken it
                    world.setBlockLight(nx, ny, nz, 0);
                    removeQueue.add(new LightNode(nx, ny, nz, neighborLevel));
                } else if (neighborLevel >= node.val) {
                    // This neighbor is lit by someone else (brighter or equal).
                    // It can reinfect the dark area.
                    relightQueue.add(new LightNode(nx, ny, nz, neighborLevel));
                }
            }
        }

        // 3. Flood Relight (from the boundaries)
        this.lightQueue.addAll(relightQueue);
        propagateBlockLight(); 
    }

    public void calculateInitialLighting(int chunkX, int chunkZ) {
        // Sky Light: Top-down
        int cx = chunkX * 16;
        int cz = chunkZ * 16;
        
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int light = 15;
                for (int y = 255; y >= 0; y--) {
                    int wx = cx + x;
                    int wz = cz + z;
                    Block b = world.getBlock(wx, y, wz);
                    if (!b.isTransparent()) {
                        light = 0;
                    } else if (light > 0 && b != Block.AIR && b != Block.WATER) { 
                        light -= 1;
                        if(light < 0) light = 0;
                    }
                    world.setSkyLight(wx, y, wz, light);
                    if (light > 0) {
                        lightQueue.add(new LightNode(wx, y, wz, light));
                    }
                }
            }
        }
        propagateSkyLight();
    }

    private void propagateSkyLight() {
        while (!lightQueue.isEmpty()) {
            LightNode node = lightQueue.poll();
            int x = node.x; int y = node.y; int z = node.z;
            int lightLevel = world.getSkyLight(x, y, z);

            if (lightLevel <= 0) continue;

            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                int nz = z + d[2];
                
                Block nb = world.getBlock(nx, ny, nz);
                if (nb.isTransparent()) {
                    int currentNeighborLight = world.getSkyLight(nx, ny, nz);
                    if (currentNeighborLight + 2 <= lightLevel) {
                        world.setSkyLight(nx, ny, nz, lightLevel - 1);
                        lightQueue.add(new LightNode(nx, ny, nz, lightLevel - 1));
                    }
                }
            }
        }
    }
    
    private void propagateBlockLight() {
        while (!lightQueue.isEmpty()) {
            LightNode node = lightQueue.poll();
            int x = node.x; int y = node.y; int z = node.z;
            int light = world.getBlockLight(x, y, z);

            if (light <= 0) continue;

            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                int nz = z + d[2];
                
                Block nb = world.getBlock(nx, ny, nz);
                if (nb.isTransparent()) {
                    int neighborLight = world.getBlockLight(nx, ny, nz);
                    if (neighborLight + 2 <= light) {
                        world.setBlockLight(nx, ny, nz, light - 1);
                        lightQueue.add(new LightNode(nx, ny, nz, light - 1));
                    }
                }
            }
        }
    }

    private static class LightNode {
        int x, y, z, val;
        LightNode(int x, int y, int z, int val) {
            this.x = x; this.y = y; this.z = z; this.val = val;
        }
    }
}