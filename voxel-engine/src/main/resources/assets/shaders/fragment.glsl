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
uniform float uDaylightFactor; 

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
        currentLight = 1.0; // UI is always full brightness
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
    
    // LIGHTING LOGIC FIX:
    float finalLight = currentLight;
    
    // Only apply Day/Night cycle dimming if it is NOT UI
    if (!isUI) {
        finalLight *= uDaylightFactor;
    }
    
    vec3 finalColor = texColor.rgb * finalLight;
    
    if (uColorMod.a > 0.0) {
        finalColor *= uColorMod.rgb;
    }

    // FOG (Only for World)
    if (!isUI) {
        float dist = length(vFragPos - uCameraPos);
        float fogFactor = clamp((dist - uFogStart) / (uFogEnd - uFogStart), 0.0, 1.0);
        finalColor = mix(finalColor, uSkyColor, fogFactor);
    }

    FragColor = vec4(finalColor, texColor.a);
}