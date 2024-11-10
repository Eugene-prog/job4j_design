package ru.job4j.io;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Dir {

    public static void outputCatalog(String name, int tab) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        File dir = new File(name);
        List<File> fileList = Arrays.asList(dir.listFiles());
        fileList.sort(Comparator.comparing(File::isDirectory).thenComparing(File::getName));
        for (File file : fileList) {
            if (file.isDirectory()) {
                System.out.printf("%s\\-- %s <DIR>%n", " ".repeat(tab), file.getName());
                outputCatalog(file.getAbsolutePath(), tab + 4);
            } else {
                System.out.printf("%s|-- %-15s %s %5d Kb%n",
                        " ".repeat(tab),
                        file.getName(),
                        sdf.format(file.lastModified()),
                        file.length() / 1024);
            }
        }
    }

    public static void main(String[] args) {
        String dirName = "c:/test";
        File file = new File(dirName);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("Total disk size : %s Gb%n", file.getTotalSpace() / 1073741824);
        System.out.printf("\\-- %s <DIR>%n",  file.getName());
        outputCatalog(dirName, 0);
    }
}