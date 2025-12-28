package com.voxelengine.render;

import com.voxelengine.render.model.BakedModel;
import com.voxelengine.render.model.BakedQuad;
import com.voxelengine.render.model.BlockModel;
import com.voxelengine.render.model.Element;
import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;

import java.util.ArrayList;
import java.util.List;

public class ModelRegistry {

    private static final BakedModel[][] fastCache = new BakedModel[256][16];
    private static TextureAtlas atlas;

    public static void init(TextureAtlas textureAtlas) {
        atlas = textureAtlas;
        System.out.println("Initializing Static Model Registry...");

        for (Block b : Block.values()) {
            if (b == Block.AIR) continue;
            
            int id = b.getId() & 0xFF;
            
            // 2. Bake for all 16 metadata states
            for (int meta = 0; meta < 16; meta++) {
                // Pass metadata to get the correct Variant (On/Off)
                BlockModel rawModel = getRawModel(b, (byte)meta);
                
                if (rawModel != null && !rawModel.getElements().isEmpty()) {
                    fastCache[id][meta] = bake(rawModel, b, (byte)meta);
                } else {
                    fastCache[id][meta] = BakedModel.EMPTY;
                }
            }
        }
    }

    /**
     * MAPPING: Now state-aware. Returns different models based on metadata flags.
     */
    private static BlockModel getRawModel(Block b, byte meta) {
        boolean active = b.isActive(meta);
        
        switch (b) {
            // --- State-Aware Blocks ---
            case LEVER: 
                return active ? GeneratedBlockModels.createLeverOn() : GeneratedBlockModels.createLever();
            
            // Torches are usually separate Blocks in standard engine, but if using state mapping:
            case REDSTONE_TORCH: return GeneratedBlockModels.createRedstoneTorch();
            case REDSTONE_TORCH_OFF: return GeneratedBlockModels.createRedstoneTorchOff(); 
            
            // Gates & Lamps
            case AND_GATE: return active ? GeneratedBlockModels.createAndGateBlockOn() : GeneratedBlockModels.createAndGateBlock();
            case OR_GATE: return active ? GeneratedBlockModels.createOrGateBlockOn() : GeneratedBlockModels.createOrGateBlock();
            case NOT_GATE: return active ? GeneratedBlockModels.createNotGateBlockOn() : GeneratedBlockModels.createNotGateBlock();
            case NAND_GATE: return active ? GeneratedBlockModels.createNandGateBlockOn() : GeneratedBlockModels.createNandGateBlock();
            case XOR_GATE: return active ? GeneratedBlockModels.createXorGateBlockOn() : GeneratedBlockModels.createXorGateBlock();
            case LATCH_OFF: return GeneratedBlockModels.createSrLatchBlock();
            case LATCH_ON: return GeneratedBlockModels.createSrLatchBlockOn();
            
            case REDSTONE_LAMP_OFF: return GeneratedBlockModels.createRedstoneLamp();
            case REDSTONE_LAMP_ON: return GeneratedBlockModels.createRedstoneLampOn();

            // --- Static Blocks ---
            case REPEATER: return GeneratedBlockModels.createRepeater();
            case COMPARATOR: return GeneratedBlockModels.createComparator();
            case WIRE: return GeneratedBlockModels.createRedstoneWire();
            
            case STONE: return GeneratedBlockModels.createStone();
            case DIRT: return GeneratedBlockModels.createDirt();
            case GRASS: return GeneratedBlockModels.createGrassBlock();
            case SAND: return GeneratedBlockModels.createSand();
            case GRAVEL: return GeneratedBlockModels.createGravel();
            case DIAMOND_ORE: return GeneratedBlockModels.createDiamondOre();
            case COBBLESTONE: return GeneratedBlockModels.createCobblestone();
            case PLANKS: return GeneratedBlockModels.createOakPlanks();
            case BEDROCK: return GeneratedBlockModels.createBedrock();
            case GLASS: return GeneratedBlockModels.createGlass();
            
            // Trees
            case LOG: return GeneratedBlockModels.createOakLog();
            
            default: return null;
        }
    }

