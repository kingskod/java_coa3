package com.voxelengine.core;

import com.voxelengine.render.Renderer;
import com.voxelengine.world.World;

/**
 * Handles the fixed-timestep game loop.
 */
public class GameLoop {

    private final Window window;
    private static final int TPS = 60;
    private static final float TICK_DURATION = 1.0f / TPS;

    private Runnable updateCallback;
    private Runnable renderCallback;

    public GameLoop(Window window) {
        this.window = window;
    }

    public void setCallbacks(Runnable update, Runnable render) {
        this.updateCallback = update;
        this.renderCallback = render;
    }

    public void run() {
        long lastTime = System.nanoTime();
        double accumulator = 0.0;

        while (!window.shouldClose()) {
            long now = System.nanoTime();
            double frameTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            // Cap frame time to prevent spiral of death
            if (frameTime > 0.25) frameTime = 0.25;

            accumulator += frameTime;

            while (accumulator >= TICK_DURATION) {
                if (updateCallback != null) updateCallback.run();
                accumulator -= TICK_DURATION;
            }

            // Alpha for interpolation
            float alpha = (float) (accumulator / TICK_DURATION);

            // In a full implementation, we pass alpha to render for smoothing
            if (renderCallback != null) renderCallback.run();

            window.update();

            // Basic performance saver
            try { Thread.sleep(1); } catch (InterruptedException e) {}
        }
    }
}