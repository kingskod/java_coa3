package com.voxelengine.logic;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;

/**
 * Helper class for evaluating logic gate states (Torches, Repeaters, etc.).
 */
public class LogicGate {

    /**
     * Evaluates if a logic block should change state based on its surroundings.
     * Schedules a tick if a change is needed (e.g. Torch turning off).
     *
     * @param world The game world.
     * @param system The logic system.
     */
    public static void evaluate(World world, LogicSystem system, int x, int y, int z, Block block) {
        if (block == Block.REDSTONE_TORCH || block == Block.REDSTONE_TORCH_OFF) {
            // Torch Logic: Attached to wall/floor. If support block is powered, torch turns OFF.
            // Simplified: Assume torch on floor (y-1) for now.
            
            // Check block below
            int supportSignal = world.getBlockLight(x, y - 1, z);
            boolean isPowered = supportSignal > 0;
            
            if (block == Block.REDSTONE_TORCH && isPowered) {
                // Turn OFF (Schedule Tick)
                system.scheduleTick(x, y, z, block, 2); // 2 Tick delay (0.1s)
            } else if (block == Block.REDSTONE_TORCH_OFF && !isPowered) {
                // Turn ON
                system.scheduleTick(x, y, z, block, 2);
            }
        }
    }
    
    /**
     * Updates the actual block state after the delay.
     * Called by LogicSystem when the scheduled tick fires.
     */
    public static void updateState(World world, int x, int y, int z, Block block) {
        if (block == Block.REDSTONE_TORCH) {
            world.setBlock(x, y, z, Block.REDSTONE_TORCH_OFF);
        } else if (block == Block.REDSTONE_TORCH_OFF) {
            world.setBlock(x, y, z, Block.REDSTONE_TORCH);
        }
    }
    
    /**
     * Gets the output signal strength of a logic component.
     */
    public static int getOutput(World world, int x, int y, int z, Block block) {
        if (block == Block.REDSTONE_TORCH) return 15;
        if (block == Block.LEVER) return 15; // Assume ON for now
        if (block == Block.REDSTONE_LAMP_ON) return 15;
        return 0;
    }
}