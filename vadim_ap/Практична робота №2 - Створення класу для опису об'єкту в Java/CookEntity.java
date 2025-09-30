public class CookEntity {
    private String cookName;

    public CookEntity(String cookName) {
        this.cookName = cookName;
    }

    public String getCookName() {
        return cookName;
    }

    public void prepareDish() {
        System.out.println("Кухар " + cookName + " готує страву.");
    }

    @Override
    public String toString() {
        return "CookEntity{ім'я='" + cookName + "'}";
    }
}
