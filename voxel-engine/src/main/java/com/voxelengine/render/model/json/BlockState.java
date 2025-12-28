package com.voxelengine.render.model.json;

import java.util.Map;

public class BlockState {
    // Map of state string -> Variant definition
    // Key example: "facing=north,powered=true"
    // OR "normal" for simple blocks
    public Map<String, Variant> variants;

    public static class Variant {
        // Path to model (e.g. "block/lever_on")
        public String model;
        // Rotation around X axis (0, 90, 180, 270)
        public int x = 0;
        // Rotation around Y axis (0, 90, 180, 270)
        public int y = 0;
        // Lock UVs during rotation
        public boolean uvlock = false;
    }
}