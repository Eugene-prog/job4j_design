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
    private final Map<String, FileProperty> filesMap = new HashMap<>();
    private final List<FileProperty> duplicateFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        String fileName = file.getFileName().toString();
        FileProperty currentFileProperty =
                new FileProperty(file.toFile().length(), file.toAbsolutePath().toString());
        if (filesMap.containsKey(fileName)) {
            FileProperty existingFileProperty = filesMap.get(fileName);
            if (currentFileProperty.equals(existingFileProperty)) {
                duplicateFiles.add(currentFileProperty);
            }
        } else {
            filesMap.put(fileName, currentFileProperty);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<FileProperty> getDuplicateFiles() {
        return duplicateFiles;
    }

    public void printDuplicateFiles() {
        if (!duplicateFiles.isEmpty()) {
            System.out.println("Duplicate found:");
            duplicateFiles.forEach(System.out::println);
        }
    }
}