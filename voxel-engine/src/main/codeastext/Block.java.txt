package com.voxelengine.world;

import com.voxelengine.entity.AABB;
import com.voxelengine.utils.Direction;
import java.util.Collections;
import java.util.List;

public enum Block {
    // ... (Enum Constants remain the same) ...
    AIR(0, true, false, false, null, true, false, null),
    STONE(1, false, true, false, SoundType.STONE, true, true, "stone"),
    GRASS(2, false, true, false, SoundType.GRASS, true, true, "grass_side"),
    DIRT(3, false, true, false, SoundType.GRASS, true, true, "dirt"),
    COBBLESTONE(4, false, true, false, SoundType.STONE, true, true, "cobblestone"),
    PLANKS(5, false, true, false, SoundType.WOOD, true, true, "planks"),
    BEDROCK(6, false, true, false, SoundType.STONE, true, false, "bedrock"),
    SAND(7, false, true, false, SoundType.SAND, true, true, "sand"),
    GRAVEL(29, false, true, false, SoundType.SAND, true, true, "gravel"),
    WATER(8, true, false, false, SoundType.LIQUID, false, false, "water_bucket"),
    LEAVES(9, true, true, false, SoundType.GRASS, true, false, "leaves"),
    LOG(10, false, true, false, SoundType.WOOD, true, true, "log"),
    GLASS(11, true, true, false, SoundType.STONE, true, true, "glass"),
    
    // Logic Blocks
    REDSTONE_LAMP_OFF(12, false, true, false, SoundType.STONE, true, true, "redstone_lamp_off"),
    REDSTONE_LAMP_ON(13, false, true, true, SoundType.STONE, true, true, "redstone_lamp_on"),
    WIRE(14, true, false, false, SoundType.STONE, false, false, "redstone_dust"),
    REDSTONE_TORCH(15, true, false, true, SoundType.WOOD, false, false, "redstone_torch"),
    LEVER(16, true, false, false, SoundType.WOOD, false, false, "lever"),
    REPEATER(17, true, true, false, SoundType.STONE, false, false, "repeater"),
    COMPARATOR(18, true, true, false, SoundType.STONE, false, false, "comparator"),
    REDSTONE_TORCH_OFF(28, true, false, false, SoundType.WOOD, false, false, "redstone_torch"),
    
    // Gates
    AND_GATE(30, true, false, false, SoundType.STONE, false, false, "and_gate_item"),
    OR_GATE(31, true, false, false, SoundType.STONE, false, false, "or_gate_item"),
    NOT_GATE(32, true, false, false, SoundType.STONE, false, false, "not_gate_item"),
    NAND_GATE(33, true, false, false, SoundType.STONE, false, false, "nand_gate_item"),
    XOR_GATE(34, true, false, false, SoundType.STONE, false, false, "xor_gate_item"),
    LATCH_OFF(35, true, false, false, SoundType.STONE, false, false, "sr_latch_item"),
    LATCH_ON(36, true, false, false, SoundType.STONE, false, false, "sr_latch_item"),
    
    DIAMOND_ORE(19, false, true, false, SoundType.STONE, true, true, "diamond_ore"),
    
    // Water Levels
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

    // ... (Keep existing getters: getId, isTransparent, etc.) ...
    public byte getId() { return id; }
    public boolean isTransparent() { return transparent; }
    public boolean isSolid() { return solid; }
    public boolean isLightSource() { return lightSource; }
    public SoundType getSoundType() { return soundType; }
    public boolean isFullCube() { return fullCube; }
    public boolean hasGravity() { return gravity; }
    public String getItemIcon() { return itemIcon; }
    
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

    // --- NEW: State String Generation for JSON System ---
    public String toStateString(byte meta) {
        // 1. Simple Blocks (Stone, Dirt, etc.)
        if (!isLogicGate() && this != LEVER && this != WIRE && this != LOG) {
            return "normal";
        }

        StringBuilder state = new StringBuilder();

        // 2. Logic Gates & Levers (Facing + Powered)
        if (isLogicGate() || this == LEVER) {
            Direction dir = getRotation(meta);
            state.append("facing=").append(dir.name().toLowerCase());
            
            // Logic Gates & Levers use Bit 2 (4) for active
            // Torches (standard) don't use powered bit in metadata for visualization usually, 
            // but we might map TORCH vs TORCH_OFF blocks.
            
            if (this == LEVER || (this.id >= 30 && this.id <= 36)) {
                 boolean powered = isActive(meta);
                 state.append(",powered=").append(powered);
            }
            return state.toString();
        }
        
        // 3. Redstone Wire (Simplified)
        if (this == WIRE) {
            // Full implementation needs neighbor connections "north=side,east=none..."
            // For now, return generic or handle in mesher specially
            return "power=" + (meta); // Assuming meta stores power
        }

        return "normal";
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
        return (meta & 4) != 0; 
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
    
    // ... (Keep existing Physics/Collision methods: getColliders, getSelectionBox) ...
    
    public List<AABB> getColliders(int x, int y, int z, byte metadata) {
        if (!solid) return Collections.emptyList();
        if (isWater()) return Collections.emptyList();
        if (this == WIRE || this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF || this == LEVER) return Collections.emptyList();
        if (this.id >= 30 && this.id <= 36) return Collections.singletonList(new AABB(0, 0, 0, 1, 0.125f, 1));
        return Collections.singletonList(new AABB(0, 0, 0, 1, 1, 1));
    }

    public AABB getSelectionBox(int x, int y, int z, byte metadata) {
        if (this == AIR) return null;
        if (this == REDSTONE_TORCH || this == REDSTONE_TORCH_OFF) return new AABB(0.4f, 0, 0.4f, 0.6f, 0.6f, 0.6f);
        if (this == WIRE) return new AABB(0, 0, 0, 1, 0.1f, 1);
        if (this.isLogicGate() || this.id >= 30 && this.id <= 36) return new AABB(0, 0, 0, 1, 0.125f, 1);
        return new AABB(0, 0, 0, 1, 1, 1);
    }
    
    // Registry Lookup
    private static final Block[] CACHE = new Block[256];
    static { for (Block b : values()) CACHE[b.id] = b; }
    public static Block getById(byte id) {
        if (id < 0 || id >= CACHE.length || CACHE[id] == null) return AIR;
        return CACHE[id];
    }
}