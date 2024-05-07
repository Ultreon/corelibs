package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.BooleanConsumer;
import org.jetbrains.annotations.NotNull;

public interface BooleanIterable extends Iterable<Boolean> {
    @NotNull
    @Override
    BooleanIterator iterator();

    default void forEach(BooleanConsumer action) {
        Iterable.super.forEach(action);
    }
}
