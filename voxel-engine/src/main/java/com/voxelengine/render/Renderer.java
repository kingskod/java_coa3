package com.voxelengine.render;

import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Renderer {

    private final Shader shader;
    private final TextureAtlas atlas;
    private final GreedyMesher mesher;
    private Mesh itemMesh; // Reusable cube mesh
    
    private static class ChunkRenderData {
        Mesh opaque;
        Mesh transparent;
        void cleanup() { if(opaque!=null) opaque.cleanup(); if(transparent!=null) transparent.cleanup(); }
    }
    private final Map<Chunk, ChunkRenderData> chunkMeshes = new HashMap<>();

    public Renderer() {
        this.shader = new Shader("assets/shaders/vertex.glsl", "assets/shaders/fragment.glsl");
        this.atlas = new TextureAtlas();
        this.atlas.build();
        this.mesher = new GreedyMesher();
        
        // Generate static item mesh
        this.itemMesh = new Mesh(generateCubeVertices());
    }
    
    public TextureAtlas getTextureAtlas() { return atlas; }

    public void render(World world, Camera camera) {
        shader.bind();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
        shader.setUniform("uTexture", 0);
        shader.setUniform("uProjection", camera.getProjectionMatrix());
        shader.setUniform("uView", camera.getViewMatrix());
        shader.setUniform("uUVScale", 1.0f);
        shader.setUniform("uUVOffset", new Vector2f(0, 0));
        
        // Send Camera Position for Fog
        shader.setUniform("uCameraPos", camera.getPosition());
        
        // Ensure Override is off by default
        shader.setUniform("uOverrideTexIndex", -1.0f);

        Collection<Chunk> activeChunks = world.getChunkManager().getLoadedChunks();

        // Cleanup
        Iterator<Map.Entry<Chunk, ChunkRenderData>> it = chunkMeshes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Chunk, ChunkRenderData> entry = it.next();
            if (!activeChunks.contains(entry.getKey())) {
                entry.getValue().cleanup();
                it.remove();
            }
        }

        // Generate/Update
        for (Chunk chunk : activeChunks) {
            if (chunk.isDirty || !chunkMeshes.containsKey(chunk)) {
                GreedyMesher.MeshData data = mesher.generateMesh(chunk, world, atlas);
                
                ChunkRenderData crd = chunkMeshes.get(chunk);
                if (crd != null) crd.cleanup();
                else crd = new ChunkRenderData();
                
                crd.opaque = data.opaque;
                crd.transparent = data.transparent;
                chunkMeshes.put(chunk, crd);
                chunk.isDirty = false;
            }
        }

        // Render Opaque Pass
        glDisable(GL_BLEND);
        glEnable(GL_DEPTH_TEST);
        for (Chunk chunk : activeChunks) {
            ChunkRenderData crd = chunkMeshes.get(chunk);
            if (crd != null && crd.opaque != null) {
                Matrix4f model = new Matrix4f().translate(chunk.worldX, 0, chunk.worldZ);
                shader.setUniform("uModel", model);
                crd.opaque.render();
            }
        }

        // Render Transparent Pass
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false);
        for (Chunk chunk : activeChunks) {
            ChunkRenderData crd = chunkMeshes.get(chunk);
            if (crd != null && crd.transparent != null) {
                Matrix4f model = new Matrix4f().translate(chunk.worldX, 0, chunk.worldZ);
                shader.setUniform("uModel", model);
                crd.transparent.render();
            }
        }
        glDepthMask(true);
        glDisable(GL_BLEND);

        shader.unbind();
    }
    
    // NEW: Render an Item Entity
    // NEW: Render an Item Entity
    public void renderItemCube(com.voxelengine.world.Block block, Matrix4f model) {
        // 1. CRITICAL: Bind Shader and Texture
        shader.bind();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
        
        // 2. Ensure Uniforms are set correctly
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", 1.0f); // Normal World Mode
        shader.setUniform("uModel", model);
        
        // 3. Lookup texture (Side face)
        int texIndex = atlas.getIndex(block.name().toLowerCase(), com.voxelengine.utils.Direction.SOUTH);
        shader.setUniform("uOverrideTexIndex", (float)texIndex);
        
        // 4. Render
        itemMesh.render();
        
        // 5. Reset / Cleanup
        shader.setUniform("uOverrideTexIndex", -1.0f);
        shader.unbind();
    }

    public void cleanup() {
        shader.cleanup();
        for (ChunkRenderData m : chunkMeshes.values()) m.cleanup();
        if (itemMesh != null) itemMesh.cleanup();
    }
    
    private float[] generateCubeVertices() {
        // 6 faces, 2 triangles each, 3 verts per tri = 36 verts
        // 8 floats per vert = 288 floats
        float[] v = new float[288];
        int i = 0;
        
        float l = 15.0f/15.0f; // Full light
        float t = 0.0f; // Dummy texture
        
        // UV MAPPING FIX:
        // Texture V=0 is Bottom, V=1 is Top in the mesh logic below.
        // But Fragment Shader does "1.0 - UV.y".
        // So:
        // Sending 0 -> Shader makes it 1 (Top of texture) -> Correct for Top Vertex
        // Sending 1 -> Shader makes it 0 (Bottom of texture) -> Correct for Bottom Vertex
        
        // FRONT (Z+)
        // v0(BL), v1(BR), v2(TR), v3(TL)
        i = addQuad(v, i, -0.5f, -0.5f, 0.5f,  0.5f, -0.5f, 0.5f,  0.5f, 0.5f, 0.5f,  -0.5f, 0.5f, 0.5f, l, t);
        // BACK (Z-)
        i = addQuad(v, i, 0.5f, -0.5f, -0.5f,  -0.5f, -0.5f, -0.5f,  -0.5f, 0.5f, -0.5f,  0.5f, 0.5f, -0.5f, l, t);
        // TOP (Y+)
        i = addQuad(v, i, -0.5f, 0.5f, 0.5f,  0.5f, 0.5f, 0.5f,  0.5f, 0.5f, -0.5f,  -0.5f, 0.5f, -0.5f, l, t);
        // BOTTOM (Y-)
        i = addQuad(v, i, -0.5f, -0.5f, -0.5f,  0.5f, -0.5f, -0.5f,  0.5f, -0.5f, 0.5f,  -0.5f, -0.5f, 0.5f, l, t);
        // RIGHT (X+)
        i = addQuad(v, i, 0.5f, -0.5f, 0.5f,  0.5f, -0.5f, -0.5f,  0.5f, 0.5f, -0.5f,  0.5f, 0.5f, 0.5f, l, t);
        // LEFT (X-)
        i = addQuad(v, i, -0.5f, -0.5f, -0.5f,  -0.5f, -0.5f, 0.5f,  -0.5f, 0.5f, 0.5f,  -0.5f, 0.5f, -0.5f, l, t);
        
        return v;
    }
    
    private int addQuad(float[] v, int i, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float l, float t) {
        // v0 (Bottom Left) -> UV(0, 1) -> Shader(0, 0) -> Texture Bottom Left
        // v2 (Top Right)   -> UV(1, 0) -> Shader(1, 1) -> Texture Top Right
        
        // Tri 1
        v[i++] = x0; v[i++] = y0; v[i++] = z0; v[i++] = 0; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x1; v[i++] = y1; v[i++] = z1; v[i++] = 1; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x2; v[i++] = y2; v[i++] = z2; v[i++] = 1; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = t;
        // Tri 2
        v[i++] = x0; v[i++] = y0; v[i++] = z0; v[i++] = 0; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x2; v[i++] = y2; v[i++] = z2; v[i++] = 1; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x3; v[i++] = y3; v[i++] = z3; v[i++] = 0; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = t;
        return i;
    }
}