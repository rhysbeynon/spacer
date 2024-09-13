package io.github.rhysbeynon.spacer.client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Player {
    private float x;
    private float y;
    private final float size;
    private final float speed;

    public Player(float startX, float startY, float size, float speed) {
        this.x = startX;
        this.y = startY;
        this.size = size;
        this.speed = speed;
    }

    public void moveUp() {
        y += speed;
    }
    public void moveDown() {
        y -= speed;
    }
    public void moveLeft() {
        x -= speed;
    }
    public void moveRight() {
        x += speed;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getSize() {
        return size;
    }


}

