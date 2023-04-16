package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.ByteConsumer;

import java.util.Iterator;

public interface ByteIterator extends Iterator<Byte> {
    @Override
    default Byte next() {
        return nextByte();
    }

    byte nextByte();

    default void forEachRemaining(ByteConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
