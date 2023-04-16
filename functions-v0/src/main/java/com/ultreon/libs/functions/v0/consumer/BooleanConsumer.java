package com.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface BooleanConsumer extends Consumer<Boolean> {
    @Override
    @Deprecated
    default void accept(Boolean aBoolean) {
        accept((boolean)aBoolean);
    }

    void accept(boolean v);
}
