package com.voxelengine.ui;

import com.voxelengine.render.Mesh;
import com.voxelengine.render.Shader;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.io.File;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class FontRenderer {

    private final int textureId;
    private final Shader shader;
    private final Mesh quadMesh;

    public FontRenderer(Shader sharedShader) {
        this.shader = sharedShader;
        this.textureId = loadTexture("assets/ui/font.png");
        
        // Single Quad (0..1 UVs)
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
        
        // Setup Shader for Text Mode
        shader.setUniform("uUVScale", 1.0f / 16.0f); // 1/16 scaling for atlas
        shader.setUniform("uColorMod", new org.joml.Vector4f(1.0f, 1.0f, 1.0f, 1.0f));

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
            
            // Calc offset
            shader.setUniform("uUVOffset", new Vector2f(col / 16.0f, row / 16.0f));
            
            Matrix4f model = new Matrix4f()
                .translate(x, y, 0)
                .scale(16 * scale, 16 * scale, 1);
            
            shader.setUniform("uModel", model);
            quadMesh.render();
            
            x += 10 * scale; // Kerning
        }
    }
    
    private int loadTexture(String path) {
        int texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);
        
        // Robust Path Logic
        String finalPath = path;
        File f = new File(path);
        if (!f.exists()) {
            String srcPath = "src/main/resources/" + path;
            if (new File(srcPath).exists()) finalPath = srcPath;
        }

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);
            
            ByteBuffer img = STBImage.stbi_load(finalPath, w, h, comp, 4);
            if (img != null) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(0), h.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, img);
                glGenerateMipmap(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                STBImage.stbi_image_free(img);
            } else {
                // Pink Fallback
                ByteBuffer pink = stack.malloc(4 * 16 * 16);
                for(int i=0; i<16*16; i++) pink.putInt(0xFF00FFFF);
                pink.flip();
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 16, 16, 0, GL_RGBA, GL_UNSIGNED_BYTE, pink);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            }
        }
        return texId;
    }
    
    public void cleanup() {
        quadMesh.cleanup();
    }
}