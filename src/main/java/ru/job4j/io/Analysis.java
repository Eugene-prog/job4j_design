package ru.job4j.io;

import java.io.*;
import java.time.LocalTime;

public class Analysis {

    public void unavailable(String source, String target) {
        try (PrintWriter output =
                     new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean isWork = true;
            StringBuilder result = new StringBuilder();
            try (BufferedReader input = new BufferedReader(new FileReader(source))) {
                String line;
                while ((line = input.readLine()) != null) {
                    line = line.trim();
                    int index = line.indexOf(' ');
                    if (index == -1) {
                        continue;
                    }
                    int status = Integer.parseInt(line.substring(0, index).trim());
                    LocalTime t = LocalTime.parse(line.substring(index + 1).trim());
                    if ((status >= 400) && (isWork)) {
                        isWork = false;
                        result.append(t).append(";");
                    } else if ((status < 400) && (!isWork)) {
                        isWork = true;
                        result.append(t).append(";");
                        output.println(result);
                        result = new StringBuilder();
                    }
                }
                if (!isWork && !result.isEmpty()) {
                    result.append("unknown").append(";");
                    output.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
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