package com.ultreon.libs.commons.v0.tuple;

import java.util.Objects;

/**
 * This is an object for having 2 values / objects inside one object, or in other words having a pair create objects.
 *
 * @param <T1> first object,
 * @param <T2> second object.
 * @author Qboi
 */
public class Quadruple<T1, T2, T3, T4> implements Cloneable {
    private T1 first;
    private T2 second;
    private T3 third;
    private T4 fourth;

    public Quadruple(T1 first, T2 second, T3 third, T4 fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
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

    public T4 getFourth() {
        return fourth;
    }

    public void setFourth(T4 fourth) {
        this.fourth = fourth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadruple<?, ?, ?, ?> quadruple = (Quadruple<?, ?, ?, ?>) o;
        return Objects.equals(getFirst(), quadruple.getFirst()) && Objects.equals(getSecond(), quadruple.getSecond()) && Objects.equals(getThird(), quadruple.getThird()) && Objects.equals(getFourth(), quadruple.getFourth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond(), getThird(), getFourth());
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ')';
    }

    @Override
    @SuppressWarnings("unchecked")
    public Quadruple<T1, T2, T3, T4> clone() throws CloneNotSupportedException {
        return (Quadruple<T1, T2, T3, T4>) super.clone();
    }
}
