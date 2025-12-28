package com.voxelengine.entity;

import com.voxelengine.render.Renderer;
import com.voxelengine.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages all entities in the game world.
 * Handles adding, removing, updating, and rendering entities safely.
 */
public class EntityManager {

    private final List<Entity> entities = new ArrayList<>();
    
    // Safety Queues to prevent ConcurrentModificationException during iteration
    private final List<Entity> pendingEntities = new ArrayList<>();
    private final List<Entity> pendingRemoval = new ArrayList<>();

    /**
     * Schedules an entity to be added to the world on the next tick.
     *
     * @param entity The entity to add.
     */
    public void addEntity(Entity entity) {
        pendingEntities.add(entity);
    }

    /**
     * Schedules an entity to be removed from the world on the next tick.
     *
     * @param entity The entity to remove.
     */
    public void remove(Entity entity) {
        pendingRemoval.add(entity);
    }

    /**
     * Updates all entities.
     * Processes pending additions and removals first, then ticks each entity.
     *
     * @param world The game world.
     * @param physics The physics engine.
     * @param dt The delta time.
     */
    public void tick(World world, PhysicsEngine physics, float dt) {
        // 1. Process Additions
        if (!pendingEntities.isEmpty()) {
            entities.addAll(pendingEntities);
            pendingEntities.clear();
        }

        // 2. Process Removals
        if (!pendingRemoval.isEmpty()) {
            entities.removeAll(pendingRemoval);
            pendingRemoval.clear();
        }

        // 3. Update Logic
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            e.tick(world, physics, dt);
            
            // Auto-remove dead entities (lifespan)
            if (e instanceof ItemEntity && ((ItemEntity) e).isDead()) {
                it.remove();
            }
        }
    }

    /**
     * Renders all entities using the provided renderer.
     *
     * @param renderer The renderer instance.
     */
    public void render(Renderer renderer) {
        for (Entity e : entities) {
            e.render(renderer);
        }
    }
    
    /**
     * Gets the list of active entities.
     * Note: Do not modify this list directly.
     *
     * @return The list of entities.
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Clears all entities immediately.
     */
    public void clearAll() {
        entities.clear();
        pendingEntities.clear();
    }
}