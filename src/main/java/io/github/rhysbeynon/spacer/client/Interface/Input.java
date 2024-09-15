package io.github.rhysbeynon.spacer.client.Interface;

import io.github.rhysbeynon.spacer.client.trinkets.Config;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Input implements Runnable {
    private final long window;
    private final Map<Integer, Boolean> keyState = new ConcurrentHashMap<>();
    private static final Map<Integer, String> keyNames = KeyNames.getKeyNames();
    private GLFWKeyCallback keyCallback;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final Thread inputThread;
    public static String pressedKey;

    public Input(long window) {
        this.window = window;
        this.inputThread = new Thread(this); // Initialise the input thread
    }

    private static int getKeyCode(String keyName) {
        return keyNames.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(keyName))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    };

    public void initialize() {
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW.GLFW_PRESS) {
                    keyState.put(key, true);
                } else if (action == GLFW.GLFW_RELEASE) {
                    keyState.put(key, false);
                }
                // Handle key-specific actions
                SYSTEM_CONTROLS(window, key, action);
            }

            // DEBUG CHARACTERS USED FOR SPECIAL COMMANDS AND ACTIONS
            // THIS IS NOT PRODUCTION CODE. PLEASE REMOVE OR DISABLE DEBUG MODE
            private void SYSTEM_CONTROLS(long window, int key, int action) {
                if (KeyPress(getKeyCode("Escape")) || KeyPress(getKeyCode("Q"))) {
                    GLFW.glfwSetWindowShouldClose(window, true);
                }
                if (KeyPress(getKeyCode("V"))) {
                    Config.VSYNC = !Config.VSYNC;
                }
                if (KeyPress(getKeyCode("Left Shift")) && KeyPress(getKeyCode("0"))) {
                    Config.DEBUG_MODE = !Config.DEBUG_MODE;
                }
            }

            public boolean isKeyPressed(int key) {
                return keyState.getOrDefault(key, false);
            }
        };

        GLFW.glfwSetKeyCallback(window, keyCallback);
    }

    public boolean KeyPress(int key) {
        return keyState.getOrDefault(key, false);
    }

    public void debugPrintKeyNames() {
        keyState.forEach((key, pressed) -> {
            if (pressed) {
                pressedKey = keyNames.getOrDefault(key, String.valueOf(key));
            }
        } );
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            GLFW.glfwPollEvents();
        }
    }

    public void start() {
        inputThread.start();
    }

    public void stop() {
        running.set(false);
        try {
            inputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() {
        keyCallback.free();
    }
}