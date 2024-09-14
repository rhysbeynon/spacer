package io.github.rhysbeynon.spacer.client.trinkets;

import org.lwjgl.glfw.GLFW;

public class Phys {

    private Phys() {}

    public static float time() {
        return (float) GLFW.glfwGetTime();
    }

}
