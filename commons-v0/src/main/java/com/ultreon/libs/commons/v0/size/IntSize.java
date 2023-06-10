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
        return new IntSize(Math.max(this.width + amount, 0), Math.max(this.height + amount, 0));
    }

    public IntSize shrunk(int amount) {
        return new IntSize(Math.max(this.width - amount, 0), Math.max(this.height - amount, 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        IntSize intSize = (IntSize) o;

        if (this.width != intSize.width) return false;
        return this.height == intSize.height;
    }

    @Override
    public int hashCode() {
        int result = this.width;
        result = 31 * result + this.height;
        return result;
    }

    @Override
    public String toString() {
        return this.width + "x" + this.height;
    }
}
