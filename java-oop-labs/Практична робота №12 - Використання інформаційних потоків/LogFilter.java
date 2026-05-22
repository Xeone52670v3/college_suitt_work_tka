import java.io.*;
import java.util.Scanner;

public class LogFilter {

    public static void filterLogs(String sourceFile, String targetFile, String[] logMarkers, int maxIndex) {
        try (
            FileReader fr = new FileReader(sourceFile);
            BufferedReader br = new BufferedReader(fr);
            Scanner fileScanner = new Scanner(br);

            FileWriter fw = new FileWriter(targetFile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)
        ) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                
                // Перевіряємо рядок на всі маркери від 0 до maxIndex
                for (int i = 0; i <= maxIndex; i++) {
                    if (line.contains(logMarkers[i])) {
                        pw.println(line);
                        break; // Знайшли збіг — переходимо до наступного рядка
                    }
                }
            }

            if (fileScanner.ioException() != null) {
                throw fileScanner.ioException();
            }

            System.out.println("[SUCCESS] Фільтрацію (з урахуванням пріоритету) завершено успішно.");

        } catch (IOException e) {
            System.err.println("[ERROR] Виникла помилка: " + e.getMessage());
        }
    }
}