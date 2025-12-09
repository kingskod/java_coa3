package com.voxelengine.render;

import com.voxelengine.world.Chunk;
import com.voxelengine.world.World;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

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
    private Mesh itemMesh; 
    
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
        shader.setUniform("uCameraPos", camera.getPosition());
        shader.setUniform("uOverrideTexIndex", -1.0f);

        // --- Day/Night Cycle Logic ---
        long time = world.getTime();
        float timeFactor = (float)time / 24000.0f; // 0.0 to 1.0
        
        float daylight = (float)Math.cos(timeFactor * Math.PI * 2) * 0.4f + 0.6f;
        daylight = Math.max(0.2f, daylight);
        shader.setUniform("uDaylightFactor", daylight);

        Vector3f dayColor = new Vector3f(0.5f, 0.7f, 1.0f);
        Vector3f nightColor = new Vector3f(0.05f, 0.05f, 0.1f);
        Vector3f skyColor = new Vector3f();
        dayColor.lerp(nightColor, 1.0f - (daylight-0.2f)/0.8f, skyColor);
        shader.setUniform("uSkyColor", skyColor);
        
        glClearColor(skyColor.x, skyColor.y, skyColor.z, 1.0f);

        Collection<Chunk> activeChunks = world.getChunkManager().getLoadedChunks();

        Iterator<Map.Entry<Chunk, ChunkRenderData>> it = chunkMeshes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Chunk, ChunkRenderData> entry = it.next();
            if (!activeChunks.contains(entry.getKey())) {
                entry.getValue().cleanup();
                it.remove();
            }
        }

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
    
    public void renderItemCube(com.voxelengine.world.Block block, Matrix4f model) {
        shader.bind();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", 1.0f);
        shader.setUniform("uModel", model);
        
        int texIndex = atlas.getIndex(block.name().toLowerCase(), com.voxelengine.utils.Direction.SOUTH);
        shader.setUniform("uOverrideTexIndex", (float)texIndex);
        
        itemMesh.render();
        
        shader.setUniform("uOverrideTexIndex", -1.0f);
        shader.unbind();
    }

    public void cleanup() {
        shader.cleanup();
        for (ChunkRenderData m : chunkMeshes.values()) m.cleanup();
        if (itemMesh != null) itemMesh.cleanup();
    }
    
    private float[] generateCubeVertices() {
        float[] v = new float[288];
        int i = 0;
        float l = 1.0f;
        float t = -1.0f;
        i = addQuad(v, i, -0.5f, -0.5f, 0.5f,  0.5f, -0.5f, 0.5f,  0.5f, 0.5f, 0.5f,  -0.5f, 0.5f, 0.5f, l, t);
        i = addQuad(v, i, 0.5f, -0.5f, -0.5f,  -0.5f, -0.5f, -0.5f,  -0.5f, 0.5f, -0.5f,  0.5f, 0.5f, -0.5f, l, t);
        i = addQuad(v, i, -0.5f, 0.5f, 0.5f,  0.5f, 0.5f, 0.5f,  0.5f, 0.5f, -0.5f,  -0.5f, 0.5f, -0.5f, l, t);
        i = addQuad(v, i, -0.5f, -0.5f, -0.5f,  0.5f, -0.5f, -0.5f,  0.5f, -0.5f, 0.5f,  -0.5f, -0.5f, 0.5f, l, t);
        i = addQuad(v, i, 0.5f, -0.5f, 0.5f,  0.5f, -0.5f, -0.5f,  0.5f, 0.5f, -0.5f,  0.5f, 0.5f, 0.5f, l, t);
        i = addQuad(v, i, -0.5f, -0.5f, -0.5f,  -0.5f, -0.5f, 0.5f,  -0.5f, 0.5f, 0.5f,  -0.5f, 0.5f, -0.5f, l, t);
        return v;
    }
    
    private int addQuad(float[] v, int i, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float l, float t) {
        v[i++] = x0; v[i++] = y0; v[i++] = z0; v[i++] = 0; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x1; v[i++] = y1; v[i++] = z1; v[i++] = 1; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x2; v[i++] = y2; v[i++] = z2; v[i++] = 1; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x0; v[i++] = y0; v[i++] = z0; v[i++] = 0; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x2; v[i++] = y2; v[i++] = z2; v[i++] = 1; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = t;
        v[i++] = x3; v[i++] = y3; v[i++] = z3; v[i++] = 0; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = t;
        return i;
    }
}