package com.ultreon.libs.functions.v0.supplier;

import java.util.function.Supplier;

@FunctionalInterface
public interface BooleanSupplier extends Supplier<Boolean> {
    @Override
    default Boolean get() {
        return getBoolean();
    }

    boolean getBoolean();
}
