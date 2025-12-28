package com.voxelengine.entity;

import com.voxelengine.audio.SoundManager;
import com.voxelengine.core.Input;
import com.voxelengine.render.Camera;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Represents the player entity controlled by the user.
 * Handles input processing, movement physics, and camera control.
 */
public class Player extends Entity {

    private final Camera camera;
    private final SoundManager soundManager;
    private final float moveSpeed = 5.0f;
    private final float jumpForce = 8.0f;
    private boolean isCrouching = false;
    
    private float stepTimer = 0;
    private boolean inWater = false;

    /**
     * Creates a new Player instance.
     *
     * @param x Initial X position.
     * @param y Initial Y position.
     * @param z Initial Z position.
     * @param camera The camera associated with the player.
     * @param soundManager The sound manager for player effects.
     */
    public Player(float x, float y, float z, Camera camera, SoundManager soundManager) {
        super(x, y, z, 0.6f, 1.8f);
        this.camera = camera;
        this.soundManager = soundManager;
    }

    /**
     * Updates the player's state for the current frame.
     * Processes input, physics, and sounds.
     *
     * @param world The game world.
     * @param physics The physics engine.
     * @param dt The delta time for the frame.
     */
    @Override
    public void tick(World world, PhysicsEngine physics, float dt) {
        if (Float.isNaN(dt) || dt <= 0.0001f || dt > 1.0f) return;

        input();
        
        // Adjust height for crouching
        if (isCrouching) {
            this.height = 1.5f;
            this.boundingBox.maxY = this.boundingBox.minY + this.height;
        } else {
            this.height = 1.8f;
            this.boundingBox.maxY = this.boundingBox.minY + this.height;
        }

        physics.resolveCollision(this, world, dt);
        handleSounds(world, dt);
        
        // Safety check for invalid positions
        if (Float.isNaN(position.x) || Float.isNaN(position.y) || Float.isNaN(position.z)) {
            System.err.println("Player position became NaN! Resetting.");
            position.set(0, 150, 0); 
            velocity.set(0, 0, 0);
        }

        // Sync camera position to player head
        camera.getPosition().set(position.x, position.y + height - 0.2f, position.z);
        
        // Mouse look
        float dy = (float)Input.getDY() * 0.1f;
        float dx = (float)Input.getDX() * 0.1f;
        
        if (!Float.isNaN(dx) && !Float.isNaN(dy)) {
            camera.rotate(dy, dx);
        }
        
        // Sync player rotation Y with camera for movement direction
        rotation.y = camera.getRotation().y;
    }

    /**
     * Processes keyboard input to control velocity.
     */
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
    
    /**
     * Handles footstep and environmental sounds (e.g., splashing).
     */
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

    /**
     * Checks for nearby item entities to pick up.
     *
     * @param manager The entity manager.
     * @param inventory The player's inventory to add items to.
     */
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