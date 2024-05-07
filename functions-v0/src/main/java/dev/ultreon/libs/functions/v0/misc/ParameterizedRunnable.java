package dev.ultreon.libs.functions.v0.misc;

@FunctionalInterface
public interface ParameterizedRunnable<T> {
    void run(T t);
}
