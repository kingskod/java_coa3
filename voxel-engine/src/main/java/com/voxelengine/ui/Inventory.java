package com.voxelengine.ui;

import com.voxelengine.world.Block;

public class Inventory {

    private final Block[] items;
    private final int size = 36; // 9 Hotbar + 27 Storage
    private int selectedSlot = 0; // 0-8

    public Inventory() {
        items = new Block[size];

        // Fill hotbar with default blocks for testing
        items[0] = Block.STONE;
        items[1] = Block.GRASS;
        items[2] = Block.DIRT;
        items[3] = Block.COBBLESTONE;
        items[4] = Block.PLANKS;
        items[5] = Block.SAND;
        items[6] = Block.GLASS;
        items[7] = Block.LOG;
        items[8] = Block.LEAVES;

        // Add Logic items
        items[9] = Block.REDSTONE_TORCH;
        items[10] = Block.WIRE;
        items[11] = Block.REDSTONE_LAMP_OFF;
        items[12] = Block.LEVER;
    }

    public Block getSelectedBlock() {
        Block b = items[selectedSlot];
        return b == null ? Block.AIR : b;
    }

    public void scroll(int direction) {
        selectedSlot -= direction;
        if (selectedSlot < 0) selectedSlot = 8;
        if (selectedSlot > 8) selectedSlot = 0;
    }

    public void setSelectedSlot(int slot) {
        if (slot >= 0 && slot <= 8) selectedSlot = slot;
    }

    public int getSelectedSlot() { return selectedSlot; }

    public Block getItem(int slot) {
        if (slot < 0 || slot >= size) return Block.AIR;
        return items[slot] == null ? Block.AIR : items[slot];
    }
}