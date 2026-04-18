public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;
    private int maxSize;
    public MyLinkedList(int maxSize) {
        if (maxSize <= 0) {
            throw new NegativeSizeException(
                "Максимальний розмір списку має бути більше 0, отримано: " + maxSize
            );
        }
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.maxSize = maxSize;
    }
    public void addLast(Object data) {
        if (data == null) {
            throw new NullElementException("Не можна додати null елемент.");
        }
        if (size >= maxSize) {
            throw new ListOverflowException(
                "Список переповнений. Максимальний розмір: " + maxSize, maxSize
            );
        }
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
    public void addFirst(Object data) {
        if (data == null) {
            throw new NullElementException("Не можна додати null елемент.");
        }
        if (size >= maxSize) {
            throw new ListOverflowException(
                "Список переповнений. Максимальний розмір: " + maxSize, maxSize
            );
        }
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
    public void addMiddle(Object data) {
        if (data == null) {
            throw new NullElementException("Не можна додати null елемент.");
        }
        if (size >= maxSize) {
            throw new ListOverflowException(
                "Список переповнений. Максимальний розмір: " + maxSize, maxSize
            );
        }
        int middleIndex = size / 2;
        addAt(middleIndex, data);
    }
    public void addAt(int index, Object data) {
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        if (index < 0 || index > size) {
            throw new NegativeSizeException(
                "Невірний індекс для вставки: " + index + ". Допустимо: 0.." + size
            );
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
    public Object get(int index) throws InvalidIndexException, EmptyListException {
        if (size == 0) {
            throw new EmptyListException("Список порожній. Неможливо отримати елемент.");
        }
        if (index < 0 || index >= size) {
            throw new InvalidIndexException(
                "Невірний індекс: " + index + ". Допустимо: 0.." + (size - 1)
            );
        }
        return getNode(index).data;
    }
    public void remove(int index) throws InvalidIndexException, EmptyListException {
        if (size == 0) {
            throw new EmptyListException("Список порожній. Неможливо видалити елемент.");
        }
        if (index < 0 || index >= size) {
            throw new InvalidIndexException(
                "Невірний індекс: " + index + ". Допустимо: 0.." + (size - 1)
            );
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
    private Node getNode(int index) {
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    public int size() { return size; }
    public int capacity() { return maxSize; }
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