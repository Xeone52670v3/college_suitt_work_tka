package ua.practical11;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserRegistry {

    private final HashMap<UserIdentifier, User> users = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public void registerUser(String login, String password) {
        boolean exists = users.keySet().stream()
                .anyMatch(k -> k.getUsername().equals(login));
        if (exists) {
            System.out.println("Користувач [" + login + "] вже є у списку");
            return;
        }
        UserIdentifier uid = new UserIdentifier(idCounter.getAndIncrement(), login);
        User newUser = new User(uid, password);
        users.put(uid, newUser);
        System.out.println("Користувача [" + login + "] успішно зареєстровано (id=" + uid.getId() + ")");
    }

    public void loginUser(String login, String password) {
        Optional<User> found = users.values().stream()
                .filter(u -> u.getIdentifier().getUsername().equals(login))
                .findFirst();

        if (found.isEmpty() || !found.get().getPassword().equals(password)) {
            System.out.println("Неможливо ідентифікувати або аутентифікувати користувача");
            return;
        }
        User user = found.get();
        user.setLoggedIn(true);
        user.setLastLoginDate(LocalDateTime.now());
        System.out.println("Користувач [" + login + "] успішно увійшов у систему");
    }

    public void logoutUser(int userId) {
        Optional<User> found = users.values().stream()
                .filter(u -> u.getIdentifier().getId() == userId)
                .findFirst();

        if (found.isEmpty()) {
            System.out.println("Користувача з id=" + userId + " не знайдено");
            return;
        }
        User user = found.get();
        if (!user.isLoggedIn()) {
            System.out.println("Користувач [" + user.getIdentifier().getUsername() + "] і так не в системі");
            return;
        }
        user.setLoggedIn(false);
        System.out.println("Користувач [" + user.getIdentifier().getUsername() + "] вийшов із системи");
    }

    public boolean isUserRegistered(String login) {
        boolean result = users.keySet().stream()
                .anyMatch(k -> k.getUsername().equals(login));
        System.out.println("Користувач [" + login + "] " + (result ? "зареєстрований" : "НЕ зареєстрований"));
        return result;
    }

    public void removeUser(int id) {
        Optional<UserIdentifier> foundKey = users.keySet().stream()
                .filter(k -> k.getId() == id)
                .findFirst();

        if (foundKey.isEmpty()) {
            System.out.println("Користувача з id=" + id + " не знайдено");
            return;
        }
        User removed = users.remove(foundKey.get());
        System.out.println("Користувача [" + removed.getIdentifier().getUsername() + "] видалено");
    }

    public void printTotalUniqueUsers() {
        System.out.println("Кількість унікальних користувачів: " + users.size());
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Список користувачів порожній");
            return;
        }
        System.out.println("─── Всі користувачі (порядок не гарантований — HashMap) ───");
        users.values().forEach(u -> System.out.println("  " + u));
        System.out.println("───────────────────────────────────────────────────────────");
    }

    public LinkedList<User> getUserList() {
        return users.values().stream()
                .sorted(Comparator.comparingInt(u -> u.getIdentifier().getId()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<User> getInOrder(Comparator<User> comparator) {
        return users.values().stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<User> getFiltered(Predicate<User> predicate) {
        return users.values().stream()
                .filter(predicate)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Map<UserIdentifier, User> getUsers() {
        return users;
    }
}
