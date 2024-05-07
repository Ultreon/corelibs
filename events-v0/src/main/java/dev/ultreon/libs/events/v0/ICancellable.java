package dev.ultreon.libs.events.v0;

public interface ICancellable {
    void cancel();

    boolean isCancelled();
}
