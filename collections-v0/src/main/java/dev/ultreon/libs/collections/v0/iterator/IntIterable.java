package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.IntConsumer;
import org.jetbrains.annotations.NotNull;

public interface IntIterable extends Iterable<Integer> {
    @NotNull
    @Override
    IntIterator iterator();

    default void forEach(IntConsumer action) {
        Iterable.super.forEach(action);
    }
}
