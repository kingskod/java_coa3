package com.voxelengine.render.model;

import com.voxelengine.utils.Direction;

/**
 * Represents a single face of a model element.
 * Stores texture reference and UV coordinates in 0-16 pixel space.
 */
public class Face {
    public final Direction direction;
    public final String textureName;
    public final float[] uv; // [uMin, vMin, uMax, vMax] (0-16 range)
    public final int rotation; // 0, 90, 180, 270

    /**
     * @param direction Which way this face points
     * @param textureName Name of the texture in the atlas (e.g. "redstone_torch")
     * @param uv Array of 4 floats [u1, v1, u2, v2]. Can be null (auto-calculate).
     * @param rotation Texture rotation in degrees (0, 90, 180, 270)
     */
    public Face(Direction direction, String textureName, float[] uv, int rotation) {
        this.direction = direction;
        this.textureName = textureName;
        this.uv = uv;
        this.rotation = rotation;
    }
}