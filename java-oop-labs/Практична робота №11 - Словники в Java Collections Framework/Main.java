import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static final UserRegistry registry = new UserRegistry();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1"  -> doRegister();
                case "2"  -> doLogin();
                case "3"  -> doLogout();
                case "4"  -> doCheckRegistered();
                case "5"  -> doRemove();
                case "6"  -> registry.printTotalUniqueUsers();
                case "7"  -> registry.displayAllUsers();
                case "8"  -> doGetUserList();
                case "9"  -> doGetInOrder();
                case "10" -> doGetFiltered();
                case "0"  -> {
                    System.out.println("До побачення!");
                    running = false;
                }
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│                    МЕНЮ                      │");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("│  1.  Зареєструвати користувача                │");
        System.out.println("│  2.  Увійти в систему (login)                 │");
        System.out.println("│  3.  Вийти із системи (logout by id)          │");
        System.out.println("│  4.  Перевірити реєстрацію                    │");
        System.out.println("│  5.  Видалити користувача (by id)             │");
        System.out.println("│  6.  Кількість унікальних користувачів        │");
        System.out.println("│  7.  Показати всіх користувачів               │");
        System.out.println("│  8.  Список за id (getUserList)               │");
        System.out.println("│  9.  Список у заданому порядку (getInOrder)   │");
        System.out.println("│  10. Відфільтрований список (getFiltered)     │");
        System.out.println("│  0.  Вийти з програми                         │");
        System.out.println("└──────────────────────────────────────────────┘");
        System.out.print("Ваш вибір: ");
    }

    private static void doRegister() {
        System.out.print("Логін: ");
        String login = scanner.nextLine().trim();
        System.out.print("Пароль: ");
        String password = scanner.nextLine().trim();
        registry.registerUser(login, password);
    }
    
    private static void doLogin() {
        System.out.print("Логін: ");
        String login = scanner.nextLine().trim();
        System.out.print("Пароль: ");
        String password = scanner.nextLine().trim();
        registry.loginUser(login, password);
    }

    private static void doLogout() {
        System.out.print("ID користувача: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            registry.logoutUser(id);
        } catch (NumberFormatException e) {
            System.out.println("Невірний формат ID");
        }
    }
    
    private static void doCheckRegistered() {
        System.out.print("Логін для перевірки: ");
        String login = scanner.nextLine().trim();
        registry.isUserRegistered(login);
    }

    private static void doRemove() {
        System.out.print("ID користувача для видалення: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            registry.removeUser(id);
        } catch (NumberFormatException e) {
            System.out.println("Невірний формат ID");
        }
    }

    private static void doGetUserList() {
        LinkedList<User> list = registry.getUserList();
        if (list.isEmpty()) {
            System.out.println("Список порожній");
            return;
        }
        System.out.println("─── Список користувачів (сортування за id) ───");
        list.forEach(u -> System.out.println("  " + u));
        System.out.println("──────────────────────────────────────────────");
    }
    
    private static void doGetInOrder() {
        System.out.println("Оберіть порядок сортування:");
        System.out.println("  1 — за іменем (А→Я)");
        System.out.println("  2 — за іменем (Я→А)");
        System.out.println("  3 — за id (спадання)");
        System.out.print("Ваш вибір: ");
        String sub = scanner.nextLine().trim();

        LinkedList<User> list = switch (sub) {
            case "1" -> registry.getInOrder((a, b) ->
                    a.getIdentifier().getUsername().compareTo(b.getIdentifier().getUsername()));
            case "2" -> registry.getInOrder((a, b) ->
                    b.getIdentifier().getUsername().compareTo(a.getIdentifier().getUsername()));
            case "3" -> registry.getInOrder((a, b) ->
                    Integer.compare(b.getIdentifier().getId(), a.getIdentifier().getId()));
            default  -> { System.out.println("Невірний вибір"); yield new LinkedList<>(); }
        };

        if (list.isEmpty()) { System.out.println("Список порожній"); return; }
        System.out.println("─── Відсортований список ───");
        list.forEach(u -> System.out.println("  " + u));
        System.out.println("────────────────────────────");
    }

    private static void doGetFiltered() {
        System.out.println("Оберіть фільтр:");
        System.out.println("  1 — тільки авторизовані зараз");
        System.out.println("  2 — ніколи не заходили");
        System.out.println("  3 — зареєстровані більше хвилини тому (демо аналог \"більше року\")");
        System.out.print("Ваш вибір: ");
        String sub = scanner.nextLine().trim();

        LinkedList<User> list = switch (sub) {
            case "1" -> registry.getFiltered(User::isLoggedIn);
            case "2" -> registry.getFiltered(u -> u.getLastLoginDate() == null);
            case "3" -> registry.getFiltered(u ->
                    u.getRegistrationDate().isBefore(LocalDateTime.now().minusMinutes(1)));
            default  -> { System.out.println("Невірний вибір"); yield new LinkedList<>(); }
        };

        if (list.isEmpty()) { System.out.println("Жодного користувача не знайдено за цим фільтром"); return; }
        System.out.println("─── Відфільтровані користувачі ───");
        list.forEach(u -> System.out.println("  " + u));
        System.out.println("──────────────────────────────────");
    }
}
