#version 330 core

in vec2 vTexCoord;
in float vLight;
in float vTexIndex;
in vec3 vFragPos;

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
uniform float uDaylightFactor; // 1.0 = Day, ~0.2 = Night

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
        int index = int(vTexIndex + 0.5);
        float col = float(index % 16);
        float row = float(index / 16);
        vec2 tiledUV = fract(vTexCoord);
        tiledUV.y = 1.0 - tiledUV.y; 
        finalUV = (vec2(col, row) * 16.0 + tiledUV * 16.0) / 256.0;
    }

    vec4 texColor = texture(uTexture, finalUV);
    
    if(texColor.a < 0.05) discard;
    
    // In a full engine, light is a vec2(sky, block).
    // Here we assume vLight is the combined max.
    // We dim based on daylight. Block light (torches) should be unaffected.
    // For simplicity, we dim everything for now.
    float finalLight = currentLight * uDaylightFactor;
    
    vec3 finalColor = texColor.rgb * finalLight;
    
    if (uColorMod.a > 0.0) {
        finalColor *= uColorMod.rgb;
    }

    // FOG
    if (!isUI) {
        float dist = length(vFragPos - uCameraPos);
        float fogFactor = clamp((dist - uFogStart) / (uFogEnd - uFogStart), 0.0, 1.0);
        finalColor = mix(finalColor, uSkyColor, fogFactor);
    }

    FragColor = vec4(finalColor, texColor.a);
}