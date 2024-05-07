package dev.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface BooleanConsumer extends Consumer<Boolean> {
    @Override
    @Deprecated
    default void accept(Boolean aBoolean) {
        this.accept((boolean)aBoolean);
    }

    void accept(boolean v);
}
