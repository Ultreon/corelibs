package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.DoubleConsumer;

import java.util.Iterator;

public interface DoubleIterator extends Iterator<Double> {
    @Override
    default Double next() {
        return nextDouble();
    }

    double nextDouble();

    default void forEachRemaining(DoubleConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
