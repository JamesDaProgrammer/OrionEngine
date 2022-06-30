package jamesdaprogrammer.orion.engine;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.system.MemoryUtil;

public class Window {
    private long glfwWindow;
    
    public Window(int width, int height, String title) {
        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        glfwWindow = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (glfwWindow == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create glfw window.");
    }

    public void show() {
        glfwShowWindow(glfwWindow);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(glfwWindow);
    }

    public void swapBuffers() {
        glfwSwapBuffers(glfwWindow);
    }

    public long getPointer() {
        return glfwWindow;
    }

}
