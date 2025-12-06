package com.voxelengine.ui;

import com.voxelengine.render.Mesh;
import com.voxelengine.render.Shader;
import com.voxelengine.render.TextureAtlas;
import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.io.File;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class UIManager {

    private final Shader shader;
    private final Mesh quadMesh;
    private final Matrix4f orthoProjection;
    
    private int crosshairTexture;
    private int hotbarTexture;
    private int selectorTexture;
    
    private final Inventory inventory;
    private final TextureAtlas textureAtlas;
    private final FontRenderer fontRenderer; // NEW

    public UIManager(Inventory inventory, TextureAtlas textureAtlas) {
        this.inventory = inventory;
        this.textureAtlas = textureAtlas;
        this.shader = new Shader("assets/shaders/vertex.glsl", "assets/shaders/fragment.glsl");
        
        // Init Font Renderer
        this.fontRenderer = new FontRenderer(this.shader);
        
        // Standard Quad (0..1 UVs)
        float[] vertices = new float[] {
            -1,  1, 0,    0, 0,      1.0f, 1.0f,    0.0f,
            -1, -1, 0,    0, 1,      1.0f, 1.0f,    0.0f, 
             1, -1, 0,    1, 1,      1.0f, 1.0f,    0.0f,
             1,  1, 0,    1, 0,      1.0f, 1.0f,    0.0f,
             -1, 1, 0,    0, 0,      1.0f, 1.0f,    0.0f,
             1, -1, 0,    1, 1,      1.0f, 1.0f,    0.0f
        };

        this.quadMesh = new Mesh(vertices);
        this.orthoProjection = new Matrix4f().ortho(0, 1280, 0, 720, -1, 1);
        
        loadUITextures();
    }
    
    private void loadUITextures() {
        crosshairTexture = loadTexture("assets/ui/crosshair.png");
        hotbarTexture = loadTexture("assets/ui/hotbar.png");
        selectorTexture = loadTexture("assets/ui/selector.png");
    }

    public void render(int windowWidth, int windowHeight) {
        orthoProjection.identity().ortho(0, windowWidth, 0, windowHeight, -1, 1);
        
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        shader.bind();
        shader.setUniform("uProjection", orthoProjection);
        shader.setUniform("uView", new Matrix4f());
        
        float centerX = windowWidth / 2.0f;
        float centerY = windowHeight / 2.0f;
        
        // ---------------------------------------------------------
        // 1. Crosshair
        // ---------------------------------------------------------
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, crosshairTexture);
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", -1.0f); // Direct Mode
        shader.setUniform("uColorMod", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f)); 
        
        float chSize = 16.0f;
        Matrix4f chModel = new Matrix4f().translate(centerX, windowHeight / 2.0f, 0).scale(chSize, chSize, 1);
        shader.setUniform("uModel", chModel);
        quadMesh.render();
        
        // ---------------------------------------------------------
        // 2. Hotbar Background
        // ---------------------------------------------------------
        glBindTexture(GL_TEXTURE_2D, hotbarTexture);
        
        float scale = 2.0f; 
        float barWidth = 182 * scale;
        float barHeight = 22 * scale;
        float barY = 10 + (barHeight / 2.0f);
        
        Matrix4f hotbarModel = new Matrix4f()
            .translate(centerX, barY, 0)
            .scale(barWidth / 2.0f, barHeight / 2.0f, 1);
            
        shader.setUniform("uModel", hotbarModel);
        quadMesh.render();

        // ---------------------------------------------------------
        // 3. Items (Icons)
        // ---------------------------------------------------------
        glBindTexture(GL_TEXTURE_2D, textureAtlas.getTextureId());
        shader.setUniform("uUVScale", 1.0f / 16.0f); // Atlas Mode
        
        float startX = centerX - (barWidth / 2.0f) + (3 * scale);
        float slotStride = 20 * scale;
        float itemY = barY;
        
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            Block item = stack.getBlock();
            
            if (item != Block.AIR) {
                int index = textureAtlas.getIndex(item.name().toLowerCase(), Direction.SOUTH);
                float col = index % 16;
                float row = index / 16;
                shader.setUniform("uUVOffset", new Vector2f(col / 16.0f, row / 16.0f));
                
                float itemSize = 16 * scale; 
                float itemX = startX + (i * slotStride) + (itemSize / 2.0f);
                
                Matrix4f iconModel = new Matrix4f()
                    .translate(itemX, itemY, 0)
                    .scale(itemSize / 2.0f, itemSize / 2.0f, 1);
                
                shader.setUniform("uModel", iconModel);
                quadMesh.render();
            }
        }

        // ---------------------------------------------------------
        // 4. Selector
        // ---------------------------------------------------------
        glBindTexture(GL_TEXTURE_2D, selectorTexture);
        shader.setUniform("uUVScale", -1.0f); // Direct Mode
        shader.setUniform("uUVOffset", new Vector2f(0, 0));
        
        int selected = inventory.getSelectedSlot();
        float selSize = 24 * scale;
        float selX = (centerX - (barWidth / 2.0f)) + (selected * 20 * scale) + (selSize / 2.0f) - (1 * scale);
        
        Matrix4f selModel = new Matrix4f()
            .translate(selX, barY, 0)
            .scale(selSize / 2.0f, selSize / 2.0f, 1);
            
        shader.setUniform("uModel", selModel);
        quadMesh.render();
        
        // ---------------------------------------------------------
        // 5. Stack Counts (Text)
        // ---------------------------------------------------------
        // We render this last so numbers appear on top of everything
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getCount() > 1) {
                String text = String.valueOf(stack.getCount());
                
                float itemSize = 16 * scale; 
                // Position text at bottom-right of slot
                float itemX = startX + (i * slotStride) + (itemSize / 2.0f);
                // Adjust position slightly
                float textX = itemX; 
                float textY = itemY - (itemSize * 0.2f);
                
                // Render text with scale 1.0 (or smaller if needed)
                fontRenderer.drawText(text, textX, textY, 1.0f, orthoProjection);
            }
        }
        
        shader.unbind();
        glDepthMask(true);
        glEnable(GL_DEPTH_TEST);
        glDisable(GL_BLEND);
    }

    private int loadTexture(String path) {
        int texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);
        
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
        shader.cleanup();
        quadMesh.cleanup();
        fontRenderer.cleanup(); // Clean font mesh
    }
}