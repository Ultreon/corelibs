package com.ultreon.libs.commons.v0.vector;

import java.awt.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@SuppressWarnings("unused")
public class Vec2i implements Externalizable, Cloneable {
    public int x, y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i() {

    }

    public Vec2i(Point point) {
        this(point.x, point.y);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void mul(int i) {
        this.x *= i;
        this.y *= i;
    }

    public void div(int i) {
        this.x /= i;
        this.y /= i;
    }

    public void add(int i) {
        this.x += i;
        this.y += i;
    }

    public void sub(int i) {
        this.x -= i;
        this.y -= i;
    }

    public void pow(int i) {
        this.x = (int) Math.pow(this.x, i);
        this.y = (int) Math.pow(this.y, i);
    }

    public void mul(int x, int y) {
        this.x *= x;
        this.y *= y;
    }

    public void div(int x, int y) {
        this.x /= x;
        this.y /= y;
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void sub(int x, int y) {
        this.x -= x;
        this.y -= y;
    }

    public void pow(int x, int y) {
        this.x = (int) Math.pow(this.x, x);
        this.y = (int) Math.pow(this.y, y);
    }

    public static Vec2i mul(Vec2i a, Vec2i b) {
        return new Vec2i(a.x * b.x, a.y * b.y);
    }

    public static Vec2i div(Vec2i a, Vec2i b) {
        return new Vec2i(a.x / b.x, a.y / b.y);
    }

    public static Vec2i add(Vec2i a, Vec2i b) {
        return new Vec2i(a.x + b.x, a.y + b.y);
    }

    public static Vec2i sub(Vec2i a, Vec2i b) {
        return new Vec2i(a.x - b.x, a.y - b.y);
    }

    public static int dot(Vec2i a, Vec2i b) {
        return a.x * b.x + a.y * b.y;
    }

    public static Vec2d pow(Vec2i a, Vec2i b) {
        return new Vec2d(Math.pow(a.x, b.x), Math.pow(a.y, b.y));
    }

    public Vec2d d() {
        return new Vec2d(this.x, this.y);
    }

    public Vec2f f() {
        return new Vec2f(this.x, this.y);
    }

    public Vec2i i() {
        return new Vec2i(this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Vec2i vec2I = (Vec2i) o;
        return this.getX() == vec2I.getX() && this.getY() == vec2I.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
    }

    @Override
    public String toString() {
        return "Vector2i{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }

    @Override
    public Vec2i clone() {
        try {
            Vec2i clone = (Vec2i) super.clone();

            clone.x = this.x;
            clone.y = this.y;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(this.x);
        out.writeInt(this.y);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.x = in.readInt();
        this.y = in.readInt();
    }

    public Dimension toAwtDimension() {
        return new Dimension(this.x, this.y);
    }

    public Point toAwtPoint() {
        return new Point(this.x, this.y);
    }
}
