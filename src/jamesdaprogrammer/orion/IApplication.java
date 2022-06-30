package jamesdaprogrammer.orion.engine;

public interface IApplication {
    void onCreate();
    void onStart();
    void onUpdate(float dt);
    void onRender();
    void onDispose();
}
