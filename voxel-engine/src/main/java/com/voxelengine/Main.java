package com.voxelengine;

import com.voxelengine.core.GameLoop;
import com.voxelengine.core.Input;
import com.voxelengine.core.Window;
import com.voxelengine.entity.PhysicsEngine;
import com.voxelengine.entity.Player;
import com.voxelengine.logic.LogicSystem;
import com.voxelengine.render.Camera;
import com.voxelengine.render.Renderer;
import com.voxelengine.ui.Inventory;
import com.voxelengine.ui.UIManager;
import com.voxelengine.utils.AssetGenerator;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static void main(String[] args) {
        AssetGenerator.verifyAndGenerateAssets();

        Window window = new Window("Voxel Engine - Java Edition", 1280, 720);
        window.init();

        Camera camera = new Camera(window.getWidth(), window.getHeight());
        Renderer renderer = new Renderer();
        World world = new World();
        PhysicsEngine physics = new PhysicsEngine();
        Player player = new Player(0, 80, 0, camera);

        // Logic System (Redstone)
        LogicSystem logic = new LogicSystem(world);

        // UI
        Inventory inventory = new Inventory();
        UIManager uiManager = new UIManager(inventory);

        // Input State for clicks
        final boolean[] mouseState = new boolean[2]; // [0]=Left, [1]=Right

        GameLoop gameLoop = new GameLoop(window);
        gameLoop.setCallbacks(
                () -> {
                    // --- Logic Tick ---
                    world.tick();
                    player.tick(world, physics, 1.0f / 60.0f);
                    world.getChunkManager().update(player.getPosition());

                    // Inventory Scrolling
                    double scroll = Input.getScrollY();
                    if (scroll != 0) inventory.scroll(scroll > 0 ? 1 : -1);

                    // Block Interaction (Raycast)
                    // Simple Raycast: Step forward from eye pos
                    boolean clickedLeft = Input.isMouseButtonDown(0);
                    boolean clickedRight = Input.isMouseButtonDown(1);

                    if (clickedLeft && !mouseState[0]) {
                        // Break Block
                        raycast(world, camera, true, null, logic);
                    }
                    if (clickedRight && !mouseState[1]) {
                        // Place Block
                        raycast(world, camera, false, inventory.getSelectedBlock(), logic);
                    }

                    mouseState[0] = clickedLeft;
                    mouseState[1] = clickedRight;
                },
                () -> {
                    // --- Render Tick ---
                    org.lwjgl.opengl.GL11.glClear(org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT | org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT);
                    if (window.isResized()) camera.updateProjection(window.getWidth(), window.getHeight());

                    renderer.render(world, camera);
                    uiManager.render(window.getWidth(), window.getHeight());
                }
        );

        window.setMouseGrabbed(true);
        gameLoop.run();

        uiManager.cleanup();
        renderer.cleanup();
        window.cleanup();
    }

    private static void raycast(World world, Camera cam, boolean destroy, Block placeType, LogicSystem logic) {
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
            if (b != Block.AIR && b != Block.WATER) {
                if (destroy) {
                    world.setBlock(x, y, z, Block.AIR);
                    logic.updateNetwork(x, y, z); // Update logic neighbors
                    return;
                } else {
                    // Place: Back up one step
                    pos.sub(dir.x * step, dir.y * step, dir.z * step);
                    int px = (int) Math.floor(pos.x);
                    int py = (int) Math.floor(pos.y);
                    int pz = (int) Math.floor(pos.z);

                    // Prevent placing inside player
                    // (Simple dist check or AABB check)
                    double distToPlayer = pos.distance(cam.getPosition());
                    if (distToPlayer > 1.0) {
                        world.setBlock(px, py, pz, placeType);
                        logic.updateNetwork(px, py, pz);
                    }
                    return;
                }
            }
        }
    }
}