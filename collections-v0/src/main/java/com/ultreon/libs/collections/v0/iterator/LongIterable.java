package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.LongConsumer;
import org.jetbrains.annotations.NotNull;

public interface LongIterable extends Iterable<Long> {
    @NotNull
    @Override
    LongIterator iterator();

    default void forEach(LongConsumer action) {
        Iterable.super.forEach(action);
    }
}
