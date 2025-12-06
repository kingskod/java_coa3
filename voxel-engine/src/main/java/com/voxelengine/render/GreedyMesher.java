/*package com.voxelengine.render;

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

    // Result container
    public static class MeshData {
        public Mesh opaque;
        public Mesh transparent;
    }

    public MeshData generateMesh(Chunk chunk, World world, TextureAtlas atlas) {
        opaqueBuffer.clear();
        transparentBuffer.clear();

        // 6-Pass Greedy Meshing
        for (Direction dir : Direction.VALUES) {
            generateFace(dir, chunk, world, atlas);
        }

        MeshData result = new MeshData();
        if (opaqueBuffer.size > 0) result.opaque = new Mesh(opaqueBuffer.toArray());
        if (transparentBuffer.size > 0) result.transparent = new Mesh(transparentBuffer.toArray());
        return result;
    }

    private void generateFace(Direction dir, Chunk chunk, World world, TextureAtlas atlas) {
        // Determine axes
        // u, v are the dimensions of the slice
        // d is the dimension of propagation
        int uAxis, vAxis, dAxis;
        int uMax, vMax;

        if (dir == Direction.UP || dir == Direction.DOWN) {
            // Y-axis propagation. Slice is XZ plane.
            dAxis = 1; uAxis = 0; vAxis = 2; // x, z
            uMax = Chunk.WIDTH; vMax = Chunk.WIDTH;
        } else if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            // Z-axis propagation. Slice is XY plane.
            dAxis = 2; uAxis = 0; vAxis = 1; // x, y
            uMax = Chunk.WIDTH; vMax = Chunk.HEIGHT;
        } else { // EAST, WEST
            // X-axis propagation. Slice is ZY plane.
            dAxis = 0; uAxis = 2; vAxis = 1; // z, y
            uMax = Chunk.WIDTH; vMax = Chunk.HEIGHT;
        }

        // Mask
        // Stores: BlockType (if face visible), or null/AIR
        Block[] mask = new Block[uMax * vMax];
        
        // We iterate through the chunk in the direction of dAxis
        // dMax depends on axis
        int dMax = (dAxis == 1) ? Chunk.HEIGHT : Chunk.WIDTH;

        // Position vector
        int[] pos = new int[3];

        for (int slice = 0; slice < dMax; slice++) {
            
            // 1. Build Mask
            int n = 0;
            pos[dAxis] = slice;
            for (int v = 0; v < vMax; v++) {
                pos[vAxis] = v;
                for (int u = 0; u < uMax; u++) {
                    pos[uAxis] = u;
                    
                    Block current = chunk.getBlockLocal(pos[0], pos[1], pos[2]);
                    Block neighbor = getNeighbor(chunk, world, pos[0], pos[1], pos[2], dir);

                    // Face is visible if:
                    // 1. Current block is not AIR
                    // 2. Neighbor block is Transparent (or Air)
                    // 3. (Optional) If both are transparent (like water), handle logic. 
                    //    Here: if current is solid/opaque, neighbor must be transparent.
                    
                    boolean visible = false;
                    if (current != Block.AIR) {
                        if (neighbor == Block.AIR || neighbor.isTransparent()) {
                             // Water logic: don't render water face if neighbor is also water
                             if (current == Block.WATER && neighbor == Block.WATER) visible = false;
                             else visible = true;
                        }
                    }

                    mask[n++] = visible ? current : null;
                }
            }

            // 2. Greedy Meshing on the Mask
            n = 0;
            for (int j = 0; j < vMax; j++) {
                for (int i = 0; i < uMax; ) {
                    if (mask[n] != null) {
                        Block type = mask[n];
                        int w = 1, h = 1;

                        // Compute Width
                        while (i + w < uMax && mask[n + w] == type) {
                            w++;
                        }

                        // Compute Height
                        boolean done = false;
                        while (j + h < vMax) {
                            for (int k = 0; k < w; k++) {
                                if (mask[n + k + h * uMax] != type) {
                                    done = true; 
                                    break;
                                }
                            }
                            if (done) break;
                            h++;
                        }

                        // Add Quad
                        // Need correct coordinates based on axes
                        int[] quadPos = new int[3];
                        quadPos[dAxis] = slice;
                        quadPos[uAxis] = i;
                        quadPos[vAxis] = j;

                        // Lighting fetch (use quadPos)
                        // Fetch light from the NEIGHBOR position (the lit face)
                        int lx = quadPos[0] + dir.x;
                        int ly = quadPos[1] + dir.y;
                        int lz = quadPos[2] + dir.z;
                        
                        // Handle world bounds for light fetch
                        int sl = 15, bl = 0;
                        if (chunk.isBounds(lx, ly, lz)) {
                            sl = chunk.getSkyLight(lx, ly, lz);
                            bl = chunk.getBlockLight(lx, ly, lz);
                        } else {
                            // Fetch from world
                             int wx = chunk.worldX + lx;
                             int wz = chunk.worldZ + lz;
                             sl = world.getSkyLight(wx, ly, wz);
                             bl = world.getBlockLight(wx, ly, wz);
                        }

                        float texIdx = atlas.getIndex(type.name().toLowerCase());
                        FloatList buffer = type.isTransparent() ? transparentBuffer : opaqueBuffer;
                        
                        addQuad(buffer, dir, uAxis, vAxis, quadPos, w, h, sl, bl, texIdx);

                        // Clear Mask
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

        if (chunk.isBounds(nx, ny, nz)) {
            return chunk.getBlockLocal(nx, ny, nz);
        } else {
            return world.getBlock(chunk.worldX + nx, ny, chunk.worldZ + nz);
        }
    }

    private void addQuad(FloatList buffer, Direction dir, int uAxis, int vAxis, int[] pos, int w, int h, int sl, int bl, float tid) {
        // Construct 4 corners
        float[] v0 = new float[]{pos[0], pos[1], pos[2]};
        float[] v1 = new float[]{pos[0], pos[1], pos[2]};
        float[] v2 = new float[]{pos[0], pos[1], pos[2]};
        float[] v3 = new float[]{pos[0], pos[1], pos[2]};

        // Offset based on direction for the face plane
        // If direction is positive (East, Up, South), face is at pos + 1
        // If direction is negative (West, Down, North), face is at pos
        if (dir.x > 0 || dir.y > 0 || dir.z > 0) {
            v0[dir.vec.minComponent()] += 1;
            v1[dir.vec.minComponent()] += 1;
            v2[dir.vec.minComponent()] += 1;
            v3[dir.vec.minComponent()] += 1;
        }
        // Actually, logic is: 
        // UP (0,1,0): Face at y+1.
        // DOWN (0,-1,0): Face at y. 
        // Correct.
        // Wait, vec.minComponent returns index of smallest component. 
        // For (1,0,0), min is Y=0. Wrong logic.
        // Use explicit axis check.
        if (dir == Direction.EAST || dir == Direction.UP || dir == Direction.SOUTH) {
             int axis = (dir == Direction.EAST) ? 0 : (dir == Direction.UP ? 1 : 2);
             v0[axis]++; v1[axis]++; v2[axis]++; v3[axis]++;
        }

        // Expand Width (uAxis) and Height (vAxis)
        // v0 is origin
        v1[uAxis] += w; 
        v2[uAxis] += w; v2[vAxis] += h;
        v3[vAxis] += h;
        
        // UVs
        float uMin = 0; float uMax = w;
        float vMin = 0; float vMax = h;
        
        float sLight = sl / 15.0f;
        float bLight = bl / 15.0f;

        // Winding Order
        // Standard: CCW. 
        // We need to check which faces need Swap.
        // Pos X (East): v0->v1->v2 is CCW?
        // v0(z,y), v1(z+w, y), v2(z+w, y+h). 
        // Normal (1,0,0). Cross product checks.
        
        // Simplified Map:
        // NORTH (Z-): Standard
        // SOUTH (Z+): Inverted
        // WEST (X-): Inverted?
        // EAST (X+): Standard
        // DOWN (Y-): Standard
        // UP (Y+): Inverted
        
        // Let's rely on standard trial. 
        // Usually:
        // Positive faces (East, Up, South): CCW relative to outside.
        // Negative faces (West, Down, North): CCW relative to outside.
        
        // Let's just output arrays.
        
        // Explicit Vertex Generation
        if (dir == Direction.NORTH) { // Z-
             // v0(x,y), v1(x+w, y), v2(x+w, y+h), v3(x, y+h)
             // CCW: v0, v3, v2, v0, v2, v1
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v3, uMin, vMax, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v1, uMax, vMin, sLight, bLight, tid);
        } 
        else if (dir == Direction.SOUTH) { // Z+
             // Invert winding
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v1, uMax, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v3, uMin, vMax, sLight, bLight, tid);
        }
        else if (dir == Direction.WEST) { // X-
             // v0(z,y)
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v1, uMax, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v3, uMin, vMax, sLight, bLight, tid);
        }
        else if (dir == Direction.EAST) { // X+
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v3, uMin, vMax, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v1, uMax, vMin, sLight, bLight, tid);
        }
        else if (dir == Direction.DOWN) { // Y-
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v3, uMin, vMax, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v1, uMax, vMin, sLight, bLight, tid);
        }
        else if (dir == Direction.UP) { // Y+
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v1, uMax, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v0, uMin, vMin, sLight, bLight, tid);
             addVert(buffer, v2, uMax, vMax, sLight, bLight, tid);
             addVert(buffer, v3, uMin, vMax, sLight, bLight, tid);
        }
    }

    private void addVert(FloatList b, float[] p, float u, float v, float sl, float bl, float tid) {
        b.add(p[0]); b.add(p[1]); b.add(p[2]);
        b.add(u); b.add(v);
        b.add(sl); b.add(bl);
        b.add(tid);
    }
}*/
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

        // Configure axes based on direction plane
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
            
            // 1. Build Mask
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
                        if (neighbor == Block.AIR || neighbor.isTransparent()) {
                             if (current == Block.WATER && neighbor == Block.WATER) visible = false;
                             else visible = true;
                        }
                    }
                    mask[n++] = visible ? current : null;
                }
            }

            // 2. Greedy Meshing
            n = 0;
            for (int j = 0; j < vMax; j++) {
                for (int i = 0; i < uMax; ) {
                    if (mask[n] != null) {
                        Block type = mask[n];
                        int w = 1, h = 1;

                        // Compute Width
                        while (i + w < uMax && mask[n + w] == type) w++;

                        // Compute Height
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

                        // Add Quad
                        int[] quadPos = new int[3];
                        quadPos[dAxis] = slice;
                        quadPos[uAxis] = i;
                        quadPos[vAxis] = j;

                        // Lighting from neighbor
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

                        float texIdx = atlas.getIndex(type.name().toLowerCase());
                        FloatList buffer = type.isTransparent() ? transparentBuffer : opaqueBuffer;
                        
                        addQuad(buffer, dir, uAxis, vAxis, quadPos, w, h, sl, bl, texIdx);

                        // Clear Mask
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

        // Offset to face plane if Positive Direction
        if (dir == Direction.EAST || dir == Direction.UP || dir == Direction.SOUTH) {
             v0[dir.x!=0?0:(dir.y!=0?1:2)]++;
             v1[dir.x!=0?0:(dir.y!=0?1:2)]++;
             v2[dir.x!=0?0:(dir.y!=0?1:2)]++;
             v3[dir.x!=0?0:(dir.y!=0?1:2)]++;
        }

        // Expand along axes (passed explicitly now!)
        // v0 is origin
        v1[uAxis] += w; 
        v2[uAxis] += w; v2[vAxis] += h;
        v3[vAxis] += h;
        
        float uMin = 0; float uMax = w;
        float vMin = 0; float vMax = h;
        float sLight = sl / 15.0f;
        float bLight = bl / 15.0f;

        // Winding Order Logic
        // Derived from Axis Cross Products
        // Standard (CCW): SOUTH, WEST, DOWN
        // Inverted (CW): NORTH, EAST, UP
        
        boolean inverted = (dir == Direction.NORTH || dir == Direction.EAST || dir == Direction.UP);

        if (inverted) {
             // CW Winding (0 -> 3 -> 2 -> 0 -> 2 -> 1)
             addVert(b, v0, uMin, vMin, sLight, bLight, tid);
             addVert(b, v3, uMin, vMax, sLight, bLight, tid);
             addVert(b, v2, uMax, vMax, sLight, bLight, tid);
             addVert(b, v0, uMin, vMin, sLight, bLight, tid);
             addVert(b, v2, uMax, vMax, sLight, bLight, tid);
             addVert(b, v1, uMax, vMin, sLight, bLight, tid);
        } else {
             // CCW Winding (0 -> 1 -> 2 -> 0 -> 2 -> 3)
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