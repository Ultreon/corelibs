package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.BooleanConsumer;

import java.util.Iterator;

public interface BooleanIterator extends Iterator<Boolean> {
    @Override
    default Boolean next() {
        return this.nextBoolean();
    }

    boolean nextBoolean();

    default void forEachRemaining(BooleanConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
