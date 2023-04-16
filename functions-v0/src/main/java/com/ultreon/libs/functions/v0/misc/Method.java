package com.ultreon.libs.functions.v0.misc;

@FunctionalInterface
public interface Method<T> {
    Object call(T instance, Object... params);
}
