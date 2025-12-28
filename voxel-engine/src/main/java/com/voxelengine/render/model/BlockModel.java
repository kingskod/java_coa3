package com.voxelengine.render.model;

import com.voxelengine.utils.Direction;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete Block Model composed of multiple elements.
 */
public class BlockModel {
    private final List<Element> elements = new ArrayList<>();

    public void addElement(Element element) {
        elements.add(element);
    }

    /**
     * Helper to add a cuboid element with the same texture on all faces.
     * @param x1 Min X (0-16)
     * @param y1 Min Y (0-16)
     * @param z1 Min Z (0-16)
     * @param x2 Max X (0-16)
     * @param y2 Max Y (0-16)
     * @param z2 Max Z (0-16)
     * @param texture Texture name (e.g. "smooth_stone")
     */
    public void addBox(float x1, float y1, float z1, float x2, float y2, float z2, String texture) {
        Element element = new Element(x1, y1, z1, x2, y2, z2);
        
        // Add faces for all directions so the renderer can find the texture
        for (Direction dir : Direction.VALUES) {
            // We pass null for UVs as the Mesher currently calculates them or assumes defaults
            element.addFace(dir, texture, null, 0);
        }
        
        elements.add(element);
    }

    public List<Element> getElements() {
        return elements;
    }
}