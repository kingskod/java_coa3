package com.voxelengine.render;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

    private final Vector3f position;
    private final Vector3f rotation;
    private final Matrix4f projectionMatrix;
    private final Matrix4f viewMatrix;

    public Camera(int width, int height) {
        this.position = new Vector3f(0, 80, 0); // Start high up
        this.rotation = new Vector3f(0, 0, 0);
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        updateProjection(width, height);
    }

    public void updateProjection(int width, int height) {
        float aspectRatio = (float) width / height;
        projectionMatrix.identity();
        projectionMatrix.perspective((float) Math.toRadians(60.0f), aspectRatio, 0.1f, 1000.0f);
    }

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

    public void rotate(float x, float y) {
        rotation.x += x;
        rotation.y += y;

        // Clamp pitch
        if (rotation.x > 90) rotation.x = 90;
        if (rotation.x < -90) rotation.x = -90;
    }
}