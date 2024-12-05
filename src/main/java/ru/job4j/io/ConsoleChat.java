package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        if (phrases.isEmpty()) {
            System.out.println("Список фраз пуст. Завершение программы.");
            return;
        }
        List<String> log = new ArrayList<>();
        boolean flagAnswer = true;
        boolean running = true;
        try (Scanner in = new Scanner(System.in)) {
            while (running) {
                String messageUser = in.nextLine().trim();
                log.add(messageUser);
                switch (messageUser.toLowerCase()) {
                    case OUT -> running = false;
                    case STOP -> flagAnswer = false;
                    case CONTINUE -> flagAnswer = true;
                    default -> {
                        if (flagAnswer) {
                            String phrase = getRandomPhrase(phrases);
                            System.out.println(phrase);
                            log.add(phrase);
                        }
                    }
                }
            }
        }
        saveLog(log);
        System.out.println("Диалог завершён. Лог сохранён.");
    }

    private String getRandomPhrase(List<String> phrases) {
        return phrases.get((int) (Math.random() * phrases.size()));
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(botAnswers, StandardCharsets.UTF_8, false))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/responses.txt", "data/answers_log.txt");
        consoleChat.run();
    }
}