package com.voxelengine.entity;

import com.voxelengine.render.Mesh;
import com.voxelengine.render.Renderer;
import com.voxelengine.render.TextureAtlas;
import com.voxelengine.ui.ItemStack;
import com.voxelengine.utils.Direction;
import com.voxelengine.world.World;
import org.joml.Matrix4f;

public class ItemEntity extends Entity {

    private final ItemStack stack;
    private int lifeTime = 0;
    private float bobOffset;
    private Mesh mesh; // Each item holds its own mini-mesh

    public ItemEntity(ItemStack stack, float x, float y, float z, TextureAtlas atlas) {
        super(x, y, z, 0.25f, 0.25f);
        this.stack = stack;
        this.bobOffset = (float) (Math.random() * Math.PI * 2);
        
        // Random velocity
        this.velocity.set((float)(Math.random()*.2-.1), 0.2f, (float)(Math.random()*.2-.1));
        
        generateMesh(atlas);
    }
    
    private void generateMesh(TextureAtlas atlas) {
        String name = stack.getBlock().name().toLowerCase();
        float[] v = new float[288]; // 6 faces * 6 verts * 8 floats
        int i = 0;
        float l = 1.0f; // Full Bright
        float s = 0.2f; // Size (radius)
        
        // Generate 6 faces with correct textures
        i = addFace(v, i, Direction.NORTH, atlas.getIndex(name, Direction.NORTH), s, l);
        i = addFace(v, i, Direction.SOUTH, atlas.getIndex(name, Direction.SOUTH), s, l);
        i = addFace(v, i, Direction.EAST,  atlas.getIndex(name, Direction.EAST),  s, l);
        i = addFace(v, i, Direction.WEST,  atlas.getIndex(name, Direction.WEST),  s, l);
        i = addFace(v, i, Direction.UP,    atlas.getIndex(name, Direction.UP),    s, l);
        i = addFace(v, i, Direction.DOWN,  atlas.getIndex(name, Direction.DOWN),  s, l);
        
        this.mesh = new Mesh(v);
    }
    
    private int addFace(float[] v, int i, Direction dir, float tid, float s, float l) {
        // Vertices relative to center (0,0,0)
        // Similar to Renderer logic but simplified for generic cube
        float x0=-s, y0=-s, z0=-s, x1=s, y1=s, z1=s;
        
        // Define quad points based on normal
        float[] p0, p1, p2, p3; // BL, BR, TR, TL
        
        if (dir == Direction.NORTH) { // Z-
             p0 = new float[]{x1, y0, z0}; p1 = new float[]{x0, y0, z0}; p2 = new float[]{x0, y1, z0}; p3 = new float[]{x1, y1, z0};
        } else if (dir == Direction.SOUTH) { // Z+
             p0 = new float[]{x0, y0, z1}; p1 = new float[]{x1, y0, z1}; p2 = new float[]{x1, y1, z1}; p3 = new float[]{x0, y1, z1};
        } else if (dir == Direction.EAST) { // X+
             p0 = new float[]{x1, y0, z1}; p1 = new float[]{x1, y0, z0}; p2 = new float[]{x1, y1, z0}; p3 = new float[]{x1, y1, z1};
        } else if (dir == Direction.WEST) { // X-
             p0 = new float[]{x0, y0, z0}; p1 = new float[]{x0, y0, z1}; p2 = new float[]{x0, y1, z1}; p3 = new float[]{x0, y1, z0};
        } else if (dir == Direction.UP) { // Y+
             p0 = new float[]{x0, y1, z1}; p1 = new float[]{x1, y1, z1}; p2 = new float[]{x1, y1, z0}; p3 = new float[]{x0, y1, z0};
        } else { // Y-
             p0 = new float[]{x0, y0, z0}; p1 = new float[]{x1, y0, z0}; p2 = new float[]{x1, y0, z1}; p3 = new float[]{x0, y0, z1};
        }
        
        // Add tris (UV 0,1 to 1,0 because shader flips)
        // Tri 1
        v[i++] = p0[0]; v[i++] = p0[1]; v[i++] = p0[2]; v[i++] = 0; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p1[0]; v[i++] = p1[1]; v[i++] = p1[2]; v[i++] = 1; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p2[0]; v[i++] = p2[1]; v[i++] = p2[2]; v[i++] = 1; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = tid;
        // Tri 2
        v[i++] = p0[0]; v[i++] = p0[1]; v[i++] = p0[2]; v[i++] = 0; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p2[0]; v[i++] = p2[1]; v[i++] = p2[2]; v[i++] = 1; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p3[0]; v[i++] = p3[1]; v[i++] = p3[2]; v[i++] = 0; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = tid;
        
        return i;
    }

    @Override
    public void tick(World world, PhysicsEngine physics, float dt) {
        lifeTime++;
        physics.resolveCollision(this, world, dt);
        if (onGround) { velocity.x *= 0.6f; velocity.z *= 0.6f; }
        else { velocity.x *= 0.98f; velocity.z *= 0.98f; }
        rotation.y += 2.0f;
    }

    @Override
    public void render(Renderer renderer) {
        if (mesh == null) return;
        
        float hover = (float) Math.sin(lifeTime / 10.0f + bobOffset) * 0.1f;
        Matrix4f model = new Matrix4f()
            .translate(position.x, position.y + hover + 0.25f, position.z) // +0.25 to center visual
            .rotate((float) Math.toRadians(rotation.y), 0, 1, 0);
            // No scale needed, vertices are pre-scaled to 0.25 radius
            
        renderer.renderMesh(mesh, model);
    }
    
    public void cleanup() {
        if (mesh != null) mesh.cleanup();
    }

    public ItemStack getStack() { return stack; }
    public boolean canPickup() { return lifeTime > 20; }
    public boolean isDead() { return lifeTime > 6000; }
}