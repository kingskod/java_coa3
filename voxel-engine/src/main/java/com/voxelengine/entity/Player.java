package com.voxelengine.entity;

import com.voxelengine.core.Input;
import com.voxelengine.render.Camera;
import com.voxelengine.world.World;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity {

    private final Camera camera;
    private final float moveSpeed = 5.0f;
    private final float jumpForce = 8.0f;

    private boolean isCrouching = false;

    public Player(float x, float y, float z, Camera camera) {
        super(x, y, z, 0.6f, 1.8f);
        this.camera = camera;
    }

    @Override
    public void tick(World world, PhysicsEngine physics, float dt) {
        input();

        // Crouch Logic (Size Change)
        if (isCrouching) {
            this.height = 1.5f;
            this.boundingBox.maxY = this.boundingBox.minY + this.height;
        } else {
            this.height = 1.8f;
            this.boundingBox.maxY = this.boundingBox.minY + this.height;
            // Note: If standing up into a block, physics will push us down or clip.
            // A robust engine checks headroom before standing.
        }

        physics.resolveCollision(this, world, dt);

        // Sync Camera
        // Eye level is usually near top of height
        camera.getPosition().set(position.x, position.y + height - 0.2f, position.z);

        // Update Camera Rotation from Mouse Input
        camera.rotate((float)Input.getDY() * 0.1f, (float)Input.getDX() * 0.1f);

        // Sync Player rotation for movement logic (yaw only)
        rotation.y = camera.getRotation().y;
    }

    private void input() {
        // Crouch
        isCrouching = Input.isKeyDown(GLFW_KEY_LEFT_SHIFT);

        float speed = isCrouching ? moveSpeed * 0.3f : moveSpeed;

        float dx = 0, dz = 0;

        if (Input.isKeyDown(GLFW_KEY_W)) dz = -1;
        if (Input.isKeyDown(GLFW_KEY_S)) dz = 1;
        if (Input.isKeyDown(GLFW_KEY_A)) dx = -1;
        if (Input.isKeyDown(GLFW_KEY_D)) dx = 1;

        // Jump
        if (onGround && Input.isKeyDown(GLFW_KEY_SPACE)) {
            velocity.y = jumpForce;
            onGround = false;
        }

        // Convert to World Coordinates based on Yaw
        if (dx != 0 || dz != 0) {
            float yaw = (float) Math.toRadians(rotation.y);
            float nx = (float) (dx * Math.cos(yaw) - dz * Math.sin(yaw));
            float nz = (float) (dx * Math.sin(yaw) + dz * Math.cos(yaw));

            // Normalize
            float len = (float) Math.sqrt(nx*nx + nz*nz);
            nx /= len; nz /= len;

            velocity.x += nx * speed * 0.2f; // Acceleration
            velocity.z += nz * speed * 0.2f;
        }
    }
}