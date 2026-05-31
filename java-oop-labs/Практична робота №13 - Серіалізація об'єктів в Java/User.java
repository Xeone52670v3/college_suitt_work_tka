import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UserIdentifier identifier;
    private String password;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginDate;
    private transient boolean isLoggedIn;

    public User(UserIdentifier identifier, String password) {
        this.identifier = identifier;
        this.password = password;
        this.registrationDate = LocalDateTime.now();
        this.lastLoginDate = null;
        this.isLoggedIn = false;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String loginDate = (lastLoginDate != null) ? lastLoginDate.format(fmt) : "ніколи";
        String regDate = registrationDate.format(fmt);
        return String.format("User{%s, loggedIn=%s, registered=%s, lastLogin=%s}",
                identifier, isLoggedIn, regDate, loginDate);
    }

    public UserIdentifier getIdentifier() { return identifier; }
    public void setIdentifier(UserIdentifier identifier) { this.identifier = identifier; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }

    public LocalDateTime getLastLoginDate() { return lastLoginDate; }
    public void setLastLoginDate(LocalDateTime lastLoginDate) { this.lastLoginDate = lastLoginDate; }

    public boolean isLoggedIn() { return isLoggedIn; }
    public void setLoggedIn(boolean loggedIn) { isLoggedIn = loggedIn; }
}
