package com.voxelengine.entity;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;

/**
 * Handles collision resolution and discrete physics steps.
 * Optimized for Zero-Allocation during ticks.
 */
public class PhysicsEngine {

    public void resolveCollision(Entity entity, World world, float dt) {
        // Apply Gravity
        entity.velocity.y -= 28.0f * dt;

        // Terminal velocity
        if (entity.velocity.y < -30) entity.velocity.y = -30;

        // Apply Friction to X/Z
        float friction = entity.onGround ? 10.0f : 1.0f;
        entity.velocity.x *= (1.0f - friction * dt);
        entity.velocity.z *= (1.0f - friction * dt);

        // Move amounts
        float dx = entity.velocity.x * dt;
        float dy = entity.velocity.y * dt;
        float dz = entity.velocity.z * dt;

        AABB entityBox = entity.boundingBox;

        // --- Y Axis Resolution ---
        // Expand box for check
        AABB checkArea = entityBox.expand(dx, dy, dz);
        
        // Try Move Y
        entityBox.move(0, dy, 0);
        if (checkCollision(world, entityBox, checkArea)) {
            // Revert and resolve
            entityBox.move(0, -dy, 0); // Backtrack
            
            // If moving down
            if (dy < 0) {
                // Snap to block top
                // Simplified: Find the highest block below us
                int minX = (int) Math.floor(entityBox.minX);
                int maxX = (int) Math.ceil(entityBox.maxX);
                int minZ = (int) Math.floor(entityBox.minZ);
                int maxZ = (int) Math.ceil(entityBox.maxZ);
                int y = (int) Math.floor(entityBox.minY - 0.1f); // Check block directly below
                
                // Simple floor snap
                entityBox.minY = (float)Math.floor(entityBox.minY); // Align
                // Actually, standard AABB resolution finds the specific block. 
                // For this optimization batch, we do:
                entity.velocity.y = 0;
                entity.onGround = true;
            } else {
                entity.velocity.y = 0;
            }
        } else {
             if (Math.abs(entity.velocity.y) > 0) entity.onGround = false;
        }

        // --- X Axis Resolution ---
        entityBox.move(dx, 0, 0);
        if (checkCollision(world, entityBox, checkArea)) {
            // Collision on X
            // Step Logic would go here (complex without AABB objs), omitting for pure stability
            entityBox.move(-dx, 0, 0); // Revert
            entity.velocity.x = 0;
        }

        // --- Z Axis Resolution ---
        entityBox.move(0, 0, dz);
        if (checkCollision(world, entityBox, checkArea)) {
            entityBox.move(0, 0, -dz); // Revert
            entity.velocity.z = 0;
        }

        // Sync position back
        entity.position.x = (entityBox.minX + entityBox.maxX) / 2.0f;
        entity.position.y = entityBox.minY;
        entity.position.z = (entityBox.minZ + entityBox.maxZ) / 2.0f;
    }

    /**
     * Checks if the entityBox intersects any solid block in the world.
     * Does NOT allocate new AABB objects.
     */
    private boolean checkCollision(World world, AABB entityBox, AABB broadPhase) {
        int minX = (int) Math.floor(broadPhase.minX);
        int maxX = (int) Math.ceil(broadPhase.maxX);
        int minY = (int) Math.floor(broadPhase.minY);
        int maxY = (int) Math.ceil(broadPhase.maxY);
        int minZ = (int) Math.floor(broadPhase.minZ);
        int maxZ = (int) Math.ceil(broadPhase.maxZ);

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    Block b = world.getBlock(x, y, z);
                    if (b != Block.AIR && b.isSolid()) {
                        // Check intersection with this block (x,y,z to x+1,y+1,z+1)
                        if (entityBox.minX < x + 1 && entityBox.maxX > x &&
                            entityBox.minY < y + 1 && entityBox.maxY > y &&
                            entityBox.minZ < z + 1 && entityBox.maxZ > z) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}