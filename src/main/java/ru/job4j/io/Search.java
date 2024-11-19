package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не заданы параметры запуска");
        }
        try {
            Path start = Paths.get(args[0]);
            if (!Files.exists(start)) {
                throw new IllegalArgumentException("Указанный путь не существует: " + args[0]);
            }
            if (!Files.isDirectory(start)) {
                throw new IllegalArgumentException("Указанный путь не является директорией: " + args[0]);
            }
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Указан некорректный путь: " + args[0], e);
        }

        Path start = Paths.get(args[0]);
        String fileExtension = args[1];
        if (fileExtension.isEmpty()) {
            throw new IllegalArgumentException("Расширение файла не должно быть пустым.");
        }
        if (fileExtension.startsWith(".")) {
            throw new IllegalArgumentException("Расширение файла не должно начинаться с точки.");
        }
        search(start, path -> path.toFile().getName().endsWith("." + fileExtension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}