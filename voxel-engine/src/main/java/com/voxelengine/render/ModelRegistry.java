package com.voxelengine.render;

import com.voxelengine.render.model.BlockModel;
import com.voxelengine.render.model.BakedModel;
import com.voxelengine.render.model.BakedQuad;
import com.voxelengine.render.model.BlockModel.QuadElement;
import com.voxelengine.world.Block;

import java.util.ArrayList;
import java.util.List;

public class ModelRegistry {

    private static final BakedModel[][] CACHE = new BakedModel[256][16];
    private static TextureAtlas atlas;

    public static void init(TextureAtlas textureAtlas) {
        atlas = textureAtlas;
        System.out.println("Initializing Static Model Registry...");

        for (Block block : Block.values()) {
            if (block == Block.AIR) continue;

            int id = block.getId() & 0xFF;

            for (int meta = 0; meta < 16; meta++) {
                // Get the raw model from the generated class (via helper)
                BlockModel raw = getRawModel(block, (byte) meta);
                
                if (raw == null) {
                    CACHE[id][meta] = BakedModel.EMPTY;
                    continue;
                }

                CACHE[id][meta] = bake(raw);
            }
        }
    }

    /**
     * Bakes the Python-generated quads into Texture-Atlas-aware quads.
     * The python script emits normalized 0..1 UVs. We must remap them to the specific sprite location in the Atlas.
     */
    private static BakedModel bake(BlockModel model) {
        List<BakedQuad> baked = new ArrayList<>();

        for (QuadElement q : model.getQuads()) {
            TextureAtlas.Sprite s = atlas.getSprite(q.texture);

            // Remap 0..1 UVs to Atlas UVs
            float[] uv = new float[8];
            for (int i = 0; i < 8; i += 2) {
                // u_final = u_min + (u_local * width)
                uv[i]     = s.uMin + q.uvs[i]     * (s.uMax - s.uMin);
                // v_final = v_min + (v_local * height)
                uv[i + 1] = s.vMin + q.uvs[i + 1] * (s.vMax - s.vMin);
            }

            baked.add(new BakedQuad(
                    q.positions,
                    uv,
                    q.face,
                    atlas.getIndex(q.texture, q.face),
                    -1
            ));
        }

        return new BakedModel(baked, true);
    }

    public static BakedModel getModel(Block block, byte meta) {
        int id = block.getId() & 0xFF;
        int m = meta & 15;
        // Safety check for race conditions or uninitialized array
        if (CACHE[id] == null || CACHE[id][m] == null) return BakedModel.EMPTY;
        return CACHE[id][m];
    }

    // ================= RAW MODEL DISPATCH =================
    // Maps Block Enum + Meta -> GeneratedBlockModels method
    private static BlockModel getRawModel(Block b, byte meta) {
        boolean active = b.isActive(meta);

        switch (b) {
            case LEVER: return active ? GeneratedBlockModels.createLeverOn() : GeneratedBlockModels.createLever();
            case REDSTONE_TORCH: return GeneratedBlockModels.createRedstoneTorch();
            case REDSTONE_TORCH_OFF: return GeneratedBlockModels.createRedstoneTorchOff();
            case WIRE: return GeneratedBlockModels.createRedstoneWire();
            
            // Logic Gates
            case AND_GATE: return active ? GeneratedBlockModels.createAndGateBlockOn() : GeneratedBlockModels.createAndGateBlock();
            case OR_GATE: return active ? GeneratedBlockModels.createOrGateBlockOn() : GeneratedBlockModels.createOrGateBlock();
            case NOT_GATE: return active ? GeneratedBlockModels.createNotGateBlockOn() : GeneratedBlockModels.createNotGateBlock();
            case NAND_GATE: return active ? GeneratedBlockModels.createNandGateBlockOn() : GeneratedBlockModels.createNandGateBlock();
            case XOR_GATE: return active ? GeneratedBlockModels.createXorGateBlockOn() : GeneratedBlockModels.createXorGateBlock();
            case LATCH_OFF: return GeneratedBlockModels.createSrLatchBlock();
            case LATCH_ON: return GeneratedBlockModels.createSrLatchBlockOn();
            case REDSTONE_LAMP_OFF: return GeneratedBlockModels.createRedstoneLamp();
            case REDSTONE_LAMP_ON: return GeneratedBlockModels.createRedstoneLampOn();
            case REPEATER: return GeneratedBlockModels.createRepeater();
            case COMPARATOR: return GeneratedBlockModels.createComparator();

            // Terrain
            case STONE: return GeneratedBlockModels.createStone();
            case DIRT: return GeneratedBlockModels.createDirt();
            case GRASS: return GeneratedBlockModels.createGrassBlock();
            case COBBLESTONE: return GeneratedBlockModels.createCobblestone();
            case PLANKS: return GeneratedBlockModels.createOakPlanks();
            case BEDROCK: return GeneratedBlockModels.createBedrock();
            case SAND: return GeneratedBlockModels.createSand();
            case GRAVEL: return GeneratedBlockModels.createGravel();
            case DIAMOND_ORE: return GeneratedBlockModels.createDiamondOre();
            case GLASS: return GeneratedBlockModels.createGlass();
            case LOG: return GeneratedBlockModels.createOakLog();

            default: return null;
        }
    }
}