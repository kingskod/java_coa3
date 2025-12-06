package com.voxelengine.world;

import com.voxelengine.world.light.LightingEngine;

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
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getBlock(x & 15, y, z & 15);
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        c.setBlock(x & 15, y, z & 15, block);
        lightingEngine.updateBlock(x, y, z);
    }

    public int getSkyLight(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) return 15;
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getSkyLight(x & 15, y, z & 15);
    }

    public void setSkyLight(int x, int y, int z, int val) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        c.setSkyLight(x & 15, y, z & 15, val);
    }

    public int getBlockLight(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) return 0;
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getBlockLight(x & 15, y, z & 15);
    }

    public void setBlockLight(int x, int y, int z, int val) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        c.setBlockLight(x & 15, y, z & 15, val);
    }

    public void tick() {
        // Random ticks for growth, etc.
    }
    public ChunkManager getChunkManager() {
        return chunkManager;
    }
    public boolean isLoaded(int x, int z) {
        // Simple check: Is the chunk in memory?
        // Map x/z to chunk coords
        int cx = x >> 4;
        int cz = z >> 4;
        return chunkManager.getChunk(cx, cz) != null;
    }
}