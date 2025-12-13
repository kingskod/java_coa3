package com.voxelengine.render;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;
import com.voxelengine.render.GreedyMesher.FloatList;

import java.util.ArrayList;
import java.util.List;

public class BlockModel {

    private final List<ModelBox> boxes = new ArrayList<>();

    public void addBox(float x, float y, float z, float width, float height, float depth, String texture) {
        boxes.add(new ModelBox(x, y, z, width, height, depth, texture));
    }

    public void render(int x, int y, int z, Chunk chunk, TextureAtlas atlas, FloatList buffer, int sl, int bl, Block block) {
        byte meta = chunk.getMetadata(x, y, z);
        for (ModelBox box : boxes) {
            box.render(x, y, z, meta, atlas, buffer, sl, bl, block);
        }
    }

    private static class ModelBox {
        float minX, minY, minZ;
        float maxX, maxY, maxZ;
        String texture;

        public ModelBox(float x, float y, float z, float w, float h, float d, String texture) {
            this.minX = x / 16.0f;
            this.minY = y / 16.0f;
            this.minZ = z / 16.0f;
            this.maxX = (x + w) / 16.0f;
            this.maxY = (y + h) / 16.0f;
            this.maxZ = (z + d) / 16.0f;
            this.texture = texture;
        }

        public void render(int x, int y, int z, byte meta, TextureAtlas atlas, FloatList buffer, int sl, int bl, Block block) {
            // 1. Rotation (Pivot 0.5)
            int rotation = meta & 3; 

            float rMinX = minX, rMinZ = minZ, rMaxX = maxX, rMaxZ = maxZ;

            for (int i = 0; i < rotation; i++) {
                float oldMinX = rMinX;
                float oldMaxX = rMaxX;
                rMinX = 1.0f - rMaxZ;
                rMaxX = 1.0f - rMinZ;
                rMinZ = oldMinX;
                rMaxZ = oldMaxX;
            }
            
            // 2. Resolve Texture
            String texName = texture;
            if (texName.equals("#top")) {
                texName = block.name().toLowerCase();
                if (block.isActive(meta)) texName += "_on";
            }
            float texIdx = atlas.getIndex(texName, Direction.UP);

            // 3. Render with Corrected UVs
            renderBoxGeometry(x + rMinX, y + minY, z + rMinZ, 
                              x + rMaxX, y + maxY, z + rMaxZ, 
                              buffer, sl, bl, texIdx);
        }

        private void renderBoxGeometry(float x0, float y0, float z0, float x1, float y1, float z1, 
                                       FloatList buffer, int sl, int bl, float tid) {
            float s = sl / 15.0f;
            float l = bl / 15.0f;
            
            // UV Coordinates logic:
            // The Shader flips Y: tiledUV.y = 1.0 - tiledUV.y
            // To get correct orientation, we must pre-flip the Y inputs here.
            // If Geometry is at Y, we pass UV.y = (1.0 - Y)
            
            // We use local coordinates (0..1) for UV mapping to handle small boxes correctly
            // (Box Mapping)
            
            // However, we need to extract the "Fractional" part of the world coordinate for the UV
            // AND flip the Y.
            
            // Helper for Y flipping:
            float uvY0 = 1.0f - y0; // Bottom of box maps to 1.0 (which shader flips to 0.0)
            float uvY1 = 1.0f - y1; // Top of box maps to (1.0-height)

            // TOP (Y+)
            addQuad(buffer, x0, y1, z0, x1, y1, z1, x0, z0, x1, z1, s, l, tid);

            // BOTTOM (Y-)
            addQuad(buffer, x0, y0, z0, x1, y0, z1, x0, z0, x1, z1, s, l, tid);

            // FRONT (Z+) - Note: Uses flipped Y for UVs
            addQuad(buffer, x0, y0, z1, x1, y1, z1, x0, uvY0, x1, uvY1, s, l, tid);
            
            // BACK (Z-)
            addQuad(buffer, x1, y0, z0, x0, y1, z0, x1, uvY0, x0, uvY1, s, l, tid);
            
            // LEFT (X-)
            addQuad(buffer, x0, y0, z0, x0, y1, z1, z0, uvY0, z1, uvY1, s, l, tid);
            
            // RIGHT (X+)
            addQuad(buffer, x1, y0, z1, x1, y1, z0, z1, uvY0, z0, uvY1, s, l, tid);
        }

        private void addQuad(FloatList b, float x0, float y0, float z0, float x1, float y1, float z1, 
                             float u0, float v0, float u1, float v1, 
                             float s, float l, float tid) {
            
            // CCW Winding
            b.add(x0); b.add(y0); b.add(z1); b.add(u0); b.add(v0); b.add(s); b.add(l); b.add(tid);
            b.add(x1); b.add(y0); b.add(z1); b.add(u1); b.add(v0); b.add(s); b.add(l); b.add(tid);
            b.add(x1); b.add(y1); b.add(z0); b.add(u1); b.add(v1); b.add(s); b.add(l); b.add(tid);
            
            b.add(x0); b.add(y0); b.add(z1); b.add(u0); b.add(v0); b.add(s); b.add(l); b.add(tid);
            b.add(x1); b.add(y1); b.add(z0); b.add(u1); b.add(v1); b.add(s); b.add(l); b.add(tid);
            b.add(x0); b.add(y1); b.add(z0); b.add(u0); b.add(v1); b.add(s); b.add(l); b.add(tid);
        }
    }
}