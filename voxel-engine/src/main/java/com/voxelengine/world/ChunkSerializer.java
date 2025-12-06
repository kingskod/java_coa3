package com.voxelengine.world;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ChunkSerializer {

    // Default, can be changed by Main
    public static String WORLD_NAME = "world1";
    
    private static String getSaveDir() {
        return "saves/" + WORLD_NAME + "/";
    }

    // Initialize directory
    static {
        createDir();
    }
    
    public static void createDir() {
        try {
            Files.createDirectories(Paths.get(getSaveDir()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileName(int x, int z) {
        return getSaveDir() + "chunk_" + x + "_" + z + ".dat";
    }

    public static boolean saveChunk(Chunk chunk) {
        if (!chunk.isPopulated) return false; 
        
        // Ensure dir exists (in case world name changed at runtime)
        createDir();

        try (DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(getFileName(chunk.chunkX, chunk.chunkZ))))) {
            out.writeInt(chunk.chunkX);
            out.writeInt(chunk.chunkZ);
            chunk.writeToStream(out);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Chunk loadChunk(int x, int z) {
        File file = new File(getFileName(x, z));
        if (!file.exists()) return null;

        try (DataInputStream in = new DataInputStream(new GZIPInputStream(new FileInputStream(file)))) {
            int cx = in.readInt();
            int cz = in.readInt();
            if (cx != x || cz != z) return null; 

            Chunk chunk = new Chunk(x, z);
            chunk.readFromStream(in);
            return chunk;
        } catch (IOException e) {
            return null;
        }
    }
    
    // --- NEW: Save/Load World Seed ---
    
    public static void saveSeed(long seed) {
        createDir();
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(getSaveDir() + "level.dat"))) {
            out.writeLong(seed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static long loadSeed() {
        File f = new File(getSaveDir() + "level.dat");
        if (!f.exists()) return 0; // Return 0 if new world
        
        try (DataInputStream in = new DataInputStream(new FileInputStream(f))) {
            return in.readLong();
        } catch (IOException e) {
            return 0;
        }
    }
}