package com.voxelengine.core;

import com.voxelengine.render.Mesh;

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
        
        long lastDebug = System.currentTimeMillis();

        while (!window.shouldClose()) {
            long now = System.nanoTime();
            double frameTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            if (frameTime > 0.25) frameTime = 0.25;

            accumulator += frameTime;

            while (accumulator >= TICK_DURATION) {
                if (updateCallback != null) updateCallback.run();
                accumulator -= TICK_DURATION;
            }

            if (renderCallback != null) renderCallback.run();

            window.update();
            
            // DEBUG REPORT (Every 1s)
            if (System.currentTimeMillis() - lastDebug > 1000) {
                long usedMem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
                System.out.println("FPS: " + (int)(1.0/frameTime) + " | Heap: " + usedMem + "MB | Meshes: " + Mesh.meshCount.get());
                lastDebug = System.currentTimeMillis();
            }

            try { Thread.sleep(1); } catch (InterruptedException e) {}
        }
    }
}