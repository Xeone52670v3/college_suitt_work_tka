
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество углов многоугольника: ");
        int n = scanner.nextInt();
        int[] angles = new int[n];
        int sumOfAngles = 0;
        System.out.println("Введите углы многоугольника: ");
        for (int i = 0; i < n; i++) {
            angles[i] = scanner.nextInt();
            sumOfAngles += angles[i];
        }
        int requiredSum = 180 * (n - 2);
        if (sumOfAngles == requiredSum) {
            System.out.println("Такой многоугольник может существовать.");
        } else {
            System.out.println("Такой многоугольник не может существовать.");
        }
    }
}
