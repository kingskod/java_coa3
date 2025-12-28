package com.voxelengine.entity;

import org.joml.Vector3f;

/**
 * Axis-Aligned Bounding Box (AABB) for collision detection.
 * Defined by minimum and maximum coordinates in 3D space.
 */
public class AABB {
    public float minX, minY, minZ;
    public float maxX, maxY, maxZ;

    public AABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX; this.minY = minY; this.minZ = minZ;
        this.maxX = maxX; this.maxY = maxY; this.maxZ = maxZ;
    }

    /**
     * Creates an AABB centered at the given position with specified width and height.
     */
    public static AABB fromPositionSize(Vector3f pos, float width, float height) {
        float w2 = width / 2f;
        return new AABB(
            pos.x - w2, pos.y, pos.z - w2,
            pos.x + w2, pos.y + height, pos.z + w2
        );
    }

    /**
     * Checks if this AABB intersects with another AABB.
     */
    public boolean intersects(AABB other) {
        return this.minX < other.maxX && this.maxX > other.minX &&
               this.minY < other.maxY && this.maxY > other.minY &&
               this.minZ < other.maxZ && this.maxZ > other.minZ;
    }

    /**
     * Moves this AABB by the specified offsets.
     */
    public void move(float x, float y, float z) {
        this.minX += x; this.maxX += x;
        this.minY += y; this.maxY += y;
        this.minZ += z; this.maxZ += z;
    }
    
    /**
     * Returns a new AABB offset by the given amount.
     * Useful for checking collisions without modifying the original box.
     */
    public AABB offset(float x, float y, float z) {
        return new AABB(minX + x, minY + y, minZ + z, maxX + x, maxY + y, maxZ + z);
    }
    
    /**
     * Updates the position of the AABB based on a center point and dimensions.
     */
    public void setPosition(Vector3f pos, float width, float height) {
        float w2 = width / 2f;
        this.minX = pos.x - w2; this.maxX = pos.x + w2;
        this.minY = pos.y;      this.maxY = pos.y + height;
        this.minZ = pos.z - w2; this.maxZ = pos.z + w2;
    }

    /**
     * Returns a new AABB expanded by the given amount in each direction.
     * If positive, expands towards positive infinity. If negative, expands towards negative infinity.
     * Used for continuous collision detection (swept AABB).
     */
    public AABB expand(float x, float y, float z) {
        float nx = minX, ny = minY, nz = minZ;
        float mx = maxX, my = maxY, mz = maxZ;
        
        if (x < 0) nx += x; else mx += x;
        if (y < 0) ny += y; else my += y;
        if (z < 0) nz += z; else mz += z;
        
        return new AABB(nx, ny, nz, mx, my, mz);
    }
    
    @Override
    public String toString() {
        return String.format("AABB[%.2f, %.2f, %.2f -> %.2f, %.2f, %.2f]", minX, minY, minZ, maxX, maxY, maxZ);
    }
}