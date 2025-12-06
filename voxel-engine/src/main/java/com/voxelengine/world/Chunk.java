package com.voxelengine.world;

import com.voxelengine.utils.Direction;

public class Chunk {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 256;
    
    public final int chunkX, chunkZ;
    public final int worldX, worldZ;
    
    private final byte[] blocks;
    private final byte[] skyLight;
    private final byte[] blockLight;
    
    public boolean isDirty = false;
    public boolean isPopulated = false;

    public Chunk(int chunkX, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.worldX = chunkX * WIDTH;
        this.worldZ = chunkZ * WIDTH;
        
        this.blocks = new byte[WIDTH * WIDTH * HEIGHT];
        this.skyLight = new byte[WIDTH * WIDTH * HEIGHT];
        this.blockLight = new byte[WIDTH * WIDTH * HEIGHT];
    }

    private int getIndex(int x, int y, int z) {
        return y * (WIDTH * WIDTH) + z * WIDTH + x;
    }

    public boolean isBounds(int x, int y, int z) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && z >= 0 && z < WIDTH;
    }

    public Block getBlockLocal(int x, int y, int z) {
        if (!isBounds(x, y, z)) return Block.AIR;
        return Block.getById(blocks[getIndex(x, y, z)]);
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (!isBounds(x, y, z)) return;
        blocks[getIndex(x, y, z)] = block.getId();
        isDirty = true;
    }

    // Light getters/setters
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
}