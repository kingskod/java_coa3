package com.voxelengine.world;

import com.voxelengine.logic.LogicSystem;
import com.voxelengine.world.light.LightingEngine;

/**
 * Represents the entire game world.
 * Manages chunks, lighting, time, and logic updates.
 */
public class World {

    private final ChunkManager chunkManager;
    private final LightingEngine lightingEngine;
    private final LogicSystem logicSystem;
    
    // Game time in ticks. 0 = Dawn, 6000 = Noon, 18000 = Midnight.
    private long time = 6000;

    public World() {
        this.chunkManager = new ChunkManager(this);
        this.logicSystem = new LogicSystem(this);
        this.lightingEngine = new LightingEngine(this);
        
        this.chunkManager.setLighting(this.lightingEngine);
    }

    /**
     * Gets the block type at the specified world coordinates.
     */
    public Block getBlock(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) return Block.AIR;
        if (!isLoaded(x, z)) return Block.AIR;
        
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getBlockLocal(x & 15, y, z & 15);
    }
    
    /**
     * Gets the metadata of the block at the specified coordinates.
     */
    public byte getMetadata(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) return 0;
        if (!isLoaded(x, z)) return 0;
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getMetadata(x & 15, y, z & 15);
    }

    /**
     * Sets the block at the specified coordinates with default metadata (0).
     */
    public void setBlock(int x, int y, int z, Block block) {
        setBlock(x, y, z, block, (byte)0);
    }

    /**
     * Sets the block and metadata at the specified coordinates.
     * triggers lighting updates, logic updates, and neighbor notifications.
     */
    public void setBlock(int x, int y, int z, Block block, byte meta) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        if (!isLoaded(x, z)) return;
        
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        int lx = x & 15;
        int lz = z & 15;
        
        c.setBlock(lx, y, lz, block, meta);
        lightingEngine.updateBlock(x, y, z);
        logicSystem.updateNetwork(x, y, z);
        
        if (block.isWater()) {
            logicSystem.scheduleTick(x, y, z, block, 5);
        }
        
        checkNeighborTick(x + 1, y, z);
        checkNeighborTick(x - 1, y, z);
        checkNeighborTick(x, y, z + 1);
        checkNeighborTick(x, y, z - 1);
        checkNeighborTick(x, y - 1, z);
        
        updateNeighbors(x, z, lx, lz);
    }
    
    /**
     * Checks if a neighbor needs a scheduled tick (e.g., water flow).
     */
    private void checkNeighborTick(int x, int y, int z) {
        Block b = getBlock(x, y, z);
        if (b.isWater()) {
            logicSystem.scheduleTick(x, y, z, b, 5);
        }
    }
    
    /**
     * Updates neighboring chunks if a block change occurred on a chunk boundary.
     */
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

    /**
     * Advances the world simulation by one tick.
     * Updates time and logic systems.
     */
    public void tick() {
        time = (time + 1) % 24000; // Cycle every 20 minutes (at 20 TPS, though this loop might be 60 TPS based on GameLoop)
        logicSystem.tick();
    }
    
    public void setTime(long time) {
        this.time = time % 24000;
    }
    
    public long getTime() {
        return time;
    }

    public ChunkManager getChunkManager() { return chunkManager; }
    
    public boolean isLoaded(int x, int z) {
        return chunkManager.hasChunk(x >> 4, z >> 4);
    }
    
    // --- Light Access Delegates ---

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
        Chunk c = chunkManager.getChunk(x >> 4, z >> 4);
        return c.getBlockLight(x & 15, y, z & 15);
    }
    public void setBlockLight(int x, int y, int z, int val) {
        if (y < 0 || y >= Chunk.HEIGHT) return;
        if (!isLoaded(x, z)) return;
        chunkManager.getChunk(x >> 4, z >> 4).setBlockLight(x & 15, y, z & 15, val);
    }
}