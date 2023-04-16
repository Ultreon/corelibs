package com.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface IntConsumer extends Consumer<Integer> {
    @Override
    @Deprecated
    default void accept(Integer aInteger) {
        accept((int)aInteger);
    }

    void accept(int v);
}
