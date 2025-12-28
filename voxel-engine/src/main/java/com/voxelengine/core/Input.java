package com.voxelengine.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharCallback;

/**
 * Handles keyboard, mouse, and text input management.
 * Provides a static interface for querying input state and handling text entry.
 */
public class Input {

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST + 1];
    private static boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];
    private static double mouseX, mouseY;
    private static double lastMouseX, lastMouseY;
    private static double scrollX, scrollY;
    
    // Text Input State
    private static final StringBuilder charBuffer = new StringBuilder();
    private static boolean captureChars = false;
    private static boolean enterPressed = false;
    private static boolean backspacePressed = false;
    
    // Window Reference
    private static long window;

    /**
     * Initializes the Input system with the given window handle.
     * Sets up GLFW callbacks for keys, mouse buttons, cursor position, scroll, and character input.
     *
     * @param windowHandle The GLFW window handle.
     */
    public static void init(long windowHandle) {
        window = windowHandle;
        
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

    /**
     * Updates the input state at the end of a frame.
     * Resets delta values and one-frame flags.
     */
    public static void update() {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        scrollX = 0;
        scrollY = 0;
        
        // Handle Backspace for text input
        if (captureChars && backspacePressed && charBuffer.length() > 0) {
            charBuffer.deleteCharAt(charBuffer.length() - 1);
        }
        
        backspacePressed = false;
        enterPressed = false; // Consumed immediately if checked
    }
    
    /**
     * Locks or unlocks the mouse cursor.
     *
     * @param locked True to lock and hide the cursor, false to unlock and show it.
     */
    public static void setCursorLocked(boolean locked) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, locked ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
    }
    
    // --- Text Input API ---

    /**
     * Starts capturing character input for text entry.
     */
    public static void startTextInput() {
        captureChars = true;
        charBuffer.setLength(0);
    }
    
    /**
     * Stops capturing character input.
     */
    public static void stopTextInput() {
        captureChars = false;
    }
    
    /**
     * Gets the string typed since text input started.
     *
     * @return The accumulated character buffer as a String.
     */
    public static String getTypedString() {
        return charBuffer.toString();
    }
    
    /**
     * Checks if the Enter key was pressed this frame.
     *
     * @return True if Enter was pressed.
     */
    public static boolean isEnterPressed() {
        return enterPressed;
    }

    // --- Standard Input API ---

    /**
     * Checks if a specific key is currently held down.
     *
     * @param key The GLFW key code.
     * @return True if the key is down.
     */
    public static boolean isKeyDown(int key) { return keys[key]; }

    /**
     * Checks if a mouse button is currently held down.
     *
     * @param button The GLFW mouse button code.
     * @return True if the button is down.
     */
    public static boolean isMouseButtonDown(int button) { return mouseButtons[button]; }

    /**
     * Gets the current mouse X position.
     *
     * @return The mouse X coordinate.
     */
    public static double getMouseX() { return mouseX; }

    /**
     * Gets the current mouse Y position.
     *
     * @return The mouse Y coordinate.
     */
    public static double getMouseY() { return mouseY; }
    
    /**
     * Gets the change in mouse X position since the last frame.
     *
     * @return The delta X.
     */
    public static double getDX() { 
        double dx = mouseX - lastMouseX;
        if (Double.isNaN(dx) || Math.abs(dx) > 1000) return 0; 
        return dx;
    }
    
    /**
     * Gets the change in mouse Y position since the last frame.
     *
     * @return The delta Y.
     */
    public static double getDY() { 
        double dy = mouseY - lastMouseY;
        if (Double.isNaN(dy) || Math.abs(dy) > 1000) return 0; 
        return dy;
    }

    /**
     * Gets the mouse scroll Y offset for this frame.
     *
     * @return The scroll Y offset.
     */
    public static double getScrollY() { return scrollY; }
}