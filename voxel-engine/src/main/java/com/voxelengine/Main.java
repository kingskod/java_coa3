package com.voxelengine;

import com.voxelengine.audio.SoundManager;
import com.voxelengine.core.GameLoop;
import com.voxelengine.core.Input;
import com.voxelengine.core.Window;
import com.voxelengine.entity.AABB;
import com.voxelengine.entity.EntityManager;
import com.voxelengine.entity.ItemEntity;
import com.voxelengine.entity.PhysicsEngine;
import com.voxelengine.entity.Player;
import com.voxelengine.logic.LogicSystem;
import com.voxelengine.render.Camera;
import com.voxelengine.render.Renderer;
import com.voxelengine.render.TextureAtlas;
import com.voxelengine.ui.Inventory;
import com.voxelengine.ui.ItemStack;
import com.voxelengine.ui.UIManager;
import com.voxelengine.utils.AssetGenerator;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class Main {
    
    // UI State
    private static boolean inventoryOpen = false;
    private static boolean ePressed = false;

    public static void main(String[] args) {
        com.voxelengine.world.ChunkSerializer.WORLD_NAME = "world1"; 
        AssetGenerator.verifyAndGenerateAssets();
        Window window = new Window("Voxel Engine - " + com.voxelengine.world.ChunkSerializer.WORLD_NAME, 1280, 720);
        window.init();

        // Sound System
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
                    // Pass renderer.getTextureAtlas() here
                    raycast(world, camera, true, inventory, logic, entityManager, soundManager, renderer.getTextureAtlas());
                }
                if (clickedRight && !mouseState[1]) {
                    // Pass renderer.getTextureAtlas() here
                    raycast(world, camera, false, inventory, logic, entityManager, soundManager, renderer.getTextureAtlas());
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
    
    // Updated signature to accept TextureAtlas
    private static void raycast(World world, Camera cam, boolean destroy, Inventory inventory, LogicSystem logic, EntityManager entityManager, SoundManager soundManager, TextureAtlas atlas) {
        Vector3f pos = new Vector3f(cam.getPosition());
        Vector3f dir = new Vector3f();
        cam.getViewMatrix().positiveZ(dir).negate();
        
        float maxDist = 5.0f;
        float closestDist = maxDist;
        int hitX = 0, hitY = 0, hitZ = 0;
        int sideX = 0, sideY = 0, sideZ = 0; 
        boolean hitFound = false;

        float step = 0.05f;
        Vector3f checkPos = new Vector3f();
        
        for (float d = 0; d < maxDist; d += step) {
            checkPos.set(dir).mul(d).add(pos);
            int x = (int) Math.floor(checkPos.x);
            int y = (int) Math.floor(checkPos.y);
            int z = (int) Math.floor(checkPos.z);
            
            Block b = world.getBlock(x, y, z);
            if (b != Block.AIR && !b.isWater()) {
                byte meta = world.getMetadata(x, y, z);
                AABB box = b.getSelectionBox(x, y, z, meta);
                if (box != null) {
                    AABB worldBox = box.offset(x, y, z);
                    float dist = worldBox.rayIntersect(pos, dir);
                    
                    if (dist != -1.0f && dist < closestDist) {
                        closestDist = dist;
                        hitX = x; hitY = y; hitZ = z;
                        hitFound = true;
                        
                        Vector3f prev = new Vector3f(dir).mul(dist - 0.01f).add(pos);
                        sideX = (int)Math.floor(prev.x);
                        sideY = (int)Math.floor(prev.y);
                        sideZ = (int)Math.floor(prev.z);
                        break; 
                    }
                }
            }
        }
        if (hitFound) {
            // FIX: Interaction Logic (Lever)
            Block target = world.getBlock(hitX, hitY, hitZ);
            if (!destroy && target == Block.LEVER) {
                byte meta = world.getMetadata(hitX, hitY, hitZ);
                meta ^= 4; // Toggle Bit 2 (Active)
                world.setMetadata(hitX, hitY, hitZ, meta);
                logic.updateNetwork(hitX, hitY, hitZ);
                if (target.getSoundType() != null) soundManager.play("lever.wav"); // Assuming sound exists
                return;
            }
            if (destroy) {
                if (target.getSoundType() != null) soundManager.play(target.getSoundType().breakSound);
                
                ItemStack drop = new ItemStack(target, 1);
                entityManager.addEntity(new ItemEntity(drop, hitX + 0.5f, hitY + 0.5f, hitZ + 0.5f, atlas));
                
                world.setBlock(hitX, hitY, hitZ, Block.AIR);
                logic.updateNetwork(hitX, hitY, hitZ); 
            } else {
                ItemStack toPlace = inventory.getSelectedStack();
                if (toPlace.isEmpty()) return;

                AABB playerBox = new AABB(pos.x-0.3f, pos.y-1.5f, pos.z-0.3f, pos.x+0.3f, pos.y+0.3f, pos.z+0.3f);
                AABB blockBox = new AABB(sideX, sideY, sideZ, sideX+1, sideY+1, sideZ+1);
                
                if (!playerBox.intersects(blockBox)) {
                    if (toPlace.getBlock().getSoundType() != null) soundManager.play(toPlace.getBlock().getSoundType().placeSound);
                    
                    world.setBlock(sideX, sideY, sideZ, toPlace.getBlock());
                    inventory.useSelectedItem();
                    logic.updateNetwork(sideX, sideY, sideZ);
                }
            }
        }
    }
}
//things to implement:
//1)if the block on the world is not a cubical block, i need the block to drop the item png as the floating entity like real minecraft. because as of now it looks very ugly because the block with the texture is floating around