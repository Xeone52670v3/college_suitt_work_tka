package utils;

import java.io.*;

public class FileUtils {
    public static void save(String path, String[] dates, String[] entries, int count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < count; i++) {
                writer.write(dates[i] + ";" + entries[i]);
                writer.newLine();
            }
            System.out.println("Diary saved.");
        } catch (IOException e) {
            System.out.println("Error saving diary: " + e.getMessage());
        }
    }

    public static String[][] load(String path) {
        String[] dates = new String[50];
        String[] entries = new String[50];
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null && index < 50) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    dates[index] = parts[0];
                    entries[index] = parts[1];
                    index++;
                }
            }
            String[][] result = new String[2][index];
            System.arraycopy(dates, 0, result[0], 0, index);
            System.arraycopy(entries, 0, result[1], 0, index);
            return result;
        } catch (IOException e) {
            System.out.println("Error loading diary: " + e.getMessage());
            return null;
        }
    }
}
