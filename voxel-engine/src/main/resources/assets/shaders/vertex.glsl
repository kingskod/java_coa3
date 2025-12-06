#version 330 core

layout(location = 0) in vec3 aPos;
layout(location = 1) in vec2 aTexCoord;
layout(location = 2) in vec2 aLight; // Changed from float to vec2 (x=Sky, y=Block)
layout(location = 3) in float aTexIndex;

uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 uModel;

uniform vec2 uUVOffset; 
uniform float uUVScale; 

out vec2 vTexCoord;
out float vLight;

void main() {
    gl_Position = uProjection * uView * uModel * vec4(aPos, 1.0);
    
    if (uUVScale > 0.0 && uUVScale < 0.9) {
        // UI Mode
        vTexCoord = uUVOffset + (aTexCoord * uUVScale);
        vLight = 1.0;
    } else {
        // World Mode
        int index = int(aTexIndex);
        int cols = 16;
        float col = float(index % cols);
        float row = float(index / cols);
        float u = (col * 16.0 + aTexCoord.x) / 256.0;
        float v = (row * 16.0 + aTexCoord.y) / 256.0;
        vTexCoord = vec2(u, v);
        
        // Combine Sky and Block light
        // Simple logic: Max of (Sky, Block)
        // Or: mix them. Sky is usually white, Block is usually warm.
        // For grayscale lighting:
        float lightVal = max(aLight.x, aLight.y);
        
        // Minimum ambient
        vLight = max(lightVal, 0.2); 
    }
}