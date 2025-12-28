package com.voxelengine.render;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Handles the camera logic (view and projection matrices).
 * Allows movement and rotation in the 3D world.
 */
public class Camera {

    private final Vector3f position;
    private final Vector3f rotation;
    private final Matrix4f projectionMatrix;
    private final Matrix4f viewMatrix;

    /**
     * Initializes the camera with the given viewport dimensions.
     *
     * @param width Viewport width.
     * @param height Viewport height.
     */
    public Camera(int width, int height) {
        this.position = new Vector3f(10, 100, 10); // Start high up
        this.rotation = new Vector3f(0, 0, 0);
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        updateProjection(width, height);
    }

    /**
     * Updates the projection matrix based on the window size.
     *
     * @param width New width.
     * @param height New height.
     */
    public void updateProjection(int width, int height) {
        float aspectRatio = (float) width / height;
        projectionMatrix.identity();
        projectionMatrix.perspective((float) Math.toRadians(60.0f), aspectRatio, 0.1f, 1000.0f);
    }

    /**
     * Calculates the view matrix based on current position and rotation.
     *
     * @return The view matrix.
     */
    public Matrix4f getViewMatrix() {
        viewMatrix.identity();
        // Rotate First (Pitch, Yaw)
        viewMatrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0));
        viewMatrix.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        // Translate inverse of position
        viewMatrix.translate(-position.x, -position.y, -position.z);
        return viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Vector3f getPosition() { return position; }
    public Vector3f getRotation() { return rotation; }

    /**
     * Moves the camera relative to its current rotation (First Person movement).
     *
     * @param x Strafe left/right.
     * @param y Up/Down (Absolute).
     * @param z Forward/Backward.
     */
    public void move(float x, float y, float z) {
        if (z != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * z;
            position.z += (float) Math.cos(Math.toRadians(rotation.y)) * z;
        }
        if (x != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * x;
            position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * x;
        }
        position.y += y;
    }

    /**
     * Rotates the camera.
     *
     * @param x Pitch change (up/down).
     * @param y Yaw change (left/right).
     */
    public void rotate(float x, float y) {
        rotation.x += x;
        rotation.y += y;

        // Clamp pitch to avoid flipping over
        if (rotation.x > 90) rotation.x = 90;
        if (rotation.x < -90) rotation.x = -90;
    }
}