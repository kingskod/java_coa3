package com.voxelengine.render.model.json;

import java.util.List;
import java.util.Map;

public class JsonBlockModel {
    // Parent model path (e.g. "block/cube_all")
    public String parent;
    // Texture variable definitions (key: variable, value: texture path)
    public Map<String, String> textures;
    // Geometric elements (if null, inherited from parent)
    public List<JsonElement> elements;
    // Ambient occlusion setting
    public boolean ambientocclusion = true;

    // Helper to check if this model defines geometry
    public boolean hasGeometry() {
        return elements != null && !elements.isEmpty();
    }
}