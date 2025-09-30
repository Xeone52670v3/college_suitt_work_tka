import java.util.List;

public class MenuEntity {
    private List<String> dishes;

    public MenuEntity(List<String> dishes) {
        this.dishes = dishes;
    }

    public List<String> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        return "Меню{страви=" + dishes + "}";
    }
}
