package com.voxelengine.render;

import static org.lwjgl.opengl.GL30.*;

public class Mesh {

    private final int vaoId;
    private final int vboId;
    private final int vertexCount;

    public Mesh(float[] positions) {
        this.vertexCount = positions.length / 8; // x,y,z, u,v, lightSky, lightBlock, texIndex

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, positions, GL_STATIC_DRAW);

        // Layout:
        // 0: Position (3 floats)
        // 1: UV (2 floats) -> Actually, we are using Greedy Meshing, so we usually pass raw bounds and calculate UV in shader or pass UV.
        // Let's use standard attributes:
        // Pos (3), UV (2), Light (2), TextureIndex (1) = 8 floats stride

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
        glDeleteBuffers(vboId);
        glDeleteVertexArrays(vaoId);
    }
}