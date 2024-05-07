package dev.ultreon.libs.functions.v0.misc;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Throwable> {
    void accept(T obj) throws E;
}
