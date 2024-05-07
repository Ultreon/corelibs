package dev.ultreon.libs.datetime.v0.exceptions;

public class DateTimeException extends IllegalArgumentException {
    public DateTimeException() {
        super();
    }

    public DateTimeException(String s) {
        super(s);
    }

    public DateTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateTimeException(Throwable cause) {
        super(cause);
    }
}
