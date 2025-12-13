#version 330 core

in vec2 vTexCoord;
in float vLight;
in float vTexIndex;
in vec3 vFragPos; // Needed for random seed

out vec4 FragColor;

uniform sampler2D uTexture;
uniform vec4 uColorMod;
uniform float uUVScale; 
uniform vec2 uUVOffset; 

// Atmosphere Uniforms
uniform vec3 uCameraPos;
uniform float uFogStart = 60.0;
uniform float uFogEnd = 120.0;
uniform vec3 uSkyColor;
uniform float uDaylightFactor;

// Pseudo-random function
float random(vec2 st) {
    return fract(sin(dot(st.xy, vec2(12.9898,78.233))) * 43758.5453123);
}

void main() {
    vec2 finalUV;
    float currentLight = vLight;
    bool isUI = false;
    
    // UI / TEXT MODE
    if (uUVScale < -0.5 || (uUVScale < 0.9 && uUVScale > 0.0)) {
        if (uUVScale < -0.5) {
            finalUV = vTexCoord;
        } else {
            finalUV = uUVOffset + (vTexCoord * uUVScale);
        }
        currentLight = 1.0; 
        isUI = true;
    } 
    // WORLD / ATLAS MODE
    else {
        float idxRaw = vTexIndex;
        bool rotate = false;
        
        // Check for Anti-Tessellation Flag (Negative Index)
        if (idxRaw < 0.0) {
            idxRaw = abs(idxRaw) - 1.0;
            rotate = true;
        }
        
        int index = int(idxRaw + 0.5);
        float col = float(index % 16);
        float row = float(index / 16);
        
        vec2 tiledUV = fract(vTexCoord);
        
        // Random Rotation Logic
        if (rotate) {
            // Use world X/Z position to pick a random rotation (0, 1, 2, 3)
            // We use floor(vFragPos) to get the block coordinate
            vec2 blockPos = floor(vFragPos.xz);
            float rand = random(blockPos);
            
            // Rotate UVs around center (0.5, 0.5)
            if (rand > 0.75) {
                // 270 deg
                tiledUV = vec2(tiledUV.y, 1.0 - tiledUV.x);
            } else if (rand > 0.5) {
                // 180 deg
                tiledUV = vec2(1.0 - tiledUV.x, 1.0 - tiledUV.y);
            } else if (rand > 0.25) {
                // 90 deg
                tiledUV = vec2(1.0 - tiledUV.y, tiledUV.x);
            }
        }
        
        tiledUV.y = 1.0 - tiledUV.y; // Standard Y flip
        finalUV = (vec2(col, row) * 16.0 + tiledUV * 16.0) / 256.0;
    }

    vec4 texColor = texture(uTexture, finalUV);
    
    if(texColor.a < 0.05) discard;
    
    float finalLight = currentLight;
    if (!isUI) {
        finalLight *= uDaylightFactor;
    }
    
    vec3 finalColor = texColor.rgb * finalLight;
    
    if (uColorMod.a > 0.0) {
        finalColor *= uColorMod.rgb;
    }

    if (!isUI) {
        float dist = length(vFragPos - uCameraPos);
        float fogFactor = clamp((dist - uFogStart) / (uFogEnd - uFogStart), 0.0, 1.0);
        finalColor = mix(finalColor, uSkyColor, fogFactor);
    }

    FragColor = vec4(finalColor, texColor.a);
}