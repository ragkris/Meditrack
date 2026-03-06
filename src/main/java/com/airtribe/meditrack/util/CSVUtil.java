package com.airtribe.meditrack.util;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static List<String[]> readCSV(String filePath) {

        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            // skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                rows.add(data);
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }

        return rows;
    }

    public static void writeCSV(String filePath, List<String> lines, String header) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write(header);
            bw.newLine();

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}
