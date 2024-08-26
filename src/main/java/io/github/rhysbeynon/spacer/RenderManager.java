package io.github.rhysbeynon.spacer;

import io.github.rhysbeynon.spacer.test.Launcher;
import io.github.rhysbeynon.spacer.entity.Model;
import io.github.rhysbeynon.spacer.utilities.Utilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderManager {

    private final WindowManager window;
    private  ShaderManager shader;

    public RenderManager() {
        window = Launcher.getWindow();
    }

    public void init() throws Exception {
        shader = new ShaderManager();

        shader.createVertexShader(Utilities.loadResource("/shaders/vertex.vs"));

        shader.createFragmentShader(Utilities.loadResource("/shaders/fragment.fs"));

        shader.link();
    }

    public void render(Model model) {
        clear();
        shader.bind();
        GL30.glBindVertexArray(model.getId());
        GL30.glEnableVertexAttribArray(0);
        GL30.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.unbind();
    }

    public void clear() {
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {
        shader.cleanup();
    }
}
