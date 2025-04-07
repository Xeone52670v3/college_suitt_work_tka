import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class A {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        boolean b = true;

        while (b) {
            m();
            int c = g(s);

            switch (c) {
                case 1:
                    w(s);
                    break;
                case 2:
                    r(s);
                    break;
                case 3:
                    b = false;
                    System.out.println("Вихiд");
                    break;
                default:
                    System.out.println("Невiрний вибiр");
            }
        }
        s.close();
    }

    private static void m() {
        System.out.println("Виберiть операцiю:\n");
        System.out.println("1. Дописати до файлу");
        System.out.println("2. Вивести вмiст файлу");
        System.out.println("3. Вихi");
        System.out.print("Введiть цифру: ");
    }

    private static int g(Scanner s) {
        while (!s.hasNextInt()) {
            System.out.println("Потрiбно ввести число");
            s.next();
        }
        return s.nextInt();
    }

    private static void w(Scanner s) {
        s.nextLine();
        System.out.print("Введiть шлях до файлу: ");
        String p = s.nextLine();

        System.out.print("Оберiть дiю (1 - перезаписати файл, 2 - дописати): ");
        int d = s.nextInt();
        s.nextLine();

        OutputStreamWriter o = null;
        try {
            o = new OutputStreamWriter(new FileOutputStream(p, d == 2));
            System.out.println("Введiть строку. Нажмiть enter пiсля кожного рядка. Введiть '!!!' для зупинки.");
            while (true) {
                String l = s.nextLine();
                if (l.equals("!!!")) break;
                o.write(l + "\n");
            }
            System.out.println("Данi збережно.");
        } catch (Exception e) {
            System.out.println("Виникла помикла пiд час запису: " + e.getMessage());
        } finally {
            if (o != null) {
                try {
                    o.close();
                } catch (Exception e) {
                    System.out.println("Помилка закриття файлу: " + e.getMessage());
                }
            }
        }
    }

    private static void r(Scanner s) {
        s.nextLine();
        System.out.print("Введiть шлях до файлу: ");
        String p = s.nextLine();

        InputStreamReader i = null;
        try {
            i = new InputStreamReader(new FileInputStream(p));
            System.out.println("Змiст файл:  '" + p + "':");
            int ch;
            while ((ch = i.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (Exception e) {
            System.out.println("Помилка зчитання файлу: " + e.getMessage());
        } finally {
            if (i != null) {
                try {
                    i.close();
                } catch (Exception e) {
                    System.out.println("Помилка закриття файлу: " + e.getMessage());
                }
            }
        }
    }
}