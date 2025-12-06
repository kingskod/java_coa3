package com.voxelengine.world;

import com.voxelengine.world.light.LightingEngine;
import com.voxelengine.utils.Direction;

public class World {

    private final ChunkManager chunkManager;
    private final LightingEngine lightingEngine;

    public World() {
        this.chunkManager = new ChunkManager(this);
        this.lightingEngine = new LightingEngine(this);
        this.chunkManager.setLighting(this.lightingEngine);
    }

    public Block getBlock(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) return Block.AIR;
        if (!isLoaded(x, z)) return Block.AIR;
        
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getBlockLocal(x & 15, y, z & 15);
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        if (!isLoaded(x, z)) return;
        
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        int lx = x & 15;
        int lz = z & 15;
        
        c.setBlock(lx, y, z & 15, block);
        lightingEngine.updateBlock(x, y, z);
        
        // Update Neighbors if on boundary
        updateNeighbors(x, z, lx, lz);
    }
    
    private void updateNeighbors(int worldX, int worldZ, int lx, int lz) {
        if (lx == 0) markDirty(worldX - 1, worldZ);
        if (lx == 15) markDirty(worldX + 1, worldZ);
        if (lz == 0) markDirty(worldX, worldZ - 1);
        if (lz == 15) markDirty(worldX, worldZ + 1);
    }
    
    private void markDirty(int x, int z) {
        if (isLoaded(x, z)) {
            chunkManager.getChunk(x >> 4, z >> 4).isDirty = true;
        }
    }

    public void tick() {}

    public ChunkManager getChunkManager() { return chunkManager; }
    
    public boolean isLoaded(int x, int z) {
        return chunkManager.hasChunk(x >> 4, z >> 4);
    }
    
    // Light delegates
    public int getSkyLight(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) return 15;
        if (!isLoaded(x, z)) return 15;
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getSkyLight(x & 15, y, z & 15);
    }
    public void setSkyLight(int x, int y, int z, int val) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        if (!isLoaded(x, z)) return;
        chunkManager.getChunk(x >> 4, z >> 4).setSkyLight(x & 15, y, z & 15, val);
    }
    public int getBlockLight(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) return 0;
        if (!isLoaded(x, z)) return 0;
        chunkManager.getChunk(x >> 4, z >> 4).getBlockLight(x & 15, y, z & 15);
        return chunkManager.getChunk(x >> 4, z >> 4).getBlockLight(x & 15, y, z & 15);
    }
    public void setBlockLight(int x, int y, int z, int val) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        if (!isLoaded(x, z)) return;
        chunkManager.getChunk(x >> 4, z >> 4).setBlockLight(x & 15, y, z & 15, val);
    }
}