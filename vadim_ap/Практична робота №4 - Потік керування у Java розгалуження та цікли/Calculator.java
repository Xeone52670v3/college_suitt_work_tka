
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення двох чисел
        System.out.print("Введіть перше число: ");
        double num1 = scanner.nextDouble();

        System.out.print("Введіть друге число: ");
        double num2 = scanner.nextDouble();

        // Введення оператора
        System.out.print("Введіть операцію (+, -, *, /): ");
        char operation = scanner.next().charAt(0);

        // Виконання арифметичної операції на основі введеного оператора
        switch (operation) {
            case '+':
                System.out.println("Результат: " + (num1 + num2));
                break;
            case '-':
                System.out.println("Результат: " + (num1 - num2));
                break;
            case '*':
                System.out.println("Результат: " + (num1 * num2));
                break;
            case '/':
                if (num2 != 0) {
                    System.out.println("Результат: " + (num1 / num2));
                } else {
                    System.out.println("Помилка: ділення на нуль!");
                }
                break;
            default:
                System.out.println("Невірний оператор!");
                break;
        }

        scanner.close();
    }
}
