package com.voxelengine.ui;

import com.voxelengine.world.Block;

public class ItemStack {
    private Block block;
    private int count;
    public static final int MAX_STACK = 64;

    public ItemStack(Block block, int count) {
        this.block = block;
        this.count = count;
    }

    public Block getBlock() { return block; }
    public int getCount() { return count; }
    
    public void setCount(int count) { this.count = count; }
    public void grow(int amount) { this.count += amount; }
    public void shrink(int amount) { this.count -= amount; }

    public boolean isEmpty() {
        return count <= 0 || block == Block.AIR;
    }

    /**
     * Tries to merge 'other' into this stack.
     * @return The number of items that COULD NOT be added (remainder).
     */
    public int merge(ItemStack other) {
        if (other.block != this.block) return other.count;
        
        int space = MAX_STACK - this.count;
        int toAdd = Math.min(space, other.count);
        
        this.count += toAdd;
        other.count -= toAdd;
        
        return other.count;
    }
    
    public ItemStack split(int amount) {
        int toRemove = Math.min(amount, count);
        this.count -= toRemove;
        return new ItemStack(this.block, toRemove);
    }
}