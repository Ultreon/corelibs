package dev.ultreon.libs.functions.v0.misc;

@FunctionalInterface
public interface Mapper<A, B> {
    B map(A value);
}
