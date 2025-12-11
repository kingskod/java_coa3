#version 330 core

// Input attributes from Mesh
layout(location = 0) in vec3 aPos;
layout(location = 1) in vec2 aTexCoord; 
layout(location = 2) in vec2 aLight;    // x = Sky Light, y = Block Light
layout(location = 3) in float aTexIndex;

// Uniforms
uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 uModel;

// Item Override Uniform
// -1.0 means use attribute from VBO
// >= 0.0 means use this specific texture index (for dropped items)
uniform float uOverrideTexIndex; 

// Outputs to Fragment Shader
out vec2 vTexCoord;
out float vLight;
out float vTexIndex;
out vec3 vFragPos; // World position for Fog

void main() {
    // Calculate World Position
    vec4 worldPos = uModel * vec4(aPos, 1.0);
    vFragPos = worldPos.xyz;
    
    // Final Screen Position
    gl_Position = uProjection * uView * worldPos;
    
    // Pass Texture Coordinates
    vTexCoord = aTexCoord;
    
    // Texture Selection Logic
    if (uOverrideTexIndex >= 0.0) {
        vTexIndex = uOverrideTexIndex;
    } else {
        vTexIndex = aTexIndex;
    }
    
    // Combine Sky and Block light
    float lightVal = max(aLight.x, aLight.y);
    vLight = max(lightVal, 0.2);
}