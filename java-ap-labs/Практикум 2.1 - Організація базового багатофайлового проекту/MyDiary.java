import utils.FileUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyDiary {
    private static final int MAX_ENTRIES = 50;
    private String[] dates = new String[MAX_ENTRIES];
    private String[] entries = new String[MAX_ENTRIES];
    private int entryCount = 0;

    private Scanner input = new Scanner(System.in);
    private SimpleDateFormat displayFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private SimpleDateFormat fileFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void addEntry() {
        if (entryCount >= MAX_ENTRIES) {
            System.out.println("Diary is full.");
            return;
        }
        System.out.print("Enter your diary entry: ");
        String entry = input.nextLine();
        String timestamp = fileFormat.format(new Date());
        dates[entryCount] = timestamp;
        entries[entryCount] = entry;
        entryCount++;
        System.out.println("Entry added.");
    }

    public void viewEntries() {
        for (int i = 0; i < entryCount; i++) {
            try {
                Date date = fileFormat.parse(dates[i]);
                System.out.println(displayFormat.format(date) + ": " + entries[i]);
            } catch (Exception e) {
                System.out.println(dates[i] + ": " + entries[i]);
            }
        }
    }

    public void searchEntry() {
        System.out.print("Enter keyword to search: ");
        String keyword = input.nextLine();
        boolean found = false;
        for (int i = 0; i < entryCount; i++) {
            if (entries[i].contains(keyword)) {
                try {
                    Date date = fileFormat.parse(dates[i]);
                    System.out.println(displayFormat.format(date) + ": " + entries[i]);
                    found = true;
                } catch (Exception e) {
                    System.out.println(dates[i] + ": " + entries[i]);
                }
            }
        }
        if (!found) {
            System.out.println("No entries found.");
        }
    }

    public void saveToFile(String path) {
        FileUtils.save(path, dates, entries, entryCount);
    }

    public void loadFromFile(String path) {
        String[][] data = FileUtils.load(path);
        if (data != null) {
            dates = data[0];
            entries = data[1];
            entryCount = dates.length;
            System.out.println("Diary loaded successfully.");
        } else {
            System.out.println("Failed to load diary.");
        }
    }
}
