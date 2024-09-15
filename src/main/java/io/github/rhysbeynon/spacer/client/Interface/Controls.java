package io.github.rhysbeynon.spacer.client.Interface;

import io.github.rhysbeynon.spacer.client.trinkets.Config;
import org.lwjgl.glfw.GLFW;

import io.github.rhysbeynon.spacer.client.Interface.Controls;

import static io.github.rhysbeynon.spacer.client.Interface.Input.KeyPress;
import static io.github.rhysbeynon.spacer.client.Interface.Input.getKeyCode;

public class Controls {

    public static void KeyBindings(long window, int key, int action) {
        if (KeyPress(getKeyCode("Escape"))) {
            GLFW.glfwSetWindowShouldClose(window, true);
        }
        if (KeyPress(getKeyCode("V"))) {
            Config.VSYNC = !Config.VSYNC;
        }
        if (KeyPress(getKeyCode("Left Shift")) && KeyPress(getKeyCode("0"))) {
            Config.DEBUG_MODE = !Config.DEBUG_MODE;
        }
    }

}
