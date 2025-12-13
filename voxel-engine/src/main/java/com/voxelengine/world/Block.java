package com.voxelengine.world;

import com.voxelengine.entity.AABB;
import com.voxelengine.utils.Direction;
import java.util.Collections;
import java.util.List;

public enum Block {
    // ID, Transparent, Solid, LightSrc, Sound, FullCube, Gravity, ItemIconName
    
    AIR(0, true, false, false, null, true, false, null),
    
    // Standard Blocks
    STONE(1, false, true, false, SoundType.STONE, true, true, "stone"),
    GRASS(2, false, true, false, SoundType.GRASS, true, true, "grass_side"),
    DIRT(3, false, true, false, SoundType.GRASS, true, true, "dirt"),
    COBBLESTONE(4, false, true, false, SoundType.STONE, true, true, "cobblestone"),
    PLANKS(5, false, true, false, SoundType.WOOD, true, true, "planks"),
    BEDROCK(6, false, true, false, SoundType.STONE, true, false, "bedrock"),
    SAND(7, false, true, false, SoundType.SAND, true, true, "sand"),
    GRAVEL(29, false, true, false, SoundType.SAND, true, true, "gravel"),
    
    // Fluids
    WATER(8, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    
    // Transparent Models
    LEAVES(9, true, true, false, SoundType.GRASS, true, false, "leaves"),
    LOG(10, false, true, false, SoundType.WOOD, true, true, "log"),
    GLASS(11, true, true, false, SoundType.STONE, true, true, "glass"),
    
    // Basic Logic Components
    REDSTONE_LAMP_OFF(12, false, true, false, SoundType.STONE, true, true, "redstone_lamp_off"),
    REDSTONE_LAMP_ON(13, false, true, true, SoundType.STONE, true, true, "redstone_lamp_on"),
    WIRE(14, true, false, false, SoundType.STONE, false, false, "redstone_dust"),
    REDSTONE_TORCH(15, true, false, true, SoundType.WOOD, false, false, "redstone_torch"),
    LEVER(16, true, false, false, SoundType.WOOD, false, false, "lever"),
    REPEATER(17, true, true, false, SoundType.STONE, false, false, "repeater"),
    COMPARATOR(18, true, true, false, SoundType.STONE, false, false, "comparator"),
    REDSTONE_TORCH_OFF(28, true, false, false, SoundType.WOOD, false, false, "redstone_torch"),
    
    // Advanced Logic Gates (Using Mod Icons)
    AND_GATE(30, true, false, false, SoundType.STONE, false, false, "and_gate_item"),
    OR_GATE(31, true, false, false, SoundType.STONE, false, false, "or_gate_item"),
    NOT_GATE(32, true, false, false, SoundType.STONE, false, false, "not_gate_item"),
    NAND_GATE(33, true, false, false, SoundType.STONE, false, false, "nand_gate_item"),
    XOR_GATE(34, true, false, false, SoundType.STONE, false, false, "xor_gate_item"),
    LATCH_OFF(35, true, false, false, SoundType.STONE, false, false, "sr_latch_item"),
    LATCH_ON(36, true, false, false, SoundType.STONE, false, false, "sr_latch_item"),
    
    // Ores
    DIAMOND_ORE(19, false, true, false, SoundType.STONE, true, true, "diamond_ore"),
    
    // Fluid Levels (Hidden from creative menu usually)
    WATER_SOURCE(20, true, false, false, SoundType.LIQUID, false, false, "water_bucket"), 
    WATER_6(21, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    WATER_5(22, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    WATER_4(23, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    WATER_3(24, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    WATER_2(25, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    WATER_1(26, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    WATER_0(27, true, false, false, SoundType.LIQUID, false, false, "water_bucket");

    private final byte id;
    private final boolean transparent;
    private final boolean solid;
    private final boolean lightSource;
    private final SoundType soundType;
    private final boolean fullCube;
    private final boolean gravity;
    private final String itemIcon;

    // Pre-defined Collision Shapes
    private static final List<AABB> FULL_CUBE_AABB = Collections.singletonList(new AABB(0, 0, 0, 1, 1, 1));
    private static final List<AABB> PLATE_AABB = Collections.singletonList(new AABB(0, 0, 0, 1, 0.125f, 1)); // For Gates
    private static final List<AABB> EMPTY_AABB = Collections.emptyList(); // For walk-through blocks

    Block(int id, boolean transparent, boolean solid, boolean lightSource, SoundType soundType, boolean fullCube, boolean gravity, String itemIcon) {
        this.id = (byte) id;
        this.transparent = transparent;
        this.solid = solid;
        this.lightSource = lightSource;
        this.soundType = soundType;
        this.fullCube = fullCube;
        this.gravity = gravity;
        this.itemIcon = itemIcon;
    }

    public byte getId() { return id; }
    public boolean isTransparent() { return transparent; }
    public boolean isSolid() { return solid; }
    public boolean isLightSource() { return lightSource; }
    public SoundType getSoundType() { return soundType; }
    public boolean isFullCube() { return fullCube; }
    public boolean hasGravity() { return gravity; }
    public String getItemIcon() { return itemIcon; }

    // --- Properties ---

    public int getLightLevel() {
        if (this == REDSTONE_LAMP_ON || this == REDSTONE_TORCH || this == LATCH_ON) return 15;
        return 0;
    }

    public boolean isPowerSource() {
        return this == REDSTONE_TORCH || this == LEVER || this == REDSTONE_LAMP_ON || this == LATCH_ON;
    }

    public boolean isLogicGate() {
        return this == REPEATER || this == COMPARATOR || this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF ||
               (this.id >= 30 && this.id <= 36);
    }
    
    public boolean isWater() {
        return this.id >= 20 && this.id <= 27 || this == WATER;
    }
    
    public int getWaterLevel() {
        if (this == WATER_SOURCE || this == WATER) return 7;
        if (this.id >= 21 && this.id <= 27) return 7 - (this.id - 20); 
        return -1;
    }
    
    public float getOpacity() {
        // Opacity for lighting calculations
        if (this.transparent || !this.fullCube) return 0.0f;
        return 1.0f; 
    }
    
    // --- Metadata Helpers ---

    public Direction getRotation(byte meta) {
        switch(meta & 3) { 
            case 0: return Direction.SOUTH;
            case 1: return Direction.WEST;
            case 2: return Direction.NORTH;
            case 3: return Direction.EAST;
            default: return Direction.SOUTH;
        }
    }
    
    public boolean isActive(byte meta) {
        return (meta & 4) != 0; // Check Bit 2
    }
    
    public byte getMetadata(Direction rot, boolean active) {
        int r = 0;
        switch(rot) {
            case SOUTH: r=0; break;
            case WEST: r=1; break;
            case NORTH: r=2; break;
            case EAST: r=3; break;
        }
        if (active) r |= 4; 
        return (byte)r;
    }

    // --- Physics & Interaction ---

    /**
     * Returns a list of collision boxes for PhysicsEngine.
     * Empty list means the player can walk through it.
     */
    public List<AABB> getColliders(int x, int y, int z, byte metadata) {
        // 1. Pass-through blocks
        if (!solid) return EMPTY_AABB;
        if (isWater()) return EMPTY_AABB;
        
        // 2. Walk-through logic components
        if (this == WIRE || this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF || this == LEVER) {
            return EMPTY_AABB;
        }

        // 3. Walk-ON logic components (Gates act as carpet/plates)
        if (this.id >= 30 && this.id <= 36) {
            return PLATE_AABB;
        }

        // 4. Standard Cubes
        if (fullCube) return FULL_CUBE_AABB; 
        
        return FULL_CUBE_AABB; // Fallback
    }
    
    /**
     * Returns the Raycast hit box for selection.
     * Allows selecting blocks that have no collision (like Torches).
     */
    public AABB getSelectionBox(int x, int y, int z, byte metadata) {
        if (this == AIR) return null;
        
        if (this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF) {
            // Stick shape (centered)
            return new AABB(0.4f, 0, 0.4f, 0.6f, 0.6f, 0.6f);
        }
        
        if (this == WIRE) {
             // Flat wire
             return new AABB(0, 0, 0, 1, 0.1f, 1);
        }

        if (this.isLogicGate() || this.id >= 30 && this.id <= 36) {
            // Plate shape
            return new AABB(0, 0, 0, 1, 0.125f, 1);
        }

        // Standard Cube
        return new AABB(0, 0, 0, 1, 1, 1);
    }

    // --- Registry Lookup ---

    private static final Block[] CACHE = new Block[256];
    static {
        for (Block b : values()) CACHE[b.id] = b;
    }

    public static Block getById(byte id) {
        if (id < 0 || id >= CACHE.length || CACHE[id] == null) return AIR;
        return CACHE[id];
    }
}