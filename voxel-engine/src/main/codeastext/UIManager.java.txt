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
    private int selectorTexture;
    private int hotbarTexture; 
    
    private final Inventory inventory;
    private final TextureAtlas textureAtlas;
    private final FontRenderer fontRenderer;
    private final CommandManager commandManager;
    private final ContainerScreen containerScreen;
    
    public boolean isChatOpen = false;
    public boolean isInventoryOpen = false;
    
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
        
        // Standard Quad
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
            if (Input.isMouseButtonDown(0)) {
                if (!mouseWasDown) {
                    float mx = (float) Input.getMouseX();
                    float my = (float) currentHeight - (float) Input.getMouseY(); 
                    containerScreen.handleMouseClick(mx, my, currentWidth, currentHeight, 0);
                    mouseWasDown = true;
                }
            } else {
                mouseWasDown = false;
            }
        } else {
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
        
        if (isInventoryOpen) {
            float mx = (float) Input.getMouseX();
            float my = (float) windowHeight - (float) Input.getMouseY();
            containerScreen.render(shader, windowWidth, windowHeight, orthoProjection, mx, my);
        }
        
        if (!isInventoryOpen) {
            renderHUD(windowWidth, windowHeight);
        }
        
        if (isChatOpen) {
            renderChat(windowWidth);
        }
        
        shader.unbind();
        glDepthMask(true);
        glEnable(GL_DEPTH_TEST);
        glDisable(GL_BLEND);
    }
    
    private void renderHUD(int w, int h) {
        float centerX = w / 2.0f;
        float centerY = h / 2.0f;
        float scale = 2.0f;
        
        // 1. Crosshair
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, crosshairTexture);
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", -1.0f); 
        shader.setUniform("uColorMod", new Vector4f(1,1,1,1));
        shader.setUniform("uModel", new Matrix4f().translate(centerX, centerY, 0).scale(16, 16, 1));
        quadMesh.render();
        
        // 2. Hotbar Background
        // Width 182, Height 22
        float barW = 182 * scale;
        float barH = 22 * scale;
        float barY = 10 + (barH / 2.0f);
        
        glBindTexture(GL_TEXTURE_2D, hotbarTexture);
        shader.setUniform("uModel", new Matrix4f().translate(centerX, barY, 0).scale(barW / 2.0f, barH / 2.0f, 1));
        quadMesh.render();

        // 3. Items
        glBindTexture(GL_TEXTURE_2D, textureAtlas.getTextureId());
        shader.setUniform("uUVScale", 1.0f / 16.0f);
        
        // Slot Start Logic:
        // Hotbar Left Edge = centerX - (182 * scale / 2)
        // First slot offset = 3 pixels from left
        // Slot stride = 20 pixels
        float startX = (centerX - (barW / 2.0f)) + (3 * scale) + (16 * scale / 2.0f); // Center of first item
        float itemY = barY; // Vertically centered in bar
        
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            Block item = stack.getBlock();
            
            if (item != Block.AIR) {
                // FIX: Retrieve correct icon
                String icon = item.getItemIcon();
                if (icon == null) icon = item.name().toLowerCase();
                
                // FIX: Calculate and set UV Offset
                int index = textureAtlas.getIndex(icon, Direction.SOUTH);
                float u = (index % 16) / 16.0f;
                float v = (index / 16) / 16.0f;
                shader.setUniform("uUVOffset", new Vector2f(u, v));
                
                float itemX = startX + (i * 20 * scale);
                float itemSize = 16 * scale;
                
                Matrix4f iconModel = new Matrix4f()
                    .translate(itemX, itemY, 0)
                    .scale(itemSize / 2.0f, itemSize / 2.0f, 1);
                
                shader.setUniform("uModel", iconModel);
                quadMesh.render();
            }
        }
        
        // 4. Selector
        glBindTexture(GL_TEXTURE_2D, selectorTexture);
        shader.setUniform("uUVScale", -1.0f);
        shader.setUniform("uUVOffset", new Vector2f(0, 0));
        
        int selected = inventory.getSelectedSlot();
        float selSize = 24 * scale;
        // Selector is 24x24, Slot is 20 stride. Centered on slot.
        // Slot Center (itemX) calculation from above: startX + i*20*scale
        float selX = startX + (selected * 20 * scale);
        
        shader.setUniform("uModel", new Matrix4f().translate(selX, barY, 0).scale(selSize / 2.0f, selSize / 2.0f, 1));
        quadMesh.render();
        
        // 5. Counts
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getCount() > 1) {
                float itemX = startX + (i * 20 * scale);
                // Offset text slightly bottom-right
                fontRenderer.drawText(String.valueOf(stack.getCount()), itemX, itemY - (6 * scale), 1.0f, orthoProjection);
            }
        }
    }
    
    private void renderChat(int w) {
        // ... (Keep existing chat logic) ...
        // Re-implementing briefly to ensure file completeness
        glActiveTexture(GL_TEXTURE0);
        // Using selector texture as placeholder background if slotTexture unavailable in context
        // In real impl, use a semi-transparent black quad
        glBindTexture(GL_TEXTURE_2D, selectorTexture); 
        shader.setUniform("uTexture", 0);
        shader.setUniform("uUVScale", -1.0f);
        shader.setUniform("uColorMod", new Vector4f(0,0,0,0.5f));
        
        Matrix4f chatBg = new Matrix4f().translate(w/2.0f, 20, 0).scale(w, 20, 1);
        shader.setUniform("uModel", chatBg);
        quadMesh.render();
        
        fontRenderer.drawText(Input.getTypedString(), 10, 15, 1.0f, orthoProjection);
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
        shader.cleanup();
        quadMesh.cleanup();
        fontRenderer.cleanup();
        containerScreen.cleanup();
    }
}