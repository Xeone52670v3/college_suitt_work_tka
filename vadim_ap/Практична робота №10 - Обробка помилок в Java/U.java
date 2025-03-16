import java.util.Scanner;

public class U {
    static class NEEx extends Exception {
        NEEx(String m) { super(m); }
    }

    static class PEEx extends Exception {
        PEEx(String m) { super(m); }
    }

    String n;
    String p;

    U(String u, String ps) {
        this.n = u;
        this.p = ps;
    }

    public static void main(String[] a) {
        final int maxU = 15;
        U[] usrs = new U[maxU];
        int cnt = 0;
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------");
            System.out.println("Меню:");
            System.out.println("-----------------------------");
            System.out.println("1 Додати користувача");
            System.out.println("2 Видалити користувача");
            System.out.println("3 Авторизацiя");
            System.out.println("4 Вийти");
            System.out.print("Ваш вибiр: ");

            int ch;
            try {
                ch = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неправильний ввiд!");
                continue;
            }

            if (ch == 4) {
                System.out.println("Вихiд...");
                break;
            } else if (ch == 1) {
                if (cnt >= maxU) {
                    System.out.println("Досягнуто максимум!");
                } else try {
                    System.out.print("Iм'я: ");
                    String u = s.nextLine();
                    valU(u);

                    for (int i = 0; i < cnt; i++)
                        if (usrs[i].n.equals(u))
                            throw new NEEx("Iм'я вже є");

                    System.out.print("Пароль: ");
                    String ps = s.nextLine();
                    valP(ps);

                    usrs[cnt++] = new U(u, ps);
                    System.out.println("Успiшно додано!");
                } catch (NEEx | PEEx e) {
                    System.out.println("Помилка: " + e.getMessage());
                }
            } else if (ch == 2) {
                System.out.print("Введіть iм'я для видалення: ");
                String u = s.nextLine();
                boolean f = false;
                for (int i = 0; i < cnt; i++) {
                    if (usrs[i].n.equals(u)) {
                        usrs[i] = usrs[cnt - 1];
                        usrs[--cnt] = null;
                        System.out.println("Видалено.");
                        f = true;
                        break;
                    }
                }
                if (!f) System.out.println("Не знайдено!");
            } else if (ch == 3) {
                System.out.print("Iм'я: ");
                String u = s.nextLine();
                System.out.print("Пароль: ");
                String ps = s.nextLine();
                boolean auth = false;
                for (int i = 0; i < cnt; i++) {
                    if (usrs[i].n.equals(u) && usrs[i].p.equals(ps)) {
                        System.out.println("Успiх!");
                        auth = true;
                        break;
                    }
                }
                if (!auth) System.out.println("Невiрнi данi!");
            } else {
                System.out.println("Невiрний вибiр!");
            }
        }
        s.close();
    }

    private static void valP(String ps) throws PEEx {
        if (ps.length() < 10) throw new PEEx("Пароль <10");
        if (ps.contains(" ")) throw new PEEx("Пробiл в паролi");

        int d = 0;
        boolean sp = false;
        String[] bad = {"admin", "pass", "password", "qwerty", "ytrewq", "123456", "111111", "222222",
                "333333", "444444", "555555", "666666", "777777", "888888", "999999", "000000"};
        String lc = ps.toLowerCase();

        for (String w : bad)
            if (lc.contains(w))
                throw new PEEx("Заборонене слово: " + w);

        for (char c : ps.toCharArray()) {
            if (!chkC(c)) throw new PEEx("Недопустимий символ");
            if (Character.isDigit(c)) d++;
            if (!Character.isLetterOrDigit(c)) sp = true;
        }

        if (d < 3) throw new PEEx("<3 цифр");
        if (!sp) throw new PEEx("Немає спецсимволу");
    }

    private static void valU(String u) throws NEEx {
        if (u.length() < 5) throw new NEEx("<5 символiв");
        if (u.contains(" ")) throw new NEEx("Є пробiл");
    }

    private static boolean chkC(char c) {
        return c >= 33 && c <= 126;
    }
}