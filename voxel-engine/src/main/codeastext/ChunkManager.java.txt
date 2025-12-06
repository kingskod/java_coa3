package com.voxelengine.world;

import com.voxelengine.world.gen.TerrainGenerator;
import com.voxelengine.world.light.LightingEngine;
import org.joml.Vector3f;

import java.util.concurrent.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ChunkManager {

    // Store chunks safely
    private final Map<Long, Chunk> chunks = new ConcurrentHashMap<>();
    private final TerrainGenerator generator;
    private LightingEngine lighting;
    private final World world;
    
    // Thread Pool for generation
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    
    // Track pending chunks to avoid queuing duplicates
    private final Map<Long, Boolean> pendingChunks = new ConcurrentHashMap<>();

    public ChunkManager(World world) {
        this.world = world;
        this.generator = new TerrainGenerator(12345);
    }

    public void setLighting(LightingEngine lighting) {
        this.lighting = lighting;
    }

    /**
     * Gets a chunk if loaded. Returns null if generating.
     */
    public Chunk getChunk(int chunkX, int chunkZ) {
        return chunks.get(getChunkKey(chunkX, chunkZ));
    }
    
    public boolean hasChunk(int chunkX, int chunkZ) {
        return chunks.containsKey(getChunkKey(chunkX, chunkZ));
    }

    private long getChunkKey(int x, int z) {
        return ((long)x << 32) | (z & 0xFFFFFFFFL);
    }

    /**
     * Called every frame. Checks distance and queues background tasks.
     */
    public void update(Vector3f playerPos) {
        int pChunkX = (int) Math.floor(playerPos.x / 16.0);
        int pChunkZ = (int) Math.floor(playerPos.z / 16.0);
        int renderDistance = 8;

        // 1. Queue Load (Async)
        for (int x = -renderDistance; x <= renderDistance; x++) {
            for (int z = -renderDistance; z <= renderDistance; z++) {
                int cx = pChunkX + x;
                int cz = pChunkZ + z;
                long key = getChunkKey(cx, cz);
                
                if (!chunks.containsKey(key) && !pendingChunks.containsKey(key)) {
                    pendingChunks.put(key, true);
                    
                    // Submit to background thread
                    executor.submit(() -> {
                        loadChunkSync(cx, cz);
                        pendingChunks.remove(key);
                    });
                }
            }
        }

        // 2. Unload (Garbage Collection)
        // Only do this occasionally or limit per frame to avoid lag spikes from Map resizing
        // For simplicity, we keep the strict distance check but use the safe ConcurrentMap
        List<Long> toRemove = new ArrayList<>();
        for (Chunk c : chunks.values()) {
            int dx = c.chunkX - pChunkX;
            int dz = c.chunkZ - pChunkZ;
            if (Math.abs(dx) > renderDistance + 2 || Math.abs(dz) > renderDistance + 2) {
                toRemove.add(getChunkKey(c.chunkX, c.chunkZ));
            }
        }
        for (Long key : toRemove) {
            chunks.remove(key);
        }
    }
    
    // The heavy lifting (runs on background thread)
private void loadChunkSync(int chunkX, int chunkZ) {
        Chunk chunk = new Chunk(chunkX, chunkZ);
        generator.generate(chunk);
        
        long key = getChunkKey(chunkX, chunkZ);
        chunks.put(key, chunk);
        
        if (lighting != null) {
            // 1. Calculate internal light
            lighting.calculateInitialLighting(chunkX, chunkZ);
            
            // 2. Stitch borders with existing neighbors (The Real Fix)
            lighting.stitchChunkBorders(chunkX, chunkZ);
        }
        
        chunk.isDirty = true;
        
        // Mark neighbors dirty so they remesh to match the new light levels
        // (Same updateNeighbor calls as before)
        markDirty(chunkX + 1, chunkZ);
        markDirty(chunkX - 1, chunkZ);
        markDirty(chunkX, chunkZ + 1);
        markDirty(chunkX, chunkZ - 1);
    }
    
    private void markDirty(int cx, int cz) {
        Chunk c = getChunk(cx, cz);
        if (c != null) c.isDirty = true;
    }

    public java.util.Collection<Chunk> getLoadedChunks() {
        return chunks.values();
    }
}