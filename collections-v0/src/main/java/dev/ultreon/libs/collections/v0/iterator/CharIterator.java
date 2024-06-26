package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.CharConsumer;

import java.util.Iterator;

public interface CharIterator extends Iterator<Character> {
    @Override
    default Character next() {
        return this.nextChar();
    }

    char nextChar();

    default void forEachRemaining(CharConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
