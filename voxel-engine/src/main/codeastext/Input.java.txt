package com.voxelengine.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharCallback;

/**
 * Handles keyboard, mouse, and text input.
 */
public class Input {

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST + 1];
    private static boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];
    private static double mouseX, mouseY;
    private static double lastMouseX, lastMouseY;
    private static double scrollX, scrollY;
    
    // Text Input
    private static final StringBuilder charBuffer = new StringBuilder();
    private static boolean captureChars = false;
    private static boolean enterPressed = false;
    private static boolean backspacePressed = false;
    
    private static int width, height;
    // Window Ref
    private static long window;

    public static void init(long windowHandle, int w, int h) {
        window = windowHandle;
        width = w;
        height = h;
        
        GLFW.glfwSetKeyCallback(windowHandle, (win, key, scancode, action, mods) -> {
            if (key >= 0 && key < keys.length) {
                keys[key] = action != GLFW.GLFW_RELEASE;
            }
            
            // Handle Text Editing Keys
            if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
                if (key == GLFW.GLFW_KEY_BACKSPACE) backspacePressed = true;
                if (key == GLFW.GLFW_KEY_ENTER) enterPressed = true;
            }
        });

        GLFW.glfwSetMouseButtonCallback(windowHandle, (win, button, action, mods) -> {
            if (button >= 0 && button < mouseButtons.length) {
                mouseButtons[button] = action != GLFW.GLFW_RELEASE;
            }
        });

        GLFW.glfwSetCursorPosCallback(windowHandle, (win, xpos, ypos) -> {
            mouseX = xpos;
            mouseY = ypos;
        });

        GLFW.glfwSetScrollCallback(windowHandle, (win, xoffset, yoffset) -> {
            scrollX = xoffset;
            scrollY = yoffset;
        });
        
        // Character Callback for Typing
        GLFW.glfwSetCharCallback(windowHandle, (win, codepoint) -> {
            if (captureChars) {
                charBuffer.append(Character.toChars(codepoint));
            }
        });
    }

    public static void update() {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        scrollX = 0;
        scrollY = 0;
        
        // Handle Backspace
        if (captureChars && backspacePressed && charBuffer.length() > 0) {
            charBuffer.deleteCharAt(charBuffer.length() - 1);
        }
        
        backspacePressed = false;
        enterPressed = false; // Consumed immediately if checked
    }
    
    public static void setCursorLocked(boolean locked) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, locked ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
    }
    
    // --- Text Input API ---
    public static void startTextInput() {
        captureChars = true;
        charBuffer.setLength(0);
    }
    
    public static void stopTextInput() {
        captureChars = false;
    }
    
    public static String getTypedString() {
        return charBuffer.toString();
    }
    
    public static boolean isEnterPressed() {
        return enterPressed;
    }

    // --- Standard Input API ---
    public static boolean isKeyDown(int key) { return keys[key]; }
    public static boolean isMouseButtonDown(int button) { return mouseButtons[button]; }
    public static double getMouseX() { return mouseX; }
    public static double getMouseY() { return mouseY; }
    
    public static double getDX() { 
        double dx = mouseX - lastMouseX;
        if (Double.isNaN(dx) || Math.abs(dx) > 1000) return 0; 
        return dx;
    }
    
    public static double getDY() { 
        double dy = mouseY - lastMouseY;
        if (Double.isNaN(dy) || Math.abs(dy) > 1000) return 0; 
        return dy;
    }
    public static double getScrollY() { return scrollY; }
}