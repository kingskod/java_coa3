package com.voxelengine.render;

/**
 * Represents a texture region within the Atlas.
 * Used for CPU-side UV baking of complex models.
 */
public class Sprite {
    public final float uMin;
    public final float vMin;
    public final float uMax;
    public final float vMax;

    public Sprite(float uMin, float vMin, float uMax, float vMax) {
        this.uMin = uMin;
        this.vMin = vMin;
        this.uMax = uMax;
        this.vMax = vMax;
    }

    /**
     * Converts a local pixel coordinate (0-16) to an Atlas UV coordinate.
     */
    public float getInterpolatedU(float localU) {
        // Linear interpolation: min + (val / 16) * width
        return uMin + (localU / 16.0f) * (uMax - uMin);
    }

    /**
     * Converts a local pixel coordinate (0-16) to an Atlas UV coordinate.
     */
    public float getInterpolatedV(float localV) {
        return vMin + (localV / 16.0f) * (vMax - vMin);
    }
}