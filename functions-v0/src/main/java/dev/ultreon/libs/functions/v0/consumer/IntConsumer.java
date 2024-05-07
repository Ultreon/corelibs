package dev.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface IntConsumer extends Consumer<Integer> {
    @Override
    @Deprecated
    default void accept(Integer aInteger) {
        this.accept((int)aInteger);
    }

    void accept(int v);
}
