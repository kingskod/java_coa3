package com.voxelengine.core;

import org.lwjgl.glfw.GLFW;

/**
 * Handles keyboard and mouse input state.
 */
public class Input {

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST + 1];
    private static boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];
    private static double mouseX, mouseY;
    private static double lastMouseX, lastMouseY;
    private static double scrollX, scrollY;

    public static void init(long windowHandle) {
        GLFW.glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
            if (key >= 0 && key < keys.length) {
                keys[key] = action != GLFW.GLFW_RELEASE;
            }
        });

        GLFW.glfwSetMouseButtonCallback(windowHandle, (window, button, action, mods) -> {
            if (button >= 0 && button < mouseButtons.length) {
                mouseButtons[button] = action != GLFW.GLFW_RELEASE;
            }
        });

        GLFW.glfwSetCursorPosCallback(windowHandle, (window, xpos, ypos) -> {
            mouseX = xpos;
            mouseY = ypos;
        });

        GLFW.glfwSetScrollCallback(windowHandle, (window, xoffset, yoffset) -> {
            scrollX = xoffset;
            scrollY = yoffset;
        });
    }

    public static void update() {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        scrollX = 0;
        scrollY = 0;
    }

    public static boolean isKeyDown(int key) {
        return keys[key];
    }

    public static boolean isMouseButtonDown(int button) {
        return mouseButtons[button];
    }

    public static double getMouseX() { return mouseX; }
    public static double getMouseY() { return mouseY; }
    public static double getDX() { 
        double dx = mouseX - lastMouseX;
        // Clamp to prevent snapping on focus loss
        if (Math.abs(dx) > 100) return 0;
        return dx;
    }
    public static double getDY() { 
        double dy = mouseY - lastMouseY;
        if (Math.abs(dy) > 100) return 0;
        return dy;
    }
    public static double getScrollY() { return scrollY; }
}