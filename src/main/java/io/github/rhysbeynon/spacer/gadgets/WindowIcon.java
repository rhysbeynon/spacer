package io.github.rhysbeynon.spacer.gadgets;

import io.github.rhysbeynon.spacer.WindowManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class WindowIcon {

    //Method to set window icon
    public static void setIcon(WindowManager window, String iconPath) {
        //load image for icon
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            //load image from file
            ByteBuffer icon = STBImage.stbi_load(iconPath, width, height, comp, 4);
            if (icon == null) {
                throw new RuntimeException("Failed to load icon image: " + STBImage.stbi_failure_reason());
            }

            //Create GLFW image
            GLFWImage.Buffer iconBuffer = GLFWImage.malloc(1);
            iconBuffer.width(width.get(0));
            iconBuffer.height(height.get(0));
            iconBuffer.pixels(icon);

            //Set icon to window
            GLFW.glfwSetWindowIcon(WindowManager.window, iconBuffer);

            //free image from mem
            STBImage.stbi_image_free(icon);
        }
    }
}
