package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number % 2 == 0) {
                    System.out.println(number + " - число чётное");
                } else {
                    System.out.println(number + " - число нечётное");
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}