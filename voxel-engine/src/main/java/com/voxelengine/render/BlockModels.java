package com.voxelengine.render;

import com.voxelengine.world.Block;
import java.util.HashMap;
import java.util.Map;

public class BlockModels {

    private static final Map<Block, BlockModel> models = new HashMap<>();

    public static void init() {
        // --- 1. GENERIC GATE (Slab + Decal) ---
        BlockModel genericGate = new BlockModel();
        genericGate.addBox(0, 0, 0, 16, 2, 16, "smooth_stone"); 
        // FIX: Raised to 2.05f
        genericGate.addBox(0, 2.05f, 0, 16, 0, 16, "#top");     
        
        models.put(Block.AND_GATE, genericGate);
        models.put(Block.OR_GATE, genericGate);
        models.put(Block.XOR_GATE, genericGate);
        models.put(Block.LATCH_OFF, genericGate);
        models.put(Block.LATCH_ON, genericGate);

        // --- 2. NAND GATE ---
        BlockModel nand = new BlockModel();
        nand.addBox(0, 0, 0, 16, 2, 16, "smooth_stone");
        // FIX: Raised to 2.05f
        nand.addBox(0, 2.05f, 0, 16, 0, 16, "#top");
        nand.addBox(7, 2, 2, 2, 6, 2, "redstone_torch");
        models.put(Block.NAND_GATE, nand);

        // --- 3. NOT GATE ---
        BlockModel notGate = new BlockModel();
        notGate.addBox(0, 0, 0, 16, 2, 16, "smooth_stone");
        // FIX: Raised to 2.05f
        notGate.addBox(0, 2.05f, 0, 16, 0, 16, "#top");
        notGate.addBox(7, 2, 2, 2, 6, 2, "redstone_torch");
        models.put(Block.NOT_GATE, notGate);

        // ... (Rest of file same as before) ...
        BlockModel torch = new BlockModel();
        torch.addBox(7, 0, 7, 2, 10, 2, "redstone_torch");
        models.put(Block.REDSTONE_TORCH, torch);
        models.put(Block.REDSTONE_TORCH_OFF, torch);

        BlockModel lever = new BlockModel();
        lever.addBox(5, 0, 4, 6, 3, 8, "cobblestone");
        lever.addBox(7, 3, 7, 2, 10, 2, "lever");
        models.put(Block.LEVER, lever);
    }

    public static BlockModel get(Block block) {
        return models.get(block);
    }
}