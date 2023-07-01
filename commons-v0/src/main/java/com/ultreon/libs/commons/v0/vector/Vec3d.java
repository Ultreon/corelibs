package com.ultreon.libs.commons.v0.vector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@SuppressWarnings("unused")
public class Vec3d implements Externalizable, Cloneable {
    public double x, y, z;

    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d() {

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

    public static Vec3d mul(Vec3d a, Vec3d b) {
        return new Vec3d(a.x * b.x, a.y * b.y, a.z * b.z);
    }

    public static Vec3d div(Vec3d a, Vec3d b) {
        return new Vec3d(a.x / b.x, a.y / b.y, a.z / b.z);
    }

    public static Vec3d add(Vec3d a, Vec3d b) {
        return new Vec3d(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vec3d sub(Vec3d a, Vec3d b) {
        return new Vec3d(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static double dot(Vec3d a, Vec3d b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vec3d pow(Vec3d a, Vec3d b) {
        return new Vec3d(Math.pow(a.x, b.x), Math.pow(a.y, b.y), Math.pow(a.z, b.z));
    }

    public double dot(Vec3d vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public double dot(double x, double y, double z) {
        return this.x * x + this.y * y + this.z * z;
    }

    public double dot(double v) {
        return this.x * v + this.y * v + this.z * v;
    }

    public Vec3d set(Vec3d vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }

    public Vec3d set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3d set(double v) {
        this.x = v;
        this.y = v;
        this.z = v;
        return this;
    }

    public Vec3d add(Vec3d vec) {
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;
        return this;
    }

    public Vec3d add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3d add(double v) {
        this.x += v;
        this.y += v;
        this.z += v;
        return this;
    }

    public Vec3d sub(Vec3d vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;
        return this;
    }

    public Vec3d sub(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3d sub(double v) {
        this.x -= v;
        this.y -= v;
        this.z -= v;
        return this;
    }

    public Vec3d mul(Vec3d vec) {
        this.x *= vec.x;
        this.y *= vec.y;
        this.z *= vec.z;
        return this;
    }

    public Vec3d mul(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3d mul(double v) {
        this.x *= v;
        this.y *= v;
        this.z *= v;
        return this;
    }

    public Vec3d div(Vec3d vec) {
        this.x /= vec.x;
        this.y /= vec.y;
        this.z /= vec.z;
        return this;
    }

    public Vec3d div(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3d div(double v) {
        this.x /= v;
        this.y /= v;
        this.z /= v;
        return this;
    }

    public Vec3d mod(Vec3d vec) {
        this.x %= vec.x;
        this.y %= vec.y;
        this.z %= vec.z;
        return this;
    }

    public Vec3d mod(double x, double y, double z) {
        this.x %= x;
        this.y %= y;
        this.z %= z;
        return this;
    }

    public Vec3d mod(double v) {
        this.x %= v;
        this.y %= v;
        this.z %= v;
        return this;
    }

    public Vec3d pow(Vec3d vec) {
        this.x = Math.pow(this.x, vec.x);
        this.y = Math.pow(this.y, vec.y);
        this.z = Math.pow(this.z, vec.z);
        return this;
    }

    public Vec3d pow(double x, double y, double z) {
        this.x = Math.pow(this.x, x);
        this.y = Math.pow(this.y, y);
        this.z = Math.pow(this.z, z);
        return this;
    }

    public Vec3d pow(double v) {
        this.x = Math.pow(this.x, v);
        this.y = Math.pow(this.y, v);
        this.z = Math.pow(this.z, v);
        return this;
    }
    
    public Vec3d neg() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Vec3d inc() {
        this.x++;
        this.y++;
        this.z++;
        return this;
    }

    public Vec3d dec() {
        this.x--;
        this.y--;
        this.z--;
        return this;
    }

    public Vec3d abs() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
        this.z = Math.abs(this.z);
        return this;
    }

    public Vec3d floor() {
        this.x = Math.floor(this.x);
        this.y = Math.floor(this.y);
        this.z = Math.floor(this.z);
        return this;
    }

    public Vec3d ceil() {
        this.x = Math.ceil(this.x);
        this.y = Math.ceil(this.y);
        this.z = Math.ceil(this.z);
        return this;
    }

    public Vec3d cpy() {
        return new Vec3d(this.x, this.y, this.z);
    }

    public Vec3d d() {
        return new Vec3d(this.x, this.y, this.z);
    }

    public Vec3f f() {
        return new Vec3f((float) this.x, (float) this.y, (float) this.z);
    }

    public Vec3i i() {
        return new Vec3i((int) this.x, (int) this.y, (int) this.z);
    }

    @Override
    public Vec3d clone() {
        try {
            Vec3d clone = (Vec3d) super.clone();

            clone.x = this.x;
            clone.y = this.y;
            clone.z = this.z;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Vec3d vector4i = (Vec3d) o;
        return this.getX() == vector4i.getX() && this.getY() == vector4i.getY() && this.getZ() == vector4i.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public String toString() {
        return "Vector4i{" +
                "x=" + this.x +
                ", y=" + this.y +
                ", z=" + this.z +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
    }
}
