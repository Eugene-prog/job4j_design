package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                line = line.trim();
                if (!line.contains("#") || line.isEmpty()) {
                    int index = line.indexOf('=');
                    if (index == -1 || index == 0 || index == line.length() - 1) {
                        throw new IllegalArgumentException("Неверный формат строки в файле: " + line);
                    }
                    String key = line.substring(0, index).trim();
                    String value = line.substring(index + 1).trim();
                    if (key.isEmpty() || value.isEmpty()) {
                        throw new IllegalArgumentException("Неверный формат строки в файле: " + line);
                    } else {
                        values.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        String filePropName = "data/app.properties";
        Config config = new Config(filePropName);
        config.load();
        System.out.println(config.values);
    }
}