package com.voxelengine.render.model;

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

    public List<Element> getElements() {
        return elements;
    }
}