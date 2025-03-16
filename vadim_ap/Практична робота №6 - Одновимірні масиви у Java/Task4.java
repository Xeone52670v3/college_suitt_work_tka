
public class Task4 {
    public static void main(String[] args) {
        System.out.println("Таблиця синусів від 0 до 90 градусів:");
        
        for (int i = 0; i <= 90; i++) {
            System.out.printf("%.2f ", Math.sin(Math.toRadians(i)));
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }
}
