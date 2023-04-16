package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.FloatConsumer;
import org.jetbrains.annotations.NotNull;

public interface FloatIterable extends Iterable<Float> {
    @NotNull
    @Override
    FloatIterator iterator();

    default void forEach(FloatConsumer action) {
        Iterable.super.forEach(action);
    }
}
