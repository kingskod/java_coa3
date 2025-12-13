package com.voxelengine.render;

import com.voxelengine.world.Block;
import java.util.HashMap;
import java.util.Map;

public class BlockModels {

    private static final Map<Block, BlockModel> models = new HashMap<>();

    public static void init() {
        // --- 1. GENERIC GATE (Slab + Decal) ---
        // Used for: AND, OR, XOR, LATCH, BRIDGE
        BlockModel genericGate = new BlockModel();
        // Base: Smooth Stone (0-16 x, 0-2 y, 0-16 z)
        genericGate.addBox(0, 0, 0, 16, 2, 16, "smooth_stone"); 
        // Top: Decal (Using "#top" tells model to load block_name.png)
        genericGate.addBox(0, 2.01f, 0, 16, 0, 16, "#top");     
        
        models.put(Block.AND_GATE, genericGate);
        models.put(Block.OR_GATE, genericGate);
        models.put(Block.XOR_GATE, genericGate);
        models.put(Block.LATCH_OFF, genericGate);
        models.put(Block.LATCH_ON, genericGate);
        // Note: You need to add BRIDGE to Block.java if you want it, for now using generic for others

        // --- 2. GATES WITH TORCHES (NAND, NOT) ---
        
        // NAND Gate
        BlockModel nand = new BlockModel();
        nand.addBox(0, 0, 0, 16, 2, 16, "smooth_stone");
        nand.addBox(0, 2.01f, 0, 16, 0, 16, "#top");
        // Torch at output (Based on JSON 7,2,2)
        // Position: x=7, y=2, z=2. Size: 2x6x2. Texture: redstone_torch
        nand.addBox(7, 2, 2, 2, 6, 2, "redstone_torch");
        models.put(Block.NAND_GATE, nand);

        // NOT Gate
        BlockModel notGate = new BlockModel();
        notGate.addBox(0, 0, 0, 16, 2, 16, "smooth_stone");
        notGate.addBox(0, 2.01f, 0, 16, 0, 16, "#top");
        // Torch at output
        notGate.addBox(7, 2, 2, 2, 6, 2, "redstone_torch");
        models.put(Block.NOT_GATE, notGate);

        // --- 3. REDSTONE TORCH ---
        BlockModel torch = new BlockModel();
        // Stick: 2x10x2 centered
        torch.addBox(7, 0, 7, 2, 10, 2, "redstone_torch");
        models.put(Block.REDSTONE_TORCH, torch);
        models.put(Block.REDSTONE_TORCH_OFF, torch);

        // --- 4. LEVER ---
        BlockModel lever = new BlockModel();
        // Base: Cobblestone 6x3x8
        lever.addBox(5, 0, 4, 6, 3, 8, "cobblestone");
        // Handle: Stick 2x10x2
        lever.addBox(7, 3, 7, 2, 10, 2, "lever");
        models.put(Block.LEVER, lever);
    }

    public static BlockModel get(Block block) {
        return models.get(block);
    }
}