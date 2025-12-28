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
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class to generate default assets (textures, sounds, fonts) if they are missing.
 * Ensures the game has basic resources to run.
 */
public class AssetGenerator {

    private static final String RESOURCE_DIR = "src/main/resources/assets";

    /**
     * Checks for the existence of required assets and generates them if missing.
     * Creates directories and default files for textures, sounds, and UI elements.
     */
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
        generateFont(root.resolve("ui"));
    }

    private static void generateTextures(Path dir) {
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
        // Crosshair
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

        // Item Selector
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

        // Inventory Tabs
        File tabItems = dir.resolve("tab_items.png").toFile();
        if (!tabItems.exists()) {
            // Standard MC inventory size approx 176x166
            BufferedImage img = new BufferedImage(176, 166, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            
            // Grey Background
            g.setColor(new Color(198, 198, 198));
            g.fillRect(0, 0, 176, 166);
            
            // Draw Borders
            g.setColor(Color.WHITE);
            g.drawRect(0, 0, 175, 165);
            
            // Draw Slot Placeholders (3 rows of 9)
            g.setColor(new Color(139, 139, 139));
            int startX = 7;
            int startY = 17; // Top Grid
            
            // Main Inventory
            for(int row=0; row<3; row++) {
                for(int col=0; col<9; col++) {
                    g.drawRect(startX + col * 18, startY + row * 18, 16, 16);
                }
            }
            
            // Hotbar
            int hotbarY = 141;
            for(int col=0; col<9; col++) {
                g.drawRect(startX + col * 18, hotbarY, 16, 16);
            }
            
            g.dispose();
            try { ImageIO.write(img, "png", tabItems); } catch (IOException e) {}
        }
    }

    private static void generateSounds(Path dir) {
        createBeepWav(dir.resolve("break.wav").toFile(), 440, 100);
    }

    private static void createBeepWav(File file, int freq, int durationMs) {
        if (file.exists()) return;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Minimal placeholder write to prevent crash
            fos.write(new byte[44]);
        } catch(IOException e) {}
    }

    /**
     * Generates a 256x256 bitmap font grid for ASCII characters 32-127.
     * Used for text rendering.
     *
     * @param dir The directory to save the font image.
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