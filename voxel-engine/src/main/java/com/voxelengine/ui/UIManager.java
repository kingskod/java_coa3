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

/**
 * Manages all User Interface rendering and interaction.
 * Handles the HUD, inventory screens, and chat/command input.
 */
public class UIManager {

    private final Shader shader;
    private final Mesh quadMesh;
    private final Matrix4f orthoProjection;
    
    // UI Texture IDs
    private int crosshairTexture;
    private int slotTexture;
    private int selectorTexture;
    private int hotbarTexture;
    
    private final Inventory inventory;
    private final TextureAtlas textureAtlas;
    private final FontRenderer fontRenderer;
    private final CommandManager commandManager;
    private final ContainerScreen containerScreen;
    
    // UI State Flags
    public boolean isChatOpen = false;
    public boolean isInventoryOpen = false;

    /**
     * Creates the UI Manager.
     *
     * @param inventory The player's inventory.
     * @param textureAtlas The main game texture atlas (for item icons).
     * @param world The game world.
     * @param entityMgr The entity manager.
     */
    public UIManager(Inventory inventory, TextureAtlas textureAtlas, World world, EntityManager entityMgr) {
        this.inventory = inventory;
        this.textureAtlas = textureAtlas;
        this.shader = new Shader("assets/shaders/vertex.glsl", "assets/shaders/fragment.glsl");
        this.fontRenderer = new FontRenderer(this.shader);
        this.commandManager = new CommandManager(world, entityMgr, inventory);
        this.containerScreen = new ContainerScreen(inventory, textureAtlas, fontRenderer);
        
        // Standard Quad geometry (0..1 UVs)
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
    
    /**
     * Processes input for UI elements (Chat, Menu toggles).
     */
    public void handleInput() {
        if (isChatOpen) {
            if (Input.isEnterPressed()) {
                String cmd = Input.getTypedString();
                commandManager.execute(cmd);
                isChatOpen = false;
                Input.stopTextInput();
                Input.setCursorLocked(true);
            }
        } else {
            if (Input.isKeyDown(GLFW.GLFW_KEY_SLASH)) { 
                isChatOpen = true;
                Input.startTextInput();
                Input.setCursorLocked(false);
            }
        }
    }
    
    /**
     * Toggles the inventory screen visibility.
     */
    public void toggleInventory() {
        isInventoryOpen = !isInventoryOpen;
        Input.setCursorLocked(!isInventoryOpen);
    }

    /**
     * Renders the UI overlay.
     *
     * @param windowWidth Current window width.
     * @param windowHeight Current window height.
     */
    public void render(int windowWidth, int windowHeight) {
        orthoProjection.identity().ortho(0, windowWidth, 0, windowHeight, -1, 1);
        
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        shader.bind();
        shader.setUniform("uProjection", orthoProjection);
        shader.setUniform("uView", new Matrix4f());
        
        // 1. Full Screen Interfaces (Inventory)
        if (isInventoryOpen) {
            containerScreen.render(shader, windowWidth, windowHeight, orthoProjection);
        }
        
        // 2. HUD (Hotbar, Crosshair)
        if (!isInventoryOpen) {
            renderHUD(windowWidth, windowHeight);
        }
        
        // 3. Chat Overlay
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
        shader.setUniform("uUVScale", 1.0f / 16.0f); // Atlas Mode
        shader.setUniform("uColorMod", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f)); 
        
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
        
        // 4. Selector
        glBindTexture(GL_TEXTURE_2D, selectorTexture);
        shader.setUniform("uUVScale", -1.0f); // Direct Mode
        shader.setUniform("uUVOffset", new Vector2f(0, 0));
        shader.setUniform("uColorMod", new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
        
        int selected = inventory.getSelectedSlot();
        float selSize = 24 * scale;
        float selX = (centerX - (barWidth / 2.0f)) + (selected * 20 * scale) + (selSize / 2.0f) - (1 * scale);
        
        Matrix4f selModel = new Matrix4f()
            .translate(selX, barY, 0)
            .scale(selSize / 2.0f, selSize / 2.0f, 1);
            
        shader.setUniform("uModel", selModel);
        quadMesh.render();
        
        // 5. Stack Counts
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getCount() > 1) {
                String text = String.valueOf(stack.getCount());
                float itemSize = 16 * scale; 
                float itemX = startX + (i * slotStride) + (itemSize / 2.0f);
                
                // Bottom right corner
                float textX = itemX + (itemSize * 0.1f); 
                float textY = itemY - (itemSize * 0.25f);
                
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
                ByteBuffer pink = stack.malloc(4 * 16 * 16);
                for(int i=0; i<16*16; i++) pink.putInt(0xFF00FFFF);
                pink.flip();
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 16, 16, 0, GL_RGBA, GL_UNSIGNED_BYTE, pink);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            }
        }
        return texId;
    }
    
    /**
     * Cleans up UI resources (shaders, meshes).
     */
    public void cleanup() {
        shader.cleanup();
        quadMesh.cleanup();
        fontRenderer.cleanup();
        containerScreen.cleanup();
    }
}