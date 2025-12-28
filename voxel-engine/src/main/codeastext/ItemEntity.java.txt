package com.voxelengine.entity;

import com.voxelengine.render.Mesh;
import com.voxelengine.render.Renderer;
import com.voxelengine.render.TextureAtlas;
import com.voxelengine.ui.ItemStack;
import com.voxelengine.utils.Direction;
import com.voxelengine.world.Block;
import com.voxelengine.world.World;
import org.joml.Matrix4f;

public class ItemEntity extends Entity {

    private final ItemStack stack;
    private int lifeTime = 0;
    private float bobOffset;
    private Mesh mesh; 

    public ItemEntity(ItemStack stack, float x, float y, float z, TextureAtlas atlas) {
        super(x, y, z, 0.25f, 0.25f);
        this.stack = stack;
        this.bobOffset = (float) (Math.random() * Math.PI * 2);
        
        // Random velocity to spread out drops
        this.velocity.set((float)(Math.random()*.2-.1), 0.2f, (float)(Math.random()*.2-.1));
        
        generateMesh(atlas);
    }
    
    private void generateMesh(TextureAtlas atlas) {
        Block block = stack.getBlock();
        String name = block.getItemIcon();
        if (name == null) name = block.name().toLowerCase();
        
        // Decide: Block Model (3D) or Flat Sprite (2D)?
        // Full cubes (Stone, Grass, Log) -> 3D
        // Non-full cubes (Gates, Wire, Torches, Saplings) -> 2D Sprite
        if (block.isFullCube()) {
            generateBlockMesh(atlas, block);
        } else {
            generateFlatMesh(atlas, name);
        }
    }
    
    // --- 2D Sprite Generator (For Gates, Wire, Torches) ---
    private void generateFlatMesh(TextureAtlas atlas, String iconName) {
        float[] v = new float[288]; 
        int i = 0;
        float l = 1.0f; // Full Bright for items
        float s = 0.25f; // Size (radius)
        
        float tex = atlas.getIndex(iconName, Direction.SOUTH);
        
        // Render a Quad standing up (North/South aligned) and rotating
        // Front Face
        i = addFace(v, i, Direction.SOUTH, tex, s, l);
        // Back Face (so it's visible from behind)
        i = addFace(v, i, Direction.NORTH, tex, s, l);
        
        this.mesh = new Mesh(trimArray(v, i));
    }

    // --- 3D Block Generator (For Grass, Stone, Log) ---
    private void generateBlockMesh(TextureAtlas atlas, Block block) {
        float[] v = new float[288]; 
        int i = 0;
        float l = 1.0f;
        float s = 0.25f; 
        
        String baseName = block.name().toLowerCase();
        
        // FIX BUG #6: Query exact face textures
        // This ensures Grass Top is Green, Log Top is rings, etc.
        
        i = addFace(v, i, Direction.NORTH, atlas.getIndex(baseName, Direction.NORTH), s, l);
        i = addFace(v, i, Direction.SOUTH, atlas.getIndex(baseName, Direction.SOUTH), s, l);
        i = addFace(v, i, Direction.EAST,  atlas.getIndex(baseName, Direction.EAST),  s, l);
        i = addFace(v, i, Direction.WEST,  atlas.getIndex(baseName, Direction.WEST),  s, l);
        i = addFace(v, i, Direction.UP,    atlas.getIndex(baseName, Direction.UP),    s, l);
        i = addFace(v, i, Direction.DOWN,  atlas.getIndex(baseName, Direction.DOWN),  s, l);
        
        this.mesh = new Mesh(v); // Block always fills array
    }
    
    private float[] trimArray(float[] v, int length) {
        float[] t = new float[length];
        System.arraycopy(v, 0, t, 0, length);
        return t;
    }
    
    private int addFace(float[] v, int i, Direction dir, float tid, float s, float l) {
        // Centered at 0,0,0
        float x0=-s, y0=-s, z0=-s, x1=s, y1=s, z1=s;
        float[] p0, p1, p2, p3; 
        
        if (dir == Direction.NORTH) { 
             p0 = new float[]{x1, y0, z0}; p1 = new float[]{x0, y0, z0}; p2 = new float[]{x0, y1, z0}; p3 = new float[]{x1, y1, z0};
        } else if (dir == Direction.SOUTH) { 
             p0 = new float[]{x0, y0, z1}; p1 = new float[]{x1, y0, z1}; p2 = new float[]{x1, y1, z1}; p3 = new float[]{x0, y1, z1};
        } else if (dir == Direction.EAST) { 
             p0 = new float[]{x1, y0, z1}; p1 = new float[]{x1, y0, z0}; p2 = new float[]{x1, y1, z0}; p3 = new float[]{x1, y1, z1};
        } else if (dir == Direction.WEST) { 
             p0 = new float[]{x0, y0, z0}; p1 = new float[]{x0, y0, z1}; p2 = new float[]{x0, y1, z1}; p3 = new float[]{x0, y1, z0};
        } else if (dir == Direction.UP) { 
             p0 = new float[]{x0, y1, z1}; p1 = new float[]{x1, y1, z1}; p2 = new float[]{x1, y1, z0}; p3 = new float[]{x0, y1, z0};
        } else { 
             p0 = new float[]{x0, y0, z0}; p1 = new float[]{x1, y0, z0}; p2 = new float[]{x1, y0, z1}; p3 = new float[]{x0, y0, z1};
        }
        
        // UV Mapping: 0=Bottom, 1=Top
        // p0/p1 are bottom vertices -> V=0
        // p2/p3 are top vertices -> V=1
        
        // Tri 1
        v[i++] = p0[0]; v[i++] = p0[1]; v[i++] = p0[2]; v[i++] = 0; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p1[0]; v[i++] = p1[1]; v[i++] = p1[2]; v[i++] = 1; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p2[0]; v[i++] = p2[1]; v[i++] = p2[2]; v[i++] = 1; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = tid;
        // Tri 2
        v[i++] = p0[0]; v[i++] = p0[1]; v[i++] = p0[2]; v[i++] = 0; v[i++] = 0; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p2[0]; v[i++] = p2[1]; v[i++] = p2[2]; v[i++] = 1; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = tid;
        v[i++] = p3[0]; v[i++] = p3[1]; v[i++] = p3[2]; v[i++] = 0; v[i++] = 1; v[i++] = l; v[i++] = l; v[i++] = tid;
        
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
        
        // If it's a flat item (not full cube), we might want it to always face the camera?
        // Minecraft items rotate. Our mesh generation creates a flat card.
        // Rotation Y handles the spin.
        
        Matrix4f model = new Matrix4f()
            .translate(position.x, position.y + hover + 0.25f, position.z)
            .rotate((float) Math.toRadians(rotation.y), 0, 1, 0);
            
        renderer.renderMesh(mesh, model);
    }
    
    public void cleanup() {
        if (mesh != null) mesh.cleanup();
    }

    public ItemStack getStack() { return stack; }
    public boolean canPickup() { return lifeTime > 20; }
    public boolean isDead() { return lifeTime > 6000; }
}