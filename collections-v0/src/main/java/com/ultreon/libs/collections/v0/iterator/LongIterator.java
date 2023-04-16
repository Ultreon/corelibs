package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.LongConsumer;

import java.util.Iterator;

public interface LongIterator extends Iterator<Long> {
    @Override
    default Long next() {
        return nextLong();
    }

    long nextLong();

    default void forEachRemaining(LongConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
