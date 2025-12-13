package com.voxelengine.world;

import com.voxelengine.logic.LogicSystem;

public class GravitySimulator {

    private final World world;
    private final LogicSystem logic;
    private static final int FALL_DELAY = 2; // Ticks per block (lower is faster)

    public GravitySimulator(World world, LogicSystem logic) {
        this.world = world;
        this.logic = logic;
    }

    public void update(int x, int y, int z, Block block) {
        // Double check it's a gravity block
        if (!block.hasGravity()) return;
        
        // Bedrock never falls (World Anchor)
        if (block == Block.BEDROCK) return;

        // Check block below
        if (y <= 0) return; // Don't fall out of world
        
        Block below = world.getBlock(x, y - 1, z);

        // Can we fall? 
        // Fall through Air, Water, or other non-solid blocks (like torches/grass)
        // We do NOT fall through other solid blocks.
        boolean canFallThrough = below == Block.AIR || below.isWater() || (!below.isSolid());

        if (canFallThrough) {
            // Move Block Down
            // 1. Set new position
            world.setBlock(x, y - 1, z, block);
            
            // 2. Clear old position (Set to Air)
            // Note: We use a specific method or order to prevent infinite loop triggers if possible,
            // but standard setBlock is fine here.
            world.setBlock(x, y, z, Block.AIR);
            
            // 3. Keep falling? Schedule tick for the new position
            logic.scheduleTick(x, y - 1, z, block, FALL_DELAY);
            
            // 4. Wake up neighbors
            // The block that was sitting ON TOP of us at (y+1) needs to know we moved
            // so it can start falling too.
            notifyNeighbors(x, y, z);
        }
    }
    
    private void notifyNeighbors(int x, int y, int z) {
        // Specifically check the block ABOVE the position that just became empty.
        checkGravity(x, y + 1, z);
    }
    
    private void checkGravity(int x, int y, int z) {
        Block b = world.getBlock(x, y, z);
        if (b.hasGravity()) {
            logic.scheduleTick(x, y, z, b, FALL_DELAY);
        }
    }
}