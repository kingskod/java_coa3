package com.voxelengine.render;

import com.voxelengine.render.model.BlockModel;
import com.voxelengine.utils.Direction;

public final class GeneratedBlockModels {

    public static BlockModel createAndGateBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "and_gate");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createAndGateBlockOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "and_gate_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBedrock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "bedrock");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "bedrock");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlockEOff() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block_e_off");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlockEOffFlip() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block_e_off");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlockEOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block_e_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlockEOnFlip() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block_e_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlockFlip() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlockOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBiDirectionalRedstoneBridgeBlockOnFlip() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, Direction.UP, "bi_directional_redstone_bridge_block_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "missing");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "missing");
        return model;
    }

    public static BlockModel createCobblestone() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "cobblestone");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "cobblestone");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "cobblestone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "cobblestone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "cobblestone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "cobblestone");
        return model;
    }

    public static BlockModel createComparator() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "comparator");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.37500f,0.12500f,0.68750f,0.25000f,0.12500f,0.68750f,0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.25000f,0.12500f,0.81250f,0.37500f,0.12500f,0.81250f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.25000f,0.12500f,0.68750f,0.25000f,0.12500f,0.81250f,0.25000f,0.43750f,0.81250f,0.25000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.37500f,0.12500f,0.81250f,0.37500f,0.12500f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.75000f,0.12500f,0.68750f,0.62500f,0.12500f,0.68750f,0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.62500f,0.12500f,0.81250f,0.75000f,0.12500f,0.81250f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.62500f,0.12500f,0.68750f,0.62500f,0.12500f,0.81250f,0.62500f,0.43750f,0.81250f,0.62500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.75000f,0.12500f,0.81250f,0.75000f,0.12500f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.31250f,0.25000f,0.43750f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createComparatorOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "comparator_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.37500f,0.12500f,0.68750f,0.25000f,0.12500f,0.68750f,0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.25000f,0.12500f,0.81250f,0.37500f,0.12500f,0.81250f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.25000f,0.12500f,0.68750f,0.25000f,0.12500f,0.81250f,0.25000f,0.43750f,0.81250f,0.25000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.37500f,0.12500f,0.81250f,0.37500f,0.12500f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.31250f,0.25000f,0.43750f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.75000f,0.12500f,0.68750f,0.62500f,0.12500f,0.68750f,0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.62500f,0.12500f,0.81250f,0.75000f,0.12500f,0.81250f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.62500f,0.12500f,0.68750f,0.62500f,0.12500f,0.81250f,0.62500f,0.43750f,0.81250f,0.62500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.75000f,0.12500f,0.81250f,0.75000f,0.12500f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.28125f,0.65625f,0.40625f,0.28125f,0.65625f,0.40625f,0.28125f,0.84375f,0.21875f,0.28125f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.46875f,0.65625f,0.40625f,0.46875f,0.65625f,0.40625f,0.46875f,0.84375f,0.21875f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.28125f,0.65625f,0.40625f,0.28125f,0.65625f,0.40625f,0.46875f,0.65625f,0.21875f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.84375f,0.21875f,0.28125f,0.84375f,0.21875f,0.46875f,0.84375f,0.40625f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.28125f,0.84375f,0.21875f,0.28125f,0.65625f,0.21875f,0.46875f,0.65625f,0.21875f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.65625f,0.40625f,0.28125f,0.84375f,0.40625f,0.46875f,0.84375f,0.40625f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.65625f,0.78125f,0.28125f,0.65625f,0.78125f,0.28125f,0.84375f,0.59375f,0.28125f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.46875f,0.65625f,0.78125f,0.46875f,0.65625f,0.78125f,0.46875f,0.84375f,0.59375f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.65625f,0.78125f,0.28125f,0.65625f,0.78125f,0.46875f,0.65625f,0.59375f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.78125f,0.28125f,0.84375f,0.59375f,0.28125f,0.84375f,0.59375f,0.46875f,0.84375f,0.78125f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.84375f,0.59375f,0.28125f,0.65625f,0.59375f,0.46875f,0.65625f,0.59375f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.78125f,0.28125f,0.65625f,0.78125f,0.28125f,0.84375f,0.78125f,0.46875f,0.84375f,0.78125f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createComparatorOnSubtract() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "comparator_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.31250f,0.25000f,0.43750f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.37500f,0.12500f,0.68750f,0.25000f,0.12500f,0.68750f,0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.25000f,0.12500f,0.81250f,0.37500f,0.12500f,0.81250f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.25000f,0.12500f,0.68750f,0.25000f,0.12500f,0.81250f,0.25000f,0.43750f,0.81250f,0.25000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.37500f,0.12500f,0.81250f,0.37500f,0.12500f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.75000f,0.12500f,0.68750f,0.62500f,0.12500f,0.68750f,0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.62500f,0.12500f,0.81250f,0.75000f,0.12500f,0.81250f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.62500f,0.12500f,0.68750f,0.62500f,0.12500f,0.81250f,0.62500f,0.43750f,0.81250f,0.62500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.75000f,0.12500f,0.81250f,0.75000f,0.12500f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.28125f,0.65625f,0.40625f,0.28125f,0.65625f,0.40625f,0.28125f,0.84375f,0.21875f,0.28125f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.46875f,0.65625f,0.40625f,0.46875f,0.65625f,0.40625f,0.46875f,0.84375f,0.21875f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.28125f,0.65625f,0.40625f,0.28125f,0.65625f,0.40625f,0.46875f,0.65625f,0.21875f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.84375f,0.21875f,0.28125f,0.84375f,0.21875f,0.46875f,0.84375f,0.40625f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.21875f,0.28125f,0.84375f,0.21875f,0.28125f,0.65625f,0.21875f,0.46875f,0.65625f,0.21875f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.65625f,0.40625f,0.28125f,0.84375f,0.40625f,0.46875f,0.84375f,0.40625f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.65625f,0.78125f,0.28125f,0.65625f,0.78125f,0.28125f,0.84375f,0.59375f,0.28125f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.46875f,0.65625f,0.78125f,0.46875f,0.65625f,0.78125f,0.46875f,0.84375f,0.59375f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.65625f,0.78125f,0.28125f,0.65625f,0.78125f,0.46875f,0.65625f,0.59375f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.78125f,0.28125f,0.84375f,0.59375f,0.28125f,0.84375f,0.59375f,0.46875f,0.84375f,0.78125f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.84375f,0.59375f,0.28125f,0.65625f,0.59375f,0.46875f,0.65625f,0.59375f,0.46875f,0.84375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.78125f,0.28125f,0.65625f,0.78125f,0.28125f,0.84375f,0.78125f,0.46875f,0.84375f,0.78125f,0.46875f,0.65625f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.15625f,0.09375f,0.59375f,0.15625f,0.09375f,0.59375f,0.15625f,0.28125f,0.40625f,0.15625f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.34375f,0.09375f,0.59375f,0.34375f,0.09375f,0.59375f,0.34375f,0.28125f,0.40625f,0.34375f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.15625f,0.09375f,0.59375f,0.15625f,0.09375f,0.59375f,0.34375f,0.09375f,0.40625f,0.34375f,0.09375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.15625f,0.28125f,0.40625f,0.15625f,0.28125f,0.40625f,0.34375f,0.28125f,0.59375f,0.34375f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.15625f,0.28125f,0.40625f,0.15625f,0.09375f,0.40625f,0.34375f,0.09375f,0.40625f,0.34375f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.15625f,0.09375f,0.59375f,0.15625f,0.28125f,0.59375f,0.34375f,0.28125f,0.59375f,0.34375f,0.09375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createComparatorSubtract() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "comparator");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.37500f,0.12500f,0.68750f,0.25000f,0.12500f,0.68750f,0.25000f,0.43750f,0.68750f,0.37500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.25000f,0.12500f,0.81250f,0.37500f,0.12500f,0.81250f,0.37500f,0.43750f,0.81250f,0.25000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.25000f,0.12500f,0.68750f,0.25000f,0.12500f,0.81250f,0.25000f,0.43750f,0.81250f,0.25000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.37500f,0.12500f,0.81250f,0.37500f,0.12500f,0.68750f,0.37500f,0.43750f,0.68750f,0.37500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.75000f,0.12500f,0.68750f,0.62500f,0.12500f,0.68750f,0.62500f,0.43750f,0.68750f,0.75000f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.62500f,0.12500f,0.81250f,0.75000f,0.12500f,0.81250f,0.75000f,0.43750f,0.81250f,0.62500f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.62500f,0.12500f,0.68750f,0.62500f,0.12500f,0.81250f,0.62500f,0.43750f,0.81250f,0.62500f,0.43750f,0.68750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.75000f,0.12500f,0.81250f,0.75000f,0.12500f,0.68750f,0.75000f,0.43750f,0.68750f,0.75000f,0.43750f,0.81250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.31250f,0.25000f,0.43750f,0.31250f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.56250f,0.43750f,0.56250f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.15625f,0.09375f,0.59375f,0.15625f,0.09375f,0.59375f,0.15625f,0.28125f,0.40625f,0.15625f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.34375f,0.09375f,0.59375f,0.34375f,0.09375f,0.59375f,0.34375f,0.28125f,0.40625f,0.34375f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.15625f,0.09375f,0.59375f,0.15625f,0.09375f,0.59375f,0.34375f,0.09375f,0.40625f,0.34375f,0.09375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.15625f,0.28125f,0.40625f,0.15625f,0.28125f,0.40625f,0.34375f,0.28125f,0.59375f,0.34375f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.15625f,0.28125f,0.40625f,0.15625f,0.09375f,0.40625f,0.34375f,0.09375f,0.40625f,0.34375f,0.28125f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.15625f,0.09375f,0.59375f,0.15625f,0.28125f,0.59375f,0.34375f,0.28125f,0.59375f,0.34375f,0.09375f}, new float[]{0.37500f,0.31250f,0.43750f,0.31250f,0.43750f,0.37500f,0.37500f,0.37500f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createCubeAll() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "missing");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "missing");
        return model;
    }

    public static BlockModel createCubeColumn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "missing");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "missing");
        return model;
    }

    public static BlockModel createDiamondOre() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "diamond_ore");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "diamond_ore");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "diamond_ore");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "diamond_ore");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "diamond_ore");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "diamond_ore");
        return model;
    }

    public static BlockModel createDirt() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "dirt");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "dirt");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "dirt");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "dirt");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "dirt");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "dirt");
        return model;
    }

    public static BlockModel createFullAdderBottom() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_bottom");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderBottomCarry() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_bottom_carry");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderBottomOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_bottom_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderBottomOnCarry() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_bottom_on_carry");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderMiddle() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_middle");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderMiddleOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_middle_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderTop() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_top");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderTopCarry() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_top_carry");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderTopOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_top_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createFullAdderTopOnCarry() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "full_adder_top_on_carry");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createGlass() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "glass");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "glass");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "glass");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "glass");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "glass");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "glass");
        return model;
    }

    public static BlockModel createGrassBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "dirt");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "grass_block_top");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "grass_block_side");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "grass_block_side");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "grass_block_side");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "grass_block_side");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "grass_block_side_overlay");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "grass_block_side_overlay");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "grass_block_side_overlay");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "grass_block_side_overlay");
        return model;
    }

    public static BlockModel createGravel() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "gravel");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "gravel");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "gravel");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "gravel");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "gravel");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "gravel");
        return model;
    }

    public static BlockModel createLeaves() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "missing");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "missing");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "missing");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "missing");
        return model;
    }

    public static BlockModel createLever() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.31250f,-0.00125f,0.25000f,0.68750f,-0.00125f,0.25000f,0.68750f,-0.00125f,0.75000f,0.31250f,-0.00125f,0.75000f}, new float[]{0.31250f,0.25000f,0.68750f,0.25000f,0.68750f,0.75000f,0.31250f,0.75000f}, Direction.DOWN, "cobblestone");
        model.addQuad(new float[]{0.31250f,0.18625f,0.25000f,0.68750f,0.18625f,0.25000f,0.68750f,0.18625f,0.75000f,0.31250f,0.18625f,0.75000f}, new float[]{0.31250f,0.25000f,0.68750f,0.25000f,0.68750f,0.75000f,0.31250f,0.75000f}, Direction.UP, "cobblestone");
        model.addQuad(new float[]{0.68750f,-0.00125f,0.25000f,0.31250f,-0.00125f,0.25000f,0.31250f,0.18625f,0.25000f,0.68750f,0.18625f,0.25000f}, new float[]{0.31250f,0.00000f,0.68750f,0.00000f,0.68750f,0.18750f,0.31250f,0.18750f}, Direction.NORTH, "cobblestone");
        model.addQuad(new float[]{0.31250f,-0.00125f,0.75000f,0.68750f,-0.00125f,0.75000f,0.68750f,0.18625f,0.75000f,0.31250f,0.18625f,0.75000f}, new float[]{0.31250f,0.00000f,0.68750f,0.00000f,0.68750f,0.18750f,0.31250f,0.18750f}, Direction.SOUTH, "cobblestone");
        model.addQuad(new float[]{0.31250f,-0.00125f,0.25000f,0.31250f,-0.00125f,0.75000f,0.31250f,0.18625f,0.75000f,0.31250f,0.18625f,0.25000f}, new float[]{0.25000f,0.00000f,0.75000f,0.00000f,0.75000f,0.18750f,0.25000f,0.18750f}, Direction.WEST, "cobblestone");
        model.addQuad(new float[]{0.68750f,-0.00125f,0.75000f,0.68750f,-0.00125f,0.25000f,0.68750f,0.18625f,0.25000f,0.68750f,0.18625f,0.75000f}, new float[]{0.25000f,0.00000f,0.75000f,0.00000f,0.75000f,0.18750f,0.25000f,0.18750f}, Direction.EAST, "cobblestone");
        model.addQuad(new float[]{0.43750f,0.46025f,0.01386f,0.56250f,0.46025f,0.01386f,0.56250f,0.54864f,0.10225f,0.43750f,0.54864f,0.10225f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "lever");
        model.addQuad(new float[]{0.56250f,0.01831f,0.45581f,0.43750f,0.01831f,0.45581f,0.43750f,0.46025f,0.01386f,0.56250f,0.46025f,0.01386f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.NORTH, "lever");
        model.addQuad(new float[]{0.43750f,0.10669f,0.54419f,0.56250f,0.10669f,0.54419f,0.56250f,0.54864f,0.10225f,0.43750f,0.54864f,0.10225f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.SOUTH, "lever");
        model.addQuad(new float[]{0.43750f,0.01831f,0.45581f,0.43750f,0.10669f,0.54419f,0.43750f,0.54864f,0.10225f,0.43750f,0.46025f,0.01386f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.WEST, "lever");
        model.addQuad(new float[]{0.56250f,0.10669f,0.54419f,0.56250f,0.01831f,0.45581f,0.56250f,0.46025f,0.01386f,0.56250f,0.54864f,0.10225f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.EAST, "lever");
        return model;
    }

    public static BlockModel createLeverOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.31250f,-0.00125f,0.25000f,0.68750f,-0.00125f,0.25000f,0.68750f,-0.00125f,0.75000f,0.31250f,-0.00125f,0.75000f}, new float[]{0.31250f,0.25000f,0.68750f,0.25000f,0.68750f,0.75000f,0.31250f,0.75000f}, Direction.DOWN, "cobblestone");
        model.addQuad(new float[]{0.31250f,0.18625f,0.25000f,0.68750f,0.18625f,0.25000f,0.68750f,0.18625f,0.75000f,0.31250f,0.18625f,0.75000f}, new float[]{0.31250f,0.25000f,0.68750f,0.25000f,0.68750f,0.75000f,0.31250f,0.75000f}, Direction.UP, "cobblestone");
        model.addQuad(new float[]{0.68750f,-0.00125f,0.25000f,0.31250f,-0.00125f,0.25000f,0.31250f,0.18625f,0.25000f,0.68750f,0.18625f,0.25000f}, new float[]{0.31250f,0.00000f,0.68750f,0.00000f,0.68750f,0.18750f,0.31250f,0.18750f}, Direction.NORTH, "cobblestone");
        model.addQuad(new float[]{0.31250f,-0.00125f,0.75000f,0.68750f,-0.00125f,0.75000f,0.68750f,0.18625f,0.75000f,0.31250f,0.18625f,0.75000f}, new float[]{0.31250f,0.00000f,0.68750f,0.00000f,0.68750f,0.18750f,0.31250f,0.18750f}, Direction.SOUTH, "cobblestone");
        model.addQuad(new float[]{0.31250f,-0.00125f,0.25000f,0.31250f,-0.00125f,0.75000f,0.31250f,0.18625f,0.75000f,0.31250f,0.18625f,0.25000f}, new float[]{0.25000f,0.00000f,0.75000f,0.00000f,0.75000f,0.18750f,0.25000f,0.18750f}, Direction.WEST, "cobblestone");
        model.addQuad(new float[]{0.68750f,-0.00125f,0.75000f,0.68750f,-0.00125f,0.25000f,0.68750f,0.18625f,0.25000f,0.68750f,0.18625f,0.75000f}, new float[]{0.25000f,0.00000f,0.75000f,0.00000f,0.75000f,0.18750f,0.25000f,0.18750f}, Direction.EAST, "cobblestone");
        model.addQuad(new float[]{0.43750f,0.54864f,0.89775f,0.56250f,0.54864f,0.89775f,0.56250f,0.46025f,0.98614f,0.43750f,0.46025f,0.98614f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "lever");
        model.addQuad(new float[]{0.56250f,0.10669f,0.45581f,0.43750f,0.10669f,0.45581f,0.43750f,0.54864f,0.89775f,0.56250f,0.54864f,0.89775f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.NORTH, "lever");
        model.addQuad(new float[]{0.43750f,0.01831f,0.54419f,0.56250f,0.01831f,0.54419f,0.56250f,0.46025f,0.98614f,0.43750f,0.46025f,0.98614f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.SOUTH, "lever");
        model.addQuad(new float[]{0.43750f,0.10669f,0.45581f,0.43750f,0.01831f,0.54419f,0.43750f,0.46025f,0.98614f,0.43750f,0.54864f,0.89775f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.WEST, "lever");
        model.addQuad(new float[]{0.56250f,0.01831f,0.54419f,0.56250f,0.10669f,0.45581f,0.56250f,0.54864f,0.89775f,0.56250f,0.46025f,0.98614f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.EAST, "lever");
        return model;
    }

    public static BlockModel createNandGateBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "nand_gate");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.56250f,0.12500f,0.12500f,0.56250f,0.12500f,0.25000f,0.43750f,0.12500f,0.25000f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.25000f,0.25000f,0.43750f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.31250f,0.12500f,0.56250f,0.31250f,0.12500f,0.56250f,0.31250f,0.25000f,0.43750f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.43125f,0.12500f,0.06250f,0.43125f,0.12500f,0.31250f,0.43125f,0.37500f,0.31250f,0.43125f,0.37500f,0.06250f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56875f,0.12500f,0.31250f,0.56875f,0.12500f,0.06250f,0.56875f,0.37500f,0.06250f,0.56875f,0.37500f,0.31250f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.62500f,0.12500f,0.11875f,0.37500f,0.12500f,0.11875f,0.37500f,0.37500f,0.11875f,0.62500f,0.37500f,0.11875f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.37500f,0.12500f,0.25625f,0.62500f,0.12500f,0.25625f,0.62500f,0.37500f,0.25625f,0.37500f,0.37500f,0.25625f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.SOUTH, "redstone_torch");
        return model;
    }

    public static BlockModel createNandGateBlockOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "nand_gate_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.56250f,0.12500f,0.12500f,0.56250f,0.12500f,0.25000f,0.43750f,0.12500f,0.25000f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.25000f,0.25000f,0.43750f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createNotGateBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "not_gate");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.56250f,0.12500f,0.12500f,0.56250f,0.12500f,0.25000f,0.43750f,0.12500f,0.25000f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.25000f,0.25000f,0.43750f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.43125f,0.31250f,0.12500f,0.56875f,0.31250f,0.12500f,0.56875f,0.31250f,0.25000f,0.43125f,0.31250f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.43125f,0.12500f,0.06250f,0.43125f,0.12500f,0.31250f,0.43125f,0.37500f,0.31250f,0.43125f,0.37500f,0.06250f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56875f,0.12500f,0.31250f,0.56875f,0.12500f,0.06250f,0.56875f,0.37500f,0.06250f,0.56875f,0.37500f,0.31250f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.62500f,0.12500f,0.11875f,0.37500f,0.12500f,0.11875f,0.37500f,0.37500f,0.11875f,0.62500f,0.37500f,0.11875f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.37500f,0.12500f,0.25625f,0.62500f,0.12500f,0.25625f,0.62500f,0.37500f,0.25625f,0.37500f,0.37500f,0.25625f}, new float[]{0.37500f,0.31250f,0.62500f,0.31250f,0.62500f,0.56250f,0.37500f,0.56250f}, Direction.SOUTH, "redstone_torch");
        return model;
    }

    public static BlockModel createNotGateBlockOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "not_gate_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.56250f,0.12500f,0.12500f,0.56250f,0.12500f,0.25000f,0.43750f,0.12500f,0.25000f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.25000f,0.12500f,0.56250f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.25000f,0.25000f,0.43750f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.25000f,0.25000f,0.43750f,0.25000f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.25000f,0.12500f,0.56250f,0.25000f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createOakLog() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "oak_log_top");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "oak_log_top");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "oak_log");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "oak_log");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "oak_log");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "oak_log");
        return model;
    }

    public static BlockModel createOakPlanks() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "oak_planks");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "oak_planks");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "oak_planks");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "oak_planks");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "oak_planks");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "oak_planks");
        return model;
    }

    public static BlockModel createOmniDirectionalRedstoneBridgeBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "omni_directional_redstone_bridge_block");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "omni_directional_redstone_bridge_block_x");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "omni_directional_redstone_bridge_block_x");
        model.addQuad(new float[]{1.00000f,0.12500f,0.00000f,0.00000f,0.12500f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.NORTH, "omni_directional_redstone_bridge_block_x");
        model.addQuad(new float[]{0.00000f,0.12500f,1.00000f,1.00000f,0.12500f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.SOUTH, "omni_directional_redstone_bridge_block_x");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.WEST, "omni_directional_redstone_bridge_block_x");
        model.addQuad(new float[]{1.00000f,0.12500f,1.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.EAST, "omni_directional_redstone_bridge_block_x");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "omni_directional_redstone_bridge_block_z");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "omni_directional_redstone_bridge_block_z");
        model.addQuad(new float[]{1.00000f,0.12500f,0.00000f,0.00000f,0.12500f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.NORTH, "omni_directional_redstone_bridge_block_z");
        model.addQuad(new float[]{0.00000f,0.12500f,1.00000f,1.00000f,0.12500f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.SOUTH, "omni_directional_redstone_bridge_block_z");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.WEST, "omni_directional_redstone_bridge_block_z");
        model.addQuad(new float[]{1.00000f,0.12500f,1.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.EAST, "omni_directional_redstone_bridge_block_z");
        return model;
    }

    public static BlockModel createOrGateBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "or_gate");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createOrGateBlockOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "or_gate_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createRedstoneDustDot() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.DOWN, "redstone_dust_dot");
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "redstone_dust_dot");
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.DOWN, "redstone_dust_overlay");
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "redstone_dust_overlay");
        return model;
    }

    public static BlockModel createRedstoneLamp() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "redstone_lamp");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "redstone_lamp");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "redstone_lamp");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "redstone_lamp");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "redstone_lamp");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "redstone_lamp");
        return model;
    }

    public static BlockModel createRedstoneLampOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "redstone_lamp_on");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "redstone_lamp_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "redstone_lamp_on");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "redstone_lamp_on");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "redstone_lamp_on");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "redstone_lamp_on");
        return model;
    }

    public static BlockModel createRedstoneTorch() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.56250f,0.00000f,0.43750f,0.56250f,0.00000f,0.56250f,0.43750f,0.00000f,0.56250f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.00000f,0.43750f,0.43750f,0.00000f,0.43750f,0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.00000f,0.56250f,0.56250f,0.00000f,0.56250f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.43750f,0.00000f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.00000f,0.56250f,0.56250f,0.00000f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.40625f,0.59375f,0.46875f,0.40625f,0.59375f,0.46875f,0.59375f,0.40625f,0.46875f,0.59375f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.65625f,0.40625f,0.59375f,0.65625f,0.40625f,0.59375f,0.65625f,0.59375f,0.40625f,0.65625f,0.59375f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.40625f,0.59375f,0.46875f,0.40625f,0.59375f,0.65625f,0.40625f,0.40625f,0.65625f,0.40625f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.46875f,0.40625f,0.59375f,0.46875f,0.59375f,0.59375f,0.65625f,0.59375f,0.59375f,0.65625f,0.40625f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.46875f,0.59375f,0.40625f,0.46875f,0.59375f,0.40625f,0.65625f,0.59375f,0.59375f,0.65625f,0.59375f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.59375f,0.40625f,0.46875f,0.40625f,0.40625f,0.65625f,0.40625f,0.40625f,0.65625f,0.59375f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        return model;
    }

    public static BlockModel createRedstoneTorchOff() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.56250f,0.00000f,0.43750f,0.56250f,0.00000f,0.56250f,0.43750f,0.00000f,0.56250f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.00000f,0.43750f,0.43750f,0.00000f,0.43750f,0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.00000f,0.56250f,0.56250f,0.00000f,0.56250f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.43750f,0.00000f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.00000f,0.56250f,0.56250f,0.00000f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRedstoneWire() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.DOWN, "redstone_dust_dot");
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "redstone_dust_dot");
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,0.00000f,0.00000f}, Direction.DOWN, "redstone_dust_overlay");
        model.addQuad(new float[]{0.00000f,0.01562f,0.00000f,1.00000f,0.01562f,0.00000f,1.00000f,0.01562f,1.00000f,0.00000f,0.01562f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "redstone_dust_overlay");
        return model;
    }

    public static BlockModel createRepeater() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.37500f,0.56250f,0.43750f,0.37500f,0.56250f,0.43750f,0.50000f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.37500f,0.43750f,0.12500f,0.37500f,0.43750f,0.43750f,0.37500f,0.56250f,0.43750f,0.37500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.50000f,0.56250f,0.12500f,0.50000f,0.56250f,0.43750f,0.50000f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.37500f,0.43750f,0.12500f,0.50000f,0.43750f,0.43750f,0.50000f,0.43750f,0.43750f,0.37500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.50000f,0.56250f,0.12500f,0.37500f,0.56250f,0.43750f,0.37500f,0.56250f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater1tick() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.37500f,0.56250f,0.43750f,0.37500f,0.56250f,0.43750f,0.50000f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.37500f,0.43750f,0.12500f,0.37500f,0.43750f,0.43750f,0.37500f,0.56250f,0.43750f,0.37500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.50000f,0.56250f,0.12500f,0.50000f,0.56250f,0.43750f,0.50000f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.37500f,0.43750f,0.12500f,0.50000f,0.43750f,0.43750f,0.50000f,0.43750f,0.43750f,0.37500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.50000f,0.56250f,0.12500f,0.37500f,0.56250f,0.43750f,0.37500f,0.56250f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater1tickLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.37500f,0.87500f,0.25000f,0.37500f,0.87500f,0.25000f,0.50000f,0.12500f,0.25000f,0.50000f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.37500f,0.12500f,0.12500f,0.37500f,0.12500f,0.25000f,0.37500f,0.87500f,0.25000f,0.37500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.50000f,0.87500f,0.12500f,0.50000f,0.87500f,0.25000f,0.50000f,0.12500f,0.25000f,0.50000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.37500f,0.12500f,0.12500f,0.50000f,0.12500f,0.25000f,0.50000f,0.12500f,0.25000f,0.37500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.50000f,0.87500f,0.12500f,0.37500f,0.87500f,0.25000f,0.37500f,0.87500f,0.25000f,0.50000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater1tickOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.37500f,0.56250f,0.43750f,0.37500f,0.56250f,0.43750f,0.50000f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.37500f,0.43750f,0.12500f,0.37500f,0.43750f,0.43750f,0.37500f,0.56250f,0.43750f,0.37500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.50000f,0.56250f,0.12500f,0.50000f,0.56250f,0.43750f,0.50000f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.37500f,0.43750f,0.12500f,0.50000f,0.43750f,0.43750f,0.50000f,0.43750f,0.43750f,0.37500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.50000f,0.56250f,0.12500f,0.37500f,0.56250f,0.43750f,0.37500f,0.56250f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.34375f,0.59375f,0.28125f,0.34375f,0.59375f,0.28125f,0.53125f,0.40625f,0.28125f,0.53125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.34375f,0.59375f,0.46875f,0.34375f,0.59375f,0.46875f,0.53125f,0.40625f,0.46875f,0.53125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.34375f,0.59375f,0.28125f,0.34375f,0.59375f,0.46875f,0.34375f,0.40625f,0.46875f,0.34375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.53125f,0.40625f,0.28125f,0.53125f,0.40625f,0.46875f,0.53125f,0.59375f,0.46875f,0.53125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.53125f,0.40625f,0.28125f,0.34375f,0.40625f,0.46875f,0.34375f,0.40625f,0.46875f,0.53125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.34375f,0.59375f,0.28125f,0.53125f,0.59375f,0.46875f,0.53125f,0.59375f,0.46875f,0.34375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createRepeater1tickOnLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.37500f,0.87500f,0.25000f,0.37500f,0.87500f,0.25000f,0.50000f,0.12500f,0.25000f,0.50000f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.37500f,0.12500f,0.12500f,0.37500f,0.12500f,0.25000f,0.37500f,0.87500f,0.25000f,0.37500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.50000f,0.87500f,0.12500f,0.50000f,0.87500f,0.25000f,0.50000f,0.12500f,0.25000f,0.50000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.37500f,0.12500f,0.12500f,0.50000f,0.12500f,0.25000f,0.50000f,0.12500f,0.25000f,0.37500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.50000f,0.87500f,0.12500f,0.37500f,0.87500f,0.25000f,0.37500f,0.87500f,0.25000f,0.50000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createRepeater2tick() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.50000f,0.56250f,0.43750f,0.50000f,0.56250f,0.43750f,0.62500f,0.43750f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.50000f,0.43750f,0.12500f,0.50000f,0.43750f,0.43750f,0.50000f,0.56250f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.62500f,0.56250f,0.12500f,0.62500f,0.56250f,0.43750f,0.62500f,0.43750f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.50000f,0.43750f,0.12500f,0.62500f,0.43750f,0.43750f,0.62500f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.62500f,0.56250f,0.12500f,0.50000f,0.56250f,0.43750f,0.50000f,0.56250f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater2tickLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.50000f,0.87500f,0.25000f,0.50000f,0.87500f,0.25000f,0.62500f,0.12500f,0.25000f,0.62500f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.50000f,0.12500f,0.12500f,0.50000f,0.12500f,0.25000f,0.50000f,0.87500f,0.25000f,0.50000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.62500f,0.87500f,0.12500f,0.62500f,0.87500f,0.25000f,0.62500f,0.12500f,0.25000f,0.62500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.50000f,0.12500f,0.12500f,0.62500f,0.12500f,0.25000f,0.62500f,0.12500f,0.25000f,0.50000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.62500f,0.87500f,0.12500f,0.50000f,0.87500f,0.25000f,0.50000f,0.87500f,0.25000f,0.62500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater2tickOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.50000f,0.56250f,0.43750f,0.50000f,0.56250f,0.43750f,0.62500f,0.43750f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.50000f,0.43750f,0.12500f,0.50000f,0.43750f,0.43750f,0.50000f,0.56250f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.62500f,0.56250f,0.12500f,0.62500f,0.56250f,0.43750f,0.62500f,0.43750f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.50000f,0.43750f,0.12500f,0.62500f,0.43750f,0.43750f,0.62500f,0.43750f,0.43750f,0.50000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.62500f,0.56250f,0.12500f,0.50000f,0.56250f,0.43750f,0.50000f,0.56250f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.46875f,0.59375f,0.28125f,0.46875f,0.59375f,0.28125f,0.65625f,0.40625f,0.28125f,0.65625f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.46875f,0.59375f,0.46875f,0.46875f,0.59375f,0.46875f,0.65625f,0.40625f,0.46875f,0.65625f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.46875f,0.59375f,0.28125f,0.46875f,0.59375f,0.46875f,0.46875f,0.40625f,0.46875f,0.46875f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.65625f,0.40625f,0.28125f,0.65625f,0.40625f,0.46875f,0.65625f,0.59375f,0.46875f,0.65625f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.65625f,0.40625f,0.28125f,0.46875f,0.40625f,0.46875f,0.46875f,0.40625f,0.46875f,0.65625f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.46875f,0.59375f,0.28125f,0.65625f,0.59375f,0.46875f,0.65625f,0.59375f,0.46875f,0.46875f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createRepeater2tickOnLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.50000f,0.87500f,0.25000f,0.50000f,0.87500f,0.25000f,0.62500f,0.12500f,0.25000f,0.62500f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.50000f,0.12500f,0.12500f,0.50000f,0.12500f,0.25000f,0.50000f,0.87500f,0.25000f,0.50000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.62500f,0.87500f,0.12500f,0.62500f,0.87500f,0.25000f,0.62500f,0.12500f,0.25000f,0.62500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.50000f,0.12500f,0.12500f,0.62500f,0.12500f,0.25000f,0.62500f,0.12500f,0.25000f,0.50000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.62500f,0.87500f,0.12500f,0.50000f,0.87500f,0.25000f,0.50000f,0.87500f,0.25000f,0.62500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createRepeater3tick() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.75000f,0.43750f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.62500f,0.43750f,0.12500f,0.62500f,0.43750f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.75000f,0.56250f,0.12500f,0.75000f,0.56250f,0.43750f,0.75000f,0.43750f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.62500f,0.43750f,0.12500f,0.75000f,0.43750f,0.43750f,0.75000f,0.43750f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.75000f,0.56250f,0.12500f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater3tickLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.62500f,0.87500f,0.25000f,0.62500f,0.87500f,0.25000f,0.75000f,0.12500f,0.25000f,0.75000f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.62500f,0.12500f,0.12500f,0.62500f,0.12500f,0.25000f,0.62500f,0.87500f,0.25000f,0.62500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.75000f,0.87500f,0.12500f,0.75000f,0.87500f,0.25000f,0.75000f,0.12500f,0.25000f,0.75000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.62500f,0.12500f,0.12500f,0.75000f,0.12500f,0.25000f,0.75000f,0.12500f,0.25000f,0.62500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.75000f,0.87500f,0.12500f,0.62500f,0.87500f,0.25000f,0.62500f,0.87500f,0.25000f,0.75000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater3tickOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.75000f,0.43750f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.62500f,0.43750f,0.12500f,0.62500f,0.43750f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.75000f,0.56250f,0.12500f,0.75000f,0.56250f,0.43750f,0.75000f,0.43750f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.62500f,0.43750f,0.12500f,0.75000f,0.43750f,0.43750f,0.75000f,0.43750f,0.43750f,0.62500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.75000f,0.56250f,0.12500f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.59375f,0.59375f,0.28125f,0.59375f,0.59375f,0.28125f,0.78125f,0.40625f,0.28125f,0.78125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.59375f,0.59375f,0.46875f,0.59375f,0.59375f,0.46875f,0.78125f,0.40625f,0.46875f,0.78125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.59375f,0.59375f,0.28125f,0.59375f,0.59375f,0.46875f,0.59375f,0.40625f,0.46875f,0.59375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.78125f,0.40625f,0.28125f,0.78125f,0.40625f,0.46875f,0.78125f,0.59375f,0.46875f,0.78125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.78125f,0.40625f,0.28125f,0.59375f,0.40625f,0.46875f,0.59375f,0.40625f,0.46875f,0.78125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.59375f,0.59375f,0.28125f,0.78125f,0.59375f,0.46875f,0.78125f,0.59375f,0.46875f,0.59375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createRepeater3tickOnLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.62500f,0.87500f,0.25000f,0.62500f,0.87500f,0.25000f,0.75000f,0.12500f,0.25000f,0.75000f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.62500f,0.12500f,0.12500f,0.62500f,0.12500f,0.25000f,0.62500f,0.87500f,0.25000f,0.62500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.75000f,0.87500f,0.12500f,0.75000f,0.87500f,0.25000f,0.75000f,0.12500f,0.25000f,0.75000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.62500f,0.12500f,0.12500f,0.75000f,0.12500f,0.25000f,0.75000f,0.12500f,0.25000f,0.62500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.75000f,0.87500f,0.12500f,0.62500f,0.87500f,0.25000f,0.62500f,0.87500f,0.25000f,0.75000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createRepeater4tick() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.75000f,0.56250f,0.43750f,0.75000f,0.56250f,0.43750f,0.87500f,0.43750f,0.43750f,0.87500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.75000f,0.43750f,0.12500f,0.75000f,0.43750f,0.43750f,0.75000f,0.56250f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.87500f,0.56250f,0.12500f,0.87500f,0.56250f,0.43750f,0.87500f,0.43750f,0.43750f,0.87500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.75000f,0.43750f,0.12500f,0.87500f,0.43750f,0.43750f,0.87500f,0.43750f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.87500f,0.56250f,0.12500f,0.75000f,0.56250f,0.43750f,0.75000f,0.56250f,0.43750f,0.87500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater4tickLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.75000f,0.87500f,0.25000f,0.75000f,0.87500f,0.25000f,0.87500f,0.12500f,0.25000f,0.87500f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.75000f,0.12500f,0.12500f,0.75000f,0.12500f,0.25000f,0.75000f,0.87500f,0.25000f,0.75000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.87500f,0.87500f,0.12500f,0.87500f,0.87500f,0.25000f,0.87500f,0.12500f,0.25000f,0.87500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.75000f,0.12500f,0.12500f,0.87500f,0.12500f,0.25000f,0.87500f,0.12500f,0.25000f,0.75000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.87500f,0.87500f,0.12500f,0.75000f,0.87500f,0.25000f,0.75000f,0.87500f,0.25000f,0.87500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch_off");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch_off");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch_off");
        return model;
    }

    public static BlockModel createRepeater4tickOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.43750f,0.43750f,0.75000f,0.56250f,0.43750f,0.75000f,0.56250f,0.43750f,0.87500f,0.43750f,0.43750f,0.87500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.75000f,0.43750f,0.12500f,0.75000f,0.43750f,0.43750f,0.75000f,0.56250f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.87500f,0.56250f,0.12500f,0.87500f,0.56250f,0.43750f,0.87500f,0.43750f,0.43750f,0.87500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.75000f,0.43750f,0.12500f,0.87500f,0.43750f,0.43750f,0.87500f,0.43750f,0.43750f,0.75000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.87500f,0.56250f,0.12500f,0.75000f,0.56250f,0.43750f,0.75000f,0.56250f,0.43750f,0.87500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.71875f,0.59375f,0.28125f,0.71875f,0.59375f,0.28125f,0.90625f,0.40625f,0.28125f,0.90625f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.71875f,0.59375f,0.46875f,0.71875f,0.59375f,0.46875f,0.90625f,0.40625f,0.46875f,0.90625f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.71875f,0.59375f,0.28125f,0.71875f,0.59375f,0.46875f,0.71875f,0.40625f,0.46875f,0.71875f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.90625f,0.40625f,0.28125f,0.90625f,0.40625f,0.46875f,0.90625f,0.59375f,0.46875f,0.90625f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.90625f,0.40625f,0.28125f,0.71875f,0.40625f,0.46875f,0.71875f,0.40625f,0.46875f,0.90625f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.71875f,0.59375f,0.28125f,0.90625f,0.59375f,0.46875f,0.90625f,0.59375f,0.46875f,0.71875f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createRepeater4tickOnLocked() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "repeater_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        model.addQuad(new float[]{0.12500f,0.25000f,0.75000f,0.87500f,0.25000f,0.75000f,0.87500f,0.25000f,0.87500f,0.12500f,0.25000f,0.87500f}, new float[]{0.43750f,0.12500f,0.56250f,0.12500f,0.56250f,0.87500f,0.43750f,0.87500f}, Direction.UP, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.75000f,0.12500f,0.12500f,0.75000f,0.12500f,0.25000f,0.75000f,0.87500f,0.25000f,0.75000f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.NORTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.87500f,0.87500f,0.12500f,0.87500f,0.87500f,0.25000f,0.87500f,0.12500f,0.25000f,0.87500f}, new float[]{0.12500f,0.43750f,0.87500f,0.43750f,0.87500f,0.56250f,0.12500f,0.56250f}, Direction.SOUTH, "bedrock");
        model.addQuad(new float[]{0.12500f,0.12500f,0.75000f,0.12500f,0.12500f,0.87500f,0.12500f,0.25000f,0.87500f,0.12500f,0.25000f,0.75000f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.WEST, "bedrock");
        model.addQuad(new float[]{0.87500f,0.12500f,0.87500f,0.87500f,0.12500f,0.75000f,0.87500f,0.25000f,0.75000f,0.87500f,0.25000f,0.87500f}, new float[]{0.37500f,0.43750f,0.50000f,0.43750f,0.50000f,0.56250f,0.37500f,0.56250f}, Direction.EAST, "bedrock");
        model.addQuad(new float[]{0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.12500f,0.43750f,0.12500f,0.12500f,0.43750f,0.43750f,0.12500f,0.56250f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.25000f,0.56250f,0.12500f,0.25000f,0.56250f,0.43750f,0.25000f,0.43750f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.43750f,0.12500f,0.12500f,0.43750f,0.12500f,0.25000f,0.43750f,0.43750f,0.25000f,0.43750f,0.43750f,0.12500f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.WEST, "redstone_torch");
        model.addQuad(new float[]{0.56250f,0.12500f,0.25000f,0.56250f,0.12500f,0.12500f,0.56250f,0.43750f,0.12500f,0.56250f,0.43750f,0.25000f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.68750f,0.43750f,0.68750f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.46875f,0.09375f,0.59375f,0.46875f,0.09375f,0.59375f,0.46875f,0.28125f,0.40625f,0.46875f,0.28125f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.09375f,0.59375f,0.28125f,0.09375f,0.59375f,0.46875f,0.09375f,0.40625f,0.46875f,0.09375f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.28125f,0.40625f,0.28125f,0.28125f,0.40625f,0.46875f,0.28125f,0.59375f,0.46875f,0.28125f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "redstone_torch");
        model.addQuad(new float[]{0.40625f,0.28125f,0.28125f,0.40625f,0.28125f,0.09375f,0.40625f,0.46875f,0.09375f,0.40625f,0.46875f,0.28125f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "redstone_torch");
        model.addQuad(new float[]{0.59375f,0.28125f,0.09375f,0.59375f,0.28125f,0.28125f,0.59375f,0.46875f,0.28125f,0.59375f,0.46875f,0.09375f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "redstone_torch");
        return model;
    }

    public static BlockModel createSand() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "sand");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "sand");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "sand");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "sand");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "sand");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "sand");
        return model;
    }

    public static BlockModel createSrLatchBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "sr_latch");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createSrLatchBlockFlip() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, Direction.UP, "sr_latch");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createSrLatchBlockOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "sr_latch_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createSrLatchBlockOnFlip() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, Direction.UP, "sr_latch_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createStone() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "stone");
        model.addQuad(new float[]{0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "stone");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,0.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "stone");
        return model;
    }

    public static BlockModel createTemplateRedstoneTorch() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.56250f,0.00000f,0.43750f,0.56250f,0.00000f,0.56250f,0.43750f,0.00000f,0.56250f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "missing");
        model.addQuad(new float[]{0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "missing");
        model.addQuad(new float[]{0.56250f,0.00000f,0.43750f,0.43750f,0.00000f,0.43750f,0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.NORTH, "missing");
        model.addQuad(new float[]{0.43750f,0.00000f,0.56250f,0.56250f,0.00000f,0.56250f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.SOUTH, "missing");
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.43750f,0.00000f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.WEST, "missing");
        model.addQuad(new float[]{0.56250f,0.00000f,0.56250f,0.56250f,0.00000f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.EAST, "missing");
        model.addQuad(new float[]{0.40625f,0.46875f,0.40625f,0.59375f,0.46875f,0.40625f,0.59375f,0.46875f,0.59375f,0.40625f,0.46875f,0.59375f}, new float[]{0.50000f,0.31250f,0.56250f,0.31250f,0.56250f,0.37500f,0.50000f,0.37500f}, Direction.UP, "missing");
        model.addQuad(new float[]{0.40625f,0.65625f,0.40625f,0.59375f,0.65625f,0.40625f,0.59375f,0.65625f,0.59375f,0.40625f,0.65625f,0.59375f}, new float[]{0.43750f,0.31250f,0.50000f,0.31250f,0.50000f,0.37500f,0.43750f,0.37500f}, Direction.DOWN, "missing");
        model.addQuad(new float[]{0.40625f,0.46875f,0.40625f,0.59375f,0.46875f,0.40625f,0.59375f,0.65625f,0.40625f,0.40625f,0.65625f,0.40625f}, new float[]{0.56250f,0.37500f,0.62500f,0.37500f,0.62500f,0.43750f,0.56250f,0.43750f}, Direction.SOUTH, "missing");
        model.addQuad(new float[]{0.59375f,0.46875f,0.40625f,0.59375f,0.46875f,0.59375f,0.59375f,0.65625f,0.59375f,0.59375f,0.65625f,0.40625f}, new float[]{0.37500f,0.43750f,0.43750f,0.43750f,0.43750f,0.50000f,0.37500f,0.50000f}, Direction.WEST, "missing");
        model.addQuad(new float[]{0.59375f,0.46875f,0.59375f,0.40625f,0.46875f,0.59375f,0.40625f,0.65625f,0.59375f,0.59375f,0.65625f,0.59375f}, new float[]{0.37500f,0.37500f,0.43750f,0.37500f,0.43750f,0.43750f,0.37500f,0.43750f}, Direction.NORTH, "missing");
        model.addQuad(new float[]{0.40625f,0.46875f,0.59375f,0.40625f,0.46875f,0.40625f,0.40625f,0.65625f,0.40625f,0.40625f,0.65625f,0.59375f}, new float[]{0.56250f,0.43750f,0.62500f,0.43750f,0.62500f,0.50000f,0.56250f,0.50000f}, Direction.EAST, "missing");
        return model;
    }

    public static BlockModel createTemplateTorchUnlit() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.56250f,0.00000f,0.43750f,0.56250f,0.00000f,0.56250f,0.43750f,0.00000f,0.56250f}, new float[]{0.43750f,0.81250f,0.56250f,0.81250f,0.56250f,0.93750f,0.43750f,0.93750f}, Direction.DOWN, "missing");
        model.addQuad(new float[]{0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,0.50000f,0.43750f,0.50000f}, Direction.UP, "missing");
        model.addQuad(new float[]{0.56250f,0.00000f,0.43750f,0.43750f,0.00000f,0.43750f,0.43750f,0.62500f,0.43750f,0.56250f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.NORTH, "missing");
        model.addQuad(new float[]{0.43750f,0.00000f,0.56250f,0.56250f,0.00000f,0.56250f,0.56250f,0.62500f,0.56250f,0.43750f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.SOUTH, "missing");
        model.addQuad(new float[]{0.43750f,0.00000f,0.43750f,0.43750f,0.00000f,0.56250f,0.43750f,0.62500f,0.56250f,0.43750f,0.62500f,0.43750f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.WEST, "missing");
        model.addQuad(new float[]{0.56250f,0.00000f,0.56250f,0.56250f,0.00000f,0.43750f,0.56250f,0.62500f,0.43750f,0.56250f,0.62500f,0.56250f}, new float[]{0.43750f,0.37500f,0.56250f,0.37500f,0.56250f,1.00000f,0.43750f,1.00000f}, Direction.EAST, "missing");
        return model;
    }

    public static BlockModel createXorGateBlock() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "xor_gate");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

    public static BlockModel createXorGateBlockOn() {
        BlockModel model = new BlockModel();
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,0.00000f,0.00000f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.DOWN, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.UP, "xor_gate_on");
        model.addQuad(new float[]{1.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,0.12500f,0.00000f,1.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.NORTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,1.00000f,1.00000f,0.00000f,1.00000f,1.00000f,0.12500f,1.00000f,0.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.SOUTH, "smooth_stone");
        model.addQuad(new float[]{0.00000f,0.00000f,0.00000f,0.00000f,0.00000f,1.00000f,0.00000f,0.12500f,1.00000f,0.00000f,0.12500f,0.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.WEST, "smooth_stone");
        model.addQuad(new float[]{1.00000f,0.00000f,1.00000f,1.00000f,0.00000f,0.00000f,1.00000f,0.12500f,0.00000f,1.00000f,0.12500f,1.00000f}, new float[]{0.00000f,0.87500f,1.00000f,0.87500f,1.00000f,1.00000f,0.00000f,1.00000f}, Direction.EAST, "smooth_stone");
        return model;
    }

}