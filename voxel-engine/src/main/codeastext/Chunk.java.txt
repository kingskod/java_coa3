package com.voxelengine.world;

import java.util.Arrays;

/**
 * Represents a 16x256x16 vertical column of blocks.
 * Stores blocks, sky light, and block light.
 */
public class Chunk {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 256;
    
    public final int chunkX, chunkZ;
    
    // Flattened array: Index = y * (WIDTH * WIDTH) + z * WIDTH + x
    // Order: YZX (Standard for column-based iteration)
    private final byte[] blocks;
    
    // Light maps (0-15)
    private final byte[] skyLight;
    private final byte[] blockLight;
    
    public boolean isDirty = false; // Requires re-meshing
    public boolean isPopulated = false; // Terrain generated

    public Chunk(int chunkX, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.blocks = new byte[WIDTH * WIDTH * HEIGHT];
        this.skyLight = new byte[WIDTH * WIDTH * HEIGHT];
        this.blockLight = new byte[WIDTH * WIDTH * HEIGHT];
    }

    private int getIndex(int x, int y, int z) {
        return y * (WIDTH * WIDTH) + z * WIDTH + x;
    }

    public Block getBlock(int x, int y, int z) {
        if (isOut(x, y, z)) return Block.AIR;
        return Block.getById(blocks[getIndex(x, y, z)]);
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (isOut(x, y, z)) return;
        blocks[getIndex(x, y, z)] = block.getId();
        isDirty = true;
    }

    public int getSkyLight(int x, int y, int z) {
        if (isOut(x, y, z)) return 15; // Sunlight outside chunk is full
        return skyLight[getIndex(x, y, z)];
    }

    public void setSkyLight(int x, int y, int z, int val) {
        if (isOut(x, y, z)) return;
        skyLight[getIndex(x, y, z)] = (byte) val;
        isDirty = true;
    }

    public int getBlockLight(int x, int y, int z) {
        if (isOut(x, y, z)) return 0;
        return blockLight[getIndex(x, y, z)];
    }

    public void setBlockLight(int x, int y, int z, int val) {
        if (isOut(x, y, z)) return;
        blockLight[getIndex(x, y, z)] = (byte) val;
        isDirty = true;
    }

    private boolean isOut(int x, int y, int z) {
        return x < 0 || x >= WIDTH || z < 0 || z >= WIDTH || y < 0 || y >= HEIGHT;
    }
}