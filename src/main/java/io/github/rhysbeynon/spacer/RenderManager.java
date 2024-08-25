package io.github.rhysbeynon.spacer;

import io.github.rhysbeynon.spacer.test.Launcher;
import io.github.rhysbeynon.spacer.entity.Model;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderManager {

    private final WindowManager window;

    public RenderManager() {
        window = Launcher.getWindow();
    }

    public void init() throws Exception {

    }

    public void render(Model model) {
        clear();
        GL30.glBindVertexArray(model.getId());
        GL30.glEnableVertexAttribArray(0);
        GL30.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);

    }

    public void clear() {
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {

    }
}
