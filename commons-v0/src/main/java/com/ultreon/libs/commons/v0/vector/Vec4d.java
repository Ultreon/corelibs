package com.ultreon.libs.commons.v0.vector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@SuppressWarnings("unused")
public class Vec4d implements Externalizable, Cloneable {
    public double x, y, z, w;

    public Vec4d(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4d() {

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

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return this.w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public static Vec4d mul(Vec4d a, Vec4d b) {
        return new Vec4d(a.x * b.x, a.y * b.y, a.z * b.z, a.w * b.w);
    }

    public static Vec4d div(Vec4d a, Vec4d b) {
        return new Vec4d(a.x / b.x, a.y / b.y, a.z / b.z, a.w / b.w);
    }

    public static Vec4d add(Vec4d a, Vec4d b) {
        return new Vec4d(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
    }

    public static Vec4d sub(Vec4d a, Vec4d b) {
        return new Vec4d(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
    }

    public static double dot(Vec4d a, Vec4d b) {
        return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
    }

    public static Vec4d pow(Vec4d a, Vec4d b) {
        return new Vec4d(Math.pow(a.x, b.x), Math.pow(a.y, b.y), Math.pow(a.z, b.z), Math.pow(a.w, b.w));
    }

    public Vec4d d() {
        return new Vec4d(this.x, this.y, this.z, this.w);
    }

    public Vec4f f() {
        return new Vec4f((float) this.x, (float) this.y, (float) this.z, (float) this.w);
    }

    public Vec4i i() {
        return new Vec4i((int) this.x, (int) this.y, (int) this.z, (int) this.w);
    }

    @Override
    public Vec4d clone() {
        try {
            Vec4d clone = (Vec4d) super.clone();

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
        Vec4d vector4i = (Vec4d) o;
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
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeDouble(this.w);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        this.w = in.readDouble();
    }
}
