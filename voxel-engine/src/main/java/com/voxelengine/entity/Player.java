package com.voxelengine.entity;

import com.voxelengine.audio.SoundManager;
import com.voxelengine.core.Input;
import com.voxelengine.render.Camera;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity {

    private final Camera camera;
    private final SoundManager soundManager;
    private final float moveSpeed = 5.0f;
    private final float jumpForce = 8.0f;
    private boolean isCrouching = false;
    
    private float stepTimer = 0;
    private boolean inWater = false;

    public Player(float x, float y, float z, Camera camera, SoundManager soundManager) {
        super(x, y, z, 0.6f, 1.8f);
        this.camera = camera;
        this.soundManager = soundManager;
    }

    @Override
    public void tick(World world, PhysicsEngine physics, float dt) {
        if (Float.isNaN(dt) || dt <= 0.0001f || dt > 1.0f) return;

        input();
        
        if (isCrouching) {
            this.height = 1.5f;
            this.boundingBox.maxY = this.boundingBox.minY + this.height;
        } else {
            this.height = 1.8f;
            this.boundingBox.maxY = this.boundingBox.minY + this.height;
        }

        physics.resolveCollision(this, world, dt);
        handleSounds(world, dt);
        
        if (Float.isNaN(position.x) || Float.isNaN(position.y) || Float.isNaN(position.z)) {
            System.err.println("Player position became NaN! Resetting.");
            position.set(0, 150, 0); 
            velocity.set(0, 0, 0);
        }

        camera.getPosition().set(position.x, position.y + height - 0.2f, position.z);
        
        float dy = (float)Input.getDY() * 0.1f;
        float dx = (float)Input.getDX() * 0.1f;
        
        if (!Float.isNaN(dx) && !Float.isNaN(dy)) {
            camera.rotate(dy, dx);
        }
        
        rotation.y = camera.getRotation().y;
    }

    private void input() {
        isCrouching = Input.isKeyDown(GLFW_KEY_LEFT_SHIFT);
        float speed = isCrouching ? moveSpeed * 0.3f : moveSpeed;
        float dx = 0, dz = 0;

        if (Input.isKeyDown(GLFW_KEY_W)) dz = -1;
        if (Input.isKeyDown(GLFW_KEY_S)) dz = 1;
        if (Input.isKeyDown(GLFW_KEY_A)) dx = -1;
        if (Input.isKeyDown(GLFW_KEY_D)) dx = 1;

        if (onGround && Input.isKeyDown(GLFW_KEY_SPACE)) {
            velocity.y = jumpForce;
            onGround = false;
        }

        if (dx != 0 || dz != 0) {
            float yaw = (float) Math.toRadians(rotation.y);
            float nx = (float) (dx * Math.cos(yaw) - dz * Math.sin(yaw));
            float nz = (float) (dx * Math.sin(yaw) + dz * Math.cos(yaw));
            float len = (float) Math.sqrt(nx*nx + nz*nz);
            nx /= len; nz /= len;
            velocity.x += nx * speed * 0.2f;
            velocity.z += nz * speed * 0.2f;
        }
    }
    
    private void handleSounds(World world, float dt) {
        stepTimer -= dt;
        if (onGround && (Math.abs(velocity.x) > 0.1 || Math.abs(velocity.z) > 0.1) && stepTimer <= 0) {
            stepTimer = 0.5f;
            Block below = world.getBlock((int)position.x, (int)(position.y - 0.1), (int)position.z);
            if (below.getSoundType() != null) {
                soundManager.play(below.getSoundType().stepSound);
            }
        }
        
        Block headBlock = world.getBlock((int)position.x, (int)(position.y + 1.5), (int)position.z);
        boolean currentlyInWater = headBlock.isWater();
        if (currentlyInWater && !inWater) {
            soundManager.play("splash.wav");
        }
        inWater = currentlyInWater;
    }

    public void checkPickups(EntityManager manager, com.voxelengine.ui.Inventory inventory) {
        for (Entity e : manager.getEntities()) {
            if (e instanceof ItemEntity) {
                ItemEntity item = (ItemEntity) e;
                if (!item.canPickup()) continue;
                if (position.distance(item.getPosition()) < 1.5f) {
                    if (inventory.add(item.getStack())) {
                        manager.remove(e);
                    }
                }
            }
        }
    }
}