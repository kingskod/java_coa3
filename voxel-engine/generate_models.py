import json
import os
import math

# --- Configuration ---
SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
INPUT_DIR = os.path.join(SCRIPT_DIR, "src", "main", "resources", "assets", "models", "block")
OUTPUT_DIR = os.path.join(SCRIPT_DIR, "src", "main", "java", "com", "voxelengine", "render")
OUTPUT_CLASS_NAME = "GeneratedBlockModels"
PACKAGE_NAME = "com.voxelengine.render"

# --- Data Structures ---
class BakedBox:
    def __init__(self, x1, y1, z1, x2, y2, z2, texture):
        self.x1, self.y1, self.z1 = x1, y1, z1
        self.x2, self.y2, self.z2 = x2, y2, z2
        self.texture = texture

# --- Module A: Model Parser (Enhanced for Inheritance) ---
class ModelParser:
    def __init__(self, root_dir):
        self.root_dir = root_dir
        self.cache = {}

    def load_flattened(self, filename):
        """
        Loads a model and recursively resolves its parent to get 'elements'.
        Returns (elements, textures) tuple or None.
        """
        if filename in self.cache: return self.cache[filename]
        
        path = os.path.join(self.root_dir, filename)
        if not path.endswith(".json"): path += ".json"
        
        try:
            with open(path, 'r') as f:
                data = json.load(f)
        except Exception as e:
            print(f"  [Error] Could not read {filename}: {e}")
            return None

        # 1. Resolve Textures
        my_textures = self.resolve_textures(data.get("textures", {}))
        
        # 2. Resolve Elements
        my_elements = data.get("elements", None)

        # 3. Handle Parent
        if my_elements is None and "parent" in data:
            parent_name = self.clean_name(data["parent"])
            # Recursion
            parent_data = self.load_flattened(parent_name)
            
            if parent_data:
                parent_elems, parent_tex = parent_data
                my_elements = parent_elems # Inherit elements
                # Merge textures (Child overrides Parent)
                for k, v in parent_tex.items():
                    if k not in my_textures:
                        my_textures[k] = v
        
        # Final texture variable resolution (Resolve #var to values)
        final_textures = self.finalize_textures(my_textures)
        
        result = (my_elements, final_textures)
        self.cache[filename] = result
        return result

    def resolve_textures(self, raw_textures):
        # Just clean values, don't resolve # variables yet
        cleaned = {}
        for k, v in raw_textures.items():
            cleaned[k] = self.clean_name(v)
        return cleaned

    def finalize_textures(self, texture_map):
        # Resolves #reference to values
        resolved = {}
        def lookup(key, stack):
            if key in stack: return "missing"
            val = texture_map.get(key, "missing")
            if val.startswith("#"):
                return lookup(val[1:], stack + [key])
            return val

        for k in texture_map:
            resolved[k] = lookup(k, [])
        return resolved

    def clean_name(self, raw_name):
        if ":" in raw_name: raw_name = raw_name.split(":")[-1]
        if "/" in raw_name: raw_name = raw_name.split("/")[-1]
        return raw_name

# --- Module B: Geometry Baker ---
class GeometryBaker:
    def bake(self, elements, resolved_textures):
        if not elements: return []
        final_boxes = []
        for el in elements:
            f = el.get("from", [0, 0, 0])
            t = el.get("to", [16, 16, 16])
            
            rot_data = el.get("rotation", None)
            corners = self._get_corners(f, t)
            if rot_data: corners = self._apply_rotation(corners, rot_data)

            min_v = [min(c[i] for c in corners) for i in range(3)]
            max_v = [max(c[i] for c in corners) for i in range(3)]
            
            x1, y1, z1 = [v / 16.0 for v in min_v]
            x2, y2, z2 = [v / 16.0 for v in max_v]

            faces = el.get("faces", {})
            used_textures = set()
            for face_data in faces.values():
                tex_var = face_data.get("texture", "").replace("#", "")
                final_tex = resolved_textures.get(tex_var, "missing")
                used_textures.add(final_tex)

            for tex in used_textures:
                final_boxes.append(BakedBox(x1, y1, z1, x2, y2, z2, tex))
        return final_boxes

    def _get_corners(self, f, t):
        return [
            [f[0], f[1], f[2]], [t[0], f[1], f[2]],
            [f[0], t[1], f[2]], [t[0], t[1], f[2]],
            [f[0], f[1], t[2]], [t[0], f[1], t[2]],
            [f[0], t[1], t[2]], [t[0], t[1], t[2]]
        ]

    def _apply_rotation(self, corners, rot_data):
        origin = rot_data.get("origin", [8, 8, 8])
        axis = rot_data.get("axis", "y")
        angle = rot_data.get("angle", 0)
        rad = math.radians(angle)
        cos_a = math.cos(rad)
        sin_a = math.sin(rad)
        new_corners = []
        for p in corners:
            x, y, z = p[0] - origin[0], p[1] - origin[1], p[2] - origin[2]
            if axis == "x":
                ny = y * cos_a - z * sin_a
                nz = y * sin_a + z * cos_a
                y, z = ny, nz
            elif axis == "y":
                nx = x * cos_a + z * sin_a
                nz = -x * sin_a + z * cos_a
                x, z = nx, nz
            elif axis == "z":
                nx = x * cos_a - y * sin_a
                ny = x * sin_a + y * cos_a
                x, y = nx, ny
            new_corners.append([x + origin[0], y + origin[1], z + origin[2]])
        return new_corners

# --- Module C: Java Emitter ---
class JavaEmitter:
    def sanitize_name(self, filename):
        # Handling "stone (1)" -> "Stone1"
        name = os.path.splitext(filename)[0]
        name = name.replace("(", "").replace(")", "").replace(" ", "")
        parts = name.replace("-", "_").split("_")
        return "".join(p.capitalize() for p in parts)

    def generate_java(self, processed_models):
        lines = []
        lines.append(f"package {PACKAGE_NAME};")
        lines.append("")
        lines.append("import com.voxelengine.render.model.BlockModel;")
        lines.append("")
        lines.append(f"public final class {OUTPUT_CLASS_NAME} {{")
        lines.append("")
        
        for model_name, boxes in processed_models.items():
            method_name = "create" + self.sanitize_name(model_name)
            lines.append(f"    public static BlockModel {method_name}() {{")
            lines.append("        BlockModel model = new BlockModel();")
            
            for b in boxes:
                lines.append(f"        model.addBox({b.x1:.4f}f, {b.y1:.4f}f, {b.z1:.4f}f, "
                             f"{b.x2:.4f}f, {b.y2:.4f}f, {b.z2:.4f}f, \"{b.texture}\");")
            
            lines.append("        return model;")
            lines.append("    }")
            lines.append("")

        lines.append("}")
        return "\n".join(lines)

# --- Main Driver ---
def main():
    print(f"--- Java Model Transpiler v2 (Inheritance Support) ---")
    if not os.path.exists(INPUT_DIR):
        print(f"ERROR: Input directory not found: {INPUT_DIR}")
        return

    parser = ModelParser(INPUT_DIR)
    baker = GeometryBaker()
    emitter = JavaEmitter()
    
    processed_models = {} 

    files = [f for f in os.listdir(INPUT_DIR) if f.endswith(".json")]
    print(f"Found {len(files)} JSON models.")

    for f in files:
        # Load Flattened (Resolves Parent)
        result = parser.load_flattened(f)
        
        if not result or not result[0]:
            print(f"  [Skip] {f} (No elements found after inheritance)")
            continue
            
        elements, textures = result
        boxes = baker.bake(elements, textures)
        processed_models[f] = boxes
        print(f"  [OK]   {f} -> {len(boxes)} boxes")

    java_code = emitter.generate_java(processed_models)
    
    if not os.path.exists(OUTPUT_DIR):
        os.makedirs(OUTPUT_DIR)
        
    out_path = os.path.join(OUTPUT_DIR, OUTPUT_CLASS_NAME + ".java")
    with open(out_path, "w") as f:
        f.write(java_code)
        
    print(f"\nSUCCESS: Generated {out_path}")

if __name__ == "__main__":
    main()