import java.util.Scanner;

public class GCD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите два целых числа: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        System.out.println("Наибольший общий делитель: " + a);
        scanner.close();
    }
}