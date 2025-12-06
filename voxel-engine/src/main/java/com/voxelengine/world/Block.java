package com.voxelengine.world;

public enum Block {
    AIR(0, true, false, false),
    STONE(1, false, true, false),
    GRASS(2, false, true, false),
    DIRT(3, false, true, false),
    COBBLESTONE(4, false, true, false),
    PLANKS(5, false, true, false),
    BEDROCK(6, false, true, false),
    SAND(7, false, true, false),
    WATER(8, true, false, false),
    LEAVES(9, true, true, false),
    LOG(10, false, true, false),
    GLASS(11, true, true, false),
    REDSTONE_LAMP_OFF(12, false, true, false),
    REDSTONE_LAMP_ON(13, false, true, true),
    WIRE(14, true, false, false),
    // New Logic Blocks
    REDSTONE_TORCH(15, true, false, true),
    LEVER(16, true, false, false),
    REPEATER(17, true, true, false),
    COMPARATOR(18, true, true, false);

    private final byte id;
    private final boolean transparent;
    private final boolean solid;
    private final boolean lightSource;

    Block(int id, boolean transparent, boolean solid, boolean lightSource) {
        this.id = (byte) id;
        this.transparent = transparent;
        this.solid = solid;
        this.lightSource = lightSource;
    }

    public byte getId() { return id; }
    public boolean isTransparent() { return transparent; }
    public boolean isSolid() { return solid; }
    public boolean isLightSource() { return lightSource; }

    public int getLightLevel() {
        return (this == REDSTONE_LAMP_ON || this == REDSTONE_TORCH) ? 15 : 0;
    }

    public boolean isPowerSource() {
        return this == REDSTONE_TORCH || this == LEVER || this == REDSTONE_LAMP_ON;
    }

    public boolean isLogicGate() {
        return this == REPEATER || this == COMPARATOR || this == REDSTONE_TORCH;
    }

    private static final Block[] CACHE = new Block[256];
    static {
        for (Block b : values()) CACHE[b.id] = b;
    }

    public static Block getById(byte id) {
        if (id < 0 || id >= CACHE.length || CACHE[id] == null) return AIR;
        return CACHE[id];
    }
}