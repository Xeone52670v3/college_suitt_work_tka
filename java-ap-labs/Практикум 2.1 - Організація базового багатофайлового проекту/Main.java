import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyDiary diary = new MyDiary();
        Scanner input = new Scanner(System.in);

        System.out.println("Restore diary from file? (yes/no)");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the file path: ");
            String filePath = input.nextLine();
            diary.loadFromFile(filePath);
        }

        while (true) {
            System.out.println("\n1. Add Entry\n2. View Entries\n3. Search Entry\n4. Save to File\n5. Exit");
            System.out.print("Choose an option: ");
            String option = input.nextLine();

            switch (option) {
                case "1":
                    diary.addEntry();
                    break;
                case "2":
                    diary.viewEntries();
                    break;
                case "3":
                    diary.searchEntry();
                    break;
                case "4":
                    System.out.print("Enter file path to save: ");
                    String path = input.nextLine();
                    diary.saveToFile(path);
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
