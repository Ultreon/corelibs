package com.ultreon.libs.commons.v0.vector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@SuppressWarnings("unused")
public class Vec4i implements Externalizable, Cloneable {
    public int x, y, z, w;

    public Vec4i(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4i() {

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

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public static Vec4i mul(Vec4i a, Vec4i b) {
        return new Vec4i(a.x * b.x, a.y * b.y, a.z * b.z, a.w * b.w);
    }

    public static Vec4i div(Vec4i a, Vec4i b) {
        return new Vec4i(a.x / b.x, a.y / b.y, a.z / b.z, a.w / b.w);
    }

    public static Vec4i add(Vec4i a, Vec4i b) {
        return new Vec4i(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
    }

    public static Vec4i sub(Vec4i a, Vec4i b) {
        return new Vec4i(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
    }

    public static int dot(Vec4i a, Vec4i b) {
        return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
    }

    public static Vec4d pow(Vec4i a, Vec4i b) {
        return new Vec4d(Math.pow(a.x, b.x), Math.pow(a.y, b.y), Math.pow(a.z, b.z), Math.pow(a.w, b.w));
    }

    public Vec4d d() {
        return new Vec4d(this.x, this.y, this.z, this.w);
    }

    public Vec4f f() {
        return new Vec4f(this.x, this.y, this.z, this.w);
    }

    public Vec4i i() {
        return new Vec4i(this.x, this.y, this.z, this.w);
    }

    @Override
    public Vec4i clone() {
        try {
            Vec4i clone = (Vec4i) super.clone();

            clone.x = this.x;
            clone.y = this.y;
            clone.z = this.z;
            clone.w = this.w;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Vec4i vec4I = (Vec4i) o;
        return this.getX() == vec4I.getX() && this.getY() == vec4I.getY() && this.getZ() == vec4I.getZ() && this.getW() == vec4I.getW();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getZ(), this.getW());
    }

    @Override
    public String toString() {
        return "Vector4i{" +
                "x=" + this.x +
                ", y=" + this.y +
                ", z=" + this.z +
                ", w=" + this.w +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(this.x);
        out.writeInt(this.y);
        out.writeInt(this.z);
        out.writeInt(this.w);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.x = in.readInt();
        this.y = in.readInt();
        this.z = in.readInt();
        this.w = in.readInt();
    }
}
