package com.voxelengine.render.model.json;

import java.util.Map;

public class JsonElement {
    // Start coordinates (0-16)
    public float[] from;
    // End coordinates (0-16)
    public float[] to;
    // Map of faces (key: "up", "down", "north", etc.)
    public Map<String, JsonFace> faces;
    // Element rotation (optional)
    public ElementRotation rotation;
    // True if shading should be applied (default true)
    public boolean shade = true;

    public static class ElementRotation {
        public float[] origin; // Pivot point [x, y, z]
        public String axis;    // "x", "y", or "z"
        public float angle;    // 45, -45, 22.5, etc.
        public boolean rescale; // Whether to scale the face to fit
    }
}