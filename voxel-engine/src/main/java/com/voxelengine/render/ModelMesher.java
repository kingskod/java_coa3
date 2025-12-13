package com.voxelengine.render;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;
import com.voxelengine.render.GreedyMesher.FloatList;

public class ModelMesher {

    // Initialize models once
    static {
        BlockModels.init();
    }

    public void generate(Chunk chunk, World world, TextureAtlas atlas, 
                         FloatList targetOpaque, 
                         FloatList targetTransparent) {
        
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    Block block = chunk.getBlockLocal(x, y, z);
                    if (block == Block.AIR || block.isFullCube()) continue;

                    // Calculate Light
                    int sl = chunk.getSkyLight(x, y, z);
                    int bl = chunk.getBlockLight(x, y, z);

                    // Choose Buffer
                    FloatList buffer = block.isTransparent() || block.isWater() ? targetTransparent : targetOpaque;
                    
                    if (block.isWater()) {
                        renderFluid(block, x, y, z, chunk, world, atlas, buffer, sl, bl);
                    } else if (block == Block.WIRE) {
                        renderWire(x, y, z, chunk, world, atlas, buffer, sl, bl);
                    } else if (block.isLogicGate()|| block==Block.LEVER) {
                        BlockModel model = BlockModels.get(block);
                        if (model != null) {
                            model.render(x, y, z, chunk, atlas, buffer, sl, bl, block);
                        } else {
                            // Fallback if model missing
                            renderBox(block, x + 0.1f, y, z + 0.1f, x + 0.9f, y + 0.2f, z + 0.9f, atlas, buffer, sl, bl);
                        }
                    } else {
                        // Default fallback (e.g. Levers not yet modeled)
                        renderBox(block, x + 0.2f, y + 0.0f, z + 0.2f, x + 0.8f, y + 0.2f, z + 0.8f, atlas, buffer, sl, bl);
                    }
                }
            }
        }
    }

    // =================================================================
    //  RENDER BOX HELPERS
    // =================================================================

    // Overload 1: Automatic Texture Lookup
     private void renderBox(Block block, float x0, float y0, float z0, float x1, float y1, float z1, 
                           TextureAtlas atlas, FloatList buffer, int sl, int bl) {
        float texIdx = atlas.getIndex(block.name().toLowerCase(), Direction.UP);
        renderBox(null, x0, y0, z0, x1, y1, z1, atlas, buffer, sl, bl, texIdx);
    }

    // Overload 2: Explicit Texture Index
    private void renderBox(Block ignored, float x0, float y0, float z0, float x1, float y1, float z1, 
                           TextureAtlas atlas, FloatList buffer, int sl, int bl, float texIdx) {
        
        // BOX MAPPING LOGIC:
        // Instead of 0..1 for every face, we use the local coordinates (relative to block).
        // This ensures a 2-pixel wide stick only grabs 2 pixels of the texture.
        
        // We need local coordinates (0..1) for UVs.
        // Since x0, y0, etc are World Coordinates (e.g., 5.4, 60.0), we need to extract the fraction.
        // However, renderPart passes coordinates that are ALREADY relative to the block anchor + rotation.
        // Wait, 'generate' passes World Coordinates (x,y,z integers + float offsets).
        
        // We can extract local UVs by taking modulo 1.0, OR simpler:
        // Just pass the float coordinates as UVs. The Shader's "fract()" will handle the wrapping for us!
        // This is the beauty of the shader fix we did earlier.
        
        // UP (Y+)
        // U: X direction, V: Z direction
        addQuad(buffer, x0, y1, z0, x1, y1, z1, x0, z0, x1, z1, Direction.UP, sl, bl, texIdx);

        // DOWN (Y-)
        // U: X, V: Z
        addQuad(buffer, x0, y0, z0, x1, y0, z1, x0, z0, x1, z1, Direction.DOWN, sl, bl, texIdx);

        // NORTH (Z-)
        // Face is in XY plane. U: X, V: Y
        // We flip X UVs for back faces usually, but let's stick to world coords for tiling consistency.
        addQuad(buffer, x1, y0, z0, x0, y1, z0, x1, y0, x0, y1, Direction.NORTH, sl, bl, texIdx);

        // SOUTH (Z+)
        addQuad(buffer, x0, y0, z1, x1, y1, z1, x0, y0, x1, y1, Direction.SOUTH, sl, bl, texIdx);

        // WEST (X-)
        // Face in ZY plane. U: Z, V: Y
        addQuad(buffer, x0, y0, z0, x0, y1, z1, z0, y0, z1, y1, Direction.WEST, sl, bl, texIdx);

        // EAST (X+)
        addQuad(buffer, x1, y0, z1, x1, y1, z0, z1, y0, z0, y1, Direction.EAST, sl, bl, texIdx);
    }
    // Overload 2: Explicit Texture Index (Used by BlockModel logic internally)
    

    // =================================================================
    //  REDSTONE WIRE
    // =================================================================

    private void renderWire(int x, int y, int z, Chunk chunk, World world, TextureAtlas atlas, FloatList buffer, int sl, int bl) {
        float texIdx = atlas.getIndex("wire", Direction.UP);
        
        // Center Dot
        addQuad(buffer, x, y+0.01f, z, x+1, y+0.01f, z+1, 0,0,1,1, Direction.UP, sl, bl, texIdx);
        
        // Check Neighbors
        checkWireConnection(x, y, z, -1, 0, Direction.WEST, chunk, world, atlas, buffer, sl, bl, texIdx);
        checkWireConnection(x, y, z, 1, 0, Direction.EAST, chunk, world, atlas, buffer, sl, bl, texIdx);
        checkWireConnection(x, y, z, 0, -1, Direction.NORTH, chunk, world, atlas, buffer, sl, bl, texIdx);
        checkWireConnection(x, y, z, 0, 1, Direction.SOUTH, chunk, world, atlas, buffer, sl, bl, texIdx);
    }
    
    private void checkWireConnection(int x, int y, int z, int dx, int dz, Direction dir, 
                                     Chunk chunk, World world, TextureAtlas atlas, FloatList buffer, int sl, int bl, float tid) {
        Block same = getNeighbor(chunk, world, x + dx, y, z + dz);
        if (same == Block.WIRE || same.isLogicGate()) {
            return; // Assume center dot covers flat connection for now
        }
        
        Block side = getNeighbor(chunk, world, x + dx, y, z + dz);
        Block up = getNeighbor(chunk, world, x + dx, y + 1, z + dz);
        
        if (side.isSolid() && up == Block.WIRE) {
            // Vertical Wire
            if (dir == Direction.NORTH) { 
                addQuad(buffer, x, y, z, x+1, y+1, z, 0,0,1,1, Direction.SOUTH, sl, bl, tid);
            } else if (dir == Direction.SOUTH) { 
                addQuad(buffer, x+1, y, z+1, x, y+1, z+1, 0,0,1,1, Direction.NORTH, sl, bl, tid);
            } else if (dir == Direction.EAST) { 
                addQuad(buffer, x+1, y, z, x+1, y+1, z+1, 0,0,1,1, Direction.WEST, sl, bl, tid);
            } else if (dir == Direction.WEST) { 
                addQuad(buffer, x, y, z+1, x, y+1, z, 0,0,1,1, Direction.EAST, sl, bl, tid);
            }
        }
    }

    // =================================================================
    //  FLUID & TORCH
    // =================================================================

    private void renderTorch(Block block, int x, int y, int z, TextureAtlas atlas, FloatList buffer, int sl, int bl) {
        float texIdx = atlas.getIndex("redstone_torch", Direction.UP);
        renderBox(null, x + 0.4375f, y, z + 0.4375f, x + 0.5625f, y + 0.6f, z + 0.5625f, atlas, buffer, sl, bl, texIdx);
    }

    private void renderFluid(Block block, int x, int y, int z, Chunk chunk, World world, TextureAtlas atlas, FloatList buffer, int sl, int bl) {
        float height = getFluidHeight(block);
        float texIdx = atlas.getIndex("water", Direction.UP); 
        
        Block above = getNeighbor(chunk, world, x, y + 1, z);
        if (!above.isWater()) {
            addQuad(buffer, x, y + height, z, x + 1, y + height, z + 1, 0, 0, 1, 1, Direction.UP, sl, bl, texIdx);
        }
        
        renderFluidFace(x, y, z, -1, 0, 0, Direction.WEST, height, chunk, world, atlas, buffer, sl, bl);
        renderFluidFace(x, y, z, 1, 0, 0, Direction.EAST, height, chunk, world, atlas, buffer, sl, bl);
        renderFluidFace(x, y, z, 0, 0, -1, Direction.NORTH, height, chunk, world, atlas, buffer, sl, bl);
        renderFluidFace(x, y, z, 0, 0, 1, Direction.SOUTH, height, chunk, world, atlas, buffer, sl, bl);
        renderFluidFace(x, y, z, 0, -1, 0, Direction.DOWN, height, chunk, world, atlas, buffer, sl, bl);
    }
    
    private void renderFluidFace(int x, int y, int z, int dx, int dy, int dz, Direction dir, float h, 
                                 Chunk chunk, World world, TextureAtlas atlas, FloatList buffer, int sl, int bl) {
        Block neighbor = getNeighbor(chunk, world, x + dx, y + dy, z + dz);
        if (neighbor == Block.AIR || (!neighbor.isWater() && neighbor.isTransparent())) {
            float texIdx = atlas.getIndex("water", dir);
            
            if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                addQuad(buffer, x + (dx==0?0:1), y, z + (dz==0?0:1), x + (dx==0?1:0), y + h, z + (dz==0?0:1), 0, 0, 1, h, dir, sl, bl, texIdx);
            } else if (dir == Direction.WEST || dir == Direction.EAST) {
                addQuad(buffer, x + (dx==0?0:1), y, z + (dz==0?0:1), x + (dx==0?0:1), y + h, z + (dz==0?1:0), 0, 0, 1, h, dir, sl, bl, texIdx);
            } else if (dir == Direction.DOWN) {
                addQuad(buffer, x, y, z, x + 1, y, z + 1, 0, 0, 1, 1, dir, sl, bl, texIdx);
            }
        }
    }

    // =================================================================
    //  CORE HELPERS
    // =================================================================

    private void addQuad(FloatList b, float x0, float y0, float z0, float x1, float y1, float z1, 
                         float u0, float v0, float u1, float v1, Direction dir, int sl, int bl, float tid) {
        
        float sLight = sl / 15.0f;
        float bLight = bl / 15.0f;
        boolean inverted = (dir == Direction.NORTH || dir == Direction.EAST || dir == Direction.UP);
        
        float[][] v = new float[4][3];
        
        if (dir == Direction.UP || dir == Direction.DOWN) {
            v[0] = new float[]{x0, y0, z0}; v[1] = new float[]{x1, y0, z0}; v[2] = new float[]{x1, y0, z1}; v[3] = new float[]{x0, y0, z1};
        } else if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            v[0] = new float[]{x0, y0, z0}; v[1] = new float[]{x1, y0, z0}; v[2] = new float[]{x1, y1, z1}; v[3] = new float[]{x0, y1, z1};
        } else { 
            v[0] = new float[]{x0, y0, z0}; v[1] = new float[]{x0, y0, z1}; v[2] = new float[]{x1, y1, z1}; v[3] = new float[]{x1, y1, z0};
        }
        
        if (inverted) {
             addVert(b, v[0], u0, v0, sLight, bLight, tid); addVert(b, v[3], u0, v1, sLight, bLight, tid); addVert(b, v[2], u1, v1, sLight, bLight, tid);
             addVert(b, v[0], u0, v0, sLight, bLight, tid); addVert(b, v[2], u1, v1, sLight, bLight, tid); addVert(b, v[1], u1, v0, sLight, bLight, tid);
        } else {
             addVert(b, v[0], u0, v0, sLight, bLight, tid); addVert(b, v[1], u1, v0, sLight, bLight, tid); addVert(b, v[2], u1, v1, sLight, bLight, tid);
             addVert(b, v[0], u0, v0, sLight, bLight, tid); addVert(b, v[2], u1, v1, sLight, bLight, tid); addVert(b, v[3], u0, v1, sLight, bLight, tid);
        }
    }

    private void addVert(FloatList b, float[] p, float u, float v, float sl, float bl, float tid) {
        b.add(p[0]); b.add(p[1]); b.add(p[2]);
        b.add(u); b.add(v);
        b.add(sl); b.add(bl);
        b.add(tid);
    }
    
    // Helper to pass floats directly
    private void addVert(FloatList b, float x, float y, float z, float u, float v, float sl, float bl, float tid) {
        b.add(x); b.add(y); b.add(z);
        b.add(u); b.add(v);
        b.add(sl); b.add(bl);
        b.add(tid);
    }

    private float getFluidHeight(Block b) {
        if (b == Block.WATER_SOURCE || b == Block.WATER) return 0.9f;
        int level = b.getWaterLevel();
        if (level <= 0) return 0.1f;
        return (level + 1) / 9.0f; 
    }

    private Block getNeighbor(Chunk chunk, World world, int x, int y, int z) {
        if (chunk.isBounds(x, y, z)) return chunk.getBlockLocal(x, y, z);
        return world.getBlock(chunk.worldX + x, y, chunk.worldZ + z);
    }
}