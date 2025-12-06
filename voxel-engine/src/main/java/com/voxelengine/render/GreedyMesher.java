package com.voxelengine.render;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;

import java.util.Arrays;

public class GreedyMesher {

    private static class FloatList {
        public float[] data = new float[4096];
        public int size = 0;
        public void add(float val) {
            if (size == data.length) {
                float[] newData = new float[data.length * 2];
                System.arraycopy(data, 0, newData, 0, data.length);
                data = newData;
            }
            data[size++] = val;
        }
        public float[] toArray() { return Arrays.copyOf(data, size); }
        public void clear() { size = 0; }
    }

    private final FloatList opaqueBuffer = new FloatList();
    private final FloatList transparentBuffer = new FloatList();

    public static class MeshData {
        public Mesh opaque;
        public Mesh transparent;
    }

    public MeshData generateMesh(Chunk chunk, World world, TextureAtlas atlas) {
        opaqueBuffer.clear();
        transparentBuffer.clear();

        for (Direction dir : Direction.VALUES) {
            generateFace(dir, chunk, world, atlas);
        }

        MeshData result = new MeshData();
        if (opaqueBuffer.size > 0) result.opaque = new Mesh(opaqueBuffer.toArray());
        if (transparentBuffer.size > 0) result.transparent = new Mesh(transparentBuffer.toArray());
        return result;
    }

    private void generateFace(Direction dir, Chunk chunk, World world, TextureAtlas atlas) {
        int uAxis, vAxis, dAxis;
        int uMax, vMax;

        if (dir == Direction.UP || dir == Direction.DOWN) {
            dAxis = 1; uAxis = 0; vAxis = 2; // y, x, z
            uMax = Chunk.WIDTH; vMax = Chunk.WIDTH;
        } else if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            dAxis = 2; uAxis = 0; vAxis = 1; // z, x, y
            uMax = Chunk.WIDTH; vMax = Chunk.HEIGHT;
        } else { // EAST, WEST
            dAxis = 0; uAxis = 2; vAxis = 1; // x, z, y
            uMax = Chunk.WIDTH; vMax = Chunk.HEIGHT;
        }

        int dMax = (dAxis == 1) ? Chunk.HEIGHT : Chunk.WIDTH;
        Block[] mask = new Block[uMax * vMax];
        int[] pos = new int[3];

