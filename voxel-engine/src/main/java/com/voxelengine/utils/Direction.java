package com.voxelengine.utils;

import org.joml.Vector3i;

/**
 * Represents the six cardinal directions in 3D space.
 * Provides utility methods for retrieving vectors and opposite directions.
 */
public enum Direction {
    UP(0, 1, 0),
    DOWN(0, -1, 0),
    NORTH(0, 0, -1),
    SOUTH(0, 0, 1),
    EAST(1, 0, 0),
    WEST(-1, 0, 0);

    /** The X component of the direction vector. */
    public final int x;
    /** The Y component of the direction vector. */
    public final int y;
    /** The Z component of the direction vector. */
    public final int z;
    /** The vector representation of the direction. */
    public final Vector3i vec;

    /**
     * Constructs a Direction with the given vector components.
     *
     * @param x X component.
     * @param y Y component.
     * @param z Z component.
     */
    Direction(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vec = new Vector3i(x, y, z);
    }

    /**
     * Gets the opposite direction.
     *
     * @return The opposite Direction.
     */
    public Direction opposite() {
        switch (this) {
            case UP: return DOWN;
            case DOWN: return UP;
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case EAST: return WEST;
            case WEST: return EAST;
            default: return UP;
        }
    }

    /** Cached array of all values to avoid allocation. */
    public static final Direction[] VALUES = values();
    
    /** Horizontal directions, useful for water flow and connections. */
    public static final Direction[] HORIZONTAL = {NORTH, SOUTH, EAST, WEST};
}