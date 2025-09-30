public class WaiterEntity {
    private String waiterName;

    public WaiterEntity(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void serveOrder() {
        System.out.println("Офіціант " + waiterName + " обслуговує замовлення.");
    }

    @Override
    public String toString() {
        return "WaiterEntity{ім'я='" + waiterName + "'}";
    }
}
