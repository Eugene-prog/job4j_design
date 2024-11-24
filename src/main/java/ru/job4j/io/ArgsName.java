package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Error: This argument '" + key + "=' does not contain a value");
        }
        return value;
    }

    @Override
    public String toString() {
        return "ArgsName{" + "values=" + values + '}';
    }

    private void validateArgument(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '"
                    + arg + "' does not start with a '-' character");
        }
        int equalsIndex = arg.indexOf('=');
        if (equalsIndex == -1) {
            throw new IllegalArgumentException("Error: This argument '"
                    + arg + "' does not contain an equal sign");
        } else if (equalsIndex == 1) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
        } else if (equalsIndex == arg.length() - 1) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
        }

    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            validateArgument(arg);
            String key = arg.substring(1, arg.indexOf('=')).trim();
            String value = arg.substring(arg.indexOf('=') + 1).trim();
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}