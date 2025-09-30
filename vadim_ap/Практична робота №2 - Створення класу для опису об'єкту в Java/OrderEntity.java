public class OrderEntity {
    private VisitorEntity visitor;
    private String dish;

    public OrderEntity(VisitorEntity visitor, String dish) {
        this.visitor = visitor;
        this.dish = dish;
    }

    public VisitorEntity getVisitor() {
        return visitor;
    }

    public String getDish() {
        return dish;
    }

    @Override
    public String toString() {
        return "Замовлення{відвідувач=" + visitor + ", страва='" + dish + "'}";
    }
}