        for (int slice = 0; slice < dMax; slice++) {
            
            int n = 0;
            pos[dAxis] = slice;
            for (int v = 0; v < vMax; v++) {
                pos[vAxis] = v;
                for (int u = 0; u < uMax; u++) {
                    pos[uAxis] = u;
                    
                    Block current = chunk.getBlockLocal(pos[0], pos[1], pos[2]);
                    Block neighbor = getNeighbor(chunk, world, pos[0], pos[1], pos[2], dir);

                    boolean visible = false;
                    if (current != Block.AIR) {
                        if (current.isWater()) {
                            // Water Logic: Render if neighbor is Air OR neighbor is water with lower level?
                            // Simple: Render if neighbor is Air. 
                            // If neighbor is Water, culling depends on faces.
                            if (neighbor == Block.AIR || (!neighbor.isWater() && neighbor.isTransparent())) {
                                visible = true;
                            } else if (neighbor.isWater() && dir == Direction.UP && neighbor != Block.WATER_SOURCE) {
                                // Render water top if neighbor above is flowing water? 
                                // Actually usually water-water faces are removed.
                                visible = false;
                            }
                        } else {
                            // Standard Solid/Transparent Logic
                            if (neighbor == Block.AIR || neighbor.isTransparent()) {
                                visible = true;
                            }
                        }
                    }
                    mask[n++] = visible ? current : null;
                }
            }

            n = 0;
            for (int j = 0; j < vMax; j++) {
                for (int i = 0; i < uMax; ) {
                    if (mask[n] != null) {
                        Block type = mask[n];
                        int w = 1, h = 1;

                        // Liquids don't merge well with greedy meshing due to height diffs
                        // Only merge if not water, OR if water levels are identical (simplified here to just type)
                        while (i + w < uMax && mask[n + w] == type) w++;

                        boolean done = false;
                        while (j + h < vMax) {
                            for (int k = 0; k < w; k++) {
                                if (mask[n + k + h * uMax] != type) {
                                    done = true; break;
                                }
                            }
                            if (done) break;
                            h++;
                        }

                        int[] quadPos = new int[3];
                        quadPos[dAxis] = slice;
                        quadPos[uAxis] = i;
                        quadPos[vAxis] = j;

                        int lx = quadPos[0] + dir.x;
                        int ly = quadPos[1] + dir.y;
                        int lz = quadPos[2] + dir.z;
                        
                        int sl, bl;
                        if (chunk.isBounds(lx, ly, lz)) {
                            sl = chunk.getSkyLight(lx, ly, lz);
                            bl = chunk.getBlockLight(lx, ly, lz);
                        } else {
                             sl = world.getSkyLight(chunk.worldX + lx, ly, chunk.worldZ + lz);
                             bl = world.getBlockLight(chunk.worldX + lx, ly, chunk.worldZ + lz);
                        }

                        float texIdx = atlas.getIndex(type.name().toLowerCase(), dir);
                        FloatList buffer = type.isWater() ? transparentBuffer : opaqueBuffer;
                        
                        // Calculate Water Height
                        float blockHeight = 1.0f;
                        if (type.isWater()) {
                            int level = type.getWaterLevel(); // 7=Source, 0=Low
                            // Source (7) -> 0.9 (almost full)
                            // Level 0 -> 0.1
                            if (level == 7) blockHeight = 0.9f; 
                            else blockHeight = (level + 1) / 9.0f;
                        }
                        
                        addQuad(buffer, dir, uAxis, vAxis, quadPos, w, h, sl, bl, texIdx, blockHeight);

                        for (int l = 0; l < h; l++) {
                            for (int k = 0; k < w; k++) {
                                mask[n + k + l * uMax] = null;
                            }
                        }
                        i += w;
                        n += w;
                    } else {
                        i++;
                        n++;
                    }
                }
            }
        }
    }

    private Block getNeighbor(Chunk chunk, World world, int x, int y, int z, Direction dir) {
        int nx = x + dir.x;
        int ny = y + dir.y;
        int nz = z + dir.z;
        if (chunk.isBounds(nx, ny, nz)) return chunk.getBlockLocal(nx, ny, nz);
        return world.getBlock(chunk.worldX + nx, ny, chunk.worldZ + nz);
    }

    private void addQuad(FloatList b, Direction dir, int uAxis, int vAxis, int[] pos, int w, int h, int sl, int bl, float tid, float heightY) {
        float[] v0 = new float[]{pos[0], pos[1], pos[2]};
        float[] v1 = new float[]{pos[0], pos[1], pos[2]};
        float[] v2 = new float[]{pos[0], pos[1], pos[2]};
        float[] v3 = new float[]{pos[0], pos[1], pos[2]};

        // Offset
        if (dir == Direction.EAST || dir == Direction.UP || dir == Direction.SOUTH) {
             v0[dir.x!=0?0:(dir.y!=0?1:2)]++;
             v1[dir.x!=0?0:(dir.y!=0?1:2)]++;
             v2[dir.x!=0?0:(dir.y!=0?1:2)]++;
             v3[dir.x!=0?0:(dir.y!=0?1:2)]++;
        }

        // Apply Height scaling (If block is water)
        // If Y-height < 1.0, we pull down the TOP vertices
        // This only affects Y coordinates.
        // Logic: if v[1] (y) is at 'top' (local + 1), clamp it to 'local + heightY'
        
        // This is complex for general greedy meshing. 
        // We simplify: If heightY < 1.0, assume it's water and we lower the Y of any vertex that is at y+1.
        
        float baseY = pos[1];
        
        // Apply Expansion
        v1[uAxis] += w; 
        v2[uAxis] += w; v2[vAxis] += h;
        v3[vAxis] += h;
        
        // Apply Height Check
        if (heightY < 1.0f) {
            if (v0[1] > baseY + 0.1f) v0[1] = baseY + heightY;
            if (v1[1] > baseY + 0.1f) v1[1] = baseY + heightY;
            if (v2[1] > baseY + 0.1f) v2[1] = baseY + heightY;
            if (v3[1] > baseY + 0.1f) v3[1] = baseY + heightY;
        }
        
        float uMin = 0; float uMax = w;
        float vMin = 0; float vMax = h;
        float sLight = sl / 15.0f;
        float bLight = bl / 15.0f;

        boolean inverted = (dir == Direction.NORTH || dir == Direction.EAST || dir == Direction.UP);

        if (inverted) {
             addVert(b, v0, uMin, vMin, sLight, bLight, tid);
             addVert(b, v3, uMin, vMax, sLight, bLight, tid);
             addVert(b, v2, uMax, vMax, sLight, bLight, tid);
             addVert(b, v0, uMin, vMin, sLight, bLight, tid);
             addVert(b, v2, uMax, vMax, sLight, bLight, tid);
             addVert(b, v1, uMax, vMin, sLight, bLight, tid);
        } else {
             addVert(b, v0, uMin, vMin, sLight, bLight, tid);
             addVert(b, v1, uMax, vMin, sLight, bLight, tid);
             addVert(b, v2, uMax, vMax, sLight, bLight, tid);
             addVert(b, v0, uMin, vMin, sLight, bLight, tid);
             addVert(b, v2, uMax, vMax, sLight, bLight, tid);
             addVert(b, v3, uMin, vMax, sLight, bLight, tid);
        }
    }

    private void addVert(FloatList b, float[] p, float u, float v, float sl, float bl, float tid) {
        b.add(p[0]); b.add(p[1]); b.add(p[2]);
        b.add(u); b.add(v);
        b.add(sl); b.add(bl);
        b.add(tid);
    }
}