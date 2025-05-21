import java.util.Scanner;

public class MenuHelper {
    public static int getUserChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Неправильний ввiд!");
            return -1;
        }
    }
}