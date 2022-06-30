package jamesdaprogrammer.orion.sandbox;

import jamesdaprogrammer.orion.engine.IApplication;
import jamesdaprogrammer.orion.engine.Orion;

public class Main {
    public static void main(String[] args) {
        Orion.init(800, 600, "Orion Engine", new TestGame());
        Orion.start();
    }

    private static class TestGame implements IApplication {

        @Override
        public void onCreate() {
            
        }

        @Override
        public void onStart() {
            
        }

        @Override
        public void onUpdate(float dt) {
            
        }

        @Override
        public void onRender() {
            
        }

        @Override
        public void onDispose() {
            
        }
        
    }
}
