package com.ultreon.libs.events.v1;

public final class EventResult {
    private final boolean interrupted;
    private final boolean canceled;

    public EventResult(boolean interrupt, boolean canceled) {
        this.interrupted = interrupt;
        this.canceled = canceled;
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public static EventResult pass() {
        return new EventResult(false, false);
    }

    public static EventResult interruptCancel() {
        return new EventResult(true, true);
    }

    public static EventResult interrupt() {
        return new EventResult(true, false);
    }

    public static EventResult interrupt(boolean cancel) {
        return new EventResult(true, cancel);
    }
}
