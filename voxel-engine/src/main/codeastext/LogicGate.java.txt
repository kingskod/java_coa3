package com.voxelengine.logic;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;

public class LogicGate {

    // Evaluate state change and schedule ticks if needed
    public static void evaluate(World world, LogicSystem system, int x, int y, int z, Block block) {
        if (!block.isLogicGate()) return;
        
        byte meta = world.getMetadata(x, y, z);
        Direction facing = block.getRotation(meta);
        boolean wasActive = block.isActive(meta);
        
        // Torch Logic (Simplified)
        if (block == Block.REDSTONE_TORCH || block == Block.REDSTONE_TORCH_OFF) {
            // ... (Existing torch logic, or keep empty if handled by updateState) ...
            return;
        }
        
        // Logic Gates
        boolean inBack = getInput(world, x, y, z, facing.opposite()) > 0;
        boolean inLeft = getInput(world, x, y, z, rotateLeft(facing)) > 0;
        boolean inRight = getInput(world, x, y, z, rotateRight(facing)) > 0;
        
        boolean shouldBeActive = false;
        
        switch (block) {
            case AND_GATE: shouldBeActive = inLeft && inRight; break;
            case OR_GATE:  shouldBeActive = inLeft || inRight || inBack; break;
            case XOR_GATE: shouldBeActive = inLeft ^ inRight; break;
            case NAND_GATE: shouldBeActive = !(inLeft && inRight); break;
            case NOT_GATE: shouldBeActive = !inBack; break;
            case LATCH_OFF:
            case LATCH_ON:
                // Latch handles its own block-swap state
                return; 
        }
        
        if (shouldBeActive != wasActive) {
            // Update Metadata to reflect visual state (Lit/Unlit)
            byte newMeta = block.getMetadata(facing, shouldBeActive);
            world.setMetadata(x, y, z, newMeta);
            
            // Trigger neighbor updates
            system.updateNetwork(x, y, z);
        }
    }
    //called to get output signal strength
    public static int getOutput(World world, int x, int y, int z, Block block) {
        // ... (Existing logic, but use metadata for gates) ...
        if (block.isLogicGate() && block != Block.REDSTONE_TORCH && block != Block.REDSTONE_TORCH_OFF) {
             // For gates, trust the metadata state we calculated in evaluate()
             return block.isActive(world.getMetadata(x, y, z)) ? 15 : 0;
        }
        // ... (Keep existing Switch cases for Lever/Torch) ...
        switch (block) {
            case REDSTONE_TORCH: return 15;
            case LEVER: return 15;
            case LATCH_ON: return 15;
            default: return 0;
        }
    }
    
    // Updates block ID for stateful blocks (Latch, Torch)
    public static void updateState(World world, int x, int y, int z, Block block) {
        // Called by LogicSystem when tick timer expires
        world.setBlock(x, y, z, block, world.getMetadata(x, y, z));
    }
    
    // Checks if a neighbor is outputting power TOWARDS us
    private static int getInput(World world, int x, int y, int z, Direction dir) {
        int nx = x + dir.x;
        int ny = y + dir.y;
        int nz = z + dir.z;
        
        // 1. Is it a wire?
        Block b = world.getBlock(nx, ny, nz);
        if (b == Block.WIRE) {
            return world.getBlockLight(nx, ny, nz);
        }
        
        // 2. Is it a directed output (Repeater/Gate) facing us?
        if (b.isLogicGate()) {
            Direction neighborFace = b.getRotation(world.getMetadata(nx, ny, nz));
            // If neighbor faces US (dir.opposite), it powers us
            if (neighborFace == dir.opposite()) {
                // Return its power (recalculate or check light)
                // Recursive safety? getOutput is local logic.
                return getOutput(world, nx, ny, nz, b);
            }
        }
        
        return 0;
    }
    
    private static Direction rotateLeft(Direction dir) {
        switch (dir) {
            case NORTH: return Direction.WEST;
            case WEST: return Direction.SOUTH;
            case SOUTH: return Direction.EAST;
            case EAST: return Direction.NORTH;
            default: return dir;
        }
    }
    
    private static Direction rotateRight(Direction dir) {
        return rotateLeft(rotateLeft(rotateLeft(dir)));
    }
}