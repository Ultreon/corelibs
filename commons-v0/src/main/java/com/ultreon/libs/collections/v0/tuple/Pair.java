package com.ultreon.libs.collections.v0.tuple;

import java.util.Objects;

/**
 * This is an object for having 2 values / objects inside one object, or in other words having a pair create objects.
 *
 * @param <T1> first object,
 * @param <T2> second object.
 * @author Qboi
 */
public class Pair<T1, T2> implements Cloneable {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(getFirst(), pair.getFirst()) && Objects.equals(getSecond(), pair.getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond());
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ')';
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<T1, T2> clone() throws CloneNotSupportedException {
        return (Pair<T1, T2>) super.clone();
    }
}
