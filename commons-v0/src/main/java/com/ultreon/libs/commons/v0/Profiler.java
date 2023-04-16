package com.ultreon.libs.commons.v0;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Profiler {
    private final Map<String, Long> values = new HashMap<>();

    private final Map<String, Long> start = new HashMap<>();

    public void start() {
    }

    public void startSection(String name) {
        start.put(name, System.currentTimeMillis());
    }

    public void endSection(String name) {
        Long start = this.start.remove(name);
        if (start == null) start = 0L;
        long end = System.currentTimeMillis();
        long time = end - start;
        values.put(name, time);
    }

    public Map<String, Long> end() {
        return Collections.unmodifiableMap(values);
    }

    public void section(String name, Runnable block) {
        startSection(name);
        block.run();
        endSection(name);
    }
}
