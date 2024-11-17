package ru.job4j.io.duplicates;

import java.nio.file.Paths;
import java.util.Objects;

public class FileProperty {

    private long size;

    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        String fileName = Paths.get(name).getFileName().toString();
        String thatFileName = Paths.get(that.name).getFileName().toString();
        return size == that.size && Objects.equals(fileName, thatFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, Paths.get(name).getFileName().toString());
    }

    @Override
    public String toString() {
        return String.format("FileProperty {path='%s', size=%d}", name, size);
    }
}