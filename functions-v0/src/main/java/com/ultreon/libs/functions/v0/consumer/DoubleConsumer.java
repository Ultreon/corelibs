package com.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface DoubleConsumer extends Consumer<Double> {
    @Override
    @Deprecated
    default void accept(Double aDouble) {
        accept((double)aDouble);
    }

    void accept(double v);
}
