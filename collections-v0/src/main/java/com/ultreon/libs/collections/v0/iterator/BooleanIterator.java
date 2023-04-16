package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.BooleanConsumer;

import java.util.Iterator;

public interface BooleanIterator extends Iterator<Boolean> {
    @Override
    default Boolean next() {
        return nextBoolean();
    }

    boolean nextBoolean();

    default void forEachRemaining(BooleanConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
