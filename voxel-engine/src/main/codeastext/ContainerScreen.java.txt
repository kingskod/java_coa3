package com.voxelengine.ui;

import com.voxelengine.render.Mesh;
import com.voxelengine.render.Shader;
import com.voxelengine.render.TextureAtlas;
import com.voxelengine.utils.Direction;
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
    
    // --- Interaction ---
    
    public void handleMouseClick(float mouseX, float mouseY, int winWidth, int winHeight, int button) {
        // Calculate Layout same as render
        float scale = 2.0f;
        float uiWidth = 176 * scale;
        float uiHeight = 166 * scale;
        float startX = (winWidth - uiWidth) / 2.0f;
        float startY = (winHeight - uiHeight) / 2.0f;
        
        // Check slots
        // Hotbar (0-8)
        // Texture Y=142. OpenGL Y (bottom-up) = 166 - 142 - 16 = 8.
        float hotbarY = startY + (8 * scale);
        float gridX = startX + (7 * scale);
        int clickedSlot = checkGridClick(mouseX, mouseY, 0, 9, gridX, hotbarY, scale);
        
        // Storage (9-35)
        // Texture Y=18. OpenGL Y = 166 - 18 - 16 = 132.
        // Wait, render loop uses logic: 3 rows.
        // Row 0 is top. Y = startY + (95 * scale)? No, let's match Render Logic.
        // Render says: Top row is highest Y. 
        // 166 (total) - 18 (top margin) - 16 (slot) - (18 * 2) (rows) = ...
        // Let's deduce from visual: Storage block starts at ~middle.
        
        // Correct Y Calculation matching Texture:
        // Top Row (9-17) is at Texture Y=18.
        // Bottom-up: Y = startY + (166 - 18 - 16)*scale = startY + 132*scale.
        float storageTopY = startY + (132 * scale);
        
        if (clickedSlot == -1) {
            clickedSlot = checkGridClick(mouseX, mouseY, 9, 36, gridX, storageTopY, scale);
        }
        
        if (clickedSlot != -1) {
            performClick(clickedSlot, button);
        }
    }
    
    private int checkGridClick(float mx, float my, int startSlot, int endSlot, float gridX, float startY, float scale) {
        float slotSize = 18 * scale;
        
        // We iterate exactly as the render loop does to find the match
        int col = 0;
        int row = 0;
        
        for (int i = startSlot; i < endSlot; i++) {
            // Hotbar is single row, handled by loop logic naturally if startY correct
            // For storage, we step DOWN (row++ means Y decreases)
            
            float x = gridX + (col * slotSize);
            float y = startY - (row * slotSize);
            
            // Note: Hotbar loop in Render uses specific logic.
            // If checking hotbar (0-9), startY is the row itself.
            // If checking storage (9-36), startY is top row.
            
            // Hitbox Check
            if (mx >= x && mx <= x + slotSize && my >= y && my <= y + slotSize) {
                return i;
            }
            
            col++;
            if (col >= 9) {
                col = 0;
                row++;
            }
        }
        return -1;
    }
    
    private void performClick(int slotIndex, int button) {
        ItemStack held = inventory.getHeldStack();
        ItemStack clicked = inventory.getStack(slotIndex);
        
        // Left Click (0)
        if (button == 0) {
            if (held.isEmpty()) {
                // Pickup
                if (!clicked.isEmpty()) {
                    inventory.setHeldStack(clicked);
                    inventory.setSlot(slotIndex, new ItemStack(com.voxelengine.world.Block.AIR, 0));
                }
            } else {
                // Place / Swap
                if (clicked.isEmpty()) {
                    // Place all
                    inventory.setSlot(slotIndex, held);
                    inventory.setHeldStack(new ItemStack(com.voxelengine.world.Block.AIR, 0));
                } else if (clicked.getBlock() == held.getBlock()) {
                    // Merge
                    int remaining = clicked.merge(held);
                    if (remaining == 0) {
                        inventory.setHeldStack(new ItemStack(com.voxelengine.world.Block.AIR, 0));
                    }
                } else {
                    // Swap
                    inventory.setSlot(slotIndex, held);
                    inventory.setHeldStack(clicked);
                }
            }
        }
        // Right Click Logic (Split) can be added here
    }

    // --- Rendering ---

    public void render(Shader shader, int winWidth, int winHeight, Matrix4f ortho, float mouseX, float mouseY) {
        float scale = 2.0f;
        float uiWidth = 176 * scale;
        float uiHeight = 166 * scale;
        float startX = (winWidth - uiWidth) / 2.0f;
        float startY = (winHeight - uiHeight) / 2.0f;

        // 1. Background
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, bgTexture);
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", -1.0f); 
        shader.setUniform("uColorMod", new Vector4f(1,1,1,1));
        
        Matrix4f model = new Matrix4f().translate(startX, startY, 0).scale(uiWidth, uiHeight, 1);
        shader.setUniform("uModel", model);
        quadMesh.render();
        
        // 2. Items
        glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
        shader.setUniform("uUVScale", 1.0f / 16.0f);
        
        float gridX = startX + (7 * scale);
        
        // Visual offsets aligned with Texture
        float hotbarY = startY + (8 * scale);  // Matches Texture Y=142 approx
        float storageY = startY + (132 * scale); // Matches Texture Y=18 approx
        
        renderGrid(shader, 0, 9, gridX, hotbarY, scale);
        renderGrid(shader, 9, 36, gridX, storageY, scale);
        
        renderCounts(0, 9, gridX, hotbarY, scale, ortho);
        renderCounts(9, 36, gridX, storageY, scale, ortho);
        
        // 3. Render Held Item (Cursor)
        ItemStack held = inventory.getHeldStack();
        if (!held.isEmpty()) {
            glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
            shader.setUniform("uUVScale", 1.0f / 16.0f);
            
            float itemSize = 16 * scale;
            
            int index = atlas.getIndex(held.getBlock().name().toLowerCase(), Direction.SOUTH);
            shader.setUniform("uUVOffset", new Vector2f((index%16)/16.0f, (index/16)/16.0f));
            
            // Center on mouse
            Matrix4f cursorModel = new Matrix4f()
                .translate(mouseX - itemSize/2, mouseY - itemSize/2, 0)
                .scale(itemSize, itemSize, 1);
                
            shader.setUniform("uModel", cursorModel);
            quadMesh.render();
            
            if (held.getCount() > 1) {
                 fontRenderer.drawText(String.valueOf(held.getCount()), mouseX, mouseY, 1.0f, ortho);
            }
        }
    }
    
    private void renderGrid(Shader shader, int startSlot, int endSlot, float startX, float startY, float scale) {
        float slotSize = 18 * scale;
        float itemSize = 16 * scale;
        float padding = (slotSize - itemSize) / 2;
        
        int col = 0;
        int row = 0;
        
        for (int i = startSlot; i < endSlot; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                float x = startX + (col * slotSize) + padding;
                float y = startY - (row * slotSize) + padding;
                
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
                float y = startY - (row * slotSize);
                fontRenderer.drawText(String.valueOf(stack.getCount()), x + 2, y + 2, 1.0f, ortho);
            }
            col++;
            if (col >= 9) { col = 0; row++; }
        }
    }

    private int loadTexture(String path) {
        // (Use robust load from previous batches)
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
            }
        }
        return texId;
    }
    
    public void cleanup() {
        quadMesh.cleanup();
    }
}