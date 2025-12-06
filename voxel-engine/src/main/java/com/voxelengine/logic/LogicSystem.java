package com.voxelengine.logic;

import com.voxelengine.world.Block;
import com.voxelengine.world.World;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Handles the propagation of redstone signals.
 * Uses BFS to simulate voltage drop and gate updates.
 */
public class LogicSystem {

    private final World world;
    private final Queue<Node> updateQueue = new LinkedList<>();
    private final Set<Long> processedInTick = new HashSet<>();

    public LogicSystem(World world) {
        this.world = world;
    }

    /**
     * Triggered when a block is placed, broken, or interacted with.
     */
    public void updateNetwork(int x, int y, int z) {
        updateQueue.add(new Node(x, y, z));
        processQueue();
    }

    private void processQueue() {
        // Prevent infinite loops in a single tick by tracking processed nodes or depth
        // "Stable propagation until no changes"

        while (!updateQueue.isEmpty()) {
            Node node = updateQueue.poll();

            if (!world.isLoaded(node.x, node.z)) continue;

            Block block = world.getBlock(node.x, node.y, node.z);
            int currentSignal = world.getBlockLight(node.x, node.y, node.z); // Using BlockLight as Signal storage
            int newSignal = 0;

            if (block == Block.WIRE) {
                // Wire Logic: Max neighbor - 1
                int maxInput = 0;
                int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};

                for (int[] d : dirs) {
                    Block nb = world.getBlock(node.x + d[0], node.y + d[1], node.z + d[2]);
                    int val = world.getBlockLight(node.x + d[0], node.y + d[1], node.z + d[2]);

                    // Logic Sources (Levers/Torches) emit full power into wires
                    if (nb.isPowerSource()) val = 15;

                    // Wires transmit decay
                    if (val > maxInput) maxInput = val;
                }

                newSignal = maxInput - 1;
                if (newSignal < 0) newSignal = 0;

            } else if (block.isLogicGate()) {
                // Gate Logic
                newSignal = LogicGate.calculateOutput(world, node.x, node.y, node.z, block);
            } else if (block.isPowerSource()) {
                newSignal = 15;
            } else {
                // Ordinary block (conducts? For now, no).
                newSignal = 0;
            }

            // If State Changed
            if (newSignal != currentSignal) {
                // Update "Signal" (stored in BlockLight for efficiency in this implementation)
                world.setBlockLight(node.x, node.y, node.z, newSignal);

                // Propagate to neighbors
                int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
                for (int[] d : dirs) {
                    updateQueue.add(new Node(node.x + d[0], node.y + d[1], node.z + d[2]));
                }

                // Specific: If Lamp, toggle texture/state
                if (block == Block.REDSTONE_LAMP_OFF && newSignal > 0) {
                    world.setBlock(node.x, node.y, node.z, Block.REDSTONE_LAMP_ON);
                } else if (block == Block.REDSTONE_LAMP_ON && newSignal == 0) {
                    world.setBlock(node.x, node.y, node.z, Block.REDSTONE_LAMP_OFF);
                }
            }
        }
    }

    private static class Node {
        int x, y, z;
        Node(int x, int y, int z) { this.x = x; this.y = y; this.z = z; }
    }
}