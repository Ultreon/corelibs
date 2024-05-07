package dev.ultreon.libs.io.v0;

@FunctionalInterface
public interface Filter<T> {
    boolean accept(T t);
}
