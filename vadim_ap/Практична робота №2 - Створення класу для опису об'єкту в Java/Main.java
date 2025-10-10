import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Visitor visitor = new Visitor(1, "Олександр", "+380501234567", "Вечеря",
                new String[]{"Салат", "Стейк"}, new String[]{"Без цибулі"}, "Картка",
                "Все сподобалось", 120);

        Waiter waiter = new Waiter(1, "Іван", 8, 9, 10, 9,
                new String[]{"Прийняти замовлення"}, new int[]{5}, new String[]{"Винна карта"});

        Cook cook = new Cook(1, "Марія", 5, 4, new String[]{"Піца", "Паста"},
                "Гарячий цех", 9, 8, new int[]{101, 102});

        Menu menu = new Menu(1, "Основне меню", new String[]{"Піца", "Паста", "Салат"},
                "Італійська кухня", true, true, true, new int[]{1, 2});

        Order order = new Order(1, 1, 1, 5, new String[]{"Піца", "Кава"},
                "Прийняте", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 350.0);

        Table table = new Table(5, "Зайнятий", 4, "Біля вікна", 9,
                new String[]{"Розетка", "Підставка для сумки"});

        Administrator admin = new Administrator(1, "Наталія", "101",
                new String[]{"Контроль залу", "Бронювання"}, true, true, 9,
                new String[]{"Без інцидентів"});

        Storage storage = new Storage(1, "Основний склад", new String[]{"Борошно", "Сир"},
                new String[]{"Замовлення постачальнику"}, 5);

        CashDesk cashDesk = new CashDesk(1, new String[]{"Картка", "Готівка"},
                new String[]{"Транзакція №1"}, true, 8);

        RestaurantSystem system = new RestaurantSystem(1, "RestoControl Pro");

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
    }
}
