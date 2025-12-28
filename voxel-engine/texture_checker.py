import json
import os

# --- Configuration ---
SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
MODELS_DIR = os.path.join(SCRIPT_DIR, "src", "main", "resources", "assets", "models", "block")
#textures path: E:\java_coa_3\src\main\resources\assets\textures
TEXTURES_DIR = r"E:\\java_coa_3\src\\main\\resources\\assets\\textures"

def check_textures():
    print(f"--- Texture Integrity Check ---")
    print(f"Models:   {MODELS_DIR}")
    print(f"Textures: {TEXTURES_DIR}")
    
    if not os.path.exists(MODELS_DIR) or not os.path.exists(TEXTURES_DIR):
        print("ERROR: Directory not found.")
        return

    # 1. Gather all existing textures
    existing_textures = set()
    for root, dirs, files in os.walk(TEXTURES_DIR):
        for f in files:
            if f.endswith(".png"):
                # Store relative path (e.g., "block/stone") and plain name ("stone")
                # Normalize slashes to forward slash
                rel_path = os.path.relpath(os.path.join(root, f), TEXTURES_DIR).replace("\\", "/")
                name_only = os.path.splitext(f)[0]
                
                existing_textures.add(rel_path)
                existing_textures.add(rel_path.replace(".png", ""))
                existing_textures.add(name_only) # Fallback for flat structure

    # 2. Scan Models
    missing_report = {}
    
    model_files = [f for f in os.listdir(MODELS_DIR) if f.endswith(".json")]
    
    for model_file in model_files:
        path = os.path.join(MODELS_DIR, model_file)
        try:
            with open(path, 'r') as f:
                data = json.load(f)
                
            textures = data.get("textures", {})
            if not textures: continue
            
            for key, val in textures.items():
                if val.startswith("#"): continue # Skip variables
                
                # Cleanup path (remove namespace)
                clean_val = val
                if ":" in clean_val: clean_val = clean_val.split(":")[-1]
                
                # Check existence
                if clean_val not in existing_textures:
                    if model_file not in missing_report:
                        missing_report[model_file] = []
                    missing_report[model_file].append(clean_val)
                    
        except Exception as e:
            print(f"Error reading {model_file}: {e}")

    # 3. Report
    print(f"\n--- Results ---")
    if not missing_report:
        print("SUCCESS: All textures found!")
    else:
        print(f"MISSING TEXTURES detected in {len(missing_report)} models:\n")
        for model, tex_list in missing_report.items():
            print(f"File: {model}")
            for t in tex_list:
                print(f"  - Missing: '{t}.png'")
            print("")

if __name__ == "__main__":
    check_textures()