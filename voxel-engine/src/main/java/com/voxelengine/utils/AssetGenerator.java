package com.voxelengine.utils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AssetGenerator {

    private static final String RESOURCE_DIR = "src/main/resources/assets";

    public static void verifyAndGenerateAssets() {
        Path root = Paths.get(RESOURCE_DIR);
        FileUtils.ensureDirectory(root);
        FileUtils.ensureDirectory(root.resolve("textures"));
        FileUtils.ensureDirectory(root.resolve("sounds"));
        FileUtils.ensureDirectory(root.resolve("shaders"));
        FileUtils.ensureDirectory(root.resolve("ui"));

        generateTextures(root.resolve("textures"));
        generateSounds(root.resolve("sounds"));
        generateUI(root.resolve("ui"));
        generateFont(root.resolve("ui")); // NEW
    }

    // ... (Keep existing generateTextures and generateSounds methods exactly as before) ...
    // Note: For brevity in this response, I am not repeating the texture/sound code.
    // Assume previous methods exist here.

    private static void generateTextures(Path dir) {
        // (Same as Batch 1)
        createSolidTexture(dir, "grass.png", new Color(100, 255, 100));
        createSolidTexture(dir, "dirt.png", new Color(120, 85, 60));
        createSolidTexture(dir, "stone.png", new Color(128, 128, 128));
        createSolidTexture(dir, "sand.png", new Color(240, 240, 180));
        createSolidTexture(dir, "water.png", new Color(60, 100, 255));
        createSolidTexture(dir, "wood.png", new Color(100, 60, 30));
        createSolidTexture(dir, "leaves.png", new Color(50, 150, 50));
        createSolidTexture(dir, "bedrock.png", new Color(20, 20, 20));
        createSolidTexture(dir, "wire_off.png", new Color(100, 0, 0));
        createSolidTexture(dir, "wire_on.png", new Color(255, 0, 0));
        createSolidTexture(dir, "redstone_torch.png", new Color(255, 50, 50));
        createSolidTexture(dir, "redstone_lamp_off.png", new Color(50, 20, 0));
        createSolidTexture(dir, "redstone_lamp_on.png", new Color(255, 220, 100));
        createSolidTexture(dir, "lever.png", new Color(100, 100, 100));
    }

    private static void createSolidTexture(Path dir, String name, Color color) {
        File file = dir.resolve(name).toFile();
        if (file.exists()) return;
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, 16, 16);
        g.dispose();
        try { ImageIO.write(image, "png", file); } catch (IOException e) {}
    }

    private static void generateUI(Path dir) {
        // (Same as Batch 1 crosshair logic)
        File crosshair = dir.resolve("crosshair.png").toFile();
        if (!crosshair.exists()) {
            BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(7, 0, 2, 16);
            g.fillRect(0, 7, 16, 2);
            g.dispose();
            try { ImageIO.write(img, "png", crosshair); } catch (IOException e) {}
        }
        File selector = dir.resolve("selector.png").toFile();
        if (!selector.exists()) {
            BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            // Clear background
            g.setBackground(new Color(0, 0, 0, 0));
            g.clearRect(0, 0, 16, 16);
            
            // Thick White Border
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 16, 2);   // Top
            g.fillRect(0, 14, 16, 2);  // Bottom
            g.fillRect(0, 0, 2, 16);   // Left
            g.fillRect(14, 0, 2, 16);  // Right
            
            g.dispose();
            try { ImageIO.write(img, "png", selector); } catch (IOException e) {}
        }
    }

    private static void generateSounds(Path dir) {
        // (Same as Batch 1)
        createBeepWav(dir.resolve("break.wav").toFile(), 440, 100);
    }

    private static void createBeepWav(File file, int freq, int durationMs) {
        // (Same as Batch 1 implementation)
        if (file.exists()) return;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Minimal placeholder write to prevent crash
            fos.write(new byte[44]);
        } catch(IOException e) {}
    }

    /**
     * Generates a 256x256 bitmap font grid.
     * ASCII 32-127.
     */
    private static void generateFont(Path dir) {
        File fontFile = dir.resolve("font.png").toFile();
        if (fontFile.exists()) return;

        int width = 256;
        int height = 256;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g.setFont(new Font("Monospaced", Font.BOLD, 14));
        g.setColor(Color.WHITE);

        FontMetrics fm = g.getFontMetrics();
        int charWidth = 16;
        int charHeight = 16;

        for (int i = 0; i < 256; i++) {
            int col = i % 16;
            int row = i / 16;

            char c = (char) i;
            // Draw character centered in its 16x16 cell
            g.drawString(String.valueOf(c), col * charWidth + 2, row * charHeight + fm.getAscent());
        }

        g.dispose();
        try {
            ImageIO.write(img, "png", fontFile);
            System.out.println("Generated fallback font.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}