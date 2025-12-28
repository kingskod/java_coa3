package com.voxelengine.render;

import com.voxelengine.utils.FileUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL20.*;

/**
 * Manages the compilation, linking, and usage of OpenGL shader programs.
 */
public class Shader {

    private final int programId;
    private final int vertexId;
    private final int fragmentId;

    /**
     * Creates a new Shader program from vertex and fragment shader source files.
     *
     * @param vertexPath Path to the vertex shader file.
     * @param fragmentPath Path to the fragment shader file.
     */
    public Shader(String vertexPath, String fragmentPath) {
        programId = glCreateProgram();

        vertexId = loadShader(vertexPath, GL_VERTEX_SHADER);
        fragmentId = loadShader(fragmentPath, GL_FRAGMENT_SHADER);

        glAttachShader(programId, vertexId);
        glAttachShader(programId, fragmentId);

        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Error linking shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating shader code: " + glGetProgramInfoLog(programId, 1024));
        }
    }

    private int loadShader(String path, int type) {
        String source = FileUtils.loadResourceAsString(path);
        int shaderId = glCreateShader(type);
        glShaderSource(shaderId, source);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Error compiling shader (" + path + "): " + glGetShaderInfoLog(shaderId, 1024));
        }
        return shaderId;
    }

    /**
     * Installs this program object as part of current rendering state.
     */
    public void bind() {
        glUseProgram(programId);
    }

    /**
     * Unbinds the current shader program.
     */
    public void unbind() {
        glUseProgram(0);
    }

    // --- Uniform Setters ---

    public void setUniform(String name, Matrix4f value) {
        int loc = glGetUniformLocation(programId, name);
        if (loc != -1) {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                glUniformMatrix4fv(loc, false, value.get(stack.mallocFloat(16)));
            }
        }
    }

    public void setUniform(String name, int value) {
        int loc = glGetUniformLocation(programId, name);
        if (loc != -1) glUniform1i(loc, value);
    }

    public void setUniform(String name, Vector3f value) {
        int loc = glGetUniformLocation(programId, name);
        if (loc != -1) glUniform3f(loc, value.x, value.y, value.z);
    }
    public void setUniform(String name, org.joml.Vector2f value) {
        int loc = org.lwjgl.opengl.GL20.glGetUniformLocation(programId, name);
        if (loc != -1) org.lwjgl.opengl.GL20.glUniform2f(loc, value.x, value.y);
    }

    public void setUniform(String name, float value) {
        int loc = org.lwjgl.opengl.GL20.glGetUniformLocation(programId, name);
        if (loc != -1) org.lwjgl.opengl.GL20.glUniform1f(loc, value);
    }
    public void setUniform(String name, org.joml.Vector4f value) {
        int loc = org.lwjgl.opengl.GL20.glGetUniformLocation(programId, name);
        if (loc != -1) org.lwjgl.opengl.GL20.glUniform4f(loc, value.x, value.y, value.z, value.w);
    }
    
    /**
     * Deletes the shader program and detaches shaders.
     */
    public void cleanup() {
        unbind();
        if (programId != 0) glDeleteProgram(programId);
    }
}