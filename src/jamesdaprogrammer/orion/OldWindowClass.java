package jamesdaprogrammer.orion;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

public class OldWindowClass implements IDisposable {
    private static OldWindowClass instance = null;

    private long handle;
    private int width, height;
    private String title;

    private OldWindowClass() {

    }

    public void create(int width, int height, String title) {
        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        handle = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (handle == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create glfw window.");
        glfwMakeContextCurrent(handle);

        GL.createCapabilities();

        glfwSwapInterval(1);
    }

    public void enableVsync() {
        glfwSwapInterval(1);
    }

    public void disableVsync() {
        glfwSwapInterval(0);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    @Override
    public void dispose() {
        glfwDestroyWindow(handle);
    }

    public static OldWindowClass get() {
        if (instance == null)
            instance = new OldWindowClass();
        return instance;
    }

    public void setWidth(int width) {
        this.width = width;
        glfwSetWindowSize(handle, width, height);
    }
    
    public void setHeight(int height) {
        this.height = height;
        glfwSetWindowSize(handle, width, height);
    }
    
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        glfwSetWindowSize(handle, width, height);

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getHandle() {
        return handle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    
}
