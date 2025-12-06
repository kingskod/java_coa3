package com.voxelengine.entity;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles collision resolution and discrete physics steps.
 */
public class PhysicsEngine {

    public void resolveCollision(Entity entity, World world, float dt) {
        // Apply Gravity
        entity.velocity.y -= 28.0f * dt;

        // Terminal velocity
        if (entity.velocity.y < -30) entity.velocity.y = -30;

        // Apply Friction to X/Z
        float friction = entity.onGround ? 10.0f : 1.0f;
        // Simple damping
        entity.velocity.x *= (1.0f - friction * dt);
        entity.velocity.z *= (1.0f - friction * dt);

        // Move
        float dx = entity.velocity.x * dt;
        float dy = entity.velocity.y * dt;
        float dz = entity.velocity.z * dt;

        AABB entityBox = entity.boundingBox;

        // --- Y Axis Resolution ---
        // Expand box to check prospective collisions
        List<AABB> collisions = getSurroundingBlockBoxes(world, entityBox.expand(dx, dy, dz));

        // Try Move Y
        entityBox.move(0, dy, 0);
        for (AABB blockBox : collisions) {
            if (entityBox.intersects(blockBox)) {
                // Collision! Resolve Y.
                if (dy > 0) {
                    // Hit ceiling
                    entityBox.maxY = blockBox.minY;
                    entityBox.minY = entityBox.maxY - entity.height;
                    entity.velocity.y = 0;
                } else if (dy < 0) {
                    // Hit floor
                    entityBox.minY = blockBox.maxY;
                    entityBox.maxY = entityBox.minY + entity.height;
                    entity.velocity.y = 0;
                    entity.onGround = true;
                }
            }
        }

        // If we didn't hit anything going down, we are in air (tentatively, corrected next frame if we land)
        if (dy != 0) {
            // Check strictly below to maintain onGround state for jumping logic
            // (Simplified: relies on collision resolution setting velocity to 0)
            if (Math.abs(entity.velocity.y) > 0) entity.onGround = false;
        }

        // --- X Axis Resolution ---
        entityBox.move(dx, 0, 0);
        for (AABB blockBox : collisions) {
            if (entityBox.intersects(blockBox)) {
                // Step Logic: If we are on ground and obstruction is low
                boolean stepped = false;
                if (entity.onGround && Math.abs(dx) > 0) {
                    float stepHeight = 0.6f; // Max step up
                    if (blockBox.maxY - entityBox.minY <= stepHeight) {
                        // Check if space above is free
                        AABB future = new AABB(entityBox.minX, blockBox.maxY, entityBox.minZ, entityBox.maxX, blockBox.maxY + entity.height, entityBox.maxZ);
                        boolean blockedAbove = false;
                        for (AABB b : collisions) {
                            if (b != blockBox && future.intersects(b)) {
                                blockedAbove = true;
                                break;
                            }
                        }
                        if (!blockedAbove) {
                            entityBox.minY = blockBox.maxY;
                            entityBox.maxY = entityBox.minY + entity.height;
                            stepped = true;
                        }
                    }
                }

                if (!stepped) {
                    if (dx > 0) {
                        entityBox.maxX = blockBox.minX;
                        entityBox.minX = entityBox.maxX - entity.width;
                    } else {
                        entityBox.minX = blockBox.maxX;
                        entityBox.maxX = entityBox.minX + entity.width;
                    }
                    entity.velocity.x = 0;
                }
            }
        }

        // --- Z Axis Resolution ---
        entityBox.move(0, 0, dz);
        for (AABB blockBox : collisions) {
            if (entityBox.intersects(blockBox)) {
                // Step Logic (Z)
                boolean stepped = false;
                if (entity.onGround && Math.abs(dz) > 0) {
                    float stepHeight = 0.6f;
                    if (blockBox.maxY - entityBox.minY <= stepHeight) {
                        AABB future = new AABB(entityBox.minX, blockBox.maxY, entityBox.minZ, entityBox.maxX, blockBox.maxY + entity.height, entityBox.maxZ);
                        boolean blockedAbove = false;
                        for (AABB b : collisions) {
                            if (b != blockBox && future.intersects(b)) {
                                blockedAbove = true;
                                break;
                            }
                        }
                        if (!blockedAbove) {
                            entityBox.minY = blockBox.maxY;
                            entityBox.maxY = entityBox.minY + entity.height;
                            stepped = true;
                        }
                    }
                }

                if (!stepped) {
                    if (dz > 0) {
                        entityBox.maxZ = blockBox.minZ;
                        entityBox.minZ = entityBox.maxZ - entity.width;
                    } else {
                        entityBox.minZ = blockBox.maxZ;
                        entityBox.maxZ = entityBox.minZ + entity.width;
                    }
                    entity.velocity.z = 0;
                }
            }
        }

        // Sync position back
        entity.position.x = (entityBox.minX + entityBox.maxX) / 2.0f;
        entity.position.y = entityBox.minY;
        entity.position.z = (entityBox.minZ + entityBox.maxZ) / 2.0f;
    }

    private List<AABB> getSurroundingBlockBoxes(World world, AABB box) {
        List<AABB> boxes = new ArrayList<>();
        int minX = (int) Math.floor(box.minX);
        int maxX = (int) Math.ceil(box.maxX);
        int minY = (int) Math.floor(box.minY);
        int maxY = (int) Math.ceil(box.maxY);
        int minZ = (int) Math.floor(box.minZ);
        int maxZ = (int) Math.ceil(box.maxZ);

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    Block b = world.getBlock(x, y, z);
                    if (b != Block.AIR && b.isSolid()) {
                        boxes.add(new AABB(x, y, z, x + 1, y + 1, z + 1));
                    }
                }
            }
        }
        return boxes;
    }
}