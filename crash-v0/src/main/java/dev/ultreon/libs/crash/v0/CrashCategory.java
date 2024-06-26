package dev.ultreon.libs.crash.v0;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrashCategory {
    protected final List<AbstractMap.SimpleEntry<String, String>> entries = new ArrayList<>();
    protected final String details;
    protected Throwable throwable;

    public CrashCategory(String details) {
        this(details, null);
    }

    public CrashCategory(String details, Throwable t) {
        this.details = details;
        this.throwable = t;
    }

    public void add(String key, @Nullable Object value) {
        if (key.contains(":")) {
            throw new IllegalArgumentException("Key cannot contain a colon");
        }

        if (key.length() > 32) {
            throw new IllegalArgumentException("Key cannot be longer than 32 characters.");
        }

        this.entries.add(new AbstractMap.SimpleEntry<>(key, value != null ? value.toString() : "null@0"));
    }

    public String getDetails() {
        return this.details;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.details).append(": \r\n");

        if (this.entries.size() > 0) {
            ArrayList<AbstractMap.SimpleEntry<String, String>> simpleEntries = new ArrayList<>(this.entries);
            for (int i = 0; i < simpleEntries.size() - 1; i++) {
                AbstractMap.SimpleEntry<String, String> entry = simpleEntries.get(i);
                sb.append("   ");
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append(System.lineSeparator());
            }

            AbstractMap.SimpleEntry<String, String> entry = simpleEntries.get(simpleEntries.size() - 1);
            sb.append("   ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append(System.lineSeparator());
        }

        if (this.throwable != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);

            this.throwable.printStackTrace(writer);
            writer.flush();

            StringBuffer buffer = stringWriter.getBuffer();
            try {
                stringWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String s = buffer.toString();
            List<String> strings = Arrays.asList(s.split("(\r\n|\r|\n)"));
            String join = "   " + String.join(System.lineSeparator() + "   ", strings);

            sb.append(join);
        }

        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
