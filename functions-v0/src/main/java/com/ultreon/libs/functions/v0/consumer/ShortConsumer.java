package com.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface ShortConsumer extends Consumer<Short> {
    @Override
    @Deprecated
    default void accept(Short aShort) {
        accept((short)aShort);
    }

    void accept(short v);
}
