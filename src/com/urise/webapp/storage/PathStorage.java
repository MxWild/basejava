package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.SerializeStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    private SerializeStrategy serializeStrategy;

    protected PathStorage(String dir, SerializeStrategy serializeStrategy) {
        this.serializeStrategy = serializeStrategy;
        this.directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path getKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("IOError ", path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializeStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IOError ", path.getFileName().toString(), e);
        }
    }


    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path %s can't delete", path.getFileName().toString());
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            serializeStrategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Resume write error in path", r.getUuid(), e);
        }
    }


    @Override
    protected List<Resume> doCopyAll() {
        try {
            return getFilesList().map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Directory is empty or read error");
        }
    }

    @Override
    public void clear() {
        try {
            getFilesList().forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error");
        }
    }

    @Override
    public int size() {
        try {
            return (int) getFilesList().count();
        } catch (IOException e) {
            throw new StorageException("Directory is empty or read error");
        }
    }

    private Stream<Path> getFilesList() throws IOException {
        return Files.list(directory);
    }
}
