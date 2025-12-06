package com.voxelengine;

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

public class Main {

    public static void main(String[] args) {
        com.voxelengine.world.ChunkSerializer.WORLD_NAME = "NewWorld1"; 
        AssetGenerator.verifyAndGenerateAssets();
        Window window = new Window("Voxel Engine - " + com.voxelengine.world.ChunkSerializer.WORLD_NAME, 1280, 720);
        window.init();

        Camera camera = new Camera(window.getWidth(), window.getHeight());
        Renderer renderer = new Renderer();
        World world = new World();
        PhysicsEngine physics = new PhysicsEngine();
        
        // Spawn high up to avoid falling through terrain immediately
        Player player = new Player(0, 100, 0, camera);
        
        // Entity System
        EntityManager entityManager = new EntityManager();
        
        LogicSystem logic = new LogicSystem(world);
        Inventory inventory = new Inventory();
        inventory.load();
        
        // Pass TextureAtlas to UIManager for icons
        UIManager uiManager = new UIManager(inventory, renderer.getTextureAtlas());

        final boolean[] mouseState = new boolean[2]; 

        GameLoop gameLoop = new GameLoop(window);
        gameLoop.setCallbacks(
            () -> {
                // 1. Chunk Loading
                world.getChunkManager().update(player.getPosition());

                // 2. Safety Check: Wait for ground to load before applying gravity
                int px = (int) player.getPosition().x;
                int pz = (int) player.getPosition().z;
                
                if (!world.isLoaded(px, pz)) {
                    player.velocity.set(0, 0, 0); 
                    if (player.getPosition().y < 80) player.getPosition().y = 80;
                    return; // Skip physics until world exists
                }

                // 3. Logic & Physics
                world.tick();
                player.tick(world, physics, 1.0f / 60.0f);
                entityManager.tick(world, physics, 1.0f / 60.0f);
                
                // 4. Pickup Logic
                player.checkPickups(entityManager, inventory);

                // 5. Input
                double scroll = Input.getScrollY();
                if (scroll != 0) inventory.scroll(scroll > 0 ? 1 : -1);
                
                boolean clickedLeft = Input.isMouseButtonDown(0);
                boolean clickedRight = Input.isMouseButtonDown(1);
                
                if (clickedLeft && !mouseState[0]) {
                    raycast(world, camera, true, inventory, logic, entityManager);
                }
                if (clickedRight && !mouseState[1]) {
                    raycast(world, camera, false, inventory, logic, entityManager);
                }
                
                mouseState[0] = clickedLeft;
                mouseState[1] = clickedRight;

                // Debug
                if (Input.isKeyDown(GLFW_KEY_M)) {
                    System.gc();
                    long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("Used Memory: " + (used / 1024 / 1024) + " MB");
                    System.out.println("Loaded Chunks: " + world.getChunkManager().getLoadedChunks().size());
                }
            },
            () -> {
                // Render Tick
                org.lwjgl.opengl.GL11.glClear(org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT | org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT);
                if (window.isResized()) camera.updateProjection(window.getWidth(), window.getHeight());
                
                renderer.render(world, camera);
                entityManager.render(renderer);
                uiManager.render(window.getWidth(), window.getHeight());
            }
        );
        
        window.setMouseGrabbed(true);
        gameLoop.run();
        inventory.save();
        world.getChunkManager().saveAll();
        uiManager.cleanup();
        renderer.cleanup();
        window.cleanup();
    }
    
    private static void raycast(World world, Camera cam, boolean destroy, Inventory inventory, LogicSystem logic, EntityManager entityManager) {
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
            if (b != Block.AIR && b != Block.WATER && !b.isWater()) {
                if (destroy) {
                    // 1. Spawn Item
                    ItemStack drop = new ItemStack(b, 1);
                    entityManager.addEntity(new ItemEntity(drop, x + 0.5f, y + 0.5f, z + 0.5f));
                    
                    // 2. Remove Block
                    world.setBlock(x, y, z, Block.AIR);
                    logic.updateNetwork(x, y, z); 
                    return;
                } else {
                    // Place Logic
                    pos.sub(dir.x * step, dir.y * step, dir.z * step);
                    int px = (int) Math.floor(pos.x);
                    int py = (int) Math.floor(pos.y);
                    int pz = (int) Math.floor(pos.z);
                    
                    ItemStack toPlace = inventory.getSelectedStack();
                    if (toPlace.isEmpty()) return;

                    double distToPlayer = pos.distance(cam.getPosition());
                    if (distToPlayer > 1.0) {
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