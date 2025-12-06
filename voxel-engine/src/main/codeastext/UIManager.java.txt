package com.voxelengine.ui;

import com.voxelengine.render.Mesh;
import com.voxelengine.render.Shader;
import com.voxelengine.utils.FileUtils;
import org.joml.Matrix4f;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 * Handles 2D Orthographic rendering for the HUD.
 */
public class UIManager {

    private final Shader shader;
    private final Mesh quadMesh;
    private final Matrix4f orthoProjection;
    private int crosshairTexture;
    
    private final Inventory inventory;

    public UIManager(Inventory inventory) {
        this.inventory = inventory;
        
        // Reuse the main shader
        this.shader = new Shader("assets/shaders/vertex.glsl", "assets/shaders/fragment.glsl");
        
        // Simple Quad with 8 floats per vertex to match Mesh class stride
        // x, y, z, u, v, skyLight, blockLight, texIndex
        float[] vertices = new float[] {
            -1,  1, 0,    0, 0,      1.0f, 1.0f,    0.0f,
            -1, -1, 0,    0, 16,     1.0f, 1.0f,    0.0f, 
             1, -1, 0,    16, 16,    1.0f, 1.0f,    0.0f,
             1,  1, 0,    16, 0,     1.0f, 1.0f,    0.0f,
             -1, 1, 0,    0, 0,      1.0f, 1.0f,    0.0f,
             1, -1, 0,    16, 16,    1.0f, 1.0f,    0.0f
        };
        
        // Fix UVs to 256 for the shader logic
        // Stride is 8
        for(int i=0; i<vertices.length; i+=8) {
            if(vertices[i+3] > 0) vertices[i+3] = 256;
            if(vertices[i+4] > 0) vertices[i+4] = 256;
        }

        this.quadMesh = new Mesh(vertices);
        this.orthoProjection = new Matrix4f().ortho(0, 1280, 0, 720, -1, 1);
        
        loadUITextures();
    }
    
    private void loadUITextures() {
        crosshairTexture = loadTexture("assets/ui/crosshair.png");
    }

    public static int loadTextureHelper(String path) {
        // Helper redirect if needed, but better to implement locally or use utils
        // This is just a stub wrapper if other classes call it static
        // Ideally removed in clean code, but kept if you pasted previous buggy FontRenderer
        return 0; 
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
                STBImage.stbi_image_free(img);
            }
        }
        return texId;
    }

    public void render(int windowWidth, int windowHeight) {
        orthoProjection.identity().ortho(0, windowWidth, 0, windowHeight, -1, 1);
        
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        shader.bind();
        shader.setUniform("uProjection", orthoProjection);
        shader.setUniform("uView", new Matrix4f());
        
        // 1. Draw Crosshair
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, crosshairTexture);
        shader.setUniform("uTexture", 0);
        
        float centerX = windowWidth / 2.0f;
        float centerY = windowHeight / 2.0f;
        float size = 16.0f;
        
        Matrix4f model = new Matrix4f().translate(centerX, centerY, 0).scale(size, size, 1);
        shader.setUniform("uModel", model);
        quadMesh.render();
        
        // 2. Draw Hotbar
        float slotSize = 40;
        float startX = centerX - (4.5f * slotSize);
        float y = 40;
        
        glBindTexture(GL_TEXTURE_2D, 0); 
        
        for (int i = 0; i < 9; i++) {
            boolean isSelected = (i == inventory.getSelectedSlot());
            Matrix4f slotModel = new Matrix4f()
                .translate(startX + i * slotSize + (slotSize/2), y, 0)
                .scale(slotSize/2 - 2, slotSize/2 - 2, 1);
            shader.setUniform("uModel", slotModel);
            
            if (isSelected) {
                shader.setUniform("uColorMod", new org.joml.Vector3f(1, 1, 1)); 
            } else {
                shader.setUniform("uColorMod", new org.joml.Vector3f(0.5f, 0.5f, 0.5f)); 
            }
            
            glBindTexture(GL_TEXTURE_2D, crosshairTexture); 
            quadMesh.render();
        }
        
        shader.setUniform("uColorMod", new org.joml.Vector3f(1, 1, 1)); 
        shader.unbind();
        glDisable(GL_BLEND);
        glEnable(GL_DEPTH_TEST);
    }
    
    public void cleanup() {
        shader.cleanup();
        quadMesh.cleanup();
    }
}