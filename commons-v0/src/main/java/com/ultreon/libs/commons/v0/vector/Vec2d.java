package com.ultreon.libs.commons.v0.vector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@SuppressWarnings("unused")
public class Vec2d implements Externalizable, Cloneable {
    public double x, y;

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d() {

    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void mul(double i) {
        this.x *= i;
        this.y *= i;
    }

    public void div(double i) {
        this.x /= i;
        this.y /= i;
    }

    public void add(double i) {
        this.x += i;
        this.y += i;
    }

    public void sub(double i) {
        this.x -= i;
        this.y -= i;
    }

    public void pow(double i) {
        this.x = Math.pow(this.x, i);
        this.y = Math.pow(this.y, i);
    }

    public void mul(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    public void div(double x, double y) {
        this.x /= x;
        this.y /= y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void sub(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void pow(double x, double y) {
        this.x = Math.pow(this.x, x);
        this.y = Math.pow(this.y, y);
    }

    public static Vec2d mul(Vec2d a, Vec2d b) {
        return new Vec2d(a.x * b.x, a.y * b.y);
    }

    public static Vec2d div(Vec2d a, Vec2d b) {
        return new Vec2d(a.x / b.x, a.y / b.y);
    }

    public static Vec2d add(Vec2d a, Vec2d b) {
        return new Vec2d(a.x + b.x, a.y + b.y);
    }

    public static Vec2d sub(Vec2d a, Vec2d b) {
        return new Vec2d(a.x - b.x, a.y - b.y);
    }

    public static double dot(Vec2d a, Vec2d b) {
        return a.x * b.x + a.y * b.y;
    }

    public static Vec2d pow(Vec2d a, Vec2d b) {
        return new Vec2d(Math.pow(a.x, b.x), Math.pow(a.y, b.y));
    }

    public Vec2d d() {
        return new Vec2d(this.x, this.y);
    }

    public Vec2f f() {
        return new Vec2f((float) this.x, (float) this.y);
    }

    public Vec2i i() {
        return new Vec2i((int) this.x, (int) this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Vec2d vec2D = (Vec2d) o;
        return Double.compare(vec2D.getX(), this.getX()) == 0 && Double.compare(vec2D.getY(), this.getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
    }

    @Override
    public String toString() {
        return "Vector2d{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }

    @Override
    public Vec2d clone() {
        try {
            Vec2d clone = (Vec2d) super.clone();

            clone.x = this.x;
            clone.y = this.y;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(this.x);
        out.writeDouble(this.y);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.x = in.readDouble();
        this.y = in.readDouble();
    }
}
