import java.util.HashMap;
import java.util.Map;

public class StorageEntity {
    private Map<String, Integer> products = new HashMap<>();

    public void addProduct(String name, int quantity) {
        products.put(name, quantity);
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "СутністьСкладу{продукти=" + products + "}";
    }
}
