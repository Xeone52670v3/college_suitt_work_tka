import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyLinkedList list = new MyLinkedList();

        System.out.println("=== Власна реалізація LinkedList ===");

        while (true) {
            System.out.println("\nОберіть дію:");
            System.out.println("1: Додати в кінець");
            System.out.println("2: Додати в середину");
            System.out.println("3: Додати на початок");
            System.out.println("4: Отримати елемент за індексом");
            System.out.println("5: Розмір та ємність списку");
            System.out.println("6: Видалити елемент за індексом");
            System.out.println("7: Очистити список");
            System.out.println("8: Вивести список");
            System.out.println("9: Вихід");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введіть елемент для додавання в кінець:");
                    String elementEnd = scanner.nextLine();
                    list.addLast(elementEnd);
                    System.out.println("Елемент додано в кінець.");
                    break;

                case 2:
                    System.out.println("Введіть елемент для додавання в середину:");
                    String elementMiddle = scanner.nextLine();
                    list.addMiddle(elementMiddle);
                    System.out.println("Елемент додано в середину.");
                    break;

                case 3:
                    System.out.println("Введіть елемент для додавання на початок:");
                    String elementBeginning = scanner.nextLine();
                    list.addFirst(elementBeginning);
                    System.out.println("Елемент додано на початок.");
                    break;

                case 4:
                    System.out.println("Введіть індекс:");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    Object element = list.get(index);
                    if (element != null) {
                        System.out.println("Елемент за індексом " + index + ": " + element);
                    }
                    break;

                case 5:
                    System.out.println("Розмір (size): " + list.size());
                    System.out.println("Ємність (capacity): " + list.capacity());
                    System.out.println("(У LinkedList capacity завжди дорівнює size)");
                    break;

                case 6:
                    System.out.println("Введіть індекс для видалення:");
                    int removeIndex = scanner.nextInt();
                    scanner.nextLine();
                    list.remove(removeIndex);
                    System.out.println("Елемент видалено.");
                    break;

                case 7:
                    System.out.println("Ви впевнені, що хочете видалити всі елементи? (yes/no)");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        list.clear();
                        System.out.println("Список очищено.");
                    } else {
                        System.out.println("Операцію скасовано.");
                    }
                    break;

                case 8:
                    System.out.print("Список: ");
                    list.print();
                    break;

                case 9:
                    System.out.println("Вихід...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
