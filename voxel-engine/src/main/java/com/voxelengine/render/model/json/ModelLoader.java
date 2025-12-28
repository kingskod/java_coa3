package com.voxelengine.render.model.json;

import com.google.gson.Gson;
import com.voxelengine.render.TextureAtlas;
import com.voxelengine.render.model.BakedModel;
import com.voxelengine.render.model.BakedQuad;
import com.voxelengine.render.model.json.JsonElement.ElementRotation;
import com.voxelengine.utils.Direction;
import com.voxelengine.utils.FileUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.*;

public class ModelLoader {

    private final Gson gson = new Gson();
    private final Map<String, JsonBlockModel> rawModelCache = new HashMap<>();
    private final TextureAtlas atlas;

    public ModelLoader(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public BlockState loadBlockState(String blockName) {
        try {
            String json = FileUtils.loadResourceAsString("assets/blockstates/" + blockName + ".json");
            return gson.fromJson(json, BlockState.class);
        } catch (Exception e) {
            System.err.println("Failed to load blockstate: " + blockName);
            return null;
        }
    }

    /**
     * Bakes a model given its resource path and variant rotations.
     */
    public BakedModel bakeModel(String modelPath, int rotX, int rotY) {
        // 1. Flatten Inheritance hierarchy
        JsonBlockModel resolved = flattenModel(modelPath);
        if (resolved == null) return BakedModel.EMPTY;

        List<BakedQuad> quads = new ArrayList<>();

        // 2. Setup Variant Rotation Matrix (Global rotation for the block)
        Matrix4f variantMatrix = new Matrix4f();
        variantMatrix.translate(0.5f, 0.5f, 0.5f); // Move to pivot
        variantMatrix.rotateY((float) Math.toRadians(-rotY)); // Minecraft uses clockwise Y? Check standard.
        variantMatrix.rotateX((float) Math.toRadians(rotX));
        variantMatrix.translate(-0.5f, -0.5f, -0.5f); // Move back

        // 3. Process Elements
        if (resolved.elements != null) {
            for (JsonElement el : resolved.elements) {
                processElement(el, resolved, variantMatrix, quads);
            }
        }

        return new BakedModel(quads, resolved.ambientocclusion);
    }

    private JsonBlockModel flattenModel(String path) {
        // Normalize path (remove "block/" prefix if generic loader uses it, or keep it)
        // Assumption: path is like "block/cube_all"
        if (rawModelCache.containsKey(path)) return rawModelCache.get(path);

        String jsonContent;
        try {
            jsonContent = FileUtils.loadResourceAsString("assets/models/" + path + ".json");
        } catch (Exception e) {
            System.err.println("Model file not found: " + path);
            return null;
        }

        JsonBlockModel model = gson.fromJson(jsonContent, JsonBlockModel.class);
        
        // Handle Parent (Recursion)
        if (model.parent != null && !model.parent.isEmpty()) {
            JsonBlockModel parent = flattenModel(model.parent);
            if (parent != null) {
                // Merge Textures (Child overrides Parent)
                if (parent.textures != null) {
                    if (model.textures == null) model.textures = new HashMap<>();
                    for (Map.Entry<String, String> entry : parent.textures.entrySet()) {
                        model.textures.putIfAbsent(entry.getKey(), entry.getValue());
                    }
                }
                // Inherit Elements if Child has none
                if (model.elements == null || model.elements.isEmpty()) {
                    model.elements = parent.elements;
                }
            }
        }
        
        rawModelCache.put(path, model);
        return model;
    }

    private void processElement(JsonElement el, JsonBlockModel context, Matrix4f variantMatrix, List<BakedQuad> outQuads) {
        float[] from = el.from; // 0-16
        float[] to = el.to;     // 0-16

        // Element-specific rotation (e.g. tilted torch)
        Matrix4f elementMatrix = new Matrix4f();
        if (el.rotation != null) {
            ElementRotation r = el.rotation;
            elementMatrix.translate(r.origin[0] / 16f, r.origin[1] / 16f, r.origin[2] / 16f);
            switch (r.axis) {
                case "x": elementMatrix.rotateX((float) Math.toRadians(r.angle)); break;
                case "y": elementMatrix.rotateY((float) Math.toRadians(r.angle)); break;
                case "z": elementMatrix.rotateZ((float) Math.toRadians(r.angle)); break;
            }
            elementMatrix.translate(-r.origin[0] / 16f, -r.origin[1] / 16f, -r.origin[2] / 16f);
            // "rescale" logic omitted for brevity, usually scales face to fit unit cube
        }

        for (Map.Entry<String, JsonFace> entry : el.faces.entrySet()) {
            String dirName = entry.getKey();
            JsonFace faceData = entry.getValue();
            Direction dir = Direction.valueOf(dirName.toUpperCase());

            // 1. Resolve Texture
            String texVar = faceData.texture;
            String resolvedTex = resolveTexture(texVar, context);
            float texIndex = atlas.getIndex(resolvedTex, Direction.UP); // Direction doesn't matter for atlas lookups by name

            // 2. Calculate UVs
            float[] uv = faceData.uv;
            if (uv == null) {
                // Auto calculate based on face coords
                uv = calculateAutoUV(dir, from, to);
            }
            
            // 3. Build Vertices (0-1 space)
            Vector3f[] corners = getFaceCorners(dir, from, to);
            
            // 4. Transform Vertices
            for (Vector3f v : corners) {
                // Apply Element Rotation
                if (el.rotation != null) {
                    Vector4f v4 = new Vector4f(v.x, v.y, v.z, 1.0f);
                    elementMatrix.transform(v4);
                    v.set(v4.x, v4.y, v4.z);
                }
                // Apply Variant Rotation (Global)
                Vector4f v4 = new Vector4f(v.x, v.y, v.z, 1.0f);
                variantMatrix.transform(v4);
                v.set(v4.x, v4.y, v4.z);
            }

            // 5. Create BakedQuad
            // Quad expects flat array x,y,z
            float[] pos = new float[] {
                corners[0].x, corners[0].y, corners[0].z,
                corners[1].x, corners[1].y, corners[1].z,
                corners[2].x, corners[2].y, corners[2].z,
                corners[3].x, corners[3].y, corners[3].z
            };
            
            // UV Rotation logic would go here (manipulating the uv array)
            
            outQuads.add(new BakedQuad(pos, uv, dir, texIndex, -1));
        }
    }

    private String resolveTexture(String ref, JsonBlockModel model) {
        while (ref.startsWith("#")) {
            String key = ref.substring(1);
            if (model.textures != null && model.textures.containsKey(key)) {
                ref = model.textures.get(key);
            } else {
                return "missing"; // Failed to resolve
            }
        }
        // Remove "block/" prefix if texture atlas doesn't use it, or normalize
        if (ref.startsWith("block/")) ref = ref.substring(6);
        if (ref.startsWith("blocks/")) ref = ref.substring(7); // Minecraft legacy
        return ref;
    }

    private float[] calculateAutoUV(Direction dir, float[] from, float[] to) {
        // Minecraft UV rule: 0-16 range
        // North/South: X/Y, etc. 
        // For simplicity in this engine: map 0-16 world coords to 0-16 uv
        // NOTE: This assumes default projection.
        float[] uv = new float[4];
        switch(dir) {
            case UP:    uv = new float[]{from[0], from[2], to[0], to[2]}; break;
            case DOWN:  uv = new float[]{from[0], from[2], to[0], to[2]}; break;
            case NORTH: uv = new float[]{16-to[0], 16-to[1], 16-from[0], 16-from[1]}; break;
            case SOUTH: uv = new float[]{from[0], 16-to[1], to[0], 16-from[1]}; break;
            case EAST:  uv = new float[]{16-to[2], 16-to[1], 16-from[2], 16-from[1]}; break;
            case WEST:  uv = new float[]{from[2], 16-to[1], to[2], 16-from[1]}; break;
        }
        return uv;
    }

    private Vector3f[] getFaceCorners(Direction dir, float[] from, float[] to) {
        float x1 = from[0]/16f, y1 = from[1]/16f, z1 = from[2]/16f;
        float x2 = to[0]/16f,   y2 = to[1]/16f,   z2 = to[2]/16f;
        
        // Counter-clockwise definition for standard winding
        switch(dir) {
            case NORTH: return new Vector3f[]{ // Negative Z
                new Vector3f(x2, y2, z1), new Vector3f(x2, y1, z1),
                new Vector3f(x1, y1, z1), new Vector3f(x1, y2, z1)
            };
            case SOUTH: return new Vector3f[]{ // Positive Z
                new Vector3f(x1, y2, z2), new Vector3f(x1, y1, z2),
                new Vector3f(x2, y1, z2), new Vector3f(x2, y2, z2)
            };
            case EAST: return new Vector3f[]{ // Positive X
                new Vector3f(x2, y2, z2), new Vector3f(x2, y1, z2),
                new Vector3f(x2, y1, z1), new Vector3f(x2, y2, z1)
            };
            case WEST: return new Vector3f[]{ // Negative X
                new Vector3f(x1, y2, z1), new Vector3f(x1, y1, z1),
                new Vector3f(x1, y1, z2), new Vector3f(x1, y2, z2)
            };
            case UP: return new Vector3f[]{ // Positive Y
                new Vector3f(x1, y2, z1), new Vector3f(x1, y2, z2),
                new Vector3f(x2, y2, z2), new Vector3f(x2, y2, z1)
            };
            case DOWN: return new Vector3f[]{ // Negative Y
                new Vector3f(x1, y1, z2), new Vector3f(x1, y1, z1),
                new Vector3f(x2, y1, z1), new Vector3f(x2, y1, z2)
            };
        }
        return new Vector3f[0];
    }
}