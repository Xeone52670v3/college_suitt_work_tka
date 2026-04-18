public class Variant23 {
    public static void main(String[] args) {
        double lambda = 0.1;
        double b = 0.6;
        double c = 2.4 * Math.pow(10, -4);
        double t = 15;
        double x = Math.exp(-lambda * b) * Math.cos(b);
        double y = b * Math.cos(t * Math.sqrt(c + b));
        System.out.println("Calculated x: " + x);
        System.out.println("Calculated y: " + y);
    }
}
