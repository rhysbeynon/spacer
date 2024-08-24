package io.github.rhysbeynon.spacer.test;

import io.github.rhysbeynon.spacer.EngineManager;
import io.github.rhysbeynon.spacer.WindowManager;
import io.github.rhysbeynon.spacer.utilities.Constants;

public class Launcher {

    private static WindowManager window;
    private static TestScene scene;

    public static void main(String[] args) {
        window = new WindowManager(false, 450, 800, Constants.TITLE);
        scene = new TestScene();
        EngineManager engine = new EngineManager();
        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    public static WindowManager getWindow() {
        return window;
    }

    public static TestScene getScene() {
        return scene;
    }
}
