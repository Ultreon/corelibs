package com.ultreon.libs.functions.v0.supplier;

import java.util.function.Supplier;

@FunctionalInterface
public interface CharSupplier extends Supplier<Character> {
    @Override
    default Character get() {
        return getChar();
    }

    char getChar();
}
