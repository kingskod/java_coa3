package com.voxelengine.render.model;

import com.voxelengine.utils.Direction;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a cuboid element within a block model.
 * Coordinates are in pixel space (0-16).
 */
public class Element {
    public final Vector3f from;
    public final Vector3f to;
    public final Map<Direction, Face> faces = new HashMap<>();

    public Element(float fromX, float fromY, float fromZ, float toX, float toY, float toZ) {
        this.from = new Vector3f(fromX, fromY, fromZ);
        this.to = new Vector3f(toX, toY, toZ);
    }

    /**
     * Adds a face to this element.
     */
    public void addFace(Direction dir, String texture, float[] uv, int rotation) {
        // If UV is null, we default to the element bounds projected onto the face
        // But for this engine, we prefer explicit UVs or we handle defaults in the Mesher.
        // We store exactly what is passed.
        faces.put(dir, new Face(dir, texture, uv, rotation));
    }
    
    public Map<Direction, Face> getFaces() {
        return faces;
    }
}