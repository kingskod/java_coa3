package com.voxelengine.ui;

import com.voxelengine.world.Block;

public class Inventory {

    private final ItemStack[] slots;
    private final int size = 36; // 9 Hotbar + 27 Storage
    private int selectedSlot = 0; // 0-8
    
    // The item currently being dragged by the mouse
    private ItemStack heldStack = new ItemStack(Block.AIR, 0);

    public Inventory() {
        slots = new ItemStack[size];
        
        // Initialize empty
        for (int i = 0; i < size; i++) {
            slots[i] = new ItemStack(Block.AIR, 0);
        }

        // --- NEW DEFAULT ITEMS (Requested) ---
        slots[0] = new ItemStack(Block.REDSTONE_TORCH, 64);
        slots[1] = new ItemStack(Block.WIRE, 64);
        slots[2] = new ItemStack(Block.OR_GATE, 64);
        slots[3] = new ItemStack(Block.AND_GATE, 64);
        slots[4] = new ItemStack(Block.XOR_GATE, 64);
        slots[5] = new ItemStack(Block.LEVER, 64);
        slots[6] = new ItemStack(Block.REDSTONE_LAMP_OFF, 64);
        slots[7] = new ItemStack(Block.STONE, 64);
        slots[8] = new ItemStack(Block.GRASS, 64);
        
        // Storage (Debug items)
        slots[9] = new ItemStack(Block.WATER_SOURCE, 1);
        slots[10] = new ItemStack(Block.DIAMOND_ORE, 64);
    }

    // --- Interaction Logic ---
    
    public ItemStack getHeldStack() {
        return heldStack;
    }
    
    public void setHeldStack(ItemStack stack) {
        this.heldStack = stack;
    }
    
    public void setSlot(int index, ItemStack stack) {
        if (index >= 0 && index < size) {
            slots[index] = stack;
        }
    }

    public ItemStack getStack(int slot) {
        if (slot < 0 || slot >= size) return new ItemStack(Block.AIR, 0);
        return slots[slot];
    }

    // --- Existing Logic ---

    public ItemStack getSelectedStack() {
        return slots[selectedSlot];
    }
    
    public boolean useSelectedItem() {
        ItemStack stack = slots[selectedSlot];
        if (stack.isEmpty()) return false;
        
        stack.shrink(1);
        if (stack.getCount() <= 0) {
            slots[selectedSlot] = new ItemStack(Block.AIR, 0);
        }
        return true;
    }

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
        return false;
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
    public void save() {
        try (java.io.DataOutputStream out = new java.io.DataOutputStream(new java.io.FileOutputStream("saves/world1/player.dat"))) {
            // Save position? Ideally pass Player here.
            // Save Slots
            for(int i=0; i<size; i++) {
                ItemStack stack = slots[i];
                out.writeByte(stack.getBlock().getId());
                out.writeInt(stack.getCount());
            }
        } catch(java.io.IOException e) { e.printStackTrace(); }
    }

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