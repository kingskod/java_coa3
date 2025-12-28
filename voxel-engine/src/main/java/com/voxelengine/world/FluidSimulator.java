package com.voxelengine.world;

import com.voxelengine.logic.LogicSystem;

/**
 * Simulates fluid dynamics for water.
 * Handles flow logic, spreading, and infinite source generation.
 */
public class FluidSimulator {

    private final World world;
    private final LogicSystem logic;
    private static final int TICK_RATE = 5;

    public FluidSimulator(World world, LogicSystem logic) {
        this.world = world;
        this.logic = logic;
    }

    /**
     * Updates the fluid state at the given position.
     * Called by LogicSystem when a scheduled fluid tick occurs.
     */
    public void update(int x, int y, int z, Block block) {
        int level = block.getWaterLevel();
        if (level < 0) return;

        // 1. Flow Down
        Block below = world.getBlock(x, y - 1, z);
        if (canFlowInto(below)) {
            // Falling water becomes full strength (like a waterfall)
            setWater(x, y - 1, z, 7); 
            return;
        }

        // 2. Flow Horizontally (only if on ground)
        if (level > 1) { // Need remaining pressure
            flowTo(x + 1, y, z, level - 1);
            flowTo(x - 1, y, z, level - 1);
            flowTo(x, y, z + 1, level - 1);
            flowTo(x, y, z - 1, level - 1);
        }
        
        // 3. Infinite Source Generation (2 sources create a 3rd)
        if (level < 7 && level > 0) {
            int sources = 0;
            if (isSource(x+1, y, z)) sources++;
            if (isSource(x-1, y, z)) sources++;
            if (isSource(x, y, z+1)) sources++;
            if (isSource(x, y, z-1)) sources++;
            
            if (sources >= 2) {
                world.setBlock(x, y, z, Block.WATER_SOURCE);
            }
        }
    }

    private void flowTo(int x, int y, int z, int newLevel) {
        Block target = world.getBlock(x, y, z);
        if (canFlowInto(target)) {
            int currentLevel = target.isWater() ? target.getWaterLevel() : -1;
            
            // Only update if we improve the water level
            if (newLevel > currentLevel) {
                setWater(x, y, z, newLevel);
            }
        }
    }

    private boolean canFlowInto(Block b) {
        // Can flow into air, other water (not source), or wash away non-solid blocks
        if (b == Block.AIR) return true;
        if (b.isWater() && b != Block.WATER_SOURCE) return true;
        if (!b.isSolid() && b != Block.WATER_SOURCE) return true; // Wash away grass/torches
        return false;
    }
    
    private boolean isSource(int x, int y, int z) {
        return world.getBlock(x, y, z) == Block.WATER_SOURCE;
    }

    private void setWater(int x, int y, int z, int level) {
        Block b = getWaterBlock(level);
        world.setBlock(x, y, z, b);
        // Keep flowing
        logic.scheduleTick(x, y, z, b, TICK_RATE);
    }

    private Block getWaterBlock(int level) {
        switch (level) {
            case 7: return Block.WATER_6; // Waterfall/Spread
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