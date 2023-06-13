package com.ultreon.libs.commons.v0.util;

public class IllegalCallerException extends RuntimeException {
    public IllegalCallerException() {
        super();
    }

    public IllegalCallerException(String message) {
        super(message);
    }

    public IllegalCallerException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCallerException(Throwable cause) {
        super(cause);
    }
}
