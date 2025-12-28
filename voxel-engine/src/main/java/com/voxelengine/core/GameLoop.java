package com.voxelengine.core;

import com.voxelengine.render.Renderer;
import com.voxelengine.world.World;

/**
 * Handles the fixed-timestep game loop.
 * Manages the timing for update and render cycles to ensure smooth gameplay.
 */
public class GameLoop {

    private final Window window;
    private static final int TPS = 60;
    private static final float TICK_DURATION = 1.0f / TPS;

    private Runnable updateCallback;
    private Runnable renderCallback;

    /**
     * Creates a new GameLoop attached to the specified window.
     *
     * @param window The game window.
     */
    public GameLoop(Window window) {
        this.window = window;
    }

    /**
     * Sets the callbacks for the update and render phases.
     *
     * @param update The logic update callback (run at fixed time steps).
     * @param render The rendering callback (run every frame).
     */
    public void setCallbacks(Runnable update, Runnable render) {
        this.updateCallback = update;
        this.renderCallback = render;
    }

    /**
     * Starts the game loop.
     * Runs until the window is closed.
     */
    public void run() {
        long lastTime = System.nanoTime();
        double accumulator = 0.0;

        while (!window.shouldClose()) {
            long now = System.nanoTime();
            double frameTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            // Cap frame time to prevent "spiral of death" where updates can't catch up
            if (frameTime > 0.25) frameTime = 0.25;

            accumulator += frameTime;

            while (accumulator >= TICK_DURATION) {
                if (updateCallback != null) updateCallback.run();
                accumulator -= TICK_DURATION;
            }

            // Alpha for interpolation could be calculated here:
            // float alpha = (float) (accumulator / TICK_DURATION);

            if (renderCallback != null) renderCallback.run();

            window.update();

            // Simple yield to reduce CPU usage
            try { Thread.sleep(1); } catch (InterruptedException e) {}
        }
    }
}