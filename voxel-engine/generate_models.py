import json
import os
import math

# ================= CONFIG =================

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
INPUT_DIR = os.path.join(SCRIPT_DIR, "src", "main", "resources", "assets", "models", "block")
OUTPUT_DIR = os.path.join(SCRIPT_DIR, "src", "main", "java", "com", "voxelengine", "render")
OUTPUT_CLASS = "GeneratedBlockModels"
PACKAGE = "com.voxelengine.render"

# ================= DATA =================

class BakedQuad:
    def __init__(self, positions, uvs, texture, face):
        self.positions = positions  # 12 floats
        self.uvs = uvs              # 8 floats (0..1)
        self.texture = texture
        self.face = face.upper()

# ================= PARSER =================

class ModelParser:
    def __init__(self, root):
        self.root = root
        self.cache = {}

    def load(self, name):
        if name in self.cache:
            return self.cache[name]

        path = os.path.join(self.root, name)
        if not path.endswith(".json"):
            path += ".json"

        with open(path) as f:
            data = json.load(f)

        textures = self.resolve_textures(data.get("textures", {}))
        elements = data.get("elements", [])

        if "parent" in data:
            parent = self.load(self.clean(data["parent"]))
            if parent:
                pe, pt = parent
                elements = elements or pe
                for k,v in pt.items():
                    textures.setdefault(k, v)

        self.cache[name] = (elements, textures)
        return elements, textures

    def resolve_textures(self, tex):
        out = {}
        for k,v in tex.items():
            out[k] = self.clean(v)
        return out

    def clean(self, s):
        return s.split(":")[-1].split("/")[-1]

# ================= GEOMETRY =================

class GeometryBaker:

    FACE_IDX = {
        "down":  [0,1,5,4],
        "up":    [2,3,7,6],
        "north": [1,0,2,3],
        "south": [4,5,7,6],
        "west":  [0,4,6,2],
        "east":  [5,1,3,7],
    }

    def bake(self, elements, textures):
        quads = []

        for el in elements:
            f = el["from"]
            t = el["to"]
            faces = el.get("faces", {})
            rot = el.get("rotation")

            corners = self.get_corners(f, t)
            if rot:
                corners = self.rotate(corners, rot)

            for face, idx in self.FACE_IDX.items():
                if face not in faces:
                    continue

                face_data = faces[face]
                tex_ref = face_data["texture"][1:]
                tex_name = textures.get(tex_ref, "missing")

                # positions
                pos = []
                for i in idx:
                    x,y,z = corners[i]
                    pos.extend([x/16.0, y/16.0, z/16.0])

                # UVs (pixel -> normalized)
                if "uv" in face_data:
                    u0, v0, u1, v1 = face_data["uv"]
                else:
                    u0, v0, u1, v1 = 0, 0, 16, 16
                
                uv = [
                    u0 / 16.0, v0 / 16.0,
                    u1 / 16.0, v0 / 16.0,
                    u1 / 16.0, v1 / 16.0,
                    u0 / 16.0, v1 / 16.0,
                ]

                quads.append(BakedQuad(pos, uv, tex_name, face))

        return quads

    def get_corners(self, f, t):
        return [
            [f[0],f[1],f[2]],[t[0],f[1],f[2]],
            [f[0],t[1],f[2]],[t[0],t[1],f[2]],
            [f[0],f[1],t[2]],[t[0],f[1],t[2]],
            [f[0],t[1],t[2]],[t[0],t[1],t[2]]
        ]

    def rotate(self, pts, r):
        ox,oy,oz = r["origin"]
        ang = math.radians(r["angle"])
        ax = r["axis"]
        ca, sa = math.cos(ang), math.sin(ang)

        out = []
        for x,y,z in pts:
            x-=ox; y-=oy; z-=oz
            if ax=="x":
                y,z = y*ca - z*sa, y*sa + z*ca
            elif ax=="y":
                x,z = x*ca + z*sa, -x*sa + z*ca
            else:
                x,y = x*ca - y*sa, x*sa + y*ca
            out.append([x+ox,y+oy,z+oz])
        return out

# ================= EMITTER =================

class JavaEmitter:

    def method_name(self, name):
        base = os.path.splitext(name)[0]
        return "create" + "".join(p.capitalize() for p in base.replace("-","_").split("_"))

    def emit(self, models):
        out = []
        out.append(f"package {PACKAGE};\n")
        out.append("import com.voxelengine.render.model.BlockModel;")
        out.append("import com.voxelengine.utils.Direction;\n")
        out.append(f"public final class {OUTPUT_CLASS} {{\n")

        for name, quads in models.items():
            out.append(f"    public static BlockModel {self.method_name(name)}() {{")
            out.append("        BlockModel model = new BlockModel();")

            for q in quads:
                p = ",".join(f"{v:.5f}f" for v in q.positions)
                uv = ",".join(f"{v:.5f}f" for v in q.uvs)
                out.append(
                    f'        model.addQuad(new float[]{{{p}}}, new float[]{{{uv}}}, '
                    f'Direction.{q.face}, "{q.texture}");'
                )

            out.append("        return model;")
            out.append("    }\n")

        out.append("}")
        return "\n".join(out)

# ================= MAIN =================

def main():
    parser = ModelParser(INPUT_DIR)
    baker = GeometryBaker()
    emitter = JavaEmitter()

    models = {}

    for f in os.listdir(INPUT_DIR):
        if not f.endswith(".json"):
            continue
        elements, textures = parser.load(f)
        quads = baker.bake(elements, textures)
        models[f] = quads
        print("OK:", f)

    os.makedirs(OUTPUT_DIR, exist_ok=True)
    path = os.path.join(OUTPUT_DIR, OUTPUT_CLASS + ".java")

    with open(path, "w") as fp:
        fp.write(emitter.emit(models))

    print("Generated:", path)

if __name__ == "__main__":
    main()
