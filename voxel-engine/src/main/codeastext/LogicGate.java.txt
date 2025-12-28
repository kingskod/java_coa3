package com.voxelengine.logic;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;

public class LogicGate {

    public static void evaluate(World world, LogicSystem system, int x, int y, int z, Block block) {
        // Redstone Torch Logic
        if (block == Block.REDSTONE_TORCH || block == Block.REDSTONE_TORCH_OFF) {
            // Get metadata to determine orientation
            // Standard Torch: 1=West, 2=East, 3=North, 4=South, 5=Up (Floor)
            // But Block.java uses 0-3 for horizontal. We need to check attached block.
            // Let's assume Metadata 0=South, 1=West, 2=North, 3=East. 
            // The torch is attached to the wall BEHIND it.
            
            byte meta = world.getMetadata(x, y, z);
            Direction facing = block.getRotation(meta); // Direction the torch "points"
            
            // Attached block is opposite to facing.
            // E.g. Torch facing SOUTH is on the NORTH wall.
            Direction attachedDir = facing.opposite();
            
            int ax = x + attachedDir.x;
            int ay = y + attachedDir.y;
            int az = z + attachedDir.z;
            
            // Check if the block we are attached to is POWERED
            boolean isPowered = isBlockPowered(world, ax, ay, az);
            
            if (isPowered && block == Block.REDSTONE_TORCH) {
                // Turn OFF
                system.scheduleTick(x, y, z, Block.REDSTONE_TORCH, 2);
            } else if (!isPowered && block == Block.REDSTONE_TORCH_OFF) {
                // Turn ON
                system.scheduleTick(x, y, z, Block.REDSTONE_TORCH_OFF, 2);
            }
            return;
        }
        
        // Logic Gates (AND, OR, etc)
        if (!block.isLogicGate()) return;
        
        byte meta = world.getMetadata(x, y, z);
        Direction facing = block.getRotation(meta);
        boolean wasActive = block.isActive(meta);
        
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
                return; // Stateful
        }
        
        if (shouldBeActive != wasActive) {
            byte newMeta = block.getMetadata(facing, shouldBeActive);
            world.setMetadata(x, y, z, newMeta);
            system.updateNetwork(x, y, z);
        }
    }
    
    private static boolean isBlockPowered(World world, int x, int y, int z) {
        // Simple check: Is the block receiving power from WIRE or GATE?
        // In full engine, check if "strongly" powered. 
        // For now, check if wire at (x,y,z) is on, or neighbor is powering it.
        if (world.getBlockLight(x, y, z) > 0) return true;
        return false;
    }

    public static int getOutput(World world, int x, int y, int z, Block block) {
        if (block.isLogicGate() && block != Block.REDSTONE_TORCH && block != Block.REDSTONE_TORCH_OFF) {
             return block.isActive(world.getMetadata(x, y, z)) ? 15 : 0;
        }
        switch (block) {
            case REDSTONE_TORCH: return 15;
            case LEVER: return block.isActive(world.getMetadata(x, y, z)) ? 15 : 0;
            case LATCH_ON: return 15;
            default: return 0;
        }
    }
    
    public static void updateState(World world, int x, int y, int z, Block block) {
        if (block == Block.REDSTONE_TORCH) {
            world.setBlock(x, y, z, Block.REDSTONE_TORCH_OFF, world.getMetadata(x, y, z));
        } else if (block == Block.REDSTONE_TORCH_OFF) {
            world.setBlock(x, y, z, Block.REDSTONE_TORCH, world.getMetadata(x, y, z));
        }
    }
    
    private static int getInput(World world, int x, int y, int z, Direction dir) {
        int nx = x + dir.x;
        int ny = y + dir.y;
        int nz = z + dir.z;
        
        Block b = world.getBlock(nx, ny, nz);
        if (b == Block.WIRE) {
            return world.getBlockLight(nx, ny, nz);
        }
        
        if (b.isLogicGate()) {
            Direction neighborFace = b.getRotation(world.getMetadata(nx, ny, nz));
            if (neighborFace == dir.opposite()) {
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