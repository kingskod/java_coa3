package com.voxelengine;

import com.voxelengine.audio.SoundManager;
import com.voxelengine.core.GameLoop;
import com.voxelengine.core.Input;
import com.voxelengine.core.Window;
import com.voxelengine.entity.EntityManager;
import com.voxelengine.entity.ItemEntity;
import com.voxelengine.entity.PhysicsEngine;
import com.voxelengine.entity.Player;
import com.voxelengine.logic.LogicSystem;
import com.voxelengine.render.Camera;
import com.voxelengine.render.Renderer;
import com.voxelengine.ui.Inventory;
import com.voxelengine.ui.ItemStack;
import com.voxelengine.ui.UIManager;
import com.voxelengine.utils.AssetGenerator;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

/**
 * The main entry point for the Voxel Engine.
 * Initializes the window, game loop, and all core subsystems.
 */
public class Main {
    
    /** Flag to track if the inventory UI is currently open. */
    private static boolean inventoryOpen = false;

    /** Flag to track if the 'E' key was pressed in the previous frame. */
    private static boolean ePressed = false;

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        com.voxelengine.world.ChunkSerializer.WORLD_NAME = "world1"; 
        AssetGenerator.verifyAndGenerateAssets();
        Window window = new Window("Voxel Engine - " + com.voxelengine.world.ChunkSerializer.WORLD_NAME, 1280, 720);
        window.init();

        // Initialize Sound System
        SoundManager soundManager = new SoundManager();
        soundManager.loadSound("step.wav", "step.wav");
        soundManager.loadSound("break.wav", "break.wav");
        soundManager.loadSound("place.wav", "place.wav");
        soundManager.loadSound("splash.wav", "splash.wav");

        Camera camera = new Camera(window.getWidth(), window.getHeight());
        Renderer renderer = new Renderer();
        World world = new World();
        PhysicsEngine physics = new PhysicsEngine();
        Player player = new Player(0, 150, 0, camera, soundManager);
        EntityManager entityManager = new EntityManager();
        LogicSystem logic = new LogicSystem(world);
        Inventory inventory = new Inventory();
        UIManager uiManager = new UIManager(inventory, renderer.getTextureAtlas(), world, entityManager);

        final boolean[] mouseState = new boolean[2]; 

        GameLoop gameLoop = new GameLoop(window);
        gameLoop.setCallbacks(
            () -> {
                world.getChunkManager().update(player.getPosition());

                int px = (int) player.getPosition().x;
                int pz = (int) player.getPosition().z;
                if (!world.isLoaded(px, pz)) {
                    player.velocity.set(0, 0, 0); 
                    if (player.getPosition().y < 80) player.getPosition().y = 80;
                    return; 
                }

                // UI Input must be checked before game logic to pause it
                uiManager.handleInput();

                if (Input.isKeyDown(GLFW_KEY_E) && !uiManager.isChatOpen) {
                    if (!ePressed) {
                        uiManager.toggleInventory();
                        inventoryOpen = uiManager.isInventoryOpen;
                    }
                    ePressed = true;
                } else {
                    ePressed = false;
                }

                // If UI is open, skip player logic
                if (inventoryOpen || uiManager.isChatOpen) {
                     return;
                }

                world.tick();
                player.tick(world, physics, 1.0f / 60.0f);
                entityManager.tick(world, physics, 1.0f / 60.0f);
                player.checkPickups(entityManager, inventory);

                double scroll = Input.getScrollY();
                if (scroll != 0) inventory.scroll(scroll > 0 ? 1 : -1);
                
                boolean clickedLeft = Input.isMouseButtonDown(0);
                boolean clickedRight = Input.isMouseButtonDown(1);
                
                if (clickedLeft && !mouseState[0]) {
                    raycast(world, camera, true, inventory, logic, entityManager, soundManager);
                }
                if (clickedRight && !mouseState[1]) {
                    raycast(world, camera, false, inventory, logic, entityManager, soundManager);
                }
                
                mouseState[0] = clickedLeft;
                mouseState[1] = clickedRight;
            },
            () -> {
                org.lwjgl.opengl.GL11.glClear(org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT | org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT);
                if (window.isResized()) camera.updateProjection(window.getWidth(), window.getHeight());
                
                renderer.render(world, camera);
                entityManager.render(renderer);
                uiManager.render(window.getWidth(), window.getHeight());
            }
        );
        
        window.setMouseGrabbed(true);
        gameLoop.run();
        
        world.getChunkManager().saveAll();
        inventory.save();

        soundManager.cleanup();
        uiManager.cleanup();
        renderer.cleanup();
        window.cleanup();
    }
    
    /**
     * Performs a raycast from the camera's perspective to interact with the world.
     *
     * @param world The game world.
     * @param cam The player's camera.
     * @param destroy If true, destroys the block looked at; otherwise places a block.
     * @param inventory The player's inventory.
     * @param logic The logic system for updating network/state.
     * @param entityManager The entity manager for spawning drops.
     * @param soundManager The sound manager for playing effects.
     */
    private static void raycast(World world, Camera cam, boolean destroy, Inventory inventory, LogicSystem logic, EntityManager entityManager, SoundManager soundManager) {
        Vector3f pos = new Vector3f(cam.getPosition());
        Vector3f dir = new Vector3f();
        cam.getViewMatrix().positiveZ(dir).negate();
        
        float step = 0.1f;
        for (float dist = 0; dist < 5.0f; dist += step) {
            pos.add(dir.x * step, dir.y * step, dir.z * step);
            
            int x = (int) Math.floor(pos.x);
            int y = (int) Math.floor(pos.y);
            int z = (int) Math.floor(pos.z);
            
            Block b = world.getBlock(x, y, z);
            if (b != Block.AIR && !b.isWater()) {
                if (destroy) {
                    if (b.getSoundType() != null) soundManager.play(b.getSoundType().breakSound);
                    
                    ItemStack drop = new ItemStack(b, 1);
                    entityManager.addEntity(new ItemEntity(drop, x + 0.5f, y + 0.5f, z + 0.5f));
                    
                    world.setBlock(x, y, z, Block.AIR);
                    logic.updateNetwork(x, y, z); 
                    return;
                } else {
                    pos.sub(dir.x * step, dir.y * step, dir.z * step);
                    int px = (int) Math.floor(pos.x);
                    int py = (int) Math.floor(pos.y);
                    int pz = (int) Math.floor(pos.z);
                    
                    ItemStack toPlace = inventory.getSelectedStack();
                    if (toPlace.isEmpty()) return;

                    double distToPlayer = pos.distance(cam.getPosition());
                    if (distToPlayer > 1.0) {
                        if (toPlace.getBlock().getSoundType() != null) soundManager.play(toPlace.getBlock().getSoundType().placeSound);
                        
                        world.setBlock(px, py, pz, toPlace.getBlock());
                        inventory.useSelectedItem();
                        logic.updateNetwork(px, py, pz);
                    }
                    return;
                }
            }
        }
    }
}