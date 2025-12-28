package com.voxelengine.ui;

import com.voxelengine.entity.EntityManager;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;

/**
 * Handles text-based commands entered via the chat interface.
 */
public class CommandManager {

    private final World world;
    private final EntityManager entityManager;
    private final Inventory inventory;

    /**
     * Initializes the command manager.
     *
     * @param world The game world.
     * @param entityManager The entity manager.
     * @param inventory The player's inventory.
     */
    public CommandManager(World world, EntityManager entityManager, Inventory inventory) {
        this.world = world;
        this.entityManager = entityManager;
        this.inventory = inventory;
    }

    /**
     * Parses and executes a command string.
     * Commands must start with '/'.
     *
     * @param command The full command string.
     */
    public void execute(String command) {
        if (!command.startsWith("/")) return;
        
        String[] parts = command.substring(1).split(" ");
        if (parts.length == 0) return;
        
        String cmd = parts[0].toLowerCase();
        
        try {
            switch (cmd) {
                case "give":
                    if (parts.length >= 2) {
                        String blockName = parts[1].toUpperCase();
                        int amount = parts.length > 2 ? Integer.parseInt(parts[2]) : 1;
                        try {
                            Block b = Block.valueOf(blockName);
                            inventory.add(new ItemStack(b, amount));
                            System.out.println("Gave " + amount + " " + blockName);
                        } catch (IllegalArgumentException e) {
                            System.err.println("Unknown block: " + blockName);
                        }
                    }
                    break;
                    
                case "clear":
                    entityManager.clearAll();
                    System.out.println("Cleared entities.");
                    break;
                    
                case "set":
                    if (parts.length >= 3 && parts[1].equals("time")) {
                        long time = Long.parseLong(parts[2]);
                        world.setTime(time);
                        System.out.println("Time set to " + time);
                    }
                    break;
                    
                default:
                    System.err.println("Unknown command: " + cmd);
            }
        } catch (Exception e) {
            System.err.println("Command failed: " + e.getMessage());
        }
    }
}