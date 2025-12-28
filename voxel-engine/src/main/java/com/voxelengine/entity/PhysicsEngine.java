package com.voxelengine.entity;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import java.util.List;

/**
 * Handles collision resolution and discrete physics steps for entities.
 * Uses AABB (Axis-Aligned Bounding Box) intersection checking against block colliders.
 */
public class PhysicsEngine {

    /**
     * Resolves collisions for an entity against the world geometry.
     * Applies gravity, friction, and corrects position to prevent clipping.
     *
     * @param entity The entity to update.
     * @param world The game world.
     * @param dt The delta time.
     */
    public void resolveCollision(Entity entity, World world, float dt) {
        // Apply Gravity
        entity.velocity.y -= 28.0f * dt;

        // Terminal velocity cap
        if (entity.velocity.y < -30) entity.velocity.y = -30;

        // Apply Friction to horizontal movement
        float friction = entity.onGround ? 5.0f : 1.0f;
        entity.velocity.x *= (1.0f - friction * dt);
        entity.velocity.z *= (1.0f - friction * dt);

        // Move amounts
        float dx = entity.velocity.x * dt;
        float dy = entity.velocity.y * dt;
        float dz = entity.velocity.z * dt;

        AABB entityBox = entity.boundingBox;

        // --- Y Axis Resolution ---
        AABB checkAreaY = entityBox.expand(0, dy, 0); // Only check vertical path
        entityBox.move(0, dy, 0);
        
        if (checkCollision(world, entityBox, checkAreaY)) {
            // Collision detected
            entityBox.move(0, -dy, 0); // Backtrack
            
            // If falling
            if (dy < 0) {
                // Find the floor Y surface to snap to
                float floorY = findHighestFloor(world, entityBox, dy);
                
                if (floorY != -Float.MAX_VALUE) {
                    entityBox.minY = floorY;
                    entityBox.maxY = floorY + entity.height;
                    entity.velocity.y = 0;
                    entity.onGround = true;
                }
            } else {
                // Hit ceiling
                entity.velocity.y = 0;
            }
        } else {
             if (Math.abs(entity.velocity.y) > 0) entity.onGround = false;
        }

        // --- X Axis Resolution ---
        AABB checkAreaX = entityBox.expand(dx, 0, 0);
        entityBox.move(dx, 0, 0);
        if (checkCollision(world, entityBox, checkAreaX)) {
            entityBox.move(-dx, 0, 0);
            entity.velocity.x = 0;
        }

        // --- Z Axis Resolution ---
        AABB checkAreaZ = entityBox.expand(0, 0, dz);
        entityBox.move(0, 0, dz);
        if (checkCollision(world, entityBox, checkAreaZ)) {
            entityBox.move(0, 0, -dz);
            entity.velocity.z = 0;
        }

        // Sync entity position back to the bounding box
        entity.position.x = (entityBox.minX + entityBox.maxX) / 2.0f;
        entity.position.y = entityBox.minY;
        entity.position.z = (entityBox.minZ + entityBox.maxZ) / 2.0f;
    }

    /**
     * Checks if the entityBox intersects any solid block collision box in the world within the broadphase area.
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
                    // Get detailed collision boxes (could be 0, 1, or multiple)
                    List<AABB> boxes = b.getColliders(x, y, z, world.getMetadata(x, y, z));
                    
                    for (AABB localBox : boxes) {
                        // Offset local box (0..1) to world position (x..x+1)
                        // Optimization: math directly here to avoid allocating new AABB objects
                        float boxMinX = localBox.minX + x;
                        float boxMinY = localBox.minY + y;
                        float boxMinZ = localBox.minZ + z;
                        float boxMaxX = localBox.maxX + x;
                        float boxMaxY = localBox.maxY + y;
                        float boxMaxZ = localBox.maxZ + z;
                        
                        if (entityBox.minX < boxMaxX && entityBox.maxX > boxMinX &&
                            entityBox.minY < boxMaxY && entityBox.maxY > boxMinY &&
                            entityBox.minZ < boxMaxZ && entityBox.maxZ > boxMinZ) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Finds the Y level of the solid surface directly below the entity.
     * Used for snapping to floors to prevent jittering or sinking.
     */
    private float findHighestFloor(World world, AABB entityBox, float dy) {
        // Expand downward to find candidates
        int minX = (int) Math.floor(entityBox.minX);
        int maxX = (int) Math.ceil(entityBox.maxX);
        int minY = (int) Math.floor(entityBox.minY + dy); // Look ahead
        int maxY = (int) Math.ceil(entityBox.minY);
        int minZ = (int) Math.floor(entityBox.minZ);
        int maxZ = (int) Math.ceil(entityBox.maxZ);
        
        float highestY = -Float.MAX_VALUE;

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    Block b = world.getBlock(x, y, z);
                    List<AABB> boxes = b.getColliders(x, y, z, world.getMetadata(x, y, z));
                    
                    for (AABB localBox : boxes) {
                        float boxMaxY = localBox.maxY + y;
                        float boxMinX = localBox.minX + x;
                        float boxMaxX = localBox.maxX + x;
                        float boxMinZ = localBox.minZ + z;
                        float boxMaxZ = localBox.maxZ + z;
                        
                        // Check horizontal overlap only
                        if (entityBox.minX < boxMaxX && entityBox.maxX > boxMinX &&
                            entityBox.minZ < boxMaxZ && entityBox.maxZ > boxMinZ) {
                            
                            // It's below us or intersecting bottom
                            if (boxMaxY <= entityBox.minY + 0.01f && boxMaxY > highestY) {
                                highestY = boxMaxY;
                            }
                        }
                    }
                }
            }
        }
        return highestY;
    }
}