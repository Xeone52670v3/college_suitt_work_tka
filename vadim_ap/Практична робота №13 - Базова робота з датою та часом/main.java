import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    private static final int MAX_ENTRIES = 50;
    private static String[] dates = new String[MAX_ENTRIES];
    private static String[] entries = new String[MAX_ENTRIES];
    private static int entryCount = 0;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMy Diary");
            System.out.println("1. Add Entry");
            System.out.println("2. Delete Entries by Date");
            System.out.println("3. View All Entries");
            System.out.println("4. Exit");
            System.out.print("Choose an action: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    addEntry();
                    break;
                case "2":
                    deleteEntriesByDate();
                    break;
                case "3":
                    viewAllEntries();
                    break;
                case "4":
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addEntry() {
        if (entryCount >= MAX_ENTRIES) {
            System.out.println("Diary is full!");
            return;
        }

        String date = getValidDate();
        if (date == null) return;

        System.out.println("Enter the entry text (empty line to finish):");
        String text = collectText();

        dates[entryCount] = date;
        entries[entryCount] = text;
        entryCount++;
        System.out.println("Entry added successfully!");
    }

    private static String getValidDate() {
        while (true) {
            System.out.print("Enter date (dd.MM.yyyy): ");
            String date = input.nextLine();
            if (isDateValid(date)) {
                return date;
            }
            System.out.println("Invalid date! Try again.");
        }
    }

    private static boolean isDateValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static String collectText() {
        StringBuilder textBuilder = new StringBuilder();
        while (true) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                if (textBuilder.length() == 0) {
                    System.out.println("Entry cannot be empty!");
                    continue;
                }
                break;
            }
            if (textBuilder.length() > 0) {
                textBuilder.append("\n");
            }
            textBuilder.append(line);
        }
        return textBuilder.toString();
    }

    private static void deleteEntriesByDate() {
        String dateToDelete = getValidDate();
        int deletedCount = 0;

        for (int i = 0; i < entryCount; i++) {
            if (dates[i].equals(dateToDelete)) {
                for (int j = i; j < entryCount - 1; j++) {
                    dates[j] = dates[j + 1];
                    entries[j] = entries[j + 1];
                }
                entryCount--;
                i--;
                deletedCount++;
            }
        }

        System.out.println(deletedCount > 0 ? "Entries deleted: " + deletedCount : "No entries found for this date");
    }

    private static void viewAllEntries() {
        if (entryCount == 0) {
            System.out.println("Diary is empty!");
            return;
        }

        System.out.println("\nAll entries:");
        for (int i = 0; i < entryCount; i++) {
            System.out.println("Date: " + dates[i]);
            System.out.println("Entry:\n" + entries[i].replace("\n", "\n"));
            System.out.println("-------------------");
        }
    }
}