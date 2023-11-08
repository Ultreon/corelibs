package com.ultreon.libs.commons.v0.platform.win32;

import java.io.*;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Alternate Data Streams for Windows.
 * <p>
 * See <a href="https://learn.microsoft.com/en-us/openspecs/windows_protocols/ms-fscc/8ac44452-328c-4d7b-a784-d72afd19bd9f#gt_03d3ec14-995b-486a-8938-e2a73f6de7e1">Section 2.1.4 of Windows Protocols</a>.
 *
 * @since 0.2.0
 * @author <a href="https://github.com/XyperCodee">XyperCode</a>
 */
public final class AlternateDataStream {
    private final File file;
    private final String id;

    /**
     * Create a new Alternate Data Stream instance.
     *
     * @param file the file of the alternate data stream.
     * @param id   the alternate data stream identifier.
     */
    public AlternateDataStream(File file, String id) {
        this.file = file;
        this.id = id;
    }

    public File file() {
        return this.file;
    }

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        AlternateDataStream that = (AlternateDataStream) obj;
        return Objects.equals(this.file, that.file) &&
                Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.file, this.id);
    }

    @Override
    public String toString() {
        return "AlternateDataStream[" +
                "file=" + this.file + ", " +
                "id=" + this.id + ']';
    }

    public OutputStream openOutputStream() throws FileNotFoundException {
        return new FileOutputStream(this.file.getPath() + ":" + this.id);
    }

    public InputStream openInputStream() throws FileNotFoundException {
        return new FileInputStream(this.file.getPath() + ":" + this.id);
    }

    public Reader openReader() throws FileNotFoundException {
        return new FileReader(this.file.getPath() + ":" + this.id);
    }

    public Writer openWriter() throws IOException {
        return new FileWriter(this.file.getPath() + ":" + this.id);
    }

    public String getPath() {
        return this.file.getPath() + ":" + this.id;
    }

    public Path toPath() {
        return this.file.toPath();
    }
}
