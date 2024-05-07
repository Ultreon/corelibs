package dev.ultreon.libs.collections.v0.iterator;

import dev.ultreon.libs.functions.v0.consumer.ByteConsumer;
import org.jetbrains.annotations.NotNull;

public interface ByteIterable extends Iterable<Byte> {
    @NotNull
    @Override
    ByteIterator iterator();

    default void forEach(ByteConsumer action) {
        Iterable.super.forEach(action);
    }
}
