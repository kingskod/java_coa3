package com.voxelengine.world;

import com.voxelengine.utils.Direction;
import java.util.Arrays;

/**
 * Represents a 16x256x16 column of blocks in the world.
 * Stores block IDs, metadata, and lighting information.
 */
public class Chunk {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 256;
    
    public final int chunkX, chunkZ;
    public final int worldX, worldZ;
    
    private final byte[] blocks;
    private final byte[] metadata; // Stores state (rotation, power, etc)
    private final byte[] skyLight;
    private final byte[] blockLight;
    
    public boolean isDirty = false;
    public boolean isPopulated = false;

    /**
     * Creates a new empty Chunk at the specified chunk coordinates.
     *
     * @param chunkX The X coordinate of the chunk grid.
     * @param chunkZ The Z coordinate of the chunk grid.
     */
    public Chunk(int chunkX, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.worldX = chunkX * WIDTH;
        this.worldZ = chunkZ * WIDTH;
        
        int size = WIDTH * WIDTH * HEIGHT;
        this.blocks = new byte[size];
        this.metadata = new byte[size];
        this.skyLight = new byte[size];
        this.blockLight = new byte[size];
    }

    private int getIndex(int x, int y, int z) {
        return y * (WIDTH * WIDTH) + z * WIDTH + x;
    }

    /**
     * Checks if local coordinates are within the chunk bounds.
     */
    public boolean isBounds(int x, int y, int z) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && z >= 0 && z < WIDTH;
    }

    // --- Block Access ---

    /**
     * Gets the block at the specified local coordinates.
     *
     * @param x Local X (0-15).
     * @param y Local Y (0-255).
     * @param z Local Z (0-15).
     * @return The Block, or AIR if out of bounds.
     */
    public Block getBlockLocal(int x, int y, int z) {
        if (!isBounds(x, y, z)) return Block.AIR;
        return Block.getById(blocks[getIndex(x, y, z)]);
    }

    /**
     * Sets the block at the specified local coordinates with default metadata.
     */
    public void setBlock(int x, int y, int z, Block block) {
        setBlock(x, y, z, block, (byte)0);
    }
    
    /**
     * Sets the block and metadata at the specified local coordinates.
     * Marks the chunk as dirty for remeshing.
     */
    public void setBlock(int x, int y, int z, Block block, byte meta) {
        if (!isBounds(x, y, z)) return;
        int idx = getIndex(x, y, z);
        blocks[idx] = block.getId();
        metadata[idx] = meta;
        isDirty = true;
    }

    // --- Metadata Access ---

    public byte getMetadata(int x, int y, int z) {
        if (!isBounds(x, y, z)) return 0;
        return metadata[getIndex(x, y, z)];
    }

    public void setMetadata(int x, int y, int z, byte meta) {
        if (!isBounds(x, y, z)) return;
        metadata[getIndex(x, y, z)] = meta;
        isDirty = true;
    }

    // --- Light Access ---

    public int getSkyLight(int x, int y, int z) {
        if (!isBounds(x, y, z)) return 15;
        return skyLight[getIndex(x, y, z)];
    }

    public void setSkyLight(int x, int y, int z, int val) {
        if (!isBounds(x, y, z)) return;
        skyLight[getIndex(x, y, z)] = (byte) val;
        isDirty = true;
    }

    public int getBlockLight(int x, int y, int z) {
        if (!isBounds(x, y, z)) return 0;
        return blockLight[getIndex(x, y, z)];
    }

    public void setBlockLight(int x, int y, int z, int val) {
        if (!isBounds(x, y, z)) return;
        blockLight[getIndex(x, y, z)] = (byte) val;
        isDirty = true;
    }
    
    // --- I/O Access (Used by Serializer) ---
    public byte[] getBlocksRaw() { return blocks; }
    public byte[] getMetadataRaw() { return metadata; }
    public byte[] getSkyLightRaw() { return skyLight; }
    public byte[] getBlockLightRaw() { return blockLight; }
}