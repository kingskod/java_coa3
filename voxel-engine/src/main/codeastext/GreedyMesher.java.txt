package com.voxelengine.render;

import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;

import java.util.Arrays;

public class GreedyMesher {

    public static class FloatList {
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
    
    private final ModelMesher modelMesher = new ModelMesher();

    public static class MeshData {
        public Mesh opaque;
        public Mesh transparent;
    }

    public MeshData generateMesh(Chunk chunk, World world, TextureAtlas atlas) {
        opaqueBuffer.clear();
        transparentBuffer.clear();

        // 1. Greedy Pass (Full Cubes)
        for (Direction dir : Direction.VALUES) {
            generateFace(dir, chunk, world, atlas);
        }
        
        // 2. Model Pass (Complex Blocks)
        modelMesher.generate(chunk, world, atlas, opaqueBuffer, transparentBuffer);

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
                    
                    if (current != Block.AIR && current.isFullCube()) {
                        // Render if neighbor is Air, Transparent, or non-full (like water)
                        if (neighbor == Block.AIR || neighbor.isTransparent() || !neighbor.isFullCube()) {
                            visible = true;
                            // Fix Z-fighting for adjacent leaves
                            if (current == neighbor && current.isTransparent()) visible = false; 
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
                        // Cutout blocks (Leaves/Glass) must write to depth, so use Opaque buffer
                        FloatList buffer = (type.isWater() || type.isTransparent()) ? transparentBuffer : opaqueBuffer;
                        if (type == Block.LEAVES || type == Block.GLASS) buffer = opaqueBuffer;

                        addQuad(buffer, dir, uAxis, vAxis, quadPos, w, h, sl, bl, texIdx);

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

    private void addQuad(FloatList b, Direction dir, int uAxis, int vAxis, int[] pos, int w, int h, int sl, int bl, float tid) {
        float[] v0 = new float[]{pos[0], pos[1], pos[2]};
        float[] v1 = new float[]{pos[0], pos[1], pos[2]};
        float[] v2 = new float[]{pos[0], pos[1], pos[2]};
        float[] v3 = new float[]{pos[0], pos[1], pos[2]};

        // FIX: Correct Axis Offset Logic
        // This shifts the face plane to the far side of the block if direction is positive
        if (dir == Direction.EAST) {
             v0[0]++; v1[0]++; v2[0]++; v3[0]++;
        } else if (dir == Direction.UP) {
             v0[1]++; v1[1]++; v2[1]++; v3[1]++;
        } else if (dir == Direction.SOUTH) {
             v0[2]++; v1[2]++; v2[2]++; v3[2]++;
        }

        v1[uAxis] += w; 
        v2[uAxis] += w; v2[vAxis] += h;
        v3[vAxis] += h;
        
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