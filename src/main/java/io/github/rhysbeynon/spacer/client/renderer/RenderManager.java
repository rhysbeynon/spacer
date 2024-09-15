package io.github.rhysbeynon.spacer.client.renderer;

import io.github.rhysbeynon.spacer.client.Player;
import io.github.rhysbeynon.spacer.client.trinkets.Config;
import io.github.rhysbeynon.spacer.client.Interface.Input;
import io.github.rhysbeynon.spacer.client.trinkets.Phys;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import io.github.rhysbeynon.spacer.client.trinkets.FrameCounter;

import static io.github.rhysbeynon.spacer.client.Interface.Input.pressedKey;

public class RenderManager {
    public long window;
    private Input Input;
    private final FrameCounter fpsCounter = new FrameCounter();
    public Player player;

    public RenderManager(Player player) {
        this.player = player;
    }

    public void createWindow() {
        // Set up an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create the window
        String windowTitle = "Initializing";

        //window resolution is at a 15.4:10 aspect ratio upon initialization. The best aspect ratio.
        int height = 700;
        int width = height * 154/100;

        window = GLFW.glfwCreateWindow(width, height, windowTitle, 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //initialize input manager
        Input = new Input(window);
        Input.initialize();

        if(Config.FULLSCREEN) {
            GLFW.glfwMaximizeWindow(window);
            GLFW.glfwMakeContextCurrent(window);
        } else {
            GLFW.glfwMakeContextCurrent(window);
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);

        // Make the window visible
        GLFW.glfwShowWindow(window);

        // Initialize OpenGL bindings
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glEnable(GL11.GL_BACK);

        // Print instance information
        System.out.println("LWJGL Version: " + Version.getVersion());
        System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("Vendor: " + GL11.glGetString(GL11.GL_VENDOR));
        System.out.println("Renderer Hardware: " + GL11.glGetString(GL11.GL_RENDERER));
    }

    public void update() {
        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwSwapInterval(Config.VSYNC ? 1 : 0);
        fpsCounter.update();
    }

    // MAIN RENDER LOOP
    public void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Swaps color buffer and polls for events
            update();
            render();

            // Clear the framebuffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            //DEBUG CODE
            if (Config.DEBUG_MODE) {
                Input.debugPrintKeyNames();
                if (FrameCounter.validFPS) {
                    GLFW.glfwSetWindowTitle(window, "Engine " + FrameCounter.fps + " [" + pressedKey + "]" );
                }
            } else {
                GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            }

            if (!Config.DEBUG_MODE & FrameCounter.validFPS) {
                GLFW.glfwSetWindowTitle(window, "Engine");
            }

        }
    }

    public void render() {

        if(Config.DEBUG_MODE) {
            // DEBUG COLOR CHANGE EXAMPLE BASED ON FRAME RATE
            GL11.glClearColor(((float) Math.sin(Phys.time()) * 0.1f) + 0.5f, 0.4f, 0.6f, 1.0f);
        }
    }

    public void terminate() {
        // Terminate GLFW and release the error callback
        GLFW.glfwSetWindowShouldClose(window, true);
        GLFW.glfwSetErrorCallback(null).free();
    }
}