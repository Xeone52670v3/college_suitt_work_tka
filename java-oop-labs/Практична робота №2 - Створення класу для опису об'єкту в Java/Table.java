public class Table {
    private int tableId;
    private String tableStatus;
    private int tableCapacity;
    private String tableLocation;
    private int tableCleanlinessScore;
    private String[] tableAmenities;

    public Table(int tableId, String tableStatus, int tableCapacity, String tableLocation,
                 int tableCleanlinessScore, String[] tableAmenities) {
        this.tableId = tableId;
        this.tableStatus = tableStatus;
        this.tableCapacity = tableCapacity;
        this.tableLocation = tableLocation;
        this.tableCleanlinessScore = tableCleanlinessScore;
        this.tableAmenities = tableAmenities;
    }

    public void reserveTable() {}
    public void freeTable() {}
    public void cleanTable() {}

    @Override
    public String toString() {
        return "Table{" +
                "id=" + tableId +
                ", status='" + tableStatus + '\'' +
                ", capacity=" + tableCapacity +
                ", location='" + tableLocation + '\'' +
                '}';
    }
}
