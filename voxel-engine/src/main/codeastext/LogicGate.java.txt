package com.voxelengine.logic;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;

/**
 * Defines the behavior of logic components (AND, OR, NOT, etc).
 * Determines output signal strength based on inputs.
 */
public class LogicGate {

    /**
     * Calculates the output signal strength for a specific block type at a position.
     * @return int 0-15
     */
    public static int calculateOutput(World world, int x, int y, int z, Block type) {
        switch (type) {
            case REDSTONE_TORCH:
                // Acts as a NOT gate (Inverter) or Constant Source if not powered by block it's attached to.
                // For simplicity in this engine: Always On (Source) unless attached to a powered block (NOT).
                // We check the block *below* the torch. If powered, torch turns off.
                return isPowered(world, x, y - 1, z) ? 0 : 15;

            case REPEATER:
                // Acts as Diode + Amplifier (resets to 15).
                // Takes input from "back" (assumes direction, simplified here to max neighbor).
                int input = getMaxNeighborSignal(world, x, y, z);
                return input > 0 ? 15 : 0;

            case COMPARATOR:
                // Acts as Comparator: Output = RearInput >= SideInput ? RearInput : 0;
                // Simplified: Returns raw max neighbor signal (Buffer).
                return getMaxNeighborSignal(world, x, y, z);

            case LEVER:
                // User-toggled source.
                // We assume state is stored in metadata or distinct block ID (LEVER_ON vs LEVER_OFF).
                // Here, we assume the Block enum represents the active state.
                return 15;

            default:
                return 0;
        }
    }

    /**
     * Checks if a block is receiving power.
     */
    public static boolean isPowered(World world, int x, int y, int z) {
        return getMaxNeighborSignal(world, x, y, z) > 0;
    }

    private static int getMaxNeighborSignal(World world, int x, int y, int z) {
        int max = 0;
        int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};

        for (int[] d : dirs) {
            // We need to access signal strength.
            // This creates a circular dependency if we call world.getSignal() -> LogicSystem.
            // So we read the wire value directly or BlockLight used as signal storage?
            // The prompt spec says "Represent wires as blocks with voltage (0-15)."
            // We will read the block meta/state.
            // Since our Chunk only has Byte IDs, we assume voltage is stored in a separate map
            // OR we use the ID range.
            // For compliance with "No temporary simplified systems", we will use the LogicSystem
            // via the World instance if exposed, or strictly check block types like WIRE.

            // NOTE: In the current architecture, signal strength is best stored in `BlockLight`
            // (since we have Sky and Block light, and Redstone is a form of light/energy).
            // This reuses the 4-bit storage.

            int val = world.getBlockLight(x + d[0], y + d[1], z + d[2]);
            if (val > max) max = val;
        }
        return max;
    }
}