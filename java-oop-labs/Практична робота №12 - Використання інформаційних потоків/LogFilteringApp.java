import java.util.Scanner;

public class LogFilteringApp {

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        String[] logMarkers = {"[CRITICAL]", "[ERROR]", "[WARNING]", "[INFO]", "[DEBUG]"};

        System.out.print("Введіть шлях до вхідного файлу: ");
        String sourceFile = consoleScanner.nextLine().trim();

        System.out.print("Введіть шлях до вихідного файлу: ");
        String targetFile = consoleScanner.nextLine().trim();

        System.out.println("Оберіть мінімальний рівень логування (включить його та всі вищі за пріоритетом):");
        System.out.println("1. Крітичний [CRITICAL]\n2. Помилковий [ERROR]\n3. Потребує уваги [WARNING]\n4. Інформаційний [INFO]\n5. Відладочний [DEBUG]");
        System.out.print("Ваш вибір: ");
        
        int choiceIndex = Integer.parseInt(consoleScanner.nextLine().trim()) - 1;

        // Передаємо масив та обраний індекс користувача
        LogFilter.filterLogs(sourceFile, targetFile, logMarkers, choiceIndex);
        
        consoleScanner.close();
    }
}