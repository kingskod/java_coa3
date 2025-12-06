package com.voxelengine.render;

import com.voxelengine.world.Block;
import com.voxelengine.world.Chunk;

import java.util.ArrayList;
import java.util.List;

public class GreedyMesher {

    public Mesh generateMesh(Chunk chunk, TextureAtlas atlas) {
        List<Float> vertices = new ArrayList<>();
        final int WIDTH = Chunk.WIDTH;
        final int HEIGHT = Chunk.HEIGHT;
        
        for (int d = 0; d < 3; d++) {
            int i, j, k, l, w, h;
            int u = (d + 1) % 3; 
            int v = (d + 2) % 3;
            int[] x = new int[3];
            int[] q = new int[3];
            
            int uMax = (u == 1 ? HEIGHT : WIDTH);
            int vMax = (v == 1 ? HEIGHT : WIDTH);
            boolean[] mask = new boolean[uMax * vMax];
            q[d] = 1; 
            
            int limit = (d == 1 ? HEIGHT : WIDTH);
            
            for (x[d] = -1; x[d] < limit; ) {
                int n = 0;
                // 1. Compute Mask
                for (x[v] = 0; x[v] < vMax; x[v]++) {
                    for (x[u] = 0; x[u] < uMax; x[u]++) {
                        Block b1 = (x[d] >= 0) ? chunk.getBlock(x[0], x[1], x[2]) : Block.AIR;
                        Block b2 = (x[d] < limit - 1) ? chunk.getBlock(x[0] + q[0], x[1] + q[1], x[2] + q[2]) : Block.AIR;
                        
                        boolean face1 = b1 != Block.AIR && b1.isSolid() && (b2 == Block.AIR || b2.isTransparent());
                        boolean face2 = b2 != Block.AIR && b2.isSolid() && (b1 == Block.AIR || b1.isTransparent());
                        
                        mask[n++] = face1 || face2;
                    }
                }
                
                x[d]++; 
                
                // 2. Greedy Generation
                n = 0;
                for (j = 0; j < vMax; j++) {
                    for (i = 0; i < uMax; ) {
                        if (mask[n]) {
                            int[] pos = new int[]{x[0], x[1], x[2]};
                            pos[d]--;
                            Block b1 = (pos[d] >= 0) ? chunk.getBlock(pos[0], pos[1], pos[2]) : Block.AIR;
                            pos[d]++;
                            Block b2 = (pos[d] < limit) ? chunk.getBlock(pos[0], pos[1], pos[2]) : Block.AIR;
                            
                            boolean face1 = b1 != Block.AIR && b1.isSolid() && (b2 == Block.AIR || b2.isTransparent());
                            Block currentBlock = face1 ? b1 : b2;
                            
                            // LIGHTING FETCH
                            int skyLight, blkLight;
                            if (face1) {
                                skyLight = chunk.getSkyLight(pos[0]-q[0], pos[1]-q[1], pos[2]-q[2]);
                                blkLight = chunk.getBlockLight(pos[0]-q[0], pos[1]-q[1], pos[2]-q[2]);
                            } else {
                                skyLight = chunk.getSkyLight(pos[0], pos[1], pos[2]);
                                blkLight = chunk.getBlockLight(pos[0], pos[1], pos[2]);
                            }

                            w = 1;
                            while (i + w < uMax && mask[n + w]) {
                                int[] checkPos = pos.clone();
                                checkPos[u] += w;
                                checkPos[d]--;
                                Block c1 = (checkPos[d] >= 0) ? chunk.getBlock(checkPos[0], checkPos[1], checkPos[2]) : Block.AIR;
                                checkPos[d]++;
                                Block c2 = (checkPos[d] < limit) ? chunk.getBlock(checkPos[0], checkPos[1], checkPos[2]) : Block.AIR;
                                
                                boolean cFace1 = c1 != Block.AIR && c1.isSolid() && (c2 == Block.AIR || c2.isTransparent());
                                Block cBlock = cFace1 ? c1 : c2;
                                
                                // Fetch lighting for merger check (simplify: merge only if light matches?)
                                // For perfect smooth lighting, we don't merge different light levels.
                                // For performance/simplicity here, we merge and use the first block's light (flat shading for merged quad).
                                // A real engine uses vertex interpolation so merging is fine.
                                
                                if (cFace1 != face1 || cBlock != currentBlock) break;
                                w++;
                            }
                            
                            h = 1;
                            boolean done = false;
                            while (j + h < vMax) {
                                for (k = 0; k < w; k++) {
                                    if (!mask[n + k + h * uMax]) { done = true; break; }
                                    
                                    int[] checkPos = pos.clone();
                                    checkPos[u] += k;
                                    checkPos[v] += h;
                                    checkPos[d]--;
                                    Block c1 = (checkPos[d] >= 0) ? chunk.getBlock(checkPos[0], checkPos[1], checkPos[2]) : Block.AIR;
                                    checkPos[d]++;
                                    Block c2 = (checkPos[d] < limit) ? chunk.getBlock(checkPos[0], checkPos[1], checkPos[2]) : Block.AIR;
                                    boolean cFace1 = c1 != Block.AIR && c1.isSolid() && (c2 == Block.AIR || c2.isTransparent());
                                    Block cBlock = cFace1 ? c1 : c2;
                                    
                                    if (cFace1 != face1 || cBlock != currentBlock) { done = true; break; }
                                }
                                if (done) break;
                                h++;
                            }
                            
                            float texIdx = atlas.getIndex(currentBlock.name().toLowerCase());
                            addQuad(vertices, d, i, j, w, h, x[d], face1, skyLight, blkLight, texIdx);
                            
                            for (l = 0; l < h; l++) {
                                for (k = 0; k < w; k++) {
                                    mask[n + k + l * uMax] = false;
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
        
        float[] arr = new float[vertices.size()];
        for (int i=0; i<vertices.size(); i++) arr[i] = vertices.get(i);
        return new Mesh(arr);
    }

    private void addQuad(List<Float> vertices, int d, int uPos, int vPos, int uSize, int vSize, int dPos, boolean backFace, int skyLight, int blkLight, float texIdx) {
        float[] v0 = new float[3];
        float[] v1 = new float[3];
        float[] v2 = new float[3];
        float[] v3 = new float[3];
        int u = (d + 1) % 3;
        int v = (d + 2) % 3;
        
        v0[d] = dPos; v0[u] = uPos;         v0[v] = vPos;
        v1[d] = dPos; v1[u] = uPos + uSize; v1[v] = vPos;
        v2[d] = dPos; v2[u] = uPos + uSize; v2[v] = vPos + vSize;
        v3[d] = dPos; v3[u] = uPos;         v3[v] = vPos + vSize;
        
        float uMin = 0; float uMax = uSize;
        float vMin = 0; float vMax = vSize;
        
        float sl = (float)skyLight / 15.0f;
        float bl = (float)blkLight / 15.0f;
        
        if (backFace) {
            addVert(vertices, v0, uMin, vMin, sl, bl, texIdx);
            addVert(vertices, v3, uMin, vMax, sl, bl, texIdx);
            addVert(vertices, v2, uMax, vMax, sl, bl, texIdx);
            addVert(vertices, v0, uMin, vMin, sl, bl, texIdx);
            addVert(vertices, v2, uMax, vMax, sl, bl, texIdx);
            addVert(vertices, v1, uMax, vMin, sl, bl, texIdx);
        } else {
            addVert(vertices, v0, uMin, vMin, sl, bl, texIdx);
            addVert(vertices, v1, uMax, vMin, sl, bl, texIdx);
            addVert(vertices, v2, uMax, vMax, sl, bl, texIdx);
            addVert(vertices, v0, uMin, vMin, sl, bl, texIdx);
            addVert(vertices, v2, uMax, vMax, sl, bl, texIdx);
            addVert(vertices, v3, uMin, vMax, sl, bl, texIdx);
        }
    }
    
    private void addVert(List<Float> list, float[] pos, float u, float v, float sl, float bl, float tid) {
        list.add(pos[0]); list.add(pos[1]); list.add(pos[2]); 
        list.add(u); list.add(v); 
        list.add(sl); // Sky
        list.add(bl); // Block (Fixed!)
        list.add(tid); 
    }
}