package io.github.rhysbeynon.spacer.test;

import io.github.rhysbeynon.spacer.EngineManager;
import io.github.rhysbeynon.spacer.WindowManager;
import io.github.rhysbeynon.spacer.gadgets.nonMacWindowIcon;
import io.github.rhysbeynon.spacer.utilities.Constants;

public class Launcher {

    private static WindowManager window;
    private static TestScene scene;

    public static void main(String[] args) {

        //get Operating System
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            System.out.println("System detected as MacOS");

        } else if (osName.contains("linux")) {
            System.out.println("System detected as Linux");

        } else if (osName.contains("windows")) {
            System.out.println("System detected as Windows");
        }


        window = new WindowManager(false, 450, 800, Constants.TITLE);

        if (WindowManager.window != 0) {
            System.out.println("GLFW window NOT created " + window);
        } else {
            System.out.println("GLFW window created " + window);
        }
        scene = new TestScene();
        EngineManager engine = new EngineManager();
        try {
            engine.start();

            //sets icon for non-mac systems
            if (!osName.contains("mac")) {
                nonMacWindowIcon.setIcon(window, "/Users/rhysbeynon/Documents/Java Projects/spacer/src/main/java/io/github/rhysbeynon/spacer/assets/spacerEngineIcon.png");
            }

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
