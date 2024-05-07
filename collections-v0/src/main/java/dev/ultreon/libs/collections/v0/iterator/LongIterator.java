package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.LongConsumer;

import java.util.Iterator;

public interface LongIterator extends Iterator<Long> {
    @Override
    default Long next() {
        return this.nextLong();
    }

    long nextLong();

    default void forEachRemaining(LongConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
