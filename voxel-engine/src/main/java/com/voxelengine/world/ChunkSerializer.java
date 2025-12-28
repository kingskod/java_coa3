package com.voxelengine.world;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Handles saving and loading chunks to/from disk using GZIP compression.
 */
public class ChunkSerializer {

    public static String WORLD_NAME = "world1";
    private static final int SAVE_VERSION = 1; // Increment when format changes
    
    private static String getSaveDir() {
        return "saves/" + WORLD_NAME + "/";
    }

    static {
        createDir();
    }
    
    /**
     * Ensures the save directory exists.
     */
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

    /**
     * Saves a chunk to a compressed file.
     *
     * @param chunk The chunk to save.
     * @return True if successful.
     */
    public static boolean saveChunk(Chunk chunk) {
        if (!chunk.isPopulated) return false; 
        createDir();

        try (DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(getFileName(chunk.chunkX, chunk.chunkZ))))) {
            // Header
            out.writeInt(SAVE_VERSION);
            out.writeInt(chunk.chunkX);
            out.writeInt(chunk.chunkZ);
            
            // Data
            out.write(chunk.getBlocksRaw());
            out.write(chunk.getMetadataRaw());
            out.write(chunk.getSkyLightRaw());
            out.write(chunk.getBlockLightRaw());
            out.writeBoolean(chunk.isPopulated);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Loads a chunk from disk.
     *
     * @param x Chunk X coordinate.
     * @param z Chunk Z coordinate.
     * @return The loaded Chunk, or null if not found/corrupted.
     */
    public static Chunk loadChunk(int x, int z) {
        File file = new File(getFileName(x, z));
        if (!file.exists()) return null;

        try (DataInputStream in = new DataInputStream(new GZIPInputStream(new FileInputStream(file)))) {
            int version = in.readInt(); // Read Version First
            
            // Verify Coords
            int cx = in.readInt();
            int cz = in.readInt();
            if (cx != x || cz != z) return null; 

            Chunk chunk = new Chunk(x, z);
            
            if (version == 1) {
                // Current Format
                in.readFully(chunk.getBlocksRaw());
                in.readFully(chunk.getMetadataRaw());
                in.readFully(chunk.getSkyLightRaw());
                in.readFully(chunk.getBlockLightRaw());
                chunk.isPopulated = in.readBoolean();
            } else {
                // Handle legacy formats or mismatch
                System.out.println("Chunk version mismatch (" + version + "). Regenerating.");
                return null;
            }
            
            chunk.isDirty = true;
            return chunk;
        } catch (IOException e) {
            return null;
        }
    }
    
    /**
     * Saves the world seed.
     */
    public static void saveSeed(long seed) {
        createDir();
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(getSaveDir() + "level.dat"))) {
            out.writeLong(seed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Loads the world seed.
     * @return The seed, or 0 if not found.
     */
    public static long loadSeed() {
        File f = new File(getSaveDir() + "level.dat");
        if (!f.exists()) return 0;
        try (DataInputStream in = new DataInputStream(new FileInputStream(f))) {
            return in.readLong();
        } catch (IOException e) {
            return 0;
        }
    }
}