package com.ultreon.libs.functions.v0.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface CharConsumer extends Consumer<Character> {
    @Override
    @Deprecated
    default void accept(Character aCharacter) {
        accept((char)aCharacter);
    }

    void accept(char v);
}
