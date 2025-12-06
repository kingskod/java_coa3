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
    
    // Store container of meshes
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
    }

    public void render(World world, Camera camera) {
        shader.bind();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
        shader.setUniform("uTexture", 0);
        shader.setUniform("uProjection", camera.getProjectionMatrix());
        shader.setUniform("uView", camera.getViewMatrix());
        shader.setUniform("uUVScale", 1.0f);
        shader.setUniform("uUVOffset", new Vector2f(0, 0));

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
        glDepthMask(false); // Don't write depth for water to allow seeing through layers
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

    public void cleanup() {
        shader.cleanup();
        for (ChunkRenderData m : chunkMeshes.values()) m.cleanup();
    }
}