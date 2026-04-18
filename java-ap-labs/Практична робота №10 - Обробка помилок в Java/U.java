import java.util.Scanner;

public class U {

    private String name;
    private String password;

    U(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static void main(String[] args) {
        final int MAX_USERS = 15;
        U[] users = new U[MAX_USERS];
        int userCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------");
            System.out.println("Меню:");
            System.out.println("-----------------------------");
            System.out.println("1 Додати користувача");
            System.out.println("2 Видалити користувача");
            System.out.println("3 Авторизацiя");
            System.out.println("4 Вийти");
            System.out.print("Ваш вибiр: ");

            int choice = getUserChoice(scanner);
            if (choice == 4) {
                System.out.println("Вихiд...");
                break;
            }

            switch (choice) {
                case 1:
                    if (userCount < MAX_USERS) {
                        addUser(scanner, users, userCount++);
                    } else {
                        System.out.println("Досягнуто максимум користувачів!");
                    }
                    break;
                case 2:
                    deleteUser(scanner, users, userCount--);
                    break;
                case 3:
                    authenticateUser(scanner, users, userCount);
                    break;
                default:
                    System.out.println("Невiрний вибiр!");
            }
        }
        scanner.close();
    }

    private static int getUserChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Неправильний ввiд!");
            return -1;
        }
    }

    private static void addUser(Scanner scanner, U[] users, int userCount) {
        try {
            System.out.print("Iм'я: ");
            String name = scanner.nextLine();
            if (name.length() < 5 || name.contains(" ")) {
                throw new IllegalArgumentException("<5 символiв або пробiл в iменi");
            }

            for (int i = 0; i < userCount; i++) {
                if (users[i].name.equals(name)) {
                    throw new IllegalArgumentException("Iм'я вже є");
                }
            }

            System.out.print("Пароль: ");
            String password = scanner.nextLine();
            if (password.length() < 10 || password.contains(" ")) {
                throw new IllegalArgumentException("Пароль повинен бути >= 10 символів без пробілів");
            }

            if (!password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()].*")) {
                throw new IllegalArgumentException("Пароль повинен містити хоча б 1 цифру та 1 спецсимвол");
            }

            String[] forbiddenWords = {"admin", "pass", "password", "qwerty", "123456"};
            for (String word : forbiddenWords) {
                if (password.toLowerCase().contains(word)) {
                    throw new IllegalArgumentException("Заборонене слово в паролi");
                }
            }

            users[userCount] = new U(name, password);
            System.out.println("Успiшно додано!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void deleteUser(Scanner scanner, U[] users, int userCount) {
        System.out.print("Введіть iм'я для видалення: ");
        String name = scanner.nextLine();
        boolean userFound = false;

        for (int i = 0; i < userCount; i++) {
            if (users[i].name.equals(name)) {
                users[i] = users[userCount - 1];
                users[userCount - 1] = null;
                userFound = true;
                System.out.println("Видалено.");
                break;
            }
        }

        if (!userFound) {
            System.out.println("Не знайдено!");
        }
    }

    private static void authenticateUser(Scanner scanner, U[] users, int userCount) {
        System.out.print("Iм'я: ");
        String name = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        boolean authSuccess = false;

        for (int i = 0; i < userCount; i++) {
            if (users[i].name.equals(name) && users[i].password.equals(password)) {
                System.out.println("Успiх!");
                authSuccess = true;
                break;
            }
        }

        if (!authSuccess) {
            System.out.println("Невiрнi данi!");
        }
    }
}
