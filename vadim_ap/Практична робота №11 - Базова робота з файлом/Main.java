import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile(scanner);
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Choose an operation:\n");
        System.out.println("1. Append to file");
        System.out.println("2. Display file content");
        System.out.println("3. Exit");
        System.out.print("Enter a number: ");
    }

    private static int getChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("You must enter a number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void writeToFile(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the file path: ");
        String path = scanner.nextLine();

        System.out.print("Choose an action (1 - overwrite file, 2 - append): ");
        int action = scanner.nextInt();
        scanner.nextLine();

        FileWriter writer = null;
        try {
            writer = new FileWriter(path, action == 2);
            System.out.println("Enter lines. Press enter after each line. Enter '!!!' to stop.");
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("!!!")) break;
                writer.write(line + "\n");
            }
            System.out.println("Data saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file: " + e.getMessage());
                }
            }
        }
    }

    private static void readFromFile(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the file path: ");
        String path = scanner.nextLine();

        FileReader reader = null;
        try {
            reader = new FileReader(path);
            System.out.println("File content of '" + path + "':");
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file: " + e.getMessage());
                }
            }
        }
    }
}