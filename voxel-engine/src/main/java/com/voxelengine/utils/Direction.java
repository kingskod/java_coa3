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

    public static final Direction[] VALUES = values();
}