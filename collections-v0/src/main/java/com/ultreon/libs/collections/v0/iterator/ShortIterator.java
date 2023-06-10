package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.ShortConsumer;

import java.util.Iterator;

public interface ShortIterator extends Iterator<Short> {
    @Override
    default Short next() {
        return this.nextShort();
    }

    short nextShort();

    default void forEachRemaining(ShortConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
