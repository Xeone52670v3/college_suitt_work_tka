
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер матрицы: ");
        int size = scanner.nextInt();

        int[][] matrix = new int[size][size];
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Определитель матрицы не реализован в данной программе.");
    }
}
