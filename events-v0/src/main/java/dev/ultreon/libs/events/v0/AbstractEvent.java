package dev.ultreon.libs.events.v0;

public abstract class AbstractEvent {
    private boolean cancelled;

    public final void cancel() {
        this.cancelled = true;
    }

    public final boolean isCancelled() {
        return this.isCancelable() && this.cancelled;
    }

    public final boolean isCancelable() {
        return this.getClass().isAnnotationPresent(Cancelable.class);
    }
}
