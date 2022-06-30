package jamesdaprogrammer.orion.engine.opengl;

import static org.lwjgl.opengl.GL43.*;

public class VertexBuffer {
    private int handle;

    public VertexBuffer(float[] data, int usage, boolean bind) {
        handle = glGenBuffers();
        bind();
        glBufferData(GL_ARRAY_BUFFER, data, usage);

        if (!bind)
            unbind();
    }

    public void setVertexAttrib(int index, int size, int type, boolean normalise, int stride, long pointer) {
        glVertexAttribPointer(index, size, type, normalise, stride, pointer);
        glEnableVertexAttribArray(index);
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, handle);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
}
