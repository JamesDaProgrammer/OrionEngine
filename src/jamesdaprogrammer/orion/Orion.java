package jamesdaprogrammer.orion;

public class Orion {
    private static OrionApplication app;

    public static void init(Configuration config, IApplication clientApp) {
        app = new OrionApplication();
        app.create(config, clientApp);
    }

    public static void start() {
        app.start();
    }

    public static OrionApplication getApp() {
        return app;
    }

    public static class Configuration {
        public int width, height;
        public String title;
        public boolean vsync;
        public boolean resizable;
        public boolean fullscreen;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setVsync(boolean vsync) {
            this.vsync = vsync;
        }

        public void setResizable(boolean resizable) {
            this.resizable = resizable;
        }

        public void setFullscreen(boolean fullscreen) {
            this.fullscreen = fullscreen;
        }

        public void setSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
