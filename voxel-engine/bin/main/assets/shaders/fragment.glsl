#version 330 core

in vec2 vTexCoord;
in float vLight;

out vec4 FragColor;

uniform sampler2D uTexture;
uniform vec4 uColorMod; // For UI tinting or selection

void main() {
    vec4 texColor = texture(uTexture, vTexCoord);

    // Alpha Cutout (Transparency for leaves/glass)
    if(texColor.a < 0.1) discard;

    // Apply Light
    vec3 finalColor = texColor.rgb * vLight;

    // Apply optional color mod (default vec4(1) if not set)
    if (uColorMod.a > 0.0) {
        finalColor *= uColorMod.rgb;
    }

    FragColor = vec4(finalColor, texColor.a);
}