package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.ShortConsumer;
import org.jetbrains.annotations.NotNull;

public interface ShortIterable extends Iterable<Short> {
    @NotNull
    @Override
    ShortIterator iterator();

    default void forEach(ShortConsumer action) {
        Iterable.super.forEach(action);
    }
}
