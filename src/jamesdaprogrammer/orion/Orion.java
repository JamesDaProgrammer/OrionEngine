package jamesdaprogrammer.orion.engine;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

public class Orion {
    private static final Orion INSTANCE = new Orion();

    private Window window;
    private IApplication app;
    private boolean running;
    
    public static void init(int width, int height, String title, IApplication app) {
        if (!glfwInit())
            throw new IllegalStateException("Failed to initialise glfw.");

        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        INSTANCE.window = new Window(width, height, title);

        // enable vsync
        glfwSwapInterval(1);

        // initialise OpenGL
        glfwMakeContextCurrent(INSTANCE.window.getPointer());
        GL.createCapabilities();

        INSTANCE.app = app;
        INSTANCE.app.onCreate();
    }

    public static void start() {
        INSTANCE.app.onStart();
        INSTANCE.run();
    }

    private void run() {
        window.show();

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000;
        double deltaTime = 0;

        running = true;
        while (!window.shouldClose()) {
            firstTime = System.nanoTime() / 1000000000;
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
