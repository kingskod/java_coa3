package com.voxelengine.entity;

import com.voxelengine.render.Renderer;
import com.voxelengine.world.World;
import org.joml.Vector3f;

public abstract class Entity {

    public Vector3f position;
    public Vector3f rotation;
    public Vector3f velocity;
    public AABB boundingBox;
    public boolean onGround;

    protected float width, height;

    public Entity(float x, float y, float z, float width, float height) {
        this.position = new Vector3f(x, y, z);
        
        this.rotation = new Vector3f(0, 0, 0);
        
        this.velocity = new Vector3f(0, 0, 0);
        
        this.width = width;
        this.height = height;
        
        this.boundingBox = AABB.fromPositionSize(position, width, height);
    }

    public abstract void tick(World world, PhysicsEngine physics, float dt);

    // Stub for rendering entities later
    public void render(Renderer renderer) {
    }

    public Vector3f getPosition() {
        return position;
    }
}