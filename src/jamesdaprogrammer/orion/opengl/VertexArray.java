package jamesdaprogrammer.orion.engine.opengl;

import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray {
    private int handle;

    public VertexArray(boolean bind) {
        handle = glGenVertexArrays();

        if (bind)
            bind();
    }

    public void bind() {
        glBindVertexArray(handle);
    }

    public void unbind() {
        glBindVertexArray(handle);
    }
}
