package com.voxelengine.render.model;

import com.voxelengine.utils.Direction;

public class BakedQuad {
    // 4 vertices, x,y,z per vertex = 12 floats
    public final float[] positions; 
    // 4 vertices, u,v per vertex = 8 floats
    public final float[] uvs;
    // The direction this face is pointing (for lighting calculation)
    public final Direction face;
    // The texture index in the atlas (calculated during baking)
    public final float textureIndex;
    // Tint index (for grass color, etc. -1 = none)
    public final int tintIndex;

    public BakedQuad(float[] positions, float[] uvs, Direction face, float textureIndex, int tintIndex) {
        this.positions = positions;
        this.uvs = uvs;
        this.face = face;
        this.textureIndex = textureIndex;
        this.tintIndex = tintIndex;
    }
}