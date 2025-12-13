package com.voxelengine.ui;

import com.voxelengine.core.Input;
import com.voxelengine.entity.EntityManager;
import com.voxelengine.render.Mesh;
import com.voxelengine.render.Shader;
import com.voxelengine.render.TextureAtlas;
import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
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
    
    // Textures
    private int crosshairTexture;
    private int slotTexture;
    private int selectorTexture;
    private int hotbarTexture; 
    
    private final Inventory inventory;
    private final TextureAtlas textureAtlas;
    private final FontRenderer fontRenderer;
    private final CommandManager commandManager;
    private final ContainerScreen containerScreen;
    
    // UI State
    public boolean isChatOpen = false;
    public boolean isInventoryOpen = false;
    
    // Input Handling State
    private boolean mouseWasDown = false;
    private int currentWidth = 1280;
    private int currentHeight = 720;

    public UIManager(Inventory inventory, TextureAtlas textureAtlas, World world, EntityManager entityMgr) {
        this.inventory = inventory;
        this.textureAtlas = textureAtlas;
        this.shader = new Shader("assets/shaders/vertex.glsl", "assets/shaders/fragment.glsl");
        this.fontRenderer = new FontRenderer(this.shader);
        this.commandManager = new CommandManager(world, entityMgr, inventory);
        this.containerScreen = new ContainerScreen(inventory, textureAtlas, fontRenderer);
        
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
        slotTexture = loadTexture("assets/ui/slot.png");
        selectorTexture = loadTexture("assets/ui/selector.png");
        hotbarTexture = loadTexture("assets/ui/hotbar.png");
    }
    
    public void handleInput() {
        if (isChatOpen) {
            if (Input.isEnterPressed()) {
                String cmd = Input.getTypedString();
                commandManager.execute(cmd);
                isChatOpen = false;
                Input.stopTextInput();
                Input.setCursorLocked(true);
            }
        } else if (isInventoryOpen) {
            // Handle Clicking in Inventory
            if (Input.isMouseButtonDown(0)) { // Left Click
                if (!mouseWasDown) {
                    float mx = (float) Input.getMouseX();
                    // Convert top-down mouse to bottom-up OpenGL coords
                    float my = (float) currentHeight - (float) Input.getMouseY(); 
                    
                    containerScreen.handleMouseClick(mx, my, currentWidth, currentHeight, 0);
                    mouseWasDown = true;
                }
            } else {
                mouseWasDown = false;
            }
        } else {
            // Open Chat
            if (Input.isKeyDown(GLFW.GLFW_KEY_SLASH)) { 
                isChatOpen = true;
                Input.startTextInput();
                Input.setCursorLocked(false);
            }
        }
    }
    
    public void toggleInventory() {
        isInventoryOpen = !isInventoryOpen;
        Input.setCursorLocked(!isInventoryOpen);
        
        // If closing, ensure we drop held item or put it back?
        // For now, it stays on cursor, simple logic.
    }

    public void render(int windowWidth, int windowHeight) {
        this.currentWidth = windowWidth;
        this.currentHeight = windowHeight;
        
        orthoProjection.identity().ortho(0, windowWidth, 0, windowHeight, -1, 1);
        
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        shader.bind();
        shader.setUniform("uProjection", orthoProjection);
        shader.setUniform("uView", new Matrix4f());
        
        // ------------------------------------------------
        // 1. Full Screen Interfaces (Inventory)
        // ------------------------------------------------
        if (isInventoryOpen) {
            float mx = (float) Input.getMouseX();
            float my = (float) windowHeight - (float) Input.getMouseY();
            containerScreen.render(shader, windowWidth, windowHeight, orthoProjection, mx, my);
        }
        
        // 2. HUD (Hotbar, Crosshair)
        // FIX: Only render HUD if inventory is CLOSED
        if (!isInventoryOpen) {
            renderHUD(windowWidth, windowHeight);
        }
        
        // ------------------------------------------------
        // 3. Chat Overlay
        // ------------------------------------------------
        if (isChatOpen) {
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, slotTexture); 
            shader.setUniform("uTexture", 0);
            shader.setUniform("uUVScale", -1.0f);
            shader.setUniform("uColorMod", new Vector4f(0,0,0,0.5f));
            
            Matrix4f chatBg = new Matrix4f().translate(windowWidth/2.0f, 20, 0).scale(windowWidth, 20, 1);
            shader.setUniform("uModel", chatBg);
            quadMesh.render();
            
            fontRenderer.drawText(Input.getTypedString(), 10, 15, 1.0f, orthoProjection);
        }
        
        shader.unbind();
        glDepthMask(true);
        glEnable(GL_DEPTH_TEST);
        glDisable(GL_BLEND);
    }
    
    private void renderHUD(int w, int h) {
        float centerX = w / 2.0f;
        float centerY = h / 2.0f;
        
        // 1. Crosshair
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, crosshairTexture);
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", -1.0f); // Direct Mode (Full Bright)
        shader.setUniform("uColorMod", new Vector4f(1,1,1,1));
        
        shader.setUniform("uModel", new Matrix4f().translate(centerX, centerY, 0).scale(16, 16, 1));
        quadMesh.render();
        
        // 2. Hotbar Background
        glBindTexture(GL_TEXTURE_2D, hotbarTexture);
        
        float scale = 2.0f; 
        float barWidth = 182 * scale;
        float barHeight = 22 * scale;
        float barY = 10 + (barHeight / 2.0f);
        
        shader.setUniform("uColorMod", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f)); 
        
        Matrix4f hotbarModel = new Matrix4f()
            .translate(centerX, barY, 0)
            .scale(barWidth / 2.0f, barHeight / 2.0f, 1);
            
        shader.setUniform("uModel", hotbarModel);
        quadMesh.render();

        // 3. Items (Icons)
        glBindTexture(GL_TEXTURE_2D, textureAtlas.getTextureId());
        shader.setUniform("uUVScale", 1.0f / 16.0f);
        shader.setUniform("uColorMod", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f)); 
        
        float startX = (w / 2.0f) - (182 * 2.0f / 2.0f) + (3 * 2.0f);
        float slotStride = 20 * 2.0f;
        float itemY = 10 + (22 * 2.0f / 2.0f); // Center Y
        
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            Block item = stack.getBlock();
            
            if (item != Block.AIR) {
                // ... (Texture lookup) ...
                
                // FIX: Reduced Size & Centered
                float itemSize = 16 * 2.0f * 0.55f; // Scale 0.55 to fit inside 20px slot
                float itemX = startX + (i * slotStride) + (20 * 2.0f / 2.0f) - (3.0f); // Manual padding adjustment
                
                Matrix4f iconModel = new Matrix4f()
                    .translate(itemX, itemY, 0)
                    .scale(itemSize / 2.0f, itemSize / 2.0f, 1);
                
                shader.setUniform("uModel", iconModel);
                quadMesh.render();
            }
        }
        
        // 4. Selector
        glBindTexture(GL_TEXTURE_2D, selectorTexture);
        shader.setUniform("uUVScale", -1.0f); // Direct Mode
        shader.setUniform("uUVOffset", new Vector2f(0, 0));
        shader.setUniform("uColorMod", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
        
        int selected = inventory.getSelectedSlot();
        float selSize = 24 * scale;
        // Align selector to the specific slot
        // Hotbar Left edge + (Index * Stride) + Offset for selector being bigger than slot
        float selX = (centerX - (barWidth / 2.0f)) + (selected * 20 * scale) + (20*scale/2.0f) - (1 * scale);
        // Correct centering logic: startX points to left edge of slot 0.
        // Let's use the exact itemX logic but for selector width
        float selCenter = startX + (selected * slotStride) + (20*scale/2.0f) - (1.5f*scale); // Tuning
        
        // Re-calibrated Selector X:
        // Start of Hotbar + (Index * 20 * scale) + Half-Selector - Padding
        float finalSelX = (centerX - (barWidth / 2.0f)) + (selected * 20 * scale) + (12 * scale) - (1 * scale);
        
        Matrix4f selModel = new Matrix4f()
            .translate(finalSelX, barY, 0)
            .scale(selSize / 2.0f, selSize / 2.0f, 1);
            
        shader.setUniform("uModel", selModel);
        quadMesh.render();
        
        // 5. Stack Counts
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getCount() > 1) {
                String text = String.valueOf(stack.getCount());
                float itemX = startX + (i * slotStride) + (20*scale/2.0f);
                float textX = itemX; 
                float textY = itemY - (8 * scale);
                
                fontRenderer.drawText(text, textX, textY, 1.0f, orthoProjection);
            }
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
        shader.cleanup();
        quadMesh.cleanup();
        fontRenderer.cleanup();
        containerScreen.cleanup();
    }
}