#version 330 core

in vec2 vTexCoord;
in float vLight;
in float vTexIndex;

out vec4 FragColor;

uniform sampler2D uTexture;
uniform vec4 uColorMod;
uniform float uUVScale; 
uniform vec2 uUVOffset; 

void main() {
    vec2 finalUV;
    
    // DIRECT MODE (UI Crosshair/Hotbar)
    // Pass UVs exactly as they are (0..1)
    if (uUVScale < -0.5) {
        finalUV = vTexCoord;
    }
    // TEXT ATLAS MODE (Font)
    // Scale and Offset for character grid
    else if (uUVScale < 0.9) {
        finalUV = uUVOffset + (vTexCoord * uUVScale);
    } 
    // WORLD ATLAS MODE (Blocks)
    // Fractal Tiling for Greedy Meshing
    else {
        int index = int(vTexIndex + 0.5);
        float col = float(index % 16);
        float row = float(index / 16);
        
        vec2 tiledUV = fract(vTexCoord);
        
        // FIX: Flip Y axis here
        tiledUV.y = 1.0 - tiledUV.y; 
        
        finalUV = (vec2(col, row) * 16.0 + tiledUV * 16.0) / 256.0;
    }

    vec4 texColor = texture(uTexture, finalUV);
    if(texColor.a < 0.1) discard;
    
    vec3 finalColor = texColor.rgb * vLight;
    if (uColorMod.a > 0.0) {
        finalColor *= uColorMod.rgb;
    }

    FragColor = vec4(finalColor, texColor.a);
}