package com.ultreon.libs.crash.v0;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class ApplicationCrash {
    private static final List<Runnable> crashHandlers = new ArrayList<>();
    @NotNull
    private final CrashLog crashLog;

    ApplicationCrash(@NotNull CrashLog crashLog) {
        this.crashLog = crashLog;
    }

    public void printCrash() {
        String crashString = this.crashLog.toString();
        List<String> strings = crashString.lines().toList();
        for (String string : strings) {
            System.err.println(string);
        }
    }

    private void crash() {
        for (Runnable handler : crashHandlers) {
            handler.run();
        }
    }

    public static void onCrash(Runnable handler) {
        crashHandlers.add(handler);
    }

    @NotNull
    public CrashLog getCrashLog() {
        return this.crashLog;
    }
}
