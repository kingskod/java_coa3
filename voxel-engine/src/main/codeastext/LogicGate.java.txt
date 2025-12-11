package com.voxelengine.logic;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;

public class LogicGate {

    // Evaluate if a logic block should change state
    public static void evaluate(World world, LogicSystem system, int x, int y, int z, Block block) {
        if (block == Block.REDSTONE_TORCH || block == Block.REDSTONE_TORCH_OFF) {
            // Torch Logic: Attached to wall/floor. If support block is powered, torch turns OFF.
            // Simplified: Assume torch on floor (y-1) for now, or use metadata for wall.
            // Requirement: "Attached" block.
            
            // Check block below (assuming floor torch for MVP)
            // If block below is powered (Signal > 0), Torch turns OFF.
            // If block below is unpowered, Torch turns ON.
            
            int supportSignal = world.getBlockLight(x, y - 1, z);
            boolean isPowered = supportSignal > 0;
            
            if (block == Block.REDSTONE_TORCH && isPowered) {
                // Turn OFF (Schedule Tick)
                system.scheduleTick(x, y, z, block, 2); // 2 Tick delay
            } else if (block == Block.REDSTONE_TORCH_OFF && !isPowered) {
                // Turn ON
                system.scheduleTick(x, y, z, block, 2);
            }
        }
    }
    
    public static void updateState(World world, int x, int y, int z, Block block) {
        if (block == Block.REDSTONE_TORCH) {
            world.setBlock(x, y, z, Block.REDSTONE_TORCH_OFF);
        } else if (block == Block.REDSTONE_TORCH_OFF) {
            world.setBlock(x, y, z, Block.REDSTONE_TORCH);
        }
    }
    
    // Get output signal strength
    public static int getOutput(World world, int x, int y, int z, Block block) {
        if (block == Block.REDSTONE_TORCH) return 15;
        if (block == Block.LEVER) return 15; // Assume ON for now, or check meta
        if (block == Block.REDSTONE_LAMP_ON) return 15; // Emits light, but acts as sink usually.
        return 0;
    }
}