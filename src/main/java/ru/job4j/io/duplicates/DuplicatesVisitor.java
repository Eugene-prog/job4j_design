package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> filesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        String fileName = file.getFileName().toString();
        FileProperty currentFileProperty = new FileProperty(file.toFile().length(), fileName);
        filesMap.putIfAbsent(currentFileProperty, new ArrayList<>());
        filesMap.get(currentFileProperty).add(file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicateFiles() {
        for (Map.Entry<FileProperty, List<Path>> entry : filesMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.printf("%s - %.2fMb (%d files)%n", entry.getKey().getName(),
                        entry.getKey().getSize() / (1024.0 * 1024.0), entry.getValue().size());
                entry.getValue().forEach(System.out::println);
            }
        }
    }
}