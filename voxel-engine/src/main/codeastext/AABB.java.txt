package com.voxelengine.entity;

import org.joml.Vector3f;

public class AABB {
    public float minX, minY, minZ;
    public float maxX, maxY, maxZ;

    public AABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX; this.minY = minY; this.minZ = minZ;
        this.maxX = maxX; this.maxY = maxY; this.maxZ = maxZ;
    }

    public static AABB fromPositionSize(Vector3f pos, float width, float height) {
        float w2 = width / 2f;
        return new AABB(
            pos.x - w2, pos.y, pos.z - w2,
            pos.x + w2, pos.y + height, pos.z + w2
        );
    }

    public boolean intersects(AABB other) {
        return this.minX < other.maxX && this.maxX > other.minX &&
               this.minY < other.maxY && this.maxY > other.minY &&
               this.minZ < other.maxZ && this.maxZ > other.minZ;
    }

    public void move(float x, float y, float z) {
        this.minX += x; this.maxX += x;
        this.minY += y; this.maxY += y;
        this.minZ += z; this.maxZ += z;
    }
    
    // Returns a NEW AABB offset by the given amount
    public AABB offset(float x, float y, float z) {
        return new AABB(minX + x, minY + y, minZ + z, maxX + x, maxY + y, maxZ + z);
    }
    
    public void setPosition(Vector3f pos, float width, float height) {
        float w2 = width / 2f;
        this.minX = pos.x - w2; this.maxX = pos.x + w2;
        this.minY = pos.y;      this.maxY = pos.y + height;
        this.minZ = pos.z - w2; this.maxZ = pos.z + w2;
    }

    public AABB expand(float x, float y, float z) {
        float nx = minX, ny = minY, nz = minZ;
        float mx = maxX, my = maxY, mz = maxZ;
        
        if (x < 0) nx += x; else mx += x;
        if (y < 0) ny += y; else my += y;
        if (z < 0) nz += z; else mz += z;
        
        return new AABB(nx, ny, nz, mx, my, mz);
    }
    
    /**
     * Checks if a ray intersects this AABB.
     * Required for precise Block Selection.
     * @param origin Ray origin
     * @param dir Ray direction (normalized)
     * @return Distance to intersection, or -1 if missed
     */
    public float rayIntersect(Vector3f origin, Vector3f dir) {
        float t1 = (minX - origin.x) / dir.x;
        float t2 = (maxX - origin.x) / dir.x;
        float t3 = (minY - origin.y) / dir.y;
        float t4 = (maxY - origin.y) / dir.y;
        float t5 = (minZ - origin.z) / dir.z;
        float t6 = (maxZ - origin.z) / dir.z;

        float tmin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
        float tmax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));

        // if tmax < 0, intersection is behind ray origin
        if (tmax < 0) return -1.0f;

        // if tmin > tmax, ray doesn't intersect
        if (tmin > tmax) return -1.0f;

        return tmin;
    }

    @Override
    public String toString() {
        return String.format("AABB[%.2f, %.2f, %.2f -> %.2f, %.2f, %.2f]", minX, minY, minZ, maxX, maxY, maxZ);
    }
}