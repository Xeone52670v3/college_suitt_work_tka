import java.util.Arrays;

public class MainEntryPoint {
    public static void main(String[] args) {
        VisitorEntity visitor = new VisitorEntity("Iван", "вечеря");
        WaiterEntity waiter = new WaiterEntity("Ольга");
        CookEntity cook = new CookEntity("Петро");
        MenuEntity menu = new MenuEntity(Arrays.asList("Борщ", "Вареники", "Салат"));
        OrderEntity order = new OrderEntity(visitor, "Борщ");
        TableEntity table = new TableEntity(5);
        AdministratorEntity admin = new AdministratorEntity("Анна");
        StorageEntity storage = new StorageEntity();
        storage.addProduct("Картопля", 100);
        CashDeskEntity cashDesk = new CashDeskEntity();
        RestaurantSystemEntity system = new RestaurantSystemEntity("Система Ресторану");

        System.out.println(visitor);
        System.out.println(waiter);
        System.out.println(cook);
        System.out.println(menu);
        System.out.println(order);
        System.out.println(table);
        System.out.println(admin);
        System.out.println(storage);
        System.out.println(cashDesk);
        System.out.println(system);

        waiter.serveOrder();
        cook.prepareDish();
        admin.manage();
        system.runSystem();
    }
}