    private static BakedModel bake(BlockModel raw, Block block, byte meta) {
        List<BakedQuad> quads = new ArrayList<>();
        Direction facing = block.getRotation(meta);

        for (Element e : raw.getElements()) {
            String texName = "missing";
            // Grab texture from the first face defined in JSON
            if (!e.faces.isEmpty()) {
                texName = e.faces.values().iterator().next().textureName;
            }
            
            float texIdx = atlas.getIndex(texName, Direction.UP);
            createBoxQuads(quads, e.from.x, e.from.y, e.from.z, e.to.x, e.to.y, e.to.z, texIdx, facing);
        }

        return new BakedModel(quads, true);
    }

    private static void createBoxQuads(List<BakedQuad> quads, float x0, float y0, float z0, float x1, float y1, float z1, float texIdx, Direction rotation) {
        // Apply rotation to the entire box
        // Simple 90 deg rotation logic around center (0.5, 0.5)
        
        // Helper: Rotate point (px, pz)
        // 0 (South): x, z
        // 1 (West):  z, 1-x
        // 2 (North): 1-x, 1-z
        // 3 (East):  1-z, x
        
        float[] p = new float[12];
        float[] uv = new float[]{0, 0, 1, 0, 1, 1, 0, 1}; 

        // Generate aligned box first, then rotate vertices
        // UP Face
        p = new float[]{x0, y1, z1, x1, y1, z1, x1, y1, z0, x0, y1, z0};
        rotateVertices(p, rotation);
        quads.add(new BakedQuad(p.clone(), uv, Direction.UP, texIdx, -1));
        
        // DOWN Face
        p = new float[]{x0, y0, z0, x1, y0, z0, x1, y0, z1, x0, y0, z1};
        rotateVertices(p, rotation);
        quads.add(new BakedQuad(p.clone(), uv, Direction.DOWN, texIdx, -1));
        
        // NORTH
        p = new float[]{x1, y0, z0, x0, y0, z0, x0, y1, z0, x1, y1, z0};
        rotateVertices(p, rotation);
        quads.add(new BakedQuad(p.clone(), uv, Direction.NORTH, texIdx, -1));
        
        // SOUTH
        p = new float[]{x0, y0, z1, x1, y0, z1, x1, y1, z1, x0, y1, z1};
        rotateVertices(p, rotation);
        quads.add(new BakedQuad(p.clone(), uv, Direction.SOUTH, texIdx, -1));
        
        // WEST
        p = new float[]{x0, y0, z0, x0, y0, z1, x0, y1, z1, x0, y1, z0};
        rotateVertices(p, rotation);
        quads.add(new BakedQuad(p.clone(), uv, Direction.WEST, texIdx, -1));
        
        // EAST
        p = new float[]{x1, y0, z1, x1, y0, z0, x1, y1, z0, x1, y1, z1};
        rotateVertices(p, rotation);
        quads.add(new BakedQuad(p.clone(), uv, Direction.EAST, texIdx, -1));
    }
    
    private static void rotateVertices(float[] p, Direction rot) {
        if (rot == Direction.SOUTH) return; // Default
        
        for (int i = 0; i < 12; i += 3) {
            float x = p[i] - 0.5f;
            float z = p[i+2] - 0.5f;
            float nx = x, nz = z;
            
            if (rot == Direction.WEST) {
                nx = z; nz = -x;
            } else if (rot == Direction.NORTH) {
                nx = -x; nz = -z;
            } else if (rot == Direction.EAST) {
                nx = -z; nz = x;
            }
            
            p[i] = nx + 0.5f;
            p[i+2] = nz + 0.5f;
        }
    }

    public static BakedModel getModel(Block block, byte meta) {
        int id = block.getId() & 0xFF;
        int m = meta & 15;
        if (fastCache[id] == null) return BakedModel.EMPTY;
        BakedModel mdl = fastCache[id][m];
        return (mdl != null) ? mdl : BakedModel.EMPTY;
    }
}