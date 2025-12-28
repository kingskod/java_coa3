package com.voxelengine.logic;

import com.voxelengine.world.Block;
import com.voxelengine.world.FluidSimulator;
import com.voxelengine.world.World;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Manages game logic updates, including redstone circuits and scheduled block events.
 */
public class LogicSystem {

    private final World world;
    private final FluidSimulator fluidSimulator;
    
    // Queue for instant updates (e.g., Redstone Wire propagation)
    private final Queue<Node> updateQueue = new ConcurrentLinkedQueue<>();
    
    // Queue for delayed updates (e.g., Water flow, Redstone Torches)
    private final PriorityQueue<ScheduledTick> tickQueue = new PriorityQueue<>();
    private long currentTick = 0;

    public LogicSystem(World world) {
        this.world = world;
        this.fluidSimulator = new FluidSimulator(world, this);
    }

    // --- Tick Engine ---

    /**
     * Advances the logic system by one tick.
     * Processes scheduled events and redstone updates.
     */
    public void tick() {
        currentTick++;
        
        // Process scheduled events
        while (!tickQueue.isEmpty() && tickQueue.peek().executeTime <= currentTick) {
            ScheduledTick tick = tickQueue.poll();
            
            if (!world.isLoaded(tick.x, tick.z)) continue;

            Block current = world.getBlock(tick.x, tick.y, tick.z);
            
            // Validate block is still the same (prevent ghost updates)
            if (current == tick.block) {
                if (current.isWater()) {
                    fluidSimulator.update(tick.x, tick.y, tick.z, current);
                } else if (current.isLogicGate()) {
                    LogicGate.updateState(world, tick.x, tick.y, tick.z, current);
                }
            }
        }
        
        processRedstoneQueue();
    }

    /**
     * Schedules a block update for a future tick.
     *
     * @param x World X.
     * @param y World Y.
     * @param z World Z.
     * @param block The block type expecting the update.
     * @param delay Ticks to wait before execution.
     */
    public void scheduleTick(int x, int y, int z, Block block, int delay) {
        tickQueue.add(new ScheduledTick(x, y, z, block, currentTick + delay));
    }

    // --- Redstone Engine ---

    /**
     * Triggers a redstone network update starting at the given coordinate.
     */
    public void updateNetwork(int x, int y, int z) {
        updateQueue.add(new Node(x, y, z));
        // Also check direct logic components
        LogicGate.evaluate(world, this, x, y, z, world.getBlock(x, y, z));
    }

    private void processRedstoneQueue() {
        int ops = 0;
        // Limit operations per tick to prevent infinite loops/lag
        while (!updateQueue.isEmpty() && ops < 1000) {
            Node node = updateQueue.poll();
            ops++;

            if (!world.isLoaded(node.x, node.z)) continue;

            Block block = world.getBlock(node.x, node.y, node.z);
            int currentSignal = world.getBlockLight(node.x, node.y, node.z); // Redstone uses LightMap channel 2 (conceptually)
            // Note: For this engine, we reuse BlockLight for redstone signal to simplify visualization.

            int newSignal = 0;

            if (block == Block.WIRE) {
                int maxInput = 0;
                int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
                
                for (int[] d : dirs) {
                    Block nb = world.getBlock(node.x + d[0], node.y + d[1], node.z + d[2]);
                    // Direct Sources
                    if (nb.isPowerSource()) {
                        maxInput = 15;
                    } 
                    // Wires (Decay)
                    else if (nb == Block.WIRE) {
                        int val = world.getBlockLight(node.x + d[0], node.y + d[1], node.z + d[2]);
                        if (val > maxInput) maxInput = val;
                    }
                }
                newSignal = Math.max(0, maxInput - 1);
                
            } else if (block.isPowerSource()) {
                newSignal = 15;
            } else if (block == Block.REDSTONE_TORCH_OFF) {
                newSignal = 0;
            }

            if (newSignal != currentSignal) {
                // Update Signal Strength
                world.setBlockLight(node.x, node.y, node.z, newSignal);
                
                // Propagate to neighbors
                int[][] dirs = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
                for (int[] d : dirs) {
                    int nx = node.x + d[0];
                    int ny = node.y + d[1];
                    int nz = node.z + d[2];
                    
                    updateQueue.add(new Node(nx, ny, nz));
                    
                    // Notify logic gates
                    Block nb = world.getBlock(nx, ny, nz);
                    if (nb.isLogicGate()) {
                        LogicGate.evaluate(world, this, nx, ny, nz, nb);
                    }
                }
                
                // Interaction (e.g. Lamps)
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

    private static class ScheduledTick implements Comparable<ScheduledTick> {
        int x, y, z;
        Block block;
        long executeTime;

        public ScheduledTick(int x, int y, int z, Block block, long executeTime) {
            this.x = x; this.y = y; this.z = z;
            this.block = block;
            this.executeTime = executeTime;
        }

        @Override
        public int compareTo(ScheduledTick o) {
            return Long.compare(this.executeTime, o.executeTime);
        }
    }
}