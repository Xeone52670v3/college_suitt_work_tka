public class Storage {
    private int storageId;
    private String storageName;
    private String[] storageProductList;
    private String[] storageSupplierOrders;
    private int storageMinimumThreshold;

    public Storage(int storageId, String storageName, String[] storageProductList,
                   String[] storageSupplierOrders, int storageMinimumThreshold) {
        this.storageId = storageId;
        this.storageName = storageName;
        this.storageProductList = storageProductList;
        this.storageSupplierOrders = storageSupplierOrders;
        this.storageMinimumThreshold = storageMinimumThreshold;
    }

    public void addProduct() {}
    public void removeProduct() {}
    public void checkStock() {}

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + storageId +
                ", name='" + storageName + '\'' +
                '}';
    }
}
