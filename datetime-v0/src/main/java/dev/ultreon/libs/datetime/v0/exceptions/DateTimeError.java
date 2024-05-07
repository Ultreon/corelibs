package dev.ultreon.libs.datetime.v0.exceptions;

public class DateTimeError extends Error {
    public DateTimeError() {
        super();
    }

    public DateTimeError(String message) {
        super(message);
    }

    public DateTimeError(String message, Throwable cause) {
        super(message, cause);
    }

    public DateTimeError(Throwable cause) {
        super(cause);
    }
}
