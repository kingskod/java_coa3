#version 330 core

in vec2 vTexCoord;
in float vLight;
in float vTexIndex;
in vec3 vFragPos; // From Vertex Shader

out vec4 FragColor;

uniform sampler2D uTexture;
uniform vec4 uColorMod;
uniform float uUVScale; 
uniform vec2 uUVOffset; 

// Fog Uniforms
uniform vec3 uCameraPos;
uniform float uFogStart = 60.0;
uniform float uFogEnd = 120.0;
uniform vec3 uSkyColor = vec3(0.5, 0.7, 1.0); // Light Blue Sky

void main() {
    vec2 finalUV;
    float currentLight = vLight;
    bool isUI = false;
    
    // DIRECT MODE (UI Crosshair/Hotbar/Selector)
    if (uUVScale < -0.5) {
        finalUV = vTexCoord;
        currentLight = 1.0; 
        isUI = true;
    }
    // TEXT ATLAS MODE (Font)
    else if (uUVScale < 0.9) {
        finalUV = uUVOffset + (vTexCoord * uUVScale);
        currentLight = 1.0; 
        isUI = true;
    } 
    // WORLD ATLAS MODE (Blocks)
    else {
        int index = int(vTexIndex + 0.5);
        float col = float(index % 16);
        float row = float(index / 16);
        vec2 tiledUV = fract(vTexCoord);
        // Flip Y for blocks
        tiledUV.y = 1.0 - tiledUV.y; 
        finalUV = (vec2(col, row) * 16.0 + tiledUV * 16.0) / 256.0;
    }

    vec4 texColor = texture(uTexture, finalUV);
    
    // Alpha Threshold
    if(texColor.a < 0.05) discard;
    
    // Apply Light & Color Mod
    vec3 finalColor = texColor.rgb * currentLight;
    if (uColorMod.a > 0.0) {
        finalColor *= uColorMod.rgb;
    }

    // --- FOG CALCULATION ---
    if (!isUI) {
        float dist = length(vFragPos - uCameraPos);
        // Calculate Fog Factor (0.0 = Clear, 1.0 = Full Fog)
        float fogFactor = clamp((dist - uFogStart) / (uFogEnd - uFogStart), 0.0, 1.0);
        
        // Mix Texture Color with Sky Color
        finalColor = mix(finalColor, uSkyColor, fogFactor);
    }

    FragColor = vec4(finalColor, texColor.a);
}