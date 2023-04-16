package com.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface LongConsumer extends Consumer<Long> {
    @Override
    @Deprecated
    default void accept(Long aLong) {
        accept((long)aLong);
    }

    void accept(long v);
}
