package ru.job4j.io;

import java.io.*;
import java.time.LocalTime;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String line;
            boolean isWork = true;
            while ((line = input.readLine()) != null) {
                int index = line.indexOf(' ');
                int status = Integer.parseInt(line.substring(0, index).trim());
                LocalTime time = LocalTime.parse(line.substring(index + 1).trim());
                if (status >= 400 && isWork) {
                    output.print(time + ";");
                    isWork = false;
                } else if (status < 400 && !isWork) {
                    output.println(time + ";");
                    isWork = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}