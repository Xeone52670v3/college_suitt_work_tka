import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyLinkedList list = null;
        System.out.println("=== Власна реалізація LinkedList ===");
        System.out.println("Введіть максимальний розмір списку:");
        try {
            int maxSize = scanner.nextInt();
            scanner.nextLine();
            list = new MyLinkedList(maxSize);
            System.out.println("Список створено. Максимальний розмір: " + maxSize);
        } catch (NegativeSizeException e) {
            System.out.println("Помилка: " + e.getMessage());
            System.out.println("Встановлено розмір за замовчуванням: 10");
            list = new MyLinkedList(10);
        }
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
            System.out.println("9: Запустити тести виключень");
            System.out.println("0: Вихід");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Введіть елемент:");
                    String el1 = scanner.nextLine();
                    try {
                        list.addLast(el1);
                        System.out.println("Додано в кінець.");
                    } catch (NullElementException | ListOverflowException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Введіть елемент:");
                    String el2 = scanner.nextLine();
                    try {
                        list.addMiddle(el2);
                        System.out.println("Додано в середину.");
                    } catch (NullElementException | ListOverflowException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Введіть елемент:");
                    String el3 = scanner.nextLine();
                    try {
                        list.addFirst(el3);
                        System.out.println("Додано на початок.");
                    } catch (NullElementException | ListOverflowException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Введіть індекс:");
                    int idx = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Object el = list.get(idx);
                        System.out.println("Елемент за індексом " + idx + ": " + el);
                    } catch (InvalidIndexException | EmptyListException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Розмір (size): " + list.size());
                    System.out.println("Ємність (capacity): " + list.capacity());
                    System.out.println("(У LinkedList capacity — максимальний розмір)");
                    break;
                case 6:
                    System.out.println("Введіть індекс для видалення:");
                    int removeIdx = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        list.remove(removeIdx);
                        System.out.println("Елемент видалено.");
                    } catch (InvalidIndexException | EmptyListException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Впевнені? (yes/no)");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        list.clear();
                        System.out.println("Список очищено.");
                    } else {
                        System.out.println("Скасовано.");
                    }
                    break;
                case 8:
                    System.out.print("Список: ");
                    list.print();
                    break;
                case 9:
                    System.out.println("\n=== Тести виключень ===");
                    try {
                        MyLinkedList badList = new MyLinkedList(-5);
                    } catch (NegativeSizeException e) {
                        System.out.println("NegativeSizeException: " + e.getMessage());
                    }
                    MyLinkedList testList = new MyLinkedList(3);
                    try {
                        testList.addLast(null);
                    } catch (NullElementException e) {
                        System.out.println("NullElementException: " + e.getMessage());
                    }
                    testList.addLast(1);
                    testList.addLast(2);
                    testList.addLast(3);
                    try {
                        testList.addLast(4);
                    } catch (ListOverflowException e) {
                        System.out.println("ListOverflowException: " + e.getMessage());
                    }
                    try {
                        testList.get(99);
                    } catch (InvalidIndexException e) {
                        System.out.println("InvalidIndexException: " + e.getMessage());
                    } catch (EmptyListException e) {
                        System.out.println("EmptyListException: " + e.getMessage());
                    }
                    testList.clear();
                    try {
                        testList.remove(0);
                    } catch (InvalidIndexException e) {
                        System.out.println("InvalidIndexException: " + e.getMessage());
                    } catch (EmptyListException e) {
                        System.out.println("EmptyListException: " + e.getMessage());
                    }
                    System.out.println("=== Тести завершено ===");
                    break;
                case 0:
                    System.out.println("Вихід...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }
}
