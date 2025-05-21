import java.util.Scanner;

public class UserManager {
    public static final int MAX_USERS = 15;
    public User[] users = new User[MAX_USERS];
    public int userCount = 0;

    public void addUser(Scanner scanner) {
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

            if (!password.matches(".*\d.*") || !password.matches(".*[!@#$%^&*()].*")) {
                throw new IllegalArgumentException("Пароль повинен містити хоча б 1 цифру та 1 спецсимвол");
            }

            String[] forbiddenWords = {"admin", "pass", "password", "qwerty", "123456"};
            for (String word : forbiddenWords) {
                if (password.toLowerCase().contains(word)) {
                    throw new IllegalArgumentException("Заборонене слово в паролi");
                }
            }

            users[userCount++] = new User(name, password);
            System.out.println("Успiшно додано!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public void deleteUser(Scanner scanner) {
        System.out.print("Введіть iм'я для видалення: ");
        String name = scanner.nextLine();
        boolean userFound = false;

        for (int i = 0; i < userCount; i++) {
            if (users[i].name.equals(name)) {
                users[i] = users[userCount - 1];
                users[userCount - 1] = null;
                userCount--;
                userFound = true;
                System.out.println("Видалено.");
                break;
            }
        }

        if (!userFound) {
            System.out.println("Не знайдено!");
        }
    }

    public void authenticateUser(Scanner scanner) {
        System.out.print("Iм'я: ");
        String name = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (users[i].name.equals(name) && users[i].password.equals(password)) {
                System.out.println("Успiх!");
                return;
            }
        }
        System.out.println("Невiрнi данi!");
    }
}