package ua.practical10;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRegistry {

    private final HashSet<User> users = new HashSet<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public void registerUser(String login, String password) {
        User probe = new User(0, login, password);
        if (users.contains(probe)) {
            System.out.println("Користувач [" + login + "] вже є у списку");
        } else {
            User newUser = new User(idCounter.getAndIncrement(), login, password);
            users.add(newUser);
            System.out.println("Користувача [" + login + "] успішно зареєстровано (id=" + newUser.getId() + ")");
        }
    }

    public void loginUser(String login, String password) {
        Optional<User> found = users.stream()
                .filter(u -> u.getName().equals(login))
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
        Optional<User> found = users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst();

        if (found.isEmpty()) {
            System.out.println("Користувача з id=" + userId + " не знайдено");
            return;
        }

        User user = found.get();
        if (!user.isLoggedIn()) {
            System.out.println("Користувач [" + user.getName() + "] і так не в системі");
            return;
        }

        user.setLoggedIn(false);
        System.out.println("Користувач [" + user.getName() + "] вийшов із системи");
    }

    public boolean isUserRegistered(String login) {
        boolean result = users.stream().anyMatch(u -> u.getName().equals(login));
        System.out.println("Користувач [" + login + "] " + (result ? "зареєстрований" : "НЕ зареєстрований"));
        return result;
    }

    public void removeUser(int id) {
        Optional<User> found = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        if (found.isEmpty()) {
            System.out.println("Користувача з id=" + id + " не знайдено");
            return;
        }

        users.remove(found.get());
        System.out.println("Користувача [" + found.get().getName() + "] видалено");
    }

    public void printTotalUniqueUsers() {
        System.out.println("Кількість унікальних користувачів: " + users.size());
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Список користувачів порожній");
            return;
        }
        System.out.println("─── Список усіх користувачів (порядок не гарантований — HashSet) ───");
        for (User u : users) {
            System.out.println("  " + u);
        }
        System.out.println("────────────────────────────────────────────────────────────────────");
    }

    public Set<User> getUsers() {
        return users;
    }
}
