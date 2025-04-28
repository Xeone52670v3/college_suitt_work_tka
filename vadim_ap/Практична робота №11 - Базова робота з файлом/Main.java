import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int action = getUserChoice(userInput);

            switch (action) {
                case 1:
                    performWrite(userInput);
                    break;
                case 2:
                    performRead(userInput);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exit");
                    break;
                default:
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

    private static void performWrite(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Choose action (1 - overwrite file, 2 - append): ");
        int mode = scanner.nextInt();
        scanner.nextLine();

        try (FileWriter fileWriter = new FileWriter(filePath, mode == 2)) {
            System.out.println("Enter a line. Press enter after each line. Enter '!!!' to stop.");
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("!!!")) break;
                fileWriter.write(line + "\n");
            }
            System.out.println("Data saved.");
        } catch (IOException error) {
            System.out.println("An error occurred during writing: " + error.getMessage());
        }
    }

    private static void performRead(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Content of file: '" + filePath + "':");
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException error) {
            System.out.println("File not found: " + error.getMessage());
        } catch (IOException error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
    }
}
