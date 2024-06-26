package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.ByteConsumer;

import java.util.Iterator;

public interface ByteIterator extends Iterator<Byte> {
    @Override
    default Byte next() {
        return this.nextByte();
    }

    byte nextByte();

    default void forEachRemaining(ByteConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
