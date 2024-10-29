package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    private static void checkAndPrintEvenOrOdd(String numberStr) {
        try {
            int number = Integer.parseInt(numberStr);
            if (number % 2 == 0) {
                System.out.println(number + " - число четное");
            } else {
                System.out.println(number + " - число нечетное");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder numberBuffer = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                char ch = (char) read;
                if (Character.isDigit(ch)) {
                    numberBuffer.append(ch);
                } else {
                    if (!numberBuffer.isEmpty()) {
                        checkAndPrintEvenOrOdd(numberBuffer.toString());
                        numberBuffer = new StringBuilder();
                    }
                }
            }
            if (!numberBuffer.isEmpty()) {
                checkAndPrintEvenOrOdd(numberBuffer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}