import java.util.Scanner;
public class Variant23Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of a: ");
        double a = scanner.nextDouble();
        System.out.print("Enter the value of b: ");
        double b = scanner.nextDouble();
        System.out.print("Enter the value of x: ");
        double x = scanner.nextDouble();
        double f_x;
        if (x < 9) {
            f_x = (a * x + 7) / (5 * Math.sqrt(b * x + 1));
        } else if (x == 15) {
            f_x = Math.sin(x);
        } else {
            System.out.println("f(x) is not defined for the given x.");
            scanner.close();
            return;
        }
        System.out.println("Calculated f(x): " + f_x);
        scanner.close();
    }
}
