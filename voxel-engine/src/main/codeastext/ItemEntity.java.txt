package com.voxelengine.entity;

import com.voxelengine.render.Renderer;
import com.voxelengine.ui.ItemStack;
import com.voxelengine.world.World;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class ItemEntity extends Entity {

    private final ItemStack stack;
    private int lifeTime = 0;
    private int pickupDelay = 40; // 2 seconds before you can pick it up
    private float bobOffset = 0;

    public ItemEntity(ItemStack stack, float x, float y, float z) {
        super(x, y, z, 0.25f, 0.25f); // Small hitbox (0.25 block size)
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
        
        // Friction
        if (onGround) {
            velocity.x *= 0.6f;
            velocity.z *= 0.6f;
        } else {
            velocity.x *= 0.98f;
            velocity.z *= 0.98f;
        }
        
        // Bobbing animation logic
        rotation.y += 2.0f; // Spin
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
    public boolean isDead() { return lifeTime > 6000; } // Despawn after 5 mins
}