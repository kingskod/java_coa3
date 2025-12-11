package com.voxelengine.world;

import com.voxelengine.entity.AABB;
import java.util.Collections;
import java.util.List;

public enum Block {
    // ID, Transparent, Solid, LightSrc, Sound, FullCube, HasGravity
    
    // Static World Base
    AIR(0, true, false, false, null, true, false),
    BEDROCK(6, false, true, false, SoundType.STONE, true, false), // Anchor
    
    // Gravity Blocks (Everything falling as requested)
    STONE(1, false, true, false, SoundType.STONE, true, true),
    GRASS(2, false, true, false, SoundType.GRASS, true, true),
    DIRT(3, false, true, false, SoundType.GRASS, true, true),
    COBBLESTONE(4, false, true, false, SoundType.STONE, true, true),
    PLANKS(5, false, true, false, SoundType.WOOD, true, true),
    SAND(7, false, true, false, SoundType.SAND, true, true),
    GRAVEL(29, false, true, false, SoundType.SAND, true, true),
    LOG(10, false, true, false, SoundType.WOOD, true, true),
    GLASS(11, true, true, false, SoundType.STONE, true, true),
    DIAMOND_ORE(19, false, true, false, SoundType.STONE, true, true),
    
    // Fluids (Do not fall via gravity system, they flow)
    WATER(8, true, false, false, SoundType.LIQUID, false, false),
    
    // Non-Solid Models (Usually attached, shouldn't fall or they break)
    LEAVES(9, true, true, false, SoundType.GRASS, true, false), // Leaves stick
    
    // Logic
    REDSTONE_LAMP_OFF(12, false, true, false, SoundType.STONE, true, true),
    REDSTONE_LAMP_ON(13, false, true, true, SoundType.STONE, true, true),
    WIRE(14, true, false, false, SoundType.STONE, false, false),
    REDSTONE_TORCH(15, true, false, true, SoundType.WOOD, false, false),
    LEVER(16, true, false, false, SoundType.WOOD, false, false),
    REPEATER(17, true, true, false, SoundType.STONE, false, false),
    COMPARATOR(18, true, true, false, SoundType.STONE, false, false),
    REDSTONE_TORCH_OFF(28, true, false, false, SoundType.WOOD, false, false),
    
    // Fluid Levels
    WATER_SOURCE(20, true, false, false, SoundType.LIQUID, false, false),
    WATER_6(21, true, false, false, SoundType.LIQUID, false, false),
    WATER_5(22, true, false, false, SoundType.LIQUID, false, false),
    WATER_4(23, true, false, false, SoundType.LIQUID, false, false),
    WATER_3(24, true, false, false, SoundType.LIQUID, false, false),
    WATER_2(25, true, false, false, SoundType.LIQUID, false, false),
    WATER_1(26, true, false, false, SoundType.LIQUID, false, false),
    WATER_0(27, true, false, false, SoundType.LIQUID, false, false);

    private final byte id;
    private final boolean transparent;
    private final boolean solid;
    private final boolean lightSource;
    private final SoundType soundType;
    private final boolean fullCube;
    private final boolean gravity;

    private static final List<AABB> FULL_CUBE_AABB = Collections.singletonList(new AABB(0, 0, 0, 1, 1, 1));
    private static final List<AABB> EMPTY_AABB = Collections.emptyList();

    Block(int id, boolean transparent, boolean solid, boolean lightSource, SoundType soundType, boolean fullCube, boolean gravity) {
        this.id = (byte) id;
        this.transparent = transparent;
        this.solid = solid;
        this.lightSource = lightSource;
        this.soundType = soundType;
        this.fullCube = fullCube;
        this.gravity = gravity;
    }

    public byte getId() { return id; }
    public boolean isTransparent() { return transparent; }
    public boolean isSolid() { return solid; }
    public boolean isLightSource() { return lightSource; }
    public SoundType getSoundType() { return soundType; }
    public boolean isFullCube() { return fullCube; }
    public boolean hasGravity() { return gravity; }

    public int getLightLevel() {
        return (this == REDSTONE_LAMP_ON || this == REDSTONE_TORCH) ? 15 : 0;
    }

    public boolean isPowerSource() {
        return this == REDSTONE_TORCH || this == LEVER || this == REDSTONE_LAMP_ON;
    }

    public boolean isLogicGate() {
        return this == REPEATER || this == COMPARATOR || this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF;
    }
    
    public boolean isWater() {
        return this.id >= 20 && this.id <= 27 || this == WATER;
    }
    
    public int getWaterLevel() {
        if (this == WATER_SOURCE || this == WATER) return 7;
        if (this.id >= 21 && this.id <= 27) return 7 - (this.id - 20); 
        return -1;
    }
    
    public String getItemTextureName() {
        return this.name().toLowerCase();
    }
    
    public float getOpacity() {
        if (this.transparent || !this.fullCube) return 0.0f;
        return 1.0f; 
    }

    public List<AABB> getColliders(int x, int y, int z, byte metadata) {
        if (!solid) return EMPTY_AABB;
        if (isWater()) return EMPTY_AABB;
        if (this == WIRE || this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF || this == LEVER) return EMPTY_AABB;
        if (fullCube) return FULL_CUBE_AABB; 
        return FULL_CUBE_AABB;
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