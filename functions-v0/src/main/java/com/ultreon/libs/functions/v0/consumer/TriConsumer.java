package com.ultreon.libs.functions.v0.consumer;

/**
 * Represents a function that accepts three arguments but returns {@code void}.
 *
 * @param <A> the first argument type
 * @param <B> the second argument type
 * @param <C> the third argument type
 * @since 0.2.0
 * @author <a href="https://github.com/XyperCodee">XyperCode</a>
 */
public interface TriConsumer<A, B, C> {
    void accept(A a, B b, C c);
}
