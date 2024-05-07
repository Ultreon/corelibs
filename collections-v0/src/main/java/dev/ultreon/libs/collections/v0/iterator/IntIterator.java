package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.IntConsumer;

import java.util.Iterator;

public interface IntIterator extends Iterator<Integer> {
    @Override
    default Integer next() {
        return this.nextInt();
    }

    int nextInt();

    default void forEachRemaining(IntConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
