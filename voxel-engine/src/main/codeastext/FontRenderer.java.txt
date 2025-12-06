package com.voxelengine.ui;

import com.voxelengine.render.Mesh;
import com.voxelengine.render.Shader;
import org.joml.Matrix4f;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class FontRenderer {

    private final int textureId;
    private final Shader shader;
    private final Mesh quadMesh;

    public FontRenderer(Shader sharedShader) {
        this.shader = sharedShader;
        this.textureId = loadTexture("src/main/resources/assets/ui/font.png");
        
        // Single Quad with 8 floats per vertex (SkyLight and BlockLight added)
        float[] vertices = new float[] {
            0, 1, 0,  0, 0,  1, 1, 0,
            0, 0, 0,  0, 1,  1, 1, 0,
            1, 0, 0,  1, 1,  1, 1, 0,
            1, 1, 0,  1, 0,  1, 1, 0,
            0, 1, 0,  0, 0,  1, 1, 0,
            1, 0, 0,  1, 1,  1, 1, 0
        };
        this.quadMesh = new Mesh(vertices);
    }

    public void drawText(String text, float x, float y, float scale, Matrix4f projection) {
        glBindTexture(GL_TEXTURE_2D, textureId);
        shader.bind();
        shader.setUniform("uProjection", projection);
        shader.setUniform("uView", new Matrix4f()); 
        shader.setUniform("uTexture", 0);
        shader.setUniform("uColorMod", new org.joml.Vector3f(1, 1, 1));

        float startX = x;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\n') {
                y -= 16 * scale;
                x = startX;
                continue;
            }

            int ascii = (int) c;
            int col = ascii % 16;
            int row = ascii / 16;
            
            shader.setUniform("uUVOffset", new org.joml.Vector2f(col / 16.0f, row / 16.0f));
            shader.setUniform("uUVScale", 1.0f / 16.0f);
            
            Matrix4f model = new Matrix4f()
                .translate(x, y, 0)
                .scale(16 * scale, 16 * scale, 1);
            
            shader.setUniform("uModel", model);
            quadMesh.render();
            
            x += 10 * scale; 
        }
    }
    
    private int loadTexture(String path) {
        int texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);
            ByteBuffer img = STBImage.stbi_load(path, w, h, comp, 4);
            if (img != null) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(0), h.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, img);
                glGenerateMipmap(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                STBImage.stbi_image_free(img);
            } else {
                ByteBuffer red = stack.malloc(4);
                red.put((byte)255).put((byte)0).put((byte)0).put((byte)255).flip();
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 1, 1, 0, GL_RGBA, GL_UNSIGNED_BYTE, red);
            }
        }
        return texId;
    }
}