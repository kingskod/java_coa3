package com.voxelengine.world;

import com.voxelengine.world.gen.TerrainGenerator;
import com.voxelengine.world.light.LightingEngine;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class ChunkManager {

    private final Map<Long, Chunk> chunks = new HashMap<>();
    private final TerrainGenerator generator;
    private LightingEngine lighting;
    private final World world;

    public ChunkManager(World world) {
        this.world = world;
        this.generator = new TerrainGenerator(12345);
    }

    public void setLighting(LightingEngine lighting) {
        this.lighting = lighting;
    }

    public Chunk getChunk(int chunkX, int chunkZ) {
        long key = getChunkKey(chunkX, chunkZ);
        if (chunks.containsKey(key)) {
            return chunks.get(key);
        }

        // Only create if explicitly requested via this method
        Chunk chunk = new Chunk(chunkX, chunkZ);
        generator.generate(chunk);
        chunks.put(key, chunk);

        if (lighting != null) {
            lighting.calculateInitialLighting(chunkX, chunkZ);
        }

        return chunk;
    }
    
    /**
     * Checks if a chunk exists without creating it.
     */
    public boolean hasChunk(int chunkX, int chunkZ) {
        return chunks.containsKey(getChunkKey(chunkX, chunkZ));
    }

    private long getChunkKey(int x, int z) {
        return ((long)x << 32) | (z & 0xFFFFFFFFL);
    }

    public void update(Vector3f playerPos) {
        int pChunkX = (int) Math.floor(playerPos.x / 16.0);
        int pChunkZ = (int) Math.floor(playerPos.z / 16.0);
        int renderDistance = 8;

        // 1. Load
        for (int x = -renderDistance; x <= renderDistance; x++) {
            for (int z = -renderDistance; z <= renderDistance; z++) {
                getChunk(pChunkX + x, pChunkZ + z);
            }
        }

        // 2. Unload (Garbage Collection)
        java.util.List<Long> toRemove = new java.util.ArrayList<>();

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

    public java.util.Collection<Chunk> getLoadedChunks() {
        return chunks.values();
    }
}