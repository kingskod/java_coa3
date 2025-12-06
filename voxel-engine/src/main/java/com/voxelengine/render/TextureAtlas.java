package com.voxelengine.render;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import com.voxelengine.utils.Direction;

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

    // Map texture name (e.g. "grass") to UV index (0..255)
    private final Map<String, Integer> textureIndex = new HashMap<>();

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

        File[] files = dir.listFiles((d, name) -> name.endsWith(".png"));
        if (files != null) {
            for (File f : files) {
                try {
                    BufferedImage img = ImageIO.read(f);
                    g.drawImage(img, x, y, BLOCK_SIZE, BLOCK_SIZE, null);

                    String name = f.getName().replace(".png", "");
                    textureIndex.put(name, index);

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
                int pixel = pixels[y * width + x]; // Standard order
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // R
                buffer.put((byte) ((pixel >> 8) & 0xFF));  // G
                buffer.put((byte) (pixel & 0xFF));         // B
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // A
            }
        }
        buffer.flip();

        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public int getTextureId() { return textureId; }

    // Returns the texture index (0..255) for the shader to calculate UVs
    public int getIndex(String name, Direction dir) {
        // 1. Try exact match (e.g. "grass_up")
        String exactName = name + "_" + dir.name().toLowerCase();
        if (textureIndex.containsKey(exactName)) return textureIndex.get(exactName);
        
        // 2. Try generic "side" if direction is horizontal
        if (dir != Direction.UP && dir != Direction.DOWN) {
            String sideName = name + "_side";
            if (textureIndex.containsKey(sideName)) return textureIndex.get(sideName);
        }
        
        // 3. Fallback to base name (e.g. "grass")
        return textureIndex.getOrDefault(name, 0);
    }  // 2. Fallback to base name (e.g., "grass")
       
}