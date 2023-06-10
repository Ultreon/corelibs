package com.ultreon.libs.commons.v0.size;

public record DoubleSize(double width, double height) {
    public DoubleSize {
        if (width < 0) throw new IllegalArgumentException("Width is negative");
        if (height < 0) throw new IllegalArgumentException("Height is negative");
    }

    public DoubleSize grown(double amount) {
        return new DoubleSize(Math.max(this.width + amount, 0), Math.max(this.height + amount, 0));
    }

    public DoubleSize shrunk(double amount) {
        return new DoubleSize(Math.max(this.width - amount, 0), Math.max(this.height - amount, 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        DoubleSize that = (DoubleSize) o;

        if (Double.compare(that.width, this.width) != 0) return false;
        return Double.compare(that.height, this.height) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.width);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return this.width + "x" + this.height;
    }
}
