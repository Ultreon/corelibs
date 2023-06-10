package com.ultreon.libs.commons.v0;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Pixel implements Serializable {
    private final Color color;
    private final Point pos;

    public Pixel(int x, int y, Color color) {
        this.pos = new Point(x, y);
        this.color = color;
    }

    public Pixel(Point pos, Color color) {
        this.pos = pos;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Point getPos() {
        return this.pos;
    }

    public int getX() {
        return this.pos.x;
    }

    public int getY() {
        return this.pos.y;
    }
}
