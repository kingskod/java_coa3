package com.voxelengine.render;

import static org.lwjgl.opengl.GL30.*;

/**
 * Represents a GPU-side mesh data structure.
 * Manages Vertex Array Objects (VAO) and Vertex Buffer Objects (VBO).
 */
public class Mesh {

    private final int vaoId;
    private final int vboId;
    private final int vertexCount;

    /**
     * Uploads vertex data to the GPU and configures vertex attributes.
     *
     * @param positions Interleaved vertex data (Position, UV, Light, TextureIndex).
     */
    public Mesh(float[] positions) {
        this.vertexCount = positions.length / 8; // 8 floats per vertex

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, positions, GL_STATIC_DRAW);

        // Define Vertex Attributes
        // Stride = 8 floats * 4 bytes = 32 bytes
        int stride = 8 * 4;

        // 0: Position (3 floats)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(0);

        // 1: UV Coordinates (2 floats)
        glVertexAttribPointer(1, 2, GL_FLOAT, false, stride, 3 * 4);
        glEnableVertexAttribArray(1);

        // 2: Light Levels (Sky, Block) (2 floats)
        glVertexAttribPointer(2, 2, GL_FLOAT, false, stride, 5 * 4);
        glEnableVertexAttribArray(2);

        // 3: Texture Index (1 float)
        glVertexAttribPointer(3, 1, GL_FLOAT, false, stride, 7 * 4);
        glEnableVertexAttribArray(3);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    /**
     * Renders the mesh using GL_TRIANGLES.
     */
    public void render() {
        glBindVertexArray(vaoId);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glBindVertexArray(0);
    }

    /**
     * Deletes the VAO and VBO buffers.
     */
    public void cleanup() {
        glDeleteBuffers(vboId);
        glDeleteVertexArrays(vaoId);
    }
}