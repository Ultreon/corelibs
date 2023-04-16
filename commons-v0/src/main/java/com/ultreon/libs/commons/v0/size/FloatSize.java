package com.ultreon.libs.commons.v0.size;

public record FloatSize(float width, float height) {
    public FloatSize {
        if (width < 0) throw new IllegalArgumentException("Width is negative");
        if (height < 0) throw new IllegalArgumentException("Height is negative");

    }

    public FloatSize grown(float amount) {
        return new FloatSize(Math.max(width + amount, 0), Math.max(height + amount, 0));
    }

    public FloatSize shrunk(float amount) {
        return new FloatSize(Math.max(width - amount, 0), Math.max(height - amount, 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloatSize floatSize = (FloatSize) o;

        if (Float.compare(floatSize.width, width) != 0) return false;
        return Float.compare(floatSize.height, height) == 0;
    }

    @Override
    public int hashCode() {
        int result = (width != 0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (height != 0.0f ? Float.floatToIntBits(height) : 0);
        return result;
    }

    @Override
    public String toString() {
        return width + "x" + height;
    }
}
