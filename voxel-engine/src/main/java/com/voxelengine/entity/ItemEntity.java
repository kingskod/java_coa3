package com.voxelengine.entity;

import com.voxelengine.render.Renderer;
import com.voxelengine.ui.ItemStack;
import com.voxelengine.world.World;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Represents a dropped item floating in the world.
 * Items bob up and down and rotate.
 */
public class ItemEntity extends Entity {

    private final ItemStack stack;
    private int lifeTime = 0;
    private int pickupDelay = 40; // 2 seconds (at 20tps) before pickup
    private float bobOffset = 0;

    /**
     * Creates a new ItemEntity.
     *
     * @param stack The item stack to drop.
     * @param x X position.
     * @param y Y position.
     * @param z Z position.
     */
    public ItemEntity(ItemStack stack, float x, float y, float z) {
        super(x, y, z, 0.25f, 0.25f); // Small hitbox
        this.stack = stack;
        
        // Random velocity spread
        float vx = (float) (Math.random() * 0.2f - 0.1f);
        float vz = (float) (Math.random() * 0.2f - 0.1f);
        this.velocity.set(vx, 0.2f, vz); // Little hop on spawn
        
        this.bobOffset = (float) (Math.random() * Math.PI * 2);
    }

    @Override
    public void tick(World world, PhysicsEngine physics, float dt) {
        lifeTime++;
        if (pickupDelay > 0) pickupDelay--;

        // Apply simple physics
        physics.resolveCollision(this, world, dt);
        
        // Ground Friction
        if (onGround) {
            velocity.x *= 0.6f;
            velocity.z *= 0.6f;
        } else {
            velocity.x *= 0.98f;
            velocity.z *= 0.98f;
        }
        
        // Rotate
        rotation.y += 2.0f;
    }

    @Override
    public void render(Renderer renderer) {
        // Calculate bobbing height
        float hover = (float) Math.sin((lifeTime / 10.0f) + bobOffset) * 0.1f + 0.1f;
        
        Matrix4f model = new Matrix4f()
            .translate(position.x, position.y + hover, position.z)
            .rotate((float) Math.toRadians(rotation.y), 0, 1, 0)
            .scale(0.25f); // Scale down to 1/4th block size
            
        renderer.renderItemCube(stack.getBlock(), model);
    }

    public ItemStack getStack() { return stack; }
    public boolean canPickup() { return pickupDelay <= 0; }
    public boolean isDead() { return lifeTime > 6000; } // Despawn after 5 mins (at 20 TPS)
}