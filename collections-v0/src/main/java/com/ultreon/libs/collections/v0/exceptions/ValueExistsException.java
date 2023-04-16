package com.ultreon.libs.collections.v0.exceptions;

public class ValueExistsException extends RuntimeException {
    public ValueExistsException() {
        super();
    }

    public ValueExistsException(String message) {
        super(message);
    }

    public ValueExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueExistsException(Throwable cause) {
        super(cause);
    }
}
