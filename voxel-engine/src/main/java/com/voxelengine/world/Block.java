package com.voxelengine.world;

public enum Block {
    AIR(0, true, false, false, null),
    STONE(1, false, true, false, SoundType.STONE),
    GRASS(2, false, true, false, SoundType.GRASS),
    DIRT(3, false, true, false, SoundType.GRASS),
    COBBLESTONE(4, false, true, false, SoundType.STONE),
    PLANKS(5, false, true, false, SoundType.WOOD),
    BEDROCK(6, false, true, false, SoundType.STONE),
    SAND(7, false, true, false, SoundType.SAND),
    
    // Legacy Water (Used by terrain gen until Batch 6 update)
    WATER(8, true, false, false, SoundType.LIQUID),
    
    LEAVES(9, true, true, false, SoundType.GRASS),
    LOG(10, false, true, false, SoundType.WOOD),
    GLASS(11, true, true, false, SoundType.STONE),
    
    // Logic
    REDSTONE_LAMP_OFF(12, false, true, false, SoundType.STONE),
    REDSTONE_LAMP_ON(13, false, true, true, SoundType.STONE),
    WIRE(14, true, false, false, SoundType.STONE),
    REDSTONE_TORCH(15, true, false, true, SoundType.WOOD),
    LEVER(16, true, false, false, SoundType.WOOD),
    REPEATER(17, true, true, false, SoundType.STONE),
    COMPARATOR(18, true, true, false, SoundType.STONE),
    
    // New Ores
    DIAMOND_ORE(19, false, true, false, SoundType.STONE),
    
    // Fluid System (Levels 7 to 0)
    // 7 = Source, 0 = Nearly empty
    WATER_SOURCE(20, true, false, false, SoundType.LIQUID), // Level 7
    WATER_6(21, true, false, false, SoundType.LIQUID),
    WATER_5(22, true, false, false, SoundType.LIQUID),
    WATER_4(23, true, false, false, SoundType.LIQUID),
    WATER_3(24, true, false, false, SoundType.LIQUID),
    WATER_2(25, true, false, false, SoundType.LIQUID),
    WATER_1(26, true, false, false, SoundType.LIQUID), // Lowest level
    WATER_0(27, true, false, false, SoundType.LIQUID); // Falling/Thin

    private final byte id;
    private final boolean transparent;
    private final boolean solid;
    private final boolean lightSource;
    private final SoundType soundType;

    Block(int id, boolean transparent, boolean solid, boolean lightSource, SoundType soundType) {
        this.id = (byte) id;
        this.transparent = transparent;
        this.solid = solid;
        this.lightSource = lightSource;
        this.soundType = soundType;
    }

    public byte getId() { return id; }
    public boolean isTransparent() { return transparent; }
    public boolean isSolid() { return solid; }
    public boolean isLightSource() { return lightSource; }
    public SoundType getSoundType() { return soundType; }

    public int getLightLevel() {
        return (this == REDSTONE_LAMP_ON || this == REDSTONE_TORCH) ? 15 : 0;
    }

    public boolean isPowerSource() {
        return this == REDSTONE_TORCH || this == LEVER || this == REDSTONE_LAMP_ON;
    }

    public boolean isLogicGate() {
        return this == REPEATER || this == COMPARATOR || this == REDSTONE_TORCH;
    }
    
    public boolean isWater() {
        return this.id >= 20 && this.id <= 27 || this == WATER;
    }
    
    // Helper to get flow level (0-7), returns -1 if not water
    public int getWaterLevel() {
        if (this == WATER_SOURCE) return 7;
        if (this == WATER) return 7; // Legacy compatibility
        if (this.id >= 21 && this.id <= 27) return 7 - (this.id - 20); 
        return -1;
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