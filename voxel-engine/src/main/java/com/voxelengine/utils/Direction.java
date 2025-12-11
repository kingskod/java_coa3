package com.voxelengine.utils;

import org.joml.Vector3i;

public enum Direction {
    UP(0, 1, 0),
    DOWN(0, -1, 0),
    NORTH(0, 0, -1),
    SOUTH(0, 0, 1),
    EAST(1, 0, 0),
    WEST(-1, 0, 0);

    public final int x, y, z;
    public final Vector3i vec;

    Direction(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vec = new Vector3i(x, y, z);
    }

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

    public static final Direction[] VALUES = values();
    
    // Horizontal directions for water flow and tree connection
    public static final Direction[] HORIZONTAL = {NORTH, SOUTH, EAST, WEST};
}