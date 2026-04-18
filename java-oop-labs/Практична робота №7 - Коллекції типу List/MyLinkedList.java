public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 1. Додавання елементу в кінець
    public void addLast(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // 2. Додавання елементу в середину
    public void addMiddle(Object data) {
        int middleIndex = size / 2;
        addAt(middleIndex, data);
    }

    // 3. Додавання елементу на початок
    public void addFirst(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Допоміжний метод: додавання за індексом
    public void addAt(int index, Object data) {
        if (index < 0 || index > size) {
            System.out.println("Невірний індекс.");
            return;
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = getNode(index);
        Node prevNode = current.prev;
        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = current;
        current.prev = newNode;
        size++;
    }

    // 4. Отримання елементу за індексом
    public Object get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Невірний індекс.");
            return null;
        }
        return getNode(index).data;
    }

    // Допоміжний метод: отримання вузла за індексом
    private Node getNode(int index) {
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // 5. Метрики: size та capacity (у LinkedList capacity == size)
    public int size() {
        return size;
    }

    public int capacity() {
        // У LinkedList немає фіксованої ємності — capacity дорівнює size
        return size;
    }

    // 6. Видалення елементу за індексом
    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Невірний індекс.");
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return;
        }
        if (index == 0) {
            head = head.next;
            head.prev = null;
            size--;
            return;
        }
        if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return;
        }
        Node toRemove = getNode(index);
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        size--;
    }

    // 7. Очищення структури даних
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Виведення списку
    public void print() {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
