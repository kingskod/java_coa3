package com.voxelengine.entity;

import com.voxelengine.render.Renderer;
import com.voxelengine.world.World;
import org.joml.Vector3f;

/**
 * Base class for all dynamic objects in the game world.
 * Contains position, rotation, velocity, and bounding box information.
 */
public abstract class Entity {

    public Vector3f position;
    public Vector3f rotation;
    public Vector3f velocity;
    public AABB boundingBox;
    public boolean onGround;

    protected float width, height;

    /**
     * Initializes a new Entity.
     *
     * @param x Initial X coordinate.
     * @param y Initial Y coordinate.
     * @param z Initial Z coordinate.
     * @param width The physical width (for collision).
     * @param height The physical height (for collision).
     */
    public Entity(float x, float y, float z, float width, float height) {
        this.position = new Vector3f(x, y, z);
        
        this.rotation = new Vector3f(0, 0, 0);
        
        this.velocity = new Vector3f(0, 0, 0);
        
        this.width = width;
        this.height = height;
        
        this.boundingBox = AABB.fromPositionSize(position, width, height);
    }

    /**
     * Updates the entity's logic and physics.
     *
     * @param world The game world.
     * @param physics The physics engine.
     * @param dt The time delta.
     */
    public abstract void tick(World world, PhysicsEngine physics, float dt);

    /**
     * Renders the entity.
     *
     * @param renderer The renderer.
     */
    public void render(Renderer renderer) {
        // Default implementation does nothing.
    }

    public Vector3f getPosition() {
        return position;
    }
}