package com.ultreon.libs.events.v0;

@Deprecated
public abstract class AbstractEvent {
    @Deprecated()
    private boolean cancelled;

    @Deprecated()
    public final void cancel() {
        this.cancelled = true;
    }

    @Deprecated()
    public final boolean isCancelled() {
        return this.isCancelable() && this.cancelled;
    }

    @Deprecated()
    public final boolean isCancelable() {
        return this.getClass().isAnnotationPresent(Cancelable.class);
    }
}
