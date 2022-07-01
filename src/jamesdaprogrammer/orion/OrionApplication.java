package jamesdaprogrammer.orion;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public class OrionApplication {
    private Window window;
    private IApplication app;
    private boolean running;

    public void create(Orion.Configuration config, IApplication app) {
        if (!glfwInit())
            throw new IllegalStateException("Failed to initialise glfw.");

        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        window = new Window(config.width, config.height, config.title);

        // initialise OpenGL
        glfwMakeContextCurrent(window.getPointer());
        GL.createCapabilities();

        // enable vsync
        if (config.vsync) glfwSwapInterval(1);
        else glfwSwapInterval(0);

        this.app = app;
        this.app.onCreate();
    }

    public void start() {
        app.onStart();
        window.show();

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double deltaTime = 0;

        running = true;
        while (!window.shouldClose()) {
            firstTime = System.nanoTime() / 1000000000.0;
            deltaTime = firstTime - lastTime;
            lastTime = firstTime;

            glfwPollEvents();

            app.onUpdate((float) deltaTime);
            app.onRender();

            window.swapBuffers();
        }

        app.onDispose();
        glfwTerminate();
    }

}
