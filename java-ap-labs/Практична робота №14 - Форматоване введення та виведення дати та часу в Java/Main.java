import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final int MAX_ENTRIES = 50;
    private static String[] dates = new String[MAX_ENTRIES];
    private static String[] entries = new String[MAX_ENTRIES];
    private static int entryCount = 0;
    private static Scanner input = new Scanner(System.in);
    private static SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println("Restore diary from file? (yes/no)");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the file path: ");
            String filePath = input.nextLine();
            loadFromFile(filePath);
        } else {
            System.out.println("Created a new diary.");
            chooseDateFormat();
        }

        while (true) {
            System.out.println("\nMy Diary");
            System.out.println("1. Add entry");
            System.out.println("2. Delete entries by date");
            System.out.println("3. View all entries");
            System.out.println("4. Change date format");
            System.out.println("5. Exit");
            System.out.print("Choose an action: ");
            choice = input.nextLine();

            switch (choice) {
                case "1":
                    addEntry();
                    break;
                case "2":
                    deleteByDate();
                    break;
                case "3":
                    showAllEntries();
                    break;
                case "4":
                    chooseDateFormat();
                    break;
                case "5":
                    saveToFile();
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void chooseDateFormat() {
        System.out.println("\nChoose a date format:");
        System.out.println("1. dd.MM.yyyy HH:mm");
        System.out.println("2. MM/dd/yyyy hh:mm a");
        System.out.println("3. yyyy-MM-dd HH:mm:ss");
        System.out.println("4. Enter your own format");
        System.out.print("Your choice: ");
        String formatChoice = input.nextLine();

        try {
            switch (formatChoice) {
                case "1":
                    displayDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    break;
                case "2":
                    displayDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                    break;
                case "3":
                    displayDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case "4":
                    System.out.print("Enter your format (e.g., dd/MM/yyyy HH:mm): ");
                    String customFormat = input.nextLine();
                    displayDateFormat = new SimpleDateFormat(customFormat);
                    break;
                default:
                    System.out.println("Invalid choice, using default format.");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid format! Using default format.");
            displayDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        }
    }

    private static void addEntry() {
        if (entryCount >= MAX_ENTRIES) {
            System.out.println("Diary is full!");
            return;
        }

        String date = getValidDate();
        if (date == null) return;

        System.out.println("Enter the text of the entry (empty line to finish):");
        String text = collectText();

        dates[entryCount] = date;
        entries[entryCount] = text;
        entryCount++;
        System.out.println("Entry added successfully!");
    }

    private static String getValidDate() {
        while (true) {
            System.out.print("Enter date and time (in the chosen format): ");
            String enteredDate = input.nextLine();
            try {
                Date dateObject = displayDateFormat.parse(enteredDate);
                return fileDateFormat.format(dateObject);
            } catch (ParseException exception) {
                System.out.println("Incorrect date or time! Try again.");
            }
        }
    }

    private static String collectText() {
        StringBuilder text = new StringBuilder();
        while (true) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                if (text.length() == 0) {
                    System.out.println("Entry cannot be empty!");
                    continue;
                }
                break;
            }
            if (text.length() > 0) {
                text.append("\n");
            }
            text.append(line);
        }
        return text.toString();
    }

    private static void deleteByDate() {
        System.out.print("Enter the date to delete (in the chosen format): ");
        String enteredDate = input.nextLine();
        int deletedCount = 0;

        try {
            Date dateToDelete = displayDateFormat.parse(enteredDate);
            String fileDateToDelete = fileDateFormat.format(dateToDelete);

            for (int i = 0; i < entryCount; i++) {
                if (dates[i].equals(fileDateToDelete)) {
                    for (int j = i; j < entryCount - 1; j++) {
                        dates[j] = dates[j + 1];
                        entries[j] = entries[j + 1];
                    }
                    entryCount--;
                    i--;
                    deletedCount++;
                }
            }
        } catch (ParseException exception) {
            System.out.println("Incorrect date!");
            return;
        }

        System.out.println(deletedCount > 0 ? "Deleted entries: " + deletedCount : "No entries found");
    }

    private static void showAllEntries() {
        if (entryCount == 0) {
            System.out.println("Diary is empty!");
            return;
        }

        System.out.println("\nAll entries:");
        for (int i = 0; i < entryCount; i++) {
            try {
                Date dateObject = fileDateFormat.parse(dates[i]);
                String formattedDate = displayDateFormat.format(dateObject);
                System.out.println("Date: " + formattedDate);
                System.out.println("Entry:\n" + entries[i].replace("\n", "\n"));
                System.out.println("-------------------");
            } catch (ParseException exception) {
                System.out.println("Error formatting date for entry #" + (i + 1));
            }
        }
    }

    private static void saveToFile() {
        System.out.print("Save diary? (yes/no): ");
        String choice = input.nextLine().trim();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the file path: ");
            String path = input.nextLine().trim();

            if (path.isEmpty()) {
                System.out.println("Incorrect path!");
                return;
            }

            File file = new File(path);
            try {
                File directory = file.getParentFile();
                if (directory != null && !directory.exists()) {
                    directory.mkdirs();
                }

                try (PrintWriter writer = new PrintWriter(file)) {
                    for (int i = 0; i < entryCount; i++) {
                        writer.println(dates[i]);
                        writer.println(entries[i]);
                        writer.println();
                    }
                    System.out.println("Successfully saved to file: " + file.getAbsolutePath());
                }
            } catch (IOException | SecurityException e) {
                System.out.println("Saving error: " + e.getMessage());
            }
        }
    }

    private static void loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null && entryCount < MAX_ENTRIES) {
                if (line.trim().isEmpty()) continue;

                String dateFromFile = line;
                StringBuilder entryText = new StringBuilder();
                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    if (entryText.length() > 0) {
                        entryText.append("\n");
                    }
                    entryText.append(line);
                }

                dates[entryCount] = dateFromFile;
                entries[entryCount] = entryText.toString();
                entryCount++;
            }
            System.out.println("Diary loaded successfully! Entries: " + entryCount);
        } catch (IOException exception) {
            System.out.println("Error loading file: " + exception.getMessage());
        }
    }
}