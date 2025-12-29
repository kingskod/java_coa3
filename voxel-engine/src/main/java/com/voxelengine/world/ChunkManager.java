package com.voxelengine.world;

import com.voxelengine.world.gen.TerrainGenerator;
import com.voxelengine.world.light.LightingEngine;
import org.joml.Vector3f;

import java.util.*;
import java.util.concurrent.*;

public class ChunkManager {

    private final Map<Long, Chunk> chunks = new ConcurrentHashMap<>();
    private final TerrainGenerator generator;
    private LightingEngine lighting;
    private final World world;
    
    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private final Map<Long, Boolean> pendingChunks = new ConcurrentHashMap<>();

    public ChunkManager(World world) {
        this.world = world;
        long seed = ChunkSerializer.loadSeed();
        if (seed == 0) {
            seed = new java.util.Random().nextLong();
            ChunkSerializer.saveSeed(seed);
            System.out.println("Created New World. Seed: " + seed);
        } else {
            System.out.println("Loaded Existing World. Seed: " + seed);
        }
        this.generator = new TerrainGenerator(12345);
    }

    public void setLighting(LightingEngine lighting) {
        this.lighting = lighting;
    }

    public Chunk getChunk(int chunkX, int chunkZ) {
        return chunks.get(getChunkKey(chunkX, chunkZ));
    }
    
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

        List<ChunkCoord> toLoad = new ArrayList<>();
        
        for (int x = -renderDistance; x <= renderDistance; x++) {
            for (int z = -renderDistance; z <= renderDistance; z++) {
                int cx = pChunkX + x;
                int cz = pChunkZ + z;
                long key = getChunkKey(cx, cz);
                
                if (!chunks.containsKey(key) && !pendingChunks.containsKey(key)) {
                    double distSq = x*x + z*z;
                    toLoad.add(new ChunkCoord(cx, cz, distSq));
                }
            }
        }
        
        toLoad.sort(Comparator.comparingDouble(c -> c.dist));
        
        int submitted = 0;
        for (ChunkCoord coord : toLoad) {
            if (submitted >= 2) break; 
            
            long key = getChunkKey(coord.x, coord.z);
            pendingChunks.put(key, true);
            
            executor.submit(() -> {
                try {
                    loadChunkSync(coord.x, coord.z);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // FIX: Ensure key is removed even if generation fails, otherwise
                    // this chunk creates a permanent hole in the world.
                    pendingChunks.remove(key);
                }
            });
            submitted++;
        }

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
    
    private void loadChunkSync(int chunkX, int chunkZ) {
        Chunk chunk = ChunkSerializer.loadChunk(chunkX, chunkZ);
        
        if (chunk == null) {
            chunk = new Chunk(chunkX, chunkZ);
            generator.generate(chunk);
            long key = getChunkKey(chunkX, chunkZ);
            chunks.put(key, chunk);
            
            generator.populate(chunk, world);
            
            if (lighting != null) {
                lighting.calculateInitialLighting(chunkX, chunkZ);
                lighting.stitchChunkBorders(chunkX, chunkZ);
            }
        } else {
            long key = getChunkKey(chunkX, chunkZ);
            chunks.put(key, chunk);
            if (lighting != null) {
                lighting.stitchChunkBorders(chunkX, chunkZ);
            }
        }
        
        chunk.isDirty = true;
        markDirty(chunkX + 1, chunkZ);
        markDirty(chunkX - 1, chunkZ);
        markDirty(chunkX, chunkZ + 1);
        markDirty(chunkX, chunkZ - 1);
    }

    public void saveAll() {
        System.out.println("Saving world...");
        for (Chunk c : chunks.values()) {
            ChunkSerializer.saveChunk(c);
        }
        System.out.println("World saved.");
    }
    
    private void markDirty(int cx, int cz) {
        Chunk c = getChunk(cx, cz);
        if (c != null) c.isDirty = true;
    }

    public java.util.Collection<Chunk> getLoadedChunks() {
        return chunks.values();
    }
    
    private static class ChunkCoord {
        int x, z;
        double dist;
        public ChunkCoord(int x, int z, double dist) {
            this.x = x; this.z = z; this.dist = dist;
        }
    }
}