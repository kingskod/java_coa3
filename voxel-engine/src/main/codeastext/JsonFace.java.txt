package com.voxelengine.render.model.json;

public class JsonFace {
    // Reference to a texture variable, e.g. "#top" or "#all"
    public String texture;
    // Custom UV coordinates [x1, y1, x2, y2], can be null
    public float[] uv;
    // Rotation of the texture (0, 90, 180, 270)
    public int rotation = 0;
    // Optimization hint (optional)
    public String cullface;
}