package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.CharConsumer;

import java.util.Iterator;

public interface CharIterator extends Iterator<Character> {
    @Override
    default Character next() {
        return nextChar();
    }

    char nextChar();

    default void forEachRemaining(CharConsumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
