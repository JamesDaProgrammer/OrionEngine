package jamesdaprogrammer.orion.opengl;

import static org.lwjgl.opengl.GL43.*;

import java.util.HashMap;

public class ShaderProgram {
    private int handle;
    private int vertexShader;
    private int fragShader;

    private HashMap<String, Integer> uniforms;

    public ShaderProgram(String vShaderSrc, String fShaderSrc, boolean bind) {
        handle = glCreateProgram();

        vertexShader = compileShader(GL_VERTEX_SHADER, vShaderSrc);
        fragShader = compileShader(GL_FRAGMENT_SHADER, fShaderSrc);

        glAttachShader(handle, vertexShader);
        glAttachShader(handle, fragShader);
        glLinkProgram(handle);
        glValidateProgram(handle);
        System.out.println(glGetProgramInfoLog(handle));

        uniforms = new HashMap<String, Integer>();

        if (bind)
            bind();
    }

    public void registerUniforms(String... names) {
        for (String uniform : names) {
            uniforms.put(uniform, glGetUniformLocation(handle, uniform));
        }
    }

    public float getUniformf(String name) {
        return glGetUniformf(handle, uniforms.get(name));
    }

    public void setUniform(String name, float v0) {
        glUniform1f(uniforms.get(name), v0);
    }

    public void setUniform(String name, int v0) {
        glUniform1i(uniforms.get(name), v0);
    }

    public void setUniform(String name, float v0, float v1, float v2) {
        glUniform3f(uniforms.get(name), v0, v1, v2);
    }

    private int compileShader(int type, String source) {
        int shader = glCreateShader(type);

        glShaderSource(shader, source);
        glCompileShader(shader);
        System.out.println(glGetShaderInfoLog(shader));

        return shader;
    }

    public void bind() {
        glUseProgram(handle);
    }

    public void unbind() {
        glUseProgram(0);
    }
}
