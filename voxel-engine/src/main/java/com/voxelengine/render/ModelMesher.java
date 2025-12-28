package com.voxelengine.render;

import com.voxelengine.render.GreedyMesher.FloatList;
import com.voxelengine.render.model.BakedModel;
import com.voxelengine.render.model.BakedQuad;
import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;

public class ModelMesher {

    public void generate(Chunk chunk, World world, TextureAtlas atlas, 
                         FloatList targetOpaque, 
                         FloatList targetTransparent) {
        
        // Ensure registry is initialized (idempotent usually, or call in Main)
        // For safety here:
        if (firstRun) {
            ModelRegistry.init(atlas);
            firstRun = false;
        }
        
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    Block block = chunk.getBlockLocal(x, y, z);
                    if (block == Block.AIR || block.isFullCube()) continue;

                    // Calculate Light at center (simplified)
                    // In robust engine, calculate per face. Here we use block center.
                    int sl = chunk.getSkyLight(x, y, z);
                    int bl = chunk.getBlockLight(x, y, z);

                    FloatList buffer = block.isTransparent() || block.isWater() ? targetTransparent : targetOpaque;
                    
                    if (block.isWater()) {
                        renderFluid(block, x, y, z, chunk, world, atlas, buffer, sl, bl);
                        continue;
                    } 
                    
                    // --- NEW JSON PIPELINE ---
                    byte meta = chunk.getMetadata(x, y, z);
                     BakedModel model = ModelRegistry.getModel(block, meta);
                    
                    if (model != BakedModel.EMPTY) {
                        for (BakedQuad quad : model.getQuads()) {
                            addBakedQuad(buffer, x, y, z, quad, sl, bl);
                        }
                    } else {
                        if (block == Block.WIRE) {
                            renderWire(x, y, z, chunk, world, atlas, buffer, sl, bl);
                        }
                    }
                }
            }
        }
    }
    
    private static boolean firstRun = true;

    private void addBakedQuad(FloatList buffer, int x, int y, int z, BakedQuad quad, int sl, int bl) {
        float sLight = sl / 15.0f;
        float bLight = bl / 15.0f;
        
        // Vertices are 0..1 relative to block origin
        float[] pos = quad.positions; // 12 floats (x,y,z * 4)
        float[] uvs = quad.uvs;       // 8 floats (u,v * 4)
        float tid = quad.textureIndex;
        
        for (int i = 0; i < 4; i++) {
            // Position
            buffer.add(x + pos[i*3 + 0]);
            buffer.add(y + pos[i*3 + 1]);
            buffer.add(z + pos[i*3 + 2]);
            
            // UV
            buffer.add(uvs[i*2 + 0]);
            buffer.add(uvs[i*2 + 1]);
            
            // Light
            buffer.add(sLight);
            buffer.add(bLight);
            
            // Texture Index
            buffer.add(tid);
        }
    }

    // --- Legacy / Special Renderers (Fluid/Wire) ---
    // Wire is often procedural due to connections, hard to do purely with static JSON without multipart.
    // Keeping logic for now as fallback.

    private void renderWire(int x, int y, int z, Chunk chunk, World world, TextureAtlas atlas, FloatList buffer, int sl, int bl) {
        float texIdx = atlas.getIndex("redstone_dust", Direction.UP);
        // Center
        addQuad(buffer, x+0.3f, y+0.01f, z+0.3f, x+0.7f, y+0.01f, z+0.7f, 0,0,1,1, Direction.UP, sl, bl, texIdx);
        // (Connections logic omitted for brevity, assumes existing logic)
    }

    private void renderFluid(Block block, int x, int y, int z, Chunk chunk, World world, TextureAtlas atlas, FloatList buffer, int sl, int bl) {
        float texIdx = atlas.getIndex("water", Direction.UP);
        // Simple flat water
        addQuad(buffer, x, y+0.9f, z, x+1, y+0.9f, z+1, 0,0,1,1, Direction.UP, sl, bl, texIdx);
    }
    
    // Helper for Legacy
    private void addQuad(FloatList b, float x0, float y0, float z0, float x1, float y1, float z1, 
                         float u0, float v0, float u1, float v1, Direction dir, int sl, int bl, float tid) {
        // (Legacy Quad Builder - Same as previous batches)
        // ...
        float sLight = sl/15f; float bLight = bl/15f;
        b.add(x0); b.add(y1); b.add(z0); b.add(u0); b.add(v0); b.add(sLight); b.add(bLight); b.add(tid);
        b.add(x1); b.add(y1); b.add(z0); b.add(u1); b.add(v0); b.add(sLight); b.add(bLight); b.add(tid);
        b.add(x1); b.add(y1); b.add(z1); b.add(u1); b.add(v1); b.add(sLight); b.add(bLight); b.add(tid);
        b.add(x0); b.add(y1); b.add(z0); b.add(u0); b.add(v0); b.add(sLight); b.add(bLight); b.add(tid);
        b.add(x1); b.add(y1); b.add(z1); b.add(u1); b.add(v1); b.add(sLight); b.add(bLight); b.add(tid);
        b.add(x0); b.add(y1); b.add(z1); b.add(u0); b.add(v1); b.add(sLight); b.add(bLight); b.add(tid);
    }
}