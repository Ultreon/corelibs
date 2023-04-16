package com.ultreon.libs.collections.v0.iterator;

import com.ultreon.libs.functions.v0.consumer.DoubleConsumer;
import org.jetbrains.annotations.NotNull;

public interface DoubleIterable extends Iterable<Double> {
    @NotNull
    @Override
    DoubleIterator iterator();

    default void forEach(DoubleConsumer action) {
        Iterable.super.forEach(action);
    }
}
