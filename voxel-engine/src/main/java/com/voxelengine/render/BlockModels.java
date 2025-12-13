package com.voxelengine.render;

import com.voxelengine.world.Block;
import java.util.HashMap;
import java.util.Map;

public class BlockModels {

    private static final Map<Block, BlockModel> models = new HashMap<>();

    public static void init() {
        // 1. Generic Gate (Base + Top Decal)
        // Used for: AND, OR, XOR, LATCH
        BlockModel genericGate = new BlockModel();
        genericGate.addBox(0, 0, 0, 16, 2, 16, "smooth_stone"); // Base
        genericGate.addBox(0, 2.01f, 0, 16, 0, 16, "#top");     // Decal Symbol
        
        models.put(Block.AND_GATE, genericGate);
        models.put(Block.OR_GATE, genericGate);
        models.put(Block.XOR_GATE, genericGate);
        models.put(Block.LATCH_OFF, genericGate);
        models.put(Block.LATCH_ON, genericGate);

        // 2. NAND Gate (Base + Torches)
        BlockModel nand = new BlockModel();
        nand.addBox(0, 0, 0, 16, 2, 16, "smooth_stone"); // Base
        nand.addBox(0, 2.01f, 0, 16, 0, 16, "#top");     // Symbol
        // Torches from JSON (7,2,2 -> 9,6,4)
        nand.addBox(7, 2, 2, 2, 4, 2, "redstone_torch");
        nand.addBox(6, 2, 2, 4, 4, 2, "redstone_torch"); // Rough approximation of diagonal
        models.put(Block.NAND_GATE, nand);

        // 3. NOT Gate
        BlockModel notGate = new BlockModel();
        notGate.addBox(0, 0, 0, 16, 2, 16, "smooth_stone");
        notGate.addBox(0, 2.01f, 0, 16, 0, 16, "#top");
        notGate.addBox(7, 2, 2, 2, 4, 2, "redstone_torch");
        models.put(Block.NOT_GATE, notGate);
        BlockModel torch = new BlockModel();

        // 4. Redstone Torch
        torch.addBox(7, 0, 7, 2, 10, 2, "redstone_torch_log");
        torch.addBox(6, 0, 6, 4, 4, 4, "redstone_torch_top");
        models.put(Block.REDSTONE_TORCH, torch);
        models.put(Block.REDSTONE_TORCH_OFF, torch);

        // 5. Lever
        BlockModel lever = new BlockModel();
        lever.addBox(5, 0, 4, 6, 3, 8, "cobblestone");
        lever.addBox(7, 3, 7, 2, 10, 2, "lever");
        models.put(Block.LEVER, lever);
    }

    public static BlockModel get(Block block) {
        return models.get(block);
    }
}