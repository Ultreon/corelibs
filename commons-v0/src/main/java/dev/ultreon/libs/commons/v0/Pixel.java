package dev.ultreon.libs.commons.v0;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Pixel implements Serializable {
    private final dev.ultreon.libs.commons.v0.Color color;
    private final Point pos;

    public Pixel(int x, int y, dev.ultreon.libs.commons.v0.Color color) {
        this.pos = new Point(x, y);
        this.color = color;
    }

    public Pixel(Point pos, dev.ultreon.libs.commons.v0.Color color) {
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
