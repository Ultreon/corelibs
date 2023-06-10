package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.FloatConsumer;

import java.util.Iterator;

public interface FloatIterator extends Iterator<Float> {
    @Override
    default Float next() {
        return this.nextFloat();
    }

    float nextFloat();

    default void forEachRemaining(FloatConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
