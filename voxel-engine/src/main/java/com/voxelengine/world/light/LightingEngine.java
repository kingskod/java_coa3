package com.voxelengine.world.light;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;

import java.util.LinkedList;
import java.util.Queue;

public class LightingEngine {
    
    private final World world;
    
    // Separate queues to handle independent propagation
    private final Queue<LightNode> skyLightQueue = new LinkedList<>();
    private final Queue<LightNode> skyRemovalQueue = new LinkedList<>();
    
    private final Queue<LightNode> blockLightQueue = new LinkedList<>();
    private final Queue<LightNode> blockRemovalQueue = new LinkedList<>();

    public LightingEngine(World world) {
        this.world = world;
    }

    /**
     * Entry point for block updates (Place/Break).
     * Synchronized to prevent crash when interacting while chunks load.
     */
    public synchronized void updateBlock(int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        updateBlockLight(x, y, z, block);
        updateSkyLight(x, y, z, block);
    }

    /**
     * Initial calculation for new chunks.
     * Synchronized for thread safety.
     */
    public synchronized void calculateInitialLighting(int chunkX, int chunkZ) {
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
                    } else if (light < 15 && light > 0) {
                        light--;
                    }
                    
                    world.setSkyLight(wx, y, wz, light);
                    if (light > 0) {
                        skyLightQueue.add(new LightNode(wx, y, wz, light));
                    }
                }
            }
        }
        propagateSkyLight();
    }

    /**
     * Scans borders. Synchronized for thread safety.
     */
    public synchronized void stitchChunkBorders(int chunkX, int chunkZ) {
        int cx = chunkX * 16;
        int cz = chunkZ * 16;

        stitchSeam(cx, cz, -1, 0, 0, 1, 16, 256); // West
        stitchSeam(cx + 15, cz, 1, 0, 0, 1, 16, 256); // East
        stitchSeam(cx, cz, 0, -1, 1, 0, 16, 256); // North
        stitchSeam(cx, cz + 15, 0, 1, 1, 0, 16, 256); // South
        
        propagateBlockLight();
        propagateSkyLight();
    }

    // =========================================
    // PRIVATE HELPERS (Must run inside sync methods)
    // =========================================

    private void updateBlockLight(int x, int y, int z, Block block) {
        int oldLevel = world.getBlockLight(x, y, z);
        int newLevel = block.getLightLevel();

        if (newLevel > 0) {
            world.setBlockLight(x, y, z, newLevel);
            blockLightQueue.add(new LightNode(x, y, z, newLevel));
            propagateBlockLight();
        } else if (oldLevel > 0) {
            blockRemovalQueue.add(new LightNode(x, y, z, oldLevel));
            world.setBlockLight(x, y, z, 0);
            performBlockLightRemoval();
            checkNeighborsForRelight(x, y, z, false);
        } else if (!block.isTransparent()) {
            checkNeighborsForRelight(x, y, z, false);
        } else {
            checkNeighborsForRelight(x, y, z, false);
        }
    }

    private void updateSkyLight(int x, int y, int z, Block block) {
        int oldLevel = world.getSkyLight(x, y, z);
        
        if (!block.isTransparent()) {
            if (oldLevel > 0) {
                skyRemovalQueue.add(new LightNode(x, y, z, oldLevel));
                world.setSkyLight(x, y, z, 0);
                performSkyLightRemoval();
            }
        } else {
            int lightAbove = world.getSkyLight(x, y + 1, z);
            if (lightAbove == 15) {
                world.setSkyLight(x, y, z, 15);
                skyLightQueue.add(new LightNode(x, y, z, 15));
                propagateSkyLight();
            } else {
                checkNeighborsForRelight(x, y, z, true);
            }
        }
    }

    private void stitchSeam(int x, int z, int dx, int dz, int stepX, int stepZ, int length, int height) {
        for (int l = 0; l < length; l++) {
            int curX = x + (l * stepX);
            int curZ = z + (l * stepZ);
            
            for (int y = 0; y < height; y++) {
                int mySky = world.getSkyLight(curX, y, curZ);
                int myBlk = world.getBlockLight(curX, y, curZ);
                
                int nX = curX + dx;
                int nZ = curZ + dz;
                
                if (!world.isLoaded(nX, nZ)) continue;

                int nSky = world.getSkyLight(nX, y, nZ);
                int nBlk = world.getBlockLight(nX, y, nZ);
                
                if (nSky > mySky + 1) {
                    world.setSkyLight(curX, y, curZ, nSky - 1);
                    skyLightQueue.add(new LightNode(curX, y, curZ, nSky - 1));
                    markChunkDirty(curX, curZ);
                } else if (mySky > nSky + 1) {
                    world.setSkyLight(nX, y, nZ, mySky - 1);
                    skyLightQueue.add(new LightNode(nX, y, nZ, mySky - 1));
                    markChunkDirty(nX, nZ);
                }
                
                if (nBlk > myBlk + 1) {
                    world.setBlockLight(curX, y, curZ, nBlk - 1);
                    blockLightQueue.add(new LightNode(curX, y, curZ, nBlk - 1));
                    markChunkDirty(curX, curZ);
                } else if (myBlk > nBlk + 1) {
                    world.setBlockLight(nX, y, nZ, myBlk - 1);
                    blockLightQueue.add(new LightNode(nX, y, nZ, myBlk - 1));
                    markChunkDirty(nX, nZ);
                }
            }
        }
    }
    
    private void markChunkDirty(int x, int z) {
        int cx = x >> 4;
        int cz = z >> 4;
        if (world.getChunkManager().hasChunk(cx, cz)) {
            world.getChunkManager().getChunk(cx, cz).isDirty = true;
        }
    }

    private void performBlockLightRemoval() {
        while (!blockRemovalQueue.isEmpty()) {
            LightNode node = blockRemovalQueue.poll();
            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            
            for (int[] d : dirs) {
                int nx = node.x + d[0];
                int ny = node.y + d[1];
                int nz = node.z + d[2];
                int neighborLevel = world.getBlockLight(nx, ny, nz);
                
                if (neighborLevel != 0 && neighborLevel < node.val) {
                    world.setBlockLight(nx, ny, nz, 0);
                    blockRemovalQueue.add(new LightNode(nx, ny, nz, neighborLevel));
                } else if (neighborLevel >= node.val) {
                    blockLightQueue.add(new LightNode(nx, ny, nz, neighborLevel));
                }
            }
        }
        propagateBlockLight();
    }

    private void propagateBlockLight() {
        while (!blockLightQueue.isEmpty()) {
            LightNode node = blockLightQueue.poll();
            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            for (int[] d : dirs) {
                int nx = node.x + d[0];
                int ny = node.y + d[1];
                int nz = node.z + d[2];
                if (world.getBlock(nx, ny, nz).isTransparent()) {
                    int currentLevel = world.getBlockLight(nx, ny, nz);
                    if (currentLevel + 2 <= node.val) {
                        world.setBlockLight(nx, ny, nz, node.val - 1);
                        blockLightQueue.add(new LightNode(nx, ny, nz, node.val - 1));
                    }
                }
            }
        }
    }

    private void performSkyLightRemoval() {
        while (!skyRemovalQueue.isEmpty()) {
            LightNode node = skyRemovalQueue.poll();
            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            
            for (int[] d : dirs) {
                int nx = node.x + d[0];
                int ny = node.y + d[1];
                int nz = node.z + d[2];
                int neighborLevel = world.getSkyLight(nx, ny, nz);
                
                if (neighborLevel == 0) continue;

                boolean verticalSun = (d[1] == -1 && node.val == 15 && neighborLevel == 15);
                boolean standardDecay = (neighborLevel < node.val);

                if (verticalSun || standardDecay) {
                    world.setSkyLight(nx, ny, nz, 0);
                    skyRemovalQueue.add(new LightNode(nx, ny, nz, neighborLevel));
                } else {
                    skyLightQueue.add(new LightNode(nx, ny, nz, neighborLevel));
                }
            }
        }
        propagateSkyLight();
    }

    private void propagateSkyLight() {
        while (!skyLightQueue.isEmpty()) {
            LightNode node = skyLightQueue.poll();
            int currentVal = node.val;
            
            int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
            for (int[] d : dirs) {
                int nx = node.x + d[0];
                int ny = node.y + d[1];
                int nz = node.z + d[2];
                
                if (world.getBlock(nx, ny, nz).isTransparent()) {
                    int neighborLevel = world.getSkyLight(nx, ny, nz);
                    int expectedLevel = currentVal - 1;
                    
                    if (d[1] == -1 && currentVal == 15) {
                        expectedLevel = 15;
                    }
                    
                    if (neighborLevel < expectedLevel) {
                        world.setSkyLight(nx, ny, nz, expectedLevel);
                        skyLightQueue.add(new LightNode(nx, ny, nz, expectedLevel));
                    }
                }
            }
        }
    }

    private void checkNeighborsForRelight(int x, int y, int z, boolean isSky) {
        int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];
            int nz = z + d[2];
            int level = isSky ? world.getSkyLight(nx, ny, nz) : world.getBlockLight(nx, ny, nz);
            if (level > 0) {
                if (isSky) skyLightQueue.add(new LightNode(nx, ny, nz, level));
                else blockLightQueue.add(new LightNode(nx, ny, nz, level));
            }
        }
        if (isSky) propagateSkyLight();
        else propagateBlockLight();
    }

    private static class LightNode {
        int x, y, z, val;
        LightNode(int x, int y, int z, int val) {
            this.x = x; this.y = y; this.z = z; this.val = val;
        }
    }
}