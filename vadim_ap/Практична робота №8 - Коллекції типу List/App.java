public class App {
    public static void main(String[] args) {
        System.out.println("=== Власна реалізація LinkedList з виключеннями ===\n");
        System.out.println("-- Тест 1: створення списку з невірним розміром --");
        try {
            MyLinkedList badList = new MyLinkedList(-5);
        } catch (NegativeSizeException e) {
            System.out.println("Спіймано NegativeSizeException: " + e.getMessage());
        }
        MyLinkedList list = new MyLinkedList(3);
        System.out.println("\nСтворено список з maxSize = 3");
        System.out.println("\n-- Тест 2: додавання null елементу --");
        try {
            list.addLast(null);
        } catch (NullElementException e) {
            System.out.println("Спіймано NullElementException: " + e.getMessage());
        }
        System.out.println("\nДодаємо елементи: 10, 20, 30");
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        System.out.print("Список: ");
        list.print();
        System.out.println("\n-- Тест 3: переповнення списку --");
        try {
            list.addLast(40);
        } catch (ListOverflowException e) {
            System.out.println("Спіймано ListOverflowException: " + e.getMessage());
        }
        System.out.println("\n-- Тест 4: отримання елементу за невірним індексом --");
        try {
            Object el = list.get(99);
        } catch (InvalidIndexException e) {
            System.out.println("Спіймано InvalidIndexException: " + e.getMessage());
        } catch (EmptyListException e) {
            System.out.println("Спіймано EmptyListException: " + e.getMessage());
        }
        System.out.println("\n-- Тест 5: операція на порожньому списку --");
        list.clear();
        System.out.println("Список очищено.");
        try {
            list.remove(0);
        } catch (InvalidIndexException e) {
            System.out.println("Спіймано InvalidIndexException: " + e.getMessage());
        } catch (EmptyListException e) {
            System.out.println("Спіймано EmptyListException: " + e.getMessage());
        }
        System.out.println("\n=== Всі тести завершено ===");
    }
}