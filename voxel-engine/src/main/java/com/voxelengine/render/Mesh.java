package com.voxelengine.render;

import java.util.concurrent.atomic.AtomicInteger;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {

    private final int vaoId;
    private final int vboId;
    private final int vertexCount;
    
    // DEBUG: Track active meshes globally
    public static final AtomicInteger meshCount = new AtomicInteger(0);

    public Mesh(float[] positions) {
        meshCount.incrementAndGet();
        this.vertexCount = positions.length / 8;

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, positions, GL_STATIC_DRAW);

        int stride = 8 * 4;
        glVertexAttribPointer(0, 3, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, stride, 3 * 4);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, stride, 5 * 4);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(3, 1, GL_FLOAT, false, stride, 7 * 4);
        glEnableVertexAttribArray(3);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void render() {
        glBindVertexArray(vaoId);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glBindVertexArray(0);
    }

    public void cleanup() {
        // Prevent double-free
        if (glIsVertexArray(vaoId)) {
            glDeleteBuffers(vboId);
            glDeleteVertexArrays(vaoId);
            meshCount.decrementAndGet();
        }
    }
}