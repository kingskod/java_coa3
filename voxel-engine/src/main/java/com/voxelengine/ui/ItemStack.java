package com.voxelengine.ui;

import com.voxelengine.world.Block;

/**
 * Represents a stack of items in the inventory.
 * Contains the block type and the quantity.
 */
public class ItemStack {
    private Block block;
    private int count;
    public static final int MAX_STACK = 64;

    /**
     * Creates a new ItemStack.
     *
     * @param block The type of block.
     * @param count The number of items.
     */
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
     *
     * @param other The stack to merge from.
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
    
    /**
     * Splits this stack, removing amount from this stack and returning a new stack.
     *
     * @param amount The number of items to split off.
     * @return The new split stack.
     */
    public ItemStack split(int amount) {
        int toRemove = Math.min(amount, count);
        this.count -= toRemove;
        return new ItemStack(this.block, toRemove);
    }
}