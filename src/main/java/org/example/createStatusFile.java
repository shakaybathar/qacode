package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class createStatusFile {

    static void createStatus(String status) {

        calculateMonths cal = new calculateMonths();
        String currentTime = cal.getNow();
        String filePath = "C:/chromeDriver/file_export/status" + currentTime +".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(status);
            System.out.println("Status file created: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
