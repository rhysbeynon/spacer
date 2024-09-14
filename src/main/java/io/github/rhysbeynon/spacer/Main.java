package io.github.rhysbeynon.spacer;

import io.github.rhysbeynon.spacer.client.Player;
import io.github.rhysbeynon.spacer.client.renderer.RenderManager;

public class Main {
    private Player player;

    public static void main(String[] args) {
        Player player = new Player(100.0f, 100.0f, 50.0f, 0.05f);
        RenderManager renderer = new RenderManager(player);
        renderer.createWindow();
        renderer.loop();
    }
}
