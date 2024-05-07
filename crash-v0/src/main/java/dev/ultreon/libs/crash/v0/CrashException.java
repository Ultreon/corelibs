package dev.ultreon.libs.crash.v0;

import org.jetbrains.annotations.NotNull;

/**
 * Exception that is thrown when an application crash occurs
 *
 * @author <a href="https://github.com/XyperCodee">XyperCode</a>
 * @see ApplicationCrash
 * @since 0.2.0
 */
public class CrashException extends RuntimeException {
    private final CrashLog crashLog;

    public CrashException(@NotNull CrashLog crashLog) {
        super("Application crashed!");
        this.crashLog = crashLog;
    }

    public CrashException(@NotNull CrashLog crashLog, @NotNull String message) {
        super(message);
        this.crashLog = crashLog;
    }

    @NotNull
    public CrashLog getCrashLog() {
        return this.crashLog;
    }
}
