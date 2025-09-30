public class TableEntity {
    private int tableNumber;
    private boolean occupied;

    public TableEntity(int tableNumber) {
        this.tableNumber = tableNumber;
        this.occupied = false;
    }

    public void occupy() {
        occupied = true;
    }

    public void free() {
        occupied = false;
    }

    @Override
    public String toString() {
        return "Стіл{номер столу=" + tableNumber + ", зайнятий=" + occupied + "}";
    }
}
