package com.voxelengine.world;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ChunkSerializer {

    private static final String SAVE_DIR = "saves/world1/";

    static {
        try {
            Files.createDirectories(Paths.get(SAVE_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileName(int x, int z) {
        return SAVE_DIR + "chunk_" + x + "_" + z + ".dat";
    }

    public static boolean saveChunk(Chunk chunk) {
        if (!chunk.isPopulated) return false; // Don't save half-gen chunks

        try (DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(getFileName(chunk.chunkX, chunk.chunkZ))))) {
            // Write coordinates to verify on load
            out.writeInt(chunk.chunkX);
            out.writeInt(chunk.chunkZ);

            // Write Blocks (RLE Compression could be here, but GZIP handles raw arrays well enough for now)
            // We need to access the raw byte array.
            // Since `blocks` is private in Chunk, we need a getter or move this logic to Chunk.
            // For now, we will assume we added a getter or package-private access.
            // Let's rely on Chunk having a 'writeToStream' method we will add.
            chunk.writeToStream(out);
            
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save chunk " + chunk.chunkX + "," + chunk.chunkZ);
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
            
            if (cx != x || cz != z) return null; // Corrupt file or wrong name

            Chunk chunk = new Chunk(x, z);
            chunk.readFromStream(in);
            return chunk;
        } catch (IOException e) {
            System.err.println("Failed to load chunk " + x + "," + z);
            // Delete corrupt file?
            return null;
        }
    }
}