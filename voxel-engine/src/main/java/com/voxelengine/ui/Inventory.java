package com.voxelengine.ui;

import com.voxelengine.world.Block;

/**
 * Manages the player's inventory, including hotbar and storage slots.
 */
public class Inventory {

    private final ItemStack[] slots;
    private final int size = 36; // 9 Hotbar + 27 Storage
    private int selectedSlot = 0; // 0-8 (Hotbar range)

    public Inventory() {
        slots = new ItemStack[size];
        
        // Initialize empty slots
        for (int i = 0; i < size; i++) {
            slots[i] = new ItemStack(Block.AIR, 0);
        }

        // Fill hotbar with default starting items
        slots[0] = new ItemStack(Block.STONE, 64);
        slots[1] = new ItemStack(Block.GRASS, 64);
        slots[2] = new ItemStack(Block.DIRT, 64);
        slots[3] = new ItemStack(Block.COBBLESTONE, 64);
        slots[4] = new ItemStack(Block.PLANKS, 64);
        slots[5] = new ItemStack(Block.SAND, 64);
        slots[6] = new ItemStack(Block.GLASS, 64);
        slots[7] = new ItemStack(Block.LOG, 64);
        slots[8] = new ItemStack(Block.LEAVES, 64);
        
        // Add additional items to main inventory storage
        slots[9] = new ItemStack(Block.WATER_SOURCE, 1);
        slots[10] = new ItemStack(Block.DIAMOND_ORE, 64);
        slots[11] = new ItemStack(Block.REDSTONE_LAMP_OFF, 16);
    }

    public ItemStack getSelectedStack() {
        return slots[selectedSlot];
    }
    
    /**
     * Decrements the selected stack count by 1.
     *
     * @return True if successful, false if empty.
     */
    public boolean useSelectedItem() {
        ItemStack stack = slots[selectedSlot];
        if (stack.isEmpty()) return false;
        
        stack.shrink(1);
        if (stack.getCount() <= 0) {
            slots[selectedSlot] = new ItemStack(Block.AIR, 0);
        }
        return true;
    }

    /**
     * Adds an item stack to the inventory.
     * Tries to merge with existing stacks first, then finds an empty slot.
     *
     * @param stack The stack to add.
     * @return True if fully added, false if inventory is full.
     */
    public boolean add(ItemStack stack) {
        // 1. Try to merge into existing stacks
        for (int i = 0; i < size; i++) {
            if (slots[i].getBlock() == stack.getBlock()) {
                int remainder = slots[i].merge(stack);
                if (remainder == 0) return true; // Fully merged
            }
        }
        
        // 2. Try to put in empty slot
        for (int i = 0; i < size; i++) {
            if (slots[i].isEmpty()) {
                slots[i] = new ItemStack(stack.getBlock(), stack.getCount());
                stack.setCount(0);
                return true;
            }
        }
        return false; // Inventory full
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

    public Block getItemType(int slot) {
        if (slot < 0 || slot >= size) return Block.AIR;
        return slots[slot].getBlock();
    }
    
    public ItemStack getStack(int slot) {
        if (slot < 0 || slot >= size) return new ItemStack(Block.AIR, 0);
        return slots[slot];
    }

    /**
     * Saves the inventory to disk.
     */
    public void save() {
        try (java.io.DataOutputStream out = new java.io.DataOutputStream(new java.io.FileOutputStream("saves/world1/player.dat"))) {
            for(int i=0; i<size; i++) {
                ItemStack stack = slots[i];
                out.writeByte(stack.getBlock().getId());
                out.writeInt(stack.getCount());
            }
        } catch(java.io.IOException e) { e.printStackTrace(); }
    }

    /**
     * Loads the inventory from disk.
     */
    public void load() {
        java.io.File f = new java.io.File("saves/world1/player.dat");
        if(!f.exists()) return;
        
        try (java.io.DataInputStream in = new java.io.DataInputStream(new java.io.FileInputStream(f))) {
            for(int i=0; i<size; i++) {
                byte id = in.readByte();
                int count = in.readInt();
                slots[i] = new ItemStack(Block.getById(id), count);
            }
        } catch(java.io.IOException e) { e.printStackTrace(); }
    }
}