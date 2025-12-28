package com.voxelengine.render.model;

import java.util.Collections;
import java.util.List;

public class BakedModel {
    private final List<BakedQuad> quads;
    private final boolean ambientOcclusion;

    public BakedModel(List<BakedQuad> quads, boolean ambientOcclusion) {
        this.quads = quads;
        this.ambientOcclusion = ambientOcclusion;
    }

    public List<BakedQuad> getQuads() {
        return quads;
    }

    // Default "Missing Model" (Empty)
    public static final BakedModel EMPTY = new BakedModel(Collections.emptyList(), true);
}