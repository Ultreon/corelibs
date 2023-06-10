package com.ultreon.libs.commons.v0.vector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@SuppressWarnings("unused")
public class Vec4f implements Externalizable, Cloneable {
    public float x, y, z, w;

    public Vec4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4f() {
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return this.w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public static Vec4f mul(Vec4f a, Vec4f b) {
        return new Vec4f(a.x * b.x, a.y * b.y, a.z * b.z, a.w * b.w);
    }

    public static Vec4f div(Vec4f a, Vec4f b) {
        return new Vec4f(a.x / b.x, a.y / b.y, a.z / b.z, a.w / b.w);
    }

    public static Vec4f add(Vec4f a, Vec4f b) {
        return new Vec4f(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
    }

    public static Vec4f sub(Vec4f a, Vec4f b) {
        return new Vec4f(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
    }

    public static float dot(Vec4f a, Vec4f b) {
        return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
    }

    public static Vec4d pow(Vec4f a, Vec4f b) {
        return new Vec4d(Math.pow(a.x, b.x), Math.pow(a.y, b.y), Math.pow(a.z, b.z), Math.pow(a.w, b.w));
    }

    public Vec4d d() {
        return new Vec4d(this.x, this.y, this.z, this.w);
    }

    public Vec4f f() {
        return new Vec4f(this.x, this.y, this.z, this.w);
    }

    public Vec4i i() {
        return new Vec4i((int) this.x, (int) this.y, (int) this.z, (int) this.w);
    }

    @Override
    public Vec4f clone() {
        try {
            Vec4f clone = (Vec4f) super.clone();

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
        Vec4f vector4i = (Vec4f) o;
        return this.getX() == vector4i.getX() && this.getY() == vector4i.getY() && this.getZ() == vector4i.getZ() && this.getW() == vector4i.getW();
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
        out.writeFloat(this.x);
        out.writeFloat(this.y);
        out.writeFloat(this.z);
        out.writeFloat(this.w);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.z = in.readFloat();
        this.w = in.readFloat();
    }
}
