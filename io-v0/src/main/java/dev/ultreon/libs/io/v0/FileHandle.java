package dev.ultreon.libs.io.v0;

import java.io.*;
import java.nio.charset.Charset;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

public abstract class FileHandle {
    private final String path;

    public FileHandle(String path) {
        this.path = path;
    }

    public static FileHandle of(String path) {
        return new RealFileHandle(path);
    }

    public String getPath() {
        return this.path;
    }

    public abstract Reader reader() throws IOException;

    public abstract InputStream read() throws IOException;

    public abstract Writer writer() throws IOException;

    public abstract OutputStream write() throws IOException;

    public abstract boolean exists();

    public abstract boolean delete();

    public abstract boolean create();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract boolean isReadable();

    public abstract boolean isWritable();

    public abstract boolean isExecutable();

    public abstract long lastModified() throws IOException;

    public abstract long length();

    public abstract FileHandle parent();

    public abstract FileHandle child(String name);

    public abstract FileHandle sibling(String name);

    public FileHandle[] list() throws IOException {
        return this.list("");
    }

    public FileHandle[] list(String filter) throws IOException {
        return this.list((handle) -> handle.path.endsWith(filter));
    }

    public abstract FileHandle[] list(Filter<FileHandle> filter) throws IOException;

    public abstract Stream<FileHandle> walk() throws IOException;

    public String readString() {
        return this.readString(4096);
    }

    public String readString(int bufferSize) {
        try (BufferedReader bufferedReader = this.bufferedReader()) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[bufferSize];
            while (bufferedReader.read(buffer) != -1) {
                stringBuilder.append(buffer);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public byte[] readBytes() {
        return this.readBytes(4096);
    }

    public byte[] readBytes(int bufferSize) {
        try (InputStream inputStream = this.read()) {
            byte[] buffer = new byte[bufferSize];
            byte[] data = new byte[0];
            int offset = 0;
            while (inputStream.read(buffer) != -1) {
                offset += bufferSize;
                IOUtils.concat(data, buffer);
            }

            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeBytes(byte[] data) {
        try (OutputStream outputStream = this.write()) {
            outputStream.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeBytes(byte[] data, int offset, int length) throws IOException {
        OutputStream outputStream = this.write();
        try {
            outputStream.write(data, offset, length);
        } catch (IOException e) {
            try {
                outputStream.close();
            } catch (IOException ex) {
                e.addSuppressed(ex);
            }

            throw new RuntimeException(e);
        }
    }

    public void writeString(String data, Charset charset) {
        this.writeBytes(data.getBytes(charset));
    }

    public void writeString(String data) {
        this.writeString(data, Charset.defaultCharset());
    }

    private BufferedReader bufferedReader() throws IOException {
        return new BufferedReader(this.reader());
    }

    public BufferedWriter bufferedWriter() throws IOException {
        return new BufferedWriter(this.writer());
    }

    public BufferedInputStream bufferedRead() throws IOException {
        return new BufferedInputStream(this.read());
    }

    public BufferedOutputStream bufferedWrite() throws IOException {
        return new BufferedOutputStream(this.write());
    }

    protected ZipInputStream openZip() throws IOException {
        return new ZipInputStream(this.read());
    }
}