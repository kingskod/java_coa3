package com.voxelengine.render.model;

import com.voxelengine.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public class BlockModel {

    public static class QuadElement {
        public final float[] positions; // 12 floats
        public final float[] uvs;       // 8 floats (0..1, face-local)
        public final Direction face;
        public final String texture;

        public QuadElement(float[] positions, float[] uvs, Direction face, String texture) {
            this.positions = positions;
            this.uvs = uvs;
            this.face = face;
            this.texture = texture;
        }
    }

    private final List<QuadElement> quads = new ArrayList<>();

    public void addQuad(float[] positions, float[] uvs, Direction face, String texture) {
        quads.add(new QuadElement(positions, uvs, face, texture));
    }

    public List<QuadElement> getQuads() {
        return quads;
    }
}
