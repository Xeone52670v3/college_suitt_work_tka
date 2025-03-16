
import java.util.Scanner;
import java.util.Random;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введіть розмір масиву: ");
        int size = scanner.nextInt();
        
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // Генерація випадкового числа від 0 до 99
        }
        
        System.out.print("Введіть значення для пошуку: ");
        int searchValue = scanner.nextInt();
        
        System.out.print("Введіть значення для заміни: ");
        int replaceValue = scanner.nextInt();
        
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == searchValue) {
                array[i] = replaceValue;
                found = true;
            }
        }

        if (found) {
            System.out.println("Масив після заміни:");
            for (int value : array) {
                System.out.print(value + " ");
            }
        } else {
            System.out.println("Значення не знайдено в масиві.");
        }
    }
}
