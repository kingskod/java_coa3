#version 330 core

// Input attributes from Mesh
layout(location = 0) in vec3 aPos;
layout(location = 1) in vec2 aTexCoord; // Can be > 1.0 for merged faces (e.g. 4.0)
layout(location = 2) in vec2 aLight;    // x = Sky Light, y = Block Light
layout(location = 3) in float aTexIndex;

// Uniforms
uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 uModel;

// Outputs to Fragment Shader
out vec2 vTexCoord;
out float vLight;
out float vTexIndex;

void main() {
    // Standard MVP transform
    gl_Position = uProjection * uView * uModel * vec4(aPos, 1.0);
    
    // Pass raw data directly to Fragment Shader
    // We do NOT divide by 256 here anymore.
    // The Fragment Shader will use fract(vTexCoord) to tile the texture.
    vTexCoord = aTexCoord;
    vTexIndex = aTexIndex;
    
    // Combine Sky and Block light
    // We take the brighter of the two sources
    float lightVal = max(aLight.x, aLight.y);
    
    // Apply minimum ambient light (0.2) so total darkness isn't pitch black
    vLight = max(lightVal, 0.2);
}