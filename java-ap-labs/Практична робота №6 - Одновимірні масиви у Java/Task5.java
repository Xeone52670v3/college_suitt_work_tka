
import java.util.Scanner;
import java.util.Random;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введіть розмір масиву: ");
        int size = scanner.nextInt();
        
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // Генерація випадкового числа від 0 до 99
        }
        
        System.out.print("Перевірити на зростання (введіть 1) або спадання (введіть 2): ");
        int choice = scanner.nextInt();

        boolean isSorted = true;
        if (choice == 1) { // Перевірка на зростання
            for (int i = 0; i < size - 1; i++) {
                if (array[i] > array[i + 1]) {
                    isSorted = false;
                    break;
                }
            }
            System.out.println("Масив " + (isSorted ? "відсортований за зростанням." : "не відсортований за зростанням."));
        } else if (choice == 2) { // Перевірка на спадання
            for (int i = 0; i < size - 1; i++) {
                if (array[i] < array[i + 1]) {
                    isSorted = false;
                    break;
                }
            }
            System.out.println("Масив " + (isSorted ? "відсортований за спаданням." : "не відсортований за спаданням."));
        } else {
            System.out.println("Невірний вибір.");
        }
    }
}
