package com.voxelengine.world;

import com.voxelengine.logic.LogicSystem;
import com.voxelengine.utils.Direction;

public class FluidSimulator {

    private final World world;
    private final LogicSystem logic;
    private static final int TICK_RATE = 5; // Ticks between updates (speed of flow)

    public FluidSimulator(World world, LogicSystem logic) {
        this.world = world;
        this.logic = logic;
    }

    public void update(int x, int y, int z, Block block) {
        int level = block.getWaterLevel(); // 7 (Source) to 0 (Dying)
        if (level < 0) return;

        // 1. Try Flow Down
        Block below = world.getBlock(x, y - 1, z);
        if (canFlowInto(below)) {
            // Flowing down resets pressure to max (simulate falling stream)
            // Use WATER_6 as falling water to distinguish from Source
            setWater(x, y - 1, z, 7); 
            return; // Don't flow sideways if falling freely
        }

        // 2. Flow Horizontally
        if (level > 1) { // Need at least level 2 to push to level 1
            flowTo(x + 1, y, z, level - 1);
            flowTo(x - 1, y, z, level - 1);
            flowTo(x, y, z + 1, level - 1);
            flowTo(x, y, z - 1, level - 1);
        }
        
        // 3. Source Generation (Infinite Water)
        // If this block is NOT a source, but surrounded by 2+ sources, become source
        if (level < 7) {
            int sources = 0;
            if (isSource(x+1, y, z)) sources++;
            if (isSource(x-1, y, z)) sources++;
            if (isSource(x, y, z+1)) sources++;
            if (isSource(x, y, z-1)) sources++;
            
            if (sources >= 2) {
                world.setBlock(x, y, z, Block.WATER_SOURCE); // Level 7
            }
        }
    }

    private void flowTo(int x, int y, int z, int newLevel) {
        Block target = world.getBlock(x, y, z);
        if (canFlowInto(target)) {
            // Only update if we are increasing the water level there
            int currentLevel = target.isWater() ? target.getWaterLevel() : -1;
            
            if (newLevel > currentLevel) {
                setWater(x, y, z, newLevel);
            }
        }
    }

    private boolean canFlowInto(Block b) {
        return b == Block.AIR || (b.isWater() && b != Block.WATER_SOURCE); 
        // Can flow into air or smaller water. Cannot overwrite Source.
        // Also add logic for washing away grass/torches later.
    }
    
    private boolean isSource(int x, int y, int z) {
        return world.getBlock(x, y, z) == Block.WATER_SOURCE;
    }

    private void setWater(int x, int y, int z, int level) {
        Block b = getWaterBlock(level);
        world.setBlock(x, y, z, b);
        // Schedule next tick for this new water block to keep flowing
        logic.scheduleTick(x, y, z, b, TICK_RATE);
    }

    private Block getWaterBlock(int level) {
        switch (level) {
            case 7: return Block.WATER_6; // Falling/Spread water (Visual diff from Source)
            case 6: return Block.WATER_6;
            case 5: return Block.WATER_5;
            case 4: return Block.WATER_4;
            case 3: return Block.WATER_3;
            case 2: return Block.WATER_2;
            case 1: return Block.WATER_1;
            default: return Block.WATER_0;
        }
    }
}