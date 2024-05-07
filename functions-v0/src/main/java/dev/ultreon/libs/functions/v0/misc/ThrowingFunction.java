package dev.ultreon.libs.functions.v0.misc;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {
    R apply(T obj) throws E;
}
