package com.voxelengine.entity;

import com.voxelengine.render.Renderer;
import com.voxelengine.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {

    private final List<Entity> entities = new ArrayList<>();
    
    // Safety Queues to prevent ConcurrentModificationException
    private final List<Entity> pendingEntities = new ArrayList<>();
    private final List<Entity> pendingRemoval = new ArrayList<>();

    public void addEntity(Entity entity) {
        pendingEntities.add(entity);
    }

    public void remove(Entity entity) {
        pendingRemoval.add(entity);
    }

    public void tick(World world, PhysicsEngine physics, float dt) {
        // 1. Process Additions
        if (!pendingEntities.isEmpty()) {
            entities.addAll(pendingEntities);
            pendingEntities.clear();
        }

        // 2. Process Removals (Safe time to remove)
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

    public void render(Renderer renderer) {
        for (Entity e : entities) {
            e.render(renderer);
        }
    }
    
    // Return a copy or raw list? Raw is faster, but we must not modify it externally.
    public List<Entity> getEntities() {
        return entities;
    }
}