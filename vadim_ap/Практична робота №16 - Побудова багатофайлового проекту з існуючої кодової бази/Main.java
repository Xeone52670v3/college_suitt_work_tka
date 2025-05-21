import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager manager = new UserManager();
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

            int choice = MenuHelper.getUserChoice(scanner);
            if (choice == 4) {
                System.out.println("Вихiд...");
                break;
            }

            switch (choice) {
                case 1 -> manager.addUser(scanner);
                case 2 -> manager.deleteUser(scanner);
                case 3 -> manager.authenticateUser(scanner);
                default -> System.out.println("Невiрний вибiр!");
            }
        }
        scanner.close();
    }
}