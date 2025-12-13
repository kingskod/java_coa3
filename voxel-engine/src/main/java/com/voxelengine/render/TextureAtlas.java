package com.voxelengine.render;

import com.voxelengine.utils.Direction;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 * Stitches all block textures into a single texture map at runtime.
 * Calculates UV coordinates for each block.
 */
public class TextureAtlas {

    private static final int BLOCK_SIZE = 16;
    private static final int ATLAS_SIZE = 256; // 16x16 blocks capacity
    private int textureId;

    // Map texture name (e.g. "grass") to UV index (0..255) for GreedyMesher
    private final Map<String, Integer> textureIndex = new HashMap<>();
    
    // Map texture name to exact UV bounds for ModelMesher
    private final Map<String, Sprite> spriteMap = new HashMap<>();

    public TextureAtlas() {
        // Pre-register known textures to specific indices if needed,
        // or just load directory.
    }

    public void build() {
        File dir = new File("src/main/resources/assets/textures");
        if (!dir.exists()) return;

        BufferedImage atlas = new BufferedImage(ATLAS_SIZE, ATLAS_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = atlas.createGraphics();

        int x = 0;
        int y = 0;
        int index = 0;
        int cols = ATLAS_SIZE / BLOCK_SIZE; // 16

        File[] files = dir.listFiles((d, name) -> name.endsWith(".png"));
        if (files != null) {
            for (File f : files) {
                try {
                    BufferedImage img = ImageIO.read(f);
                    g.drawImage(img, x, y, BLOCK_SIZE, BLOCK_SIZE, null);

                    String name = f.getName().replace(".png", "");
                    textureIndex.put(name, index);

                    // --- NEW: Calculate Sprite UVs ---
                    // Normalize coordinates 0..1
                    float uMin = (float)x / ATLAS_SIZE;
                    float vMin = (float)y / ATLAS_SIZE;
                    float uMax = (float)(x + BLOCK_SIZE) / ATLAS_SIZE;
                    float vMax = (float)(y + BLOCK_SIZE) / ATLAS_SIZE;

                    // Bleed Fix: Shrink window slightly to prevent sampling neighbor pixels
                    float padding = 0.0001f;
                    spriteMap.put(name, new Sprite(uMin + padding, vMin + padding, uMax - padding, vMax - padding));
                    // ---------------------------------

                    // Advance
                    x += BLOCK_SIZE;
                    if (x >= ATLAS_SIZE) {
                        x = 0;
                        y += BLOCK_SIZE;
                    }
                    index++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        g.dispose();

        // Upload to OpenGL
        upload(atlas);
    }

    private void upload(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);

        ByteBuffer buffer = ByteBuffer.allocateDirect(width * height * 4);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixels[y * width + x];
                
                int r = (pixel >> 16) & 0xFF;
                int g = (pixel >> 8) & 0xFF;
                int b = (pixel & 0xFF);
                int a = (pixel >> 24) & 0xFF;

                // BUG FIX: Force Water Transparency
                // If alpha is full (255) but it looks like water (blue > red+green)
                if (a == 255 && b > r + g) {
                    a = 160; // 60% opacity
                }

                buffer.put((byte) r);
                buffer.put((byte) g);
                buffer.put((byte) b);
                buffer.put((byte) a);
            }
        }
        buffer.flip();

        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        // Nearest Neighbor for crisp pixels
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public int getTextureId() { return textureId; }

    // Returns the texture index (0..255) for the shader (Greedy Meshing)
    public int getIndex(String name, Direction dir) {
        String sideName = name + "_" + dir.name().toLowerCase();
        if (textureIndex.containsKey(sideName)) {
            return textureIndex.get(sideName);
        }
        
        if (dir == Direction.UP || dir == Direction.DOWN) {
             String topName = name + "_up";
             if (textureIndex.containsKey(topName)) return textureIndex.get(topName);
        } else {
             String sideGeneric = name + "_side";
             if (textureIndex.containsKey(sideGeneric)) return textureIndex.get(sideGeneric);
        }
        
        return textureIndex.getOrDefault(name, 0); 
    }
    
    // Returns the Sprite object for CPU baking (Model Meshing)
    public Sprite getSprite(String name) {
        // We use the same fallback logic as getIndex, but return the Sprite object
        if (spriteMap.containsKey(name)) return spriteMap.get(name);
        
        // Fallback to "missing texture" (usually index 0)
        // In a robust engine we'd return a specific purple/black checkerboard sprite.
        // For now, return the first sprite found or a default.
        return spriteMap.values().stream().findFirst().orElse(new Sprite(0,0,0,0));
    }
}