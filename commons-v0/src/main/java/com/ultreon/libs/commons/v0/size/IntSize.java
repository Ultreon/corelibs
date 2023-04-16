package com.ultreon.libs.commons.v0.size;

import java.awt.*;

public record IntSize(int width, int height) {
    public IntSize {
        if (width < 0) throw new IllegalArgumentException("Width is negative");
        if (height < 0) throw new IllegalArgumentException("Height is negative");

    }

    public IntSize(Dimension size) {
        this(size.width, size.height);
    }

    public IntSize grown(int amount) {
        return new IntSize(Math.max(width + amount, 0), Math.max(height + amount, 0));
    }

    public IntSize shrunk(int amount) {
        return new IntSize(Math.max(width - amount, 0), Math.max(height - amount, 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntSize intSize = (IntSize) o;

        if (width != intSize.width) return false;
        return height == intSize.height;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return width + "x" + height;
    }
}
