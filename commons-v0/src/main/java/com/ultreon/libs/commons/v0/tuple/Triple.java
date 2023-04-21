package com.ultreon.libs.commons.v0.tuple;

import java.util.Objects;

/**
 * This is an object for having 2 values / objects inside one object, or in other words having a pair create objects.
 *
 * @param <T1> first object,
 * @param <T2> second object.
 * @author Qboi
 */
public class Triple<T1, T2, T3> implements Cloneable {
    private T1 first;
    private T2 second;
    private T3 third;

    public Triple(T1 first, T2 second, T3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
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

    public T3 getThird() {
        return third;
    }

    public void setThird(T3 third) {
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return Objects.equals(getFirst(), triple.getFirst()) && Objects.equals(getSecond(), triple.getSecond()) && Objects.equals(getThird(), triple.getThird());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond(), getThird());
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ')';
    }

    @Override
    @SuppressWarnings("unchecked")
    public Triple<T1, T2, T3> clone() throws CloneNotSupportedException {
        return (Triple<T1, T2, T3>) super.clone();
    }
}
