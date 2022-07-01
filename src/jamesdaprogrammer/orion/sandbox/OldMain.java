package jamesdaprogrammer.orion.sandbox;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import jamesdaprogrammer.orion.OldWindowClass;
import jamesdaprogrammer.orion.opengl.ShaderProgram;
import jamesdaprogrammer.orion.opengl.VertexArray;
import jamesdaprogrammer.orion.opengl.VertexBuffer;

public class OldMain {
    private static OldWindowClass window = OldWindowClass.get();

    private static float[] vertices = {
        -0.5f, -0.5f, 0.0f,
         0.5f, -0.5f, 0.0f,
         0.0f,  0.5f, 0.0f
    };

    public static void oldMain(String[] args) {
        if (!glfwInit())
            throw new IllegalStateException("Failed to initialise glfw.");

        window.create(800, 600, "Orion Engine");
        window.enableVsync();

        glfwSetKeyCallback(window.getHandle(), (window, key, scancode, action, mods) -> {
            if (action == GLFW_PRESS) {
                if (key == GLFW_KEY_ESCAPE) {
                    glfwSetWindowShouldClose(window, true);
                }
            }
        });

        VertexArray vao = new VertexArray(true);
        VertexBuffer vbo = new VertexBuffer(vertices, GL_STATIC_DRAW, true);
        vbo.setVertexAttrib(0, 3, GL_FLOAT, false, 12, 0L);

        ShaderProgram program = new ShaderProgram(readFile("res/shaders/basic_shader.vert"), readFile("res/shaders/basic_shader.frag"), true);
        program.registerUniforms("u_RedValue");
        program.setUniform("u_RedValue", 0.0f);

        while (!window.shouldClose()) {
            glfwPollEvents();

            if (glfwGetKey(window.getHandle(), GLFW_KEY_UP) == GLFW_PRESS) {
                float currentRedValue = program.getUniformf("u_RedValue");
                if (currentRedValue < 1.0f)
                    program.setUniform("u_RedValue", currentRedValue + 0.1f);
            }
            if (glfwGetKey(window.getHandle(), GLFW_KEY_DOWN) == GLFW_PRESS) {
                float currentRedValue = program.getUniformf("u_RedValue");
                if (currentRedValue > 0.0f)
                    program.setUniform("u_RedValue", currentRedValue - 0.1f);
            }

            glDrawArrays(GL_TRIANGLES, 0, 3);

            window.swapBuffers();
        }

        window.dispose();
        glfwTerminate();
    }

    private static String readFile(String path) {
        String toReturn = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            String line;
            while ((line = reader.readLine()) != null)
                toReturn += line + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
