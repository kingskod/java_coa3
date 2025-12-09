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

public class ContainerScreen {

    private final Mesh quadMesh;
    private int bgTexture;
    private final Inventory inventory;
    private final TextureAtlas atlas;
    private final FontRenderer fontRenderer;

    public ContainerScreen(Inventory inventory, TextureAtlas atlas, FontRenderer fontRenderer) {
        this.inventory = inventory;
        this.atlas = atlas;
        this.fontRenderer = fontRenderer;
        
        // Standard Quad 0..1
        float[] vertices = new float[] {
            0, 1, 0,  0, 0,  1, 1, 0,
            0, 0, 0,  0, 1,  1, 1, 0,
            1, 0, 0,  1, 1,  1, 1, 0,
            1, 1, 0,  1, 0,  1, 1, 0,
            0, 1, 0,  0, 0,  1, 1, 0,
            1, 0, 0,  1, 1,  1, 1, 0
        };
        this.quadMesh = new Mesh(vertices);
        
        this.bgTexture = loadTexture("assets/ui/tab_items.png");
    }

    public void render(Shader shader, int winWidth, int winHeight, Matrix4f ortho) {
        float scale = 2.0f;
        float uiWidth = 176 * scale;
        float uiHeight = 166 * scale;
        float startX = (winWidth - uiWidth) / 2.0f;
        float startY = (winHeight - uiHeight) / 2.0f;

        // 1. Background
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, bgTexture);
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", -1.0f); // Direct Mode (Full Bright)
        shader.setUniform("uColorMod", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f)); // Pure White
        
        Matrix4f model = new Matrix4f().translate(startX, startY, 0).scale(uiWidth, uiHeight, 1);
        shader.setUniform("uModel", model);
        quadMesh.render();
        
        // 2. Items
        // Bind Atlas
        glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
        shader.setUniform("uUVScale", 1.0f / 16.0f);
        
        // Grid Offsets (Based on Minecraft texture coords)
        float gridX = startX + (7 * scale);
        // Manual tuning for bottom-left origin
        float hotbarY = startY + (8 * scale); // Near bottom
        float storageY = startY + (84 * scale); // Middle section
        
        // Hotbar (Slots 0-8)
        renderGrid(shader, 0, 9, gridX, hotbarY, scale);
        
        // Storage (Slots 9-35)
        renderGrid(shader, 9, 36, gridX, storageY, scale);
        
        // Render Counts (Text Pass)
        renderCounts(0, 9, gridX, hotbarY, scale, ortho);
        renderCounts(9, 36, gridX, storageY, scale, ortho);
    }
    
    private void renderGrid(Shader shader, int startSlot, int endSlot, float startX, float startY, float scale) {
        float slotSize = 18 * scale;
        float itemSize = 16 * scale;
        float padding = (slotSize - itemSize) / 2;
        
        int col = 0;
        int row = 0;
        
        // Calculate rows based on count
        // For storage (27 items), we want 3 rows of 9.
        // We render bottom-up visually? No, slot 9 is top-left.
        // OpenGL 0,0 is bottom-left.
        // Row 0 (Slot 9-17) should be at Top of grid.
        
        for (int i = startSlot; i < endSlot; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                // Pos
                // Logic: Higher row index = Lower Y position
                float x = startX + (col * slotSize) + padding;
                float y = startY + ((2 - row) * slotSize) + padding; // Invert row for storage
                
                if (endSlot == 9) {
                    // Hotbar is single row
                    y = startY + padding;
                }
                
                // Texture
                int index = atlas.getIndex(stack.getBlock().name().toLowerCase(), Direction.SOUTH);
                float tx = index % 16;
                float ty = index / 16;
                shader.setUniform("uUVOffset", new Vector2f(tx / 16.0f, ty / 16.0f));
                
                Matrix4f model = new Matrix4f().translate(x, y, 0).scale(itemSize, itemSize, 1);
                shader.setUniform("uModel", model);
                quadMesh.render();
            }
            
            col++;
            if (col >= 9) {
                col = 0;
                row++;
            }
        }
    }
    
    private void renderCounts(int startSlot, int endSlot, float startX, float startY, float scale, Matrix4f ortho) {
        float slotSize = 18 * scale;
        int col = 0;
        int row = 0;
        
        for (int i = startSlot; i < endSlot; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && stack.getCount() > 1) {
                float x = startX + (col * slotSize);
                float y = startY + ((2 - row) * slotSize);
                if (endSlot == 9) y = startY;
                
                fontRenderer.drawText(String.valueOf(stack.getCount()), x + 2, y + 2, 1.0f, ortho);
            }
            col++;
            if (col >= 9) {
                col = 0;
                row++;
            }
        }
    }

    private int loadTexture(String path) {
        int texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);
        // Robust load logic (copy from UIManager)
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
            }
        }
        return texId;
    }
    
    public void cleanup() {
        quadMesh.cleanup();
    }
}