package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.CharConsumer;
import org.jetbrains.annotations.NotNull;

public interface CharIterable extends Iterable<Character> {
    @NotNull
    @Override
    CharIterator iterator();

    default void forEach(CharConsumer action) {
        Iterable.super.forEach(action);
    }
}
