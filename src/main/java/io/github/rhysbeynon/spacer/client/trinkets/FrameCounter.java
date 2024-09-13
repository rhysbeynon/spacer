package io.github.rhysbeynon.spacer.client.trinkets;

import static org.lwjgl.glfw.GLFW .*;

public class FrameCounter {

    private long window;
    public static int frames;
    private double lastTime;
    public static String fps;
    public static boolean validFPS = false;

    public FrameCounter() {
        this.window = window;
        this.frames = 0;
        this.lastTime = glfwGetTime();
    }

    public void update() {
        double currentTime = glfwGetTime();
        frames++;

        if (currentTime - lastTime >= 1.0) {
            fps = "FPS: " + frames;
            frames = 0;
            lastTime = currentTime;
            validFPS = true;
        }
    }
}