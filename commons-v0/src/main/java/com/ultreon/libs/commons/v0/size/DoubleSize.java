package com.ultreon.libs.commons.v0.size;

public record DoubleSize(double width, double height) {
    public DoubleSize {
        if (width < 0) throw new IllegalArgumentException("Width is negative");
        if (height < 0) throw new IllegalArgumentException("Height is negative");
    }

    public DoubleSize grown(double amount) {
        return new DoubleSize(Math.max(width + amount, 0), Math.max(height + amount, 0));
    }

    public DoubleSize shrunk(double amount) {
        return new DoubleSize(Math.max(width - amount, 0), Math.max(height - amount, 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleSize that = (DoubleSize) o;

        if (Double.compare(that.width, width) != 0) return false;
        return Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(width);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return width + "x" + height;
    }
}
