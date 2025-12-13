package com.voxelengine.render;

import com.voxelengine.render.model.BlockModel;
import com.voxelengine.render.model.Element;
import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;

import java.util.HashMap;
import java.util.Map;

public class ModelRegistry {

    private static final Map<Block, BlockModel> models = new HashMap<>();

    public static void init() {
        // --- 1. REDSTONE TORCH ---
        BlockModel torch = new BlockModel();
        // Stick: 7,0,7 -> 9,10,9. Texture: redstone_torch
        torch.addElement(buildElement(7, 0, 7, 9, 10, 9, "redstone_torch"));
        models.put(Block.REDSTONE_TORCH, torch);
        models.put(Block.REDSTONE_TORCH_OFF, torch);

        // --- 2. LOGIC GATES (Generic) ---
        // AND, OR, XOR, LATCH
        registerGate(Block.AND_GATE, "and_gate");
        registerGate(Block.OR_GATE, "or_gate");
        registerGate(Block.XOR_GATE, "xor_gate");
        registerGate(Block.LATCH_OFF, "latch_off");
        registerGate(Block.LATCH_ON, "latch_on");
        registerGate(Block.COMPARATOR, "comparator");
        registerGate(Block.REPEATER, "repeater");

        // --- 3. NAND GATE (Base + Torches) ---
        BlockModel nand = new BlockModel();
        addBasePlate(nand, "nand_gate");
        // Torches from JSON (7,2,2 -> 9,6,4)
        nand.addElement(buildElement(7, 2, 2, 9, 6, 4, "redstone_torch"));
        nand.addElement(buildElement(6, 2, 2, 10, 6, 4, "redstone_torch")); // Simplified visual
        models.put(Block.NAND_GATE, nand);

        // --- 4. NOT GATE (Base + Torch) ---
        BlockModel notGate = new BlockModel();
        addBasePlate(notGate, "not_gate");
        notGate.addElement(buildElement(7, 2, 2, 9, 6, 4, "redstone_torch"));
        models.put(Block.NOT_GATE, notGate);

        // --- 5. LEVER ---
         BlockModel lever = new BlockModel();
        // Base
        lever.addElement(buildElement(5, 0, 4, 11, 3, 12, "cobblestone"));
        lever.addElement(buildElement(7, 3, 7, 9, 13, 9, "lever"));
        
        // --- 6. SLAB ---
        BlockModel slab = new BlockModel();
        slab.addElement(buildElement(0, 0, 0, 16, 8, 16, "smooth_stone")); // Bottom half
        // Note: Real slabs need a "Top Slab" state logic, handled by metadata later
    }

    private static void registerGate(Block block, String topTexture) {
        BlockModel model = new BlockModel();
        addBasePlate(model, topTexture);
        models.put(block, model);
    }

    private static void addBasePlate(BlockModel model, String topTexture) {
        // Base: Smooth Stone (0,0,0 -> 16,2,16)
        model.addElement(buildElement(0, 0, 0, 16, 2, 16, "smooth_stone"));
        
        // Top Decal (0, 2.01, 0 -> 16, 2.01, 16)
        Element top = new Element(0, 2.01f, 0, 16, 2.01f, 16);
        top.addFace(Direction.UP, topTexture, new float[]{0, 0, 16, 16}, 0);
        model.addElement(top);
    }

    // Helper to build a simple box with same texture on all sides
    private static Element buildElement(float x1, float y1, float z1, float x2, float y2, float z2, String texture) {
        Element e = new Element(x1, y1, z1, x2, y2, z2);
        // Box Mapping UVs
        e.addFace(Direction.NORTH, texture, new float[]{16-x2, 16-y2, 16-x1, 16-y1}, 0);
        e.addFace(Direction.SOUTH, texture, new float[]{x1, 16-y2, x2, 16-y1}, 0);
        e.addFace(Direction.EAST, texture, new float[]{16-z2, 16-y2, 16-z1, 16-y1}, 0);
        e.addFace(Direction.WEST, texture, new float[]{z1, 16-y2, z2, 16-y1}, 0);
        e.addFace(Direction.UP, texture, new float[]{x1, z1, x2, z2}, 0);
        e.addFace(Direction.DOWN, texture, new float[]{x1, 16-z2, x2, 16-z1}, 0);
        return e;
    }

    public static BlockModel get(Block block) {
        return models.get(block);
    }
}