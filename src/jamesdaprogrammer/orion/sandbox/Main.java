package jamesdaprogrammer.orion.sandbox;

import jamesdaprogrammer.orion.IApplication;
import jamesdaprogrammer.orion.Orion;

public class Main {
    public static void main(String[] args) {
        Orion.Configuration config = new Orion.Configuration();
        config.setSize(800, 600);
        config.setTitle("Orion Engine");
        config.setVsync(true);

        Orion.init(config, new TestGame());
        Orion.start();
    }

    private static class TestGame implements IApplication {
        @Override
        public void onCreate() {
            System.out.println("Creating...");
        }

        @Override
        public void onStart() {
            System.out.println("Starting...");
        }

        @Override
        public void onUpdate(float dt) {
            System.out.println("Updating...");
        }

        @Override
        public void onRender() {
            System.out.println("Rendering...");
        }

        @Override
        public void onDispose() {
            System.out.println("Disposing...");
        }
    }
}
