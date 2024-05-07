package dev.ultreon.libs.functions.v0.misc;

@FunctionalInterface
public interface Applier<T, R> {
    R apply(T obj);
}
