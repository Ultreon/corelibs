package dev.ultreon.libs.io.v0;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RealFileHandle extends FileHandle {
    private final Path path;

    public RealFileHandle(Path path) {
        super(path.toString());

        this.path = path;
    }

    public RealFileHandle(String path) {
        super(path);

        this.path = Paths.get(path);
    }

    @Override
    public Reader reader() throws IOException {
        return Files.newBufferedReader(this.path);
    }

    @Override
    public InputStream read() throws IOException {
        return Files.newInputStream(this.path);
    }

    @Override
    public Writer writer() throws IOException {
        return Files.newBufferedWriter(this.path);
    }

    @Override
    public OutputStream write() throws IOException {
        return Files.newOutputStream(this.path);
    }

    @Override
    public boolean exists() {
        return Files.exists(this.path);
    }

    @Override
    public boolean delete() {
        try {
            Files.delete(this.path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean create() {
        try {
            Files.createFile(this.path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean isDirectory() {
        return Files.isDirectory(this.path);
    }

    @Override
    public boolean isFile() {
        return Files.isRegularFile(this.path);
    }

    @Override
    public boolean isReadable() {
        return Files.isReadable(this.path);
    }

    @Override
    public boolean isWritable() {
        return Files.isWritable(this.path);
    }

    @Override
    public boolean isExecutable() {
        return Files.isExecutable(this.path);
    }

    @Override
    public long lastModified() throws IOException {
        return Files.readAttributes(this.path, BasicFileAttributes.class).lastModifiedTime().toMillis();
    }

    @Override
    public long length() {
        try {
            return Files.size(this.path);
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public FileHandle parent() {
        return new RealFileHandle(this.path.getParent());
    }

    @Override
    public FileHandle child(String name) {
        return new RealFileHandle(this.path.resolve(name));
    }

    @Override
    public FileHandle sibling(String name) {
        return new RealFileHandle(this.path.resolveSibling(name));
    }

    @Override
    public FileHandle[] list(Filter<FileHandle> filter) throws IOException {
        List<FileHandle> files = new ArrayList<>();

        try (Stream<Path> stream = Files.list(this.path)) {
            stream
                    .filter(Files::isRegularFile)
                    .map(RealFileHandle::new)
                    .filter(filter::accept)
                    .forEach(files::add);
        }

        return files.toArray(new FileHandle[0]);
    }

    @Override
    public Stream<FileHandle> walk() throws IOException {
        return Files.walk(this.path).map(RealFileHandle::new);
    }
}
