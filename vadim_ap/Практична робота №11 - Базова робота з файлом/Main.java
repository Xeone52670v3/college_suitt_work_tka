import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getUserChoice(userInput);

            if (choice == 1) {
                writeToFile(userInput);
            } else if (choice == 2) {
                readFromFile(userInput);
            } else if (choice == 3) {
                System.out.println("Exit");
                running = false;
            } else {
                System.out.println("Invalid choice");
            }
        }

        userInput.close();
    }

    private static void displayMenu() {
        System.out.println("Choose an operation:\n");
        System.out.println("1. Append to file");
        System.out.println("2. Display file content");
        System.out.println("3. Exit");
        System.out.print("Enter a number: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("You need to enter a number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void writeToFile(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Choose action (1 - overwrite file, 2 - append): ");
        int mode = 1;
        if (scanner.hasNextInt()) {
            mode = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Enter lines. Type '!!!' to stop.");

        String[] lines = new String[100];
        int count = 0;

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("!!!")) {
                break;
            }
            if (count < lines.length) {
                lines[count] = line;
                count++;
            }
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath, mode == 2);
            for (int i = 0; i < count; i++) {
                writer.write(lines[i] + "\n");
            }
            System.out.println("Data saved.");
        } catch (IOException e) {
            System.out.println("An error occurred during writing: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    private static void readFromFile(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);

            System.out.println("Content of file: '" + filePath + "':");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
}
