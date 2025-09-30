public class RestaurantSystemEntity {
    private String systemName;

    public RestaurantSystemEntity(String systemName) {
        this.systemName = systemName;
    }

    public void runSystem() {
        System.out.println("Система " + systemName + " працює.");
    }

    @Override
    public String toString() {
        return "RestaurantSystemEntity{назва='" + systemName + "'}";
    }
}
