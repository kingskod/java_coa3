package com.voxelengine.render;

import com.voxelengine.world.Chunk;
import com.voxelengine.world.ChunkManager;
import com.voxelengine.world.World;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Renderer {

    private final Shader shader;
    private final TextureAtlas atlas;
    private final GreedyMesher mesher;
    private final Map<Chunk, Mesh> chunkMeshes = new HashMap<>();

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

        java.util.Collection<Chunk> activeChunks = world.getChunkManager().getLoadedChunks();

        // 1. Cleanup Stale Meshes (Memory Leak Fix)
        // We iterate the CURRENT existing meshes. If a mesh belongs to a chunk
        // that is NO LONGER in the activeChunks list, we destroy it.
        Iterator<Map.Entry<Chunk, Mesh>> it = chunkMeshes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Chunk, Mesh> entry = it.next();
            if (!activeChunks.contains(entry.getKey())) {
                entry.getValue().cleanup(); // Free VRAM (GLDeleteBuffers)
                it.remove(); // Remove from Java Map
            }
        }

        // 2. Render Active
        for (Chunk chunk : activeChunks) {
            Matrix4f model = new Matrix4f().translate(chunk.chunkX * 16, 0, chunk.chunkZ * 16);
            renderChunk(chunk, model);
        }

        shader.unbind();
    }

    public void renderChunk(Chunk chunk, Matrix4f modelMatrix) {
        if (chunk.isDirty || !chunkMeshes.containsKey(chunk)) {
            Mesh mesh = mesher.generateMesh(chunk, atlas);
            // clean old mesh if it existed (e.g. block update)
            if (chunkMeshes.containsKey(chunk)) {
                chunkMeshes.get(chunk).cleanup();
            }
            chunkMeshes.put(chunk, mesh);
            chunk.isDirty = false;
        }

        shader.setUniform("uModel", modelMatrix);
        chunkMeshes.get(chunk).render();
    }

    public void cleanup() {
        shader.cleanup();
        for (Mesh m : chunkMeshes.values()) m.cleanup();
    }
}