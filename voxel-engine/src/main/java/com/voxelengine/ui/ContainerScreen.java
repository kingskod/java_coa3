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
    
    // Texture Constants (tab_items.png)
    private static final int TEX_WIDTH = 195;
    private static final int TEX_HEIGHT = 136;
    private static final int SLOT_SIZE = 15; // 16px item + borders
    private static final int GRID_START_X = 7;
    private static final int STORAGE_START_Y = 17; // From Top
    private static final int HOTBAR_START_Y = 111; // From Top

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
    
    public void handleMouseClick(float mouseX, float mouseY, int winWidth, int winHeight, int button) {
        float scale = 2.0f;
        
        // Calculate UI Screen Position (Bottom-Left corner of the UI texture)
        // Center the 176x166 texture
        float uiW = TEX_WIDTH * scale;
        float uiH = TEX_HEIGHT * scale;
        float uiX = (winWidth - uiW) / 2.0f;
        float uiY = (winHeight - uiH) / 2.0f;

        // Check Hotbar (0-9)
        int slot = checkSlots(mouseX, mouseY, 0, 9, uiX, uiY, scale, GRID_START_X, HOTBAR_START_Y);
        if (slot == -1) {
            // Check Storage (9-36)
            slot = checkSlots(mouseX, mouseY, 9, 36, uiX, uiY, scale, GRID_START_X, STORAGE_START_Y);
        }
        
        if (slot != -1) {
            performClick(slot, button);
        }
    }
    
    private int checkSlots(float mx, float my, int startSlot, int endSlot, 
                           float uiX, float uiY, float scale, 
                           int texGridX, int texGridY) {
        
        int col = 0;
        int row = 0;
        
        for (int i = startSlot; i < endSlot; i++) {
            // Calculate Slot Position in Window Coords
            // texGridY is from TOP of texture.
            // OpenGL Y is Bottom-Up.
            // slotY = uiY + (TEX_HEIGHT - texGridY - SLOT_SIZE) * scale - (row * SLOT_SIZE * scale)
            // Actually:
            // Top of UI is uiY + uiH.
            // Slot Top is (uiY + uiH) - (texGridY * scale) - (row * stride).
            // Slot Bottom is Slot Top - (SLOT_SIZE * scale).
            
            float topY = (uiY + TEX_HEIGHT * scale) - (texGridY * scale) - (row * SLOT_SIZE * scale);
            float bottomY = topY - (SLOT_SIZE * scale);
            
            float leftX = uiX + (texGridX * scale) + (col * SLOT_SIZE * scale);
            float rightX = leftX + (SLOT_SIZE * scale);
            
            if (mx >= leftX && mx < rightX && my >= bottomY && my < topY) {
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
        
        if (button == 0) {
            if (held.isEmpty()) {
                if (!clicked.isEmpty()) {
                    inventory.setHeldStack(clicked);
                    inventory.setSlot(slotIndex, new ItemStack(com.voxelengine.world.Block.AIR, 0));
                }
            } else {
                if (clicked.isEmpty()) {
                    inventory.setSlot(slotIndex, held);
                    inventory.setHeldStack(new ItemStack(com.voxelengine.world.Block.AIR, 0));
                } else if (clicked.getBlock() == held.getBlock()) {
                    int remainder = clicked.merge(held);
                    if (remainder == 0) {
                        inventory.setHeldStack(new ItemStack(com.voxelengine.world.Block.AIR, 0));
                    }
                } else {
                    inventory.setSlot(slotIndex, held);
                    inventory.setHeldStack(clicked);
                }
            }
        }
    }

    public void render(Shader shader, int winWidth, int winHeight, Matrix4f ortho, float mouseX, float mouseY) {
        float scale = 2.0f;
        float uiW = TEX_WIDTH * scale;
        float uiH = TEX_HEIGHT * scale;
        float uiX = (winWidth - uiW) / 2.0f;
        float uiY = (winHeight - uiH) / 2.0f;

        // 1. Background
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, bgTexture);
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", -1.0f); 
        shader.setUniform("uColorMod", new Vector4f(1,1,1,1));
        
        Matrix4f model = new Matrix4f().translate(uiX, uiY, 0).scale(uiW, uiH, 1);
        shader.setUniform("uModel", model);
        quadMesh.render();
        
        // 2. Items
        glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
        shader.setUniform("uUVScale", 1.0f / 16.0f);
        
        // Render Hotbar Items
        renderGrid(shader, 0, 9, uiX, uiY, scale, GRID_START_X, HOTBAR_START_Y);
        // Render Storage Items
        renderGrid(shader, 9, 36, uiX, uiY, scale, GRID_START_X, STORAGE_START_Y);
        
        // Render Counts
        renderCounts(0, 9, uiX, uiY, scale, ortho, GRID_START_X, HOTBAR_START_Y);
        renderCounts(9, 36, uiX, uiY, scale, ortho, GRID_START_X, STORAGE_START_Y);
        
        // 3. Render Held Item
        ItemStack held = inventory.getHeldStack();
        if (!held.isEmpty()) {
            glBindTexture(GL_TEXTURE_2D, atlas.getTextureId());
            shader.setUniform("uUVScale", 1.0f / 16.0f);
            
            float itemSize = 16 * scale;
            
            String icon = held.getBlock().getItemIcon();
            if (icon == null) icon = held.getBlock().name().toLowerCase();
            int index = atlas.getIndex(icon, Direction.SOUTH);
            
            shader.setUniform("uUVOffset", new Vector2f((index%16)/16.0f, (index/16)/16.0f));
            
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
    
    private void renderGrid(Shader shader, int startSlot, int endSlot, 
                            float uiX, float uiY, float scale, 
                            int texGridX, int texGridY) {
        
        float itemSize = 16 * scale; // Items are 16x16
        // Slot is 18x18. Item is centered: 1px offset in texture.
        
        int col = 0;
        int row = 0;
        
        for (int i = startSlot; i < endSlot; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                // Determine screen position
                // Center of item in slot.
                // Tex Pos: texGridX + 1 + (col*18)
                
                float xOffset = (texGridX + 1 + (col * 18)) * scale;
                // Y calculation: From Top of UI down to Item Top
                // Item Top in Texture: texGridY + 1 + (row*18)
                float texItemTop = texGridY + 1 + (row * 18);
                // Convert to Bottom-Up OpenGL Y
                // itemY (bottom) = (uiY + uiH) - texItemTop * scale - itemSize
                float yOffset = (TEX_HEIGHT * scale) - (texItemTop * scale) - itemSize;
                
                float x = uiX + xOffset;
                float y = uiY + yOffset;
                
                String icon = stack.getBlock().getItemIcon();
                if (icon == null) icon = stack.getBlock().name().toLowerCase();
                
                int index = atlas.getIndex(icon, Direction.SOUTH);
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
    
    private void renderCounts(int startSlot, int endSlot, float uiX, float uiY, float scale, Matrix4f ortho, int texGridX, int texGridY) {
        int col = 0;
        int row = 0;
        for (int i = startSlot; i < endSlot; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && stack.getCount() > 1) {
                float xOffset = (texGridX + 1 + (col * 18)) * scale;
                float texItemTop = texGridY + 1 + (row * 18);
                float yOffset = (TEX_HEIGHT * scale) - (texItemTop * scale) - (16*scale);
                
                float x = uiX + xOffset;
                float y = uiY + yOffset;
                
                fontRenderer.drawText(String.valueOf(stack.getCount()), x + 2, y, 1.0f, ortho);
            }
            col++;
            if (col >= 9) { col = 0; row++; }
        }
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
            }
        }
        return texId;
    }
    
    public void cleanup() {
        quadMesh.cleanup();
    }
}