package com.voxelengine.world;

import com.voxelengine.entity.AABB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the different types of blocks in the game.
 * Each enum constant defines the properties of a block type, such as transparency, solidity, and sound.
 */
public enum Block {
    AIR(0, true, false, false, null, true),
    STONE(1, false, true, false, SoundType.STONE, true),
    GRASS(2, false, true, false, SoundType.GRASS, true),
    DIRT(3, false, true, false, SoundType.GRASS, true),
    COBBLESTONE(4, false, true, false, SoundType.STONE, true),
    PLANKS(5, false, true, false, SoundType.WOOD, true),
    BEDROCK(6, false, true, false, SoundType.STONE, true),
    SAND(7, false, true, false, SoundType.SAND, true),
    
    // Fluids
    WATER(8, true, false, false, SoundType.LIQUID, false), 
    
    // Transparent / Model Blocks
    LEAVES(9, true, true, false, SoundType.GRASS, true),
    LOG(10, false, true, false, SoundType.WOOD, true),
    GLASS(11, true, true, false, SoundType.STONE, true),
    
    // Logic Components
    REDSTONE_LAMP_OFF(12, false, true, false, SoundType.STONE, true),
    REDSTONE_LAMP_ON(13, false, true, true, SoundType.STONE, true),
    WIRE(14, true, false, false, SoundType.STONE, false), 
    REDSTONE_TORCH(15, true, false, true, SoundType.WOOD, false), 
    LEVER(16, true, false, false, SoundType.WOOD, false), 
    REPEATER(17, true, true, false, SoundType.STONE, false), 
    COMPARATOR(18, true, true, false, SoundType.STONE, false),
    REDSTONE_TORCH_OFF(28, true, false, false, SoundType.WOOD, false),
    
    // Ores
    DIAMOND_ORE(19, false, true, false, SoundType.STONE, true),
    
    // Fluid Levels (for simulating flow)
    WATER_SOURCE(20, true, false, false, SoundType.LIQUID, false), 
    WATER_6(21, true, false, false, SoundType.LIQUID, false),
    WATER_5(22, true, false, false, SoundType.LIQUID, false),
    WATER_4(23, true, false, false, SoundType.LIQUID, false),
    WATER_3(24, true, false, false, SoundType.LIQUID, false),
    WATER_2(25, true, false, false, SoundType.LIQUID, false),
    WATER_1(26, true, false, false, SoundType.LIQUID, false),
    WATER_0(27, true, false, false, SoundType.LIQUID, false);

    private final byte id;
    private final boolean transparent;
    private final boolean solid;
    private final boolean lightSource;
    private final SoundType soundType;
    private final boolean fullCube;

    private static final List<AABB> FULL_CUBE_AABB = Collections.singletonList(new AABB(0, 0, 0, 1, 1, 1));
    private static final List<AABB> EMPTY_AABB = Collections.emptyList();

    Block(int id, boolean transparent, boolean solid, boolean lightSource, SoundType soundType, boolean fullCube) {
        this.id = (byte) id;
        this.transparent = transparent;
        this.solid = solid;
        this.lightSource = lightSource;
        this.soundType = soundType;
        this.fullCube = fullCube;
    }

    public byte getId() { return id; }
    public boolean isTransparent() { return transparent; }
    public boolean isSolid() { return solid; }
    public boolean isLightSource() { return lightSource; }
    public SoundType getSoundType() { return soundType; }
    public boolean isFullCube() { return fullCube; }

    /**
     * Gets the emitted light level of the block.
     * @return 0-15 (15 is brightest).
     */
    public int getLightLevel() {
        return (this == REDSTONE_LAMP_ON || this == REDSTONE_TORCH) ? 15 : 0;
    }

    /**
     * Checks if the block is a source of redstone power.
     */
    public boolean isPowerSource() {
        return this == REDSTONE_TORCH || this == LEVER || this == REDSTONE_LAMP_ON;
    }

    /**
     * Checks if the block is a complex logic component (gate).
     */
    public boolean isLogicGate() {
        return this == REPEATER || this == COMPARATOR || this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF;
    }
    
    /**
     * Checks if the block is any form of water.
     */
    public boolean isWater() {
        return this.id >= 20 && this.id <= 27 || this == WATER;
    }
    
    /**
     * Gets the fluid level for water blocks.
     * @return 7 for source, declining for flowing blocks, or -1 if not water.
     */
    public int getWaterLevel() {
        if (this == WATER_SOURCE || this == WATER) return 7;
        if (this.id >= 21 && this.id <= 27) return 7 - (this.id - 20); 
        return -1;
    }
    
    /**
     * Gets the base name used for looking up textures.
     */
    public String getItemTextureName() {
        return this.name().toLowerCase();
    }
    
    /**
     * Gets the opacity factor for lighting calculations.
     * @return 1.0f for opaque blocks, 0.0f for transparent.
     */
    public float getOpacity() {
        if (this.transparent || !this.fullCube) return 0.0f;
        return 1.0f; 
    }

    /**
     * Gets the collision bounding boxes for this block at a specific location.
     *
     * @param x World X.
     * @param y World Y.
     * @param z World Z.
     * @param metadata Block metadata.
     * @return A list of AABBs (Axis Aligned Bounding Boxes).
     */
    public List<AABB> getColliders(int x, int y, int z, byte metadata) {
        if (!solid) return EMPTY_AABB;
        if (isWater()) return EMPTY_AABB;
        if (this == WIRE || this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF || this == LEVER) return EMPTY_AABB;

        if (fullCube) return FULL_CUBE_AABB; 
        
        // Placeholder for future slab or model-specific collisions
        return FULL_CUBE_AABB;
    }

    private static final Block[] CACHE = new Block[256];
    static {
        for (Block b : values()) CACHE[b.id] = b;
    }

    /**
     * Retrieves a Block by its numerical ID.
     *
     * @param id The byte ID.
     * @return The Block constant, or AIR if invalid.
     */
    public static Block getById(byte id) {
        if (id < 0 || id >= CACHE.length || CACHE[id] == null) return AIR;
        return CACHE[id];
    }
}