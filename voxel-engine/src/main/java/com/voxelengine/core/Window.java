package com.voxelengine.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Manages the GLFW window and OpenGL context.
 */
public class Window {

    private final String title;
    private int width;
    private int height;
    private long windowHandle;
    private boolean resized;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

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

        // Setup resize callback
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

        // Initialize OpenGL capabilities
        GL.createCapabilities();
        
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        
        // Basic clear color
        glClearColor(0.5f, 0.7f, 1.0f, 1.0f);
    }

    public void update() {
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
        Input.update();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    public void cleanup() {
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isResized() {
        boolean ret = resized;
        resized = false;
        return ret;
    }
    
    public void setMouseGrabbed(boolean grabbed) {
        glfwSetInputMode(windowHandle, GLFW_CURSOR, grabbed ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL);
    }
}