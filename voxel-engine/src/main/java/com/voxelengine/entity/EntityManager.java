package com.voxelengine.entity;

import com.voxelengine.render.Renderer;
import com.voxelengine.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {

    private final List<Entity> entities = new ArrayList<>();
    
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

        // 2. Process Removals (FIXED LEAK)
        if (!pendingRemoval.isEmpty()) {
            for (Entity e : pendingRemoval) {
                // IMPORTANT: Release OpenGL resources before dropping reference
                e.cleanup(); 
            }
            entities.removeAll(pendingRemoval);
            pendingRemoval.clear();
        }

        // 3. Update Logic
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            e.tick(world, physics, dt);
            
            // Auto-remove dead entities
            if (e instanceof ItemEntity && ((ItemEntity) e).isDead()) {
                // Schedule removal so cleanup happens in step 2 next frame
                remove(e); 
            }
        }
    }

    public void render(Renderer renderer) {
        for (Entity e : entities) {
            e.render(renderer);
        }
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
    public void clearAll() {
        // Cleanup all active entities
        for (Entity e : entities) e.cleanup();
        for (Entity e : pendingEntities) e.cleanup();
        
        entities.clear();
        pendingEntities.clear();
        pendingRemoval.clear();
    }
}