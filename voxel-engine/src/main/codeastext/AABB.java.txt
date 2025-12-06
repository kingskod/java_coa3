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
}