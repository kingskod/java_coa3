package com.voxelengine.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Manages the application window using GLFW.
 * Handles window creation, OpenGL context initialization, and event polling.
 */
public class Window {

    private final String title;
    private int width;
    private int height;
    private long windowHandle;
    private boolean resized;

    /**
     * Creates a new Window configuration.
     *
     * @param title The window title.
     * @param width The initial width.
     * @param height The initial height.
     */
    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    /**
     * Initializes the GLFW window and OpenGL context.
     * Sets up window hints, creates the window, and configures OpenGL capabilities.
     *
     * @throws IllegalStateException If GLFW cannot be initialized.
     * @throws RuntimeException If the window cannot be created.
     */
    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        windowHandle = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (windowHandle == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetFramebufferSizeCallback(windowHandle, (window, w, h) -> {
            this.width = w;
            this.height = h;
            this.resized = true;
            glViewport(0, 0, w, h);
        });

        Input.init(windowHandle);

        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1); // Enable V-Sync
        glfwShowWindow(windowHandle);

        GL.createCapabilities();
        
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        
        // Clear color (Black background)
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    /**
     * Updates the window state.
     * Swaps buffers, updates input, and polls events.
     */
    public void update() {
        glfwSwapBuffers(windowHandle);
        Input.update();
        glfwPollEvents();
    }

    /**
     * Checks if the window should close.
     *
     * @return True if the close flag is set.
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    /**
     * Cleans up resources and destroys the window.
     */
    public void cleanup() {
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * Gets the current window width.
     *
     * @return The width in pixels.
     */
    public int getWidth() { return width; }

    /**
     * Gets the current window height.
     *
     * @return The height in pixels.
     */
    public int getHeight() {return height; }

    /**
     * Checks if the window was resized since the last check.
     * Resets the flag upon reading.
     *
     * @return True if resized.
     */
    public boolean isResized() {
        boolean ret = resized;
        resized = false;
        return ret;
    }
    
    /**
     * Sets the mouse cursor grab state.
     *
     * @param grabbed True to grab (lock/hide) the cursor, false to release.
     */
    public void setMouseGrabbed(boolean grabbed) {
        glfwSetInputMode(windowHandle, GLFW_CURSOR, grabbed ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL);
    }
}