import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
                    performReadRange(userInput);
                    break;
                case 4:
                    performInsert(userInput);
                    break;
                case 5:
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
        System.out.println("\nSelect operation:");
        System.out.println("1. Append to file");
        System.out.println("2. Display file content");
        System.out.println("3. Display range of lines from file");
        System.out.println("4. Insert text into specific line");
        System.out.println("5. Exit");
        System.out.print("Enter number: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void performWrite(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Select action (1 - overwrite file, 2 - append): ");
        int mode = scanner.nextInt();
        scanner.nextLine();

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath, mode == 2))) {
            System.out.println("Enter line. Press enter after each line. Enter '!!!' to stop.");
            int lineNumber = 1;
            if (mode == 2) {
                try (BufferedReader lineCounter = new BufferedReader(new FileReader(filePath))) {
                    while (lineCounter.readLine() != null) {
                        lineNumber++;
                    }
                } catch (IOException e) {
                }
            }

            while (true) {
                String line = scanner.nextLine();
                if (line.equals("!!!")) break;
                fileWriter.write(lineNumber + ". " + line + "\n");
                lineNumber++;
            }
            System.out.println("Data saved.");
        } catch (IOException error) {
            System.out.println("Error during writing: " + error.getMessage());
        }
    }

    private static void performRead(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("\nContent of file '" + filePath + "':");
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException error) {
            System.out.println("File read error: " + error.getMessage());
        }
    }

    private static void performReadRange(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter starting line number: ");
        int start = scanner.nextInt();
        System.out.print("Enter ending line number: ");
        int end = scanner.nextInt();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("\nContent of file '" + filePath + "' (lines " + start + "-" + end + "):");
            String line;
            int currentLine = 1;
            while ((line = fileReader.readLine()) != null) {
                if (currentLine >= start && currentLine <= end) {
                    System.out.println(line);
                }
                currentLine++;
                if (currentLine > end) break;
            }
        } catch (IOException error) {
            System.out.println("File read error: " + error.getMessage());
        }
    }

    private static void performInsert(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter line number to insert: ");
        int insertLine = scanner.nextInt();
        scanner.nextLine();

        String[] allLines = new String[1000];
        int lineCount = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = fileReader.readLine()) != null && lineCount < allLines.length) {
                allLines[lineCount++] = line;
            }
        } catch (IOException error) {
            System.out.println("File read error: " + error.getMessage());
            return;
        }

        System.out.println("Enter text to insert (multiple lines). Press enter after each line. Enter '!!!' to stop.");
        String[] newLines = new String[100];
        int newLinesCount = 0;
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("!!!")) break;
            if (newLinesCount < newLines.length) {
                newLines[newLinesCount++] = line;
            }
        }

        if (insertLine < 1 || insertLine > lineCount + 1) {
            System.out.println("Invalid line number");
            return;
        }

        String[] result = new String[lineCount + newLinesCount];
        int resultIndex = 0;

        for (int i = 0; i < insertLine - 1; i++) {
            result[resultIndex++] = allLines[i];
        }

        for (int i = 0; i < newLinesCount; i++) {
            result[resultIndex++] = (insertLine + i) + ". " + newLines[i];
        }

        for (int i = insertLine - 1; i < lineCount; i++) {
            String oldLine = allLines[i];
            String text = oldLine.substring(oldLine.indexOf(". ") + 2);
            result[resultIndex++] = (insertLine + newLinesCount + (i - (insertLine - 1))) + ". " + text;
        }

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : result) {
                fileWriter.write(line + "\n");
            }
            System.out.println("Data inserted successfully.");
        } catch (IOException error) {
            System.out.println("File write error: " + error.getMessage());
        }
    }
}