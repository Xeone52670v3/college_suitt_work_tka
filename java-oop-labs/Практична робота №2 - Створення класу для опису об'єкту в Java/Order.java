import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private int orderVisitorId;
    private int orderWaiterId;
    private int orderTableId;
    private String[] orderItems;
    private String orderStatus;
    private LocalDateTime orderCreationTime;
    private LocalDateTime orderEstimatedCompletionTime;
    private double orderTotalAmount;

    public Order(int orderId, int orderVisitorId, int orderWaiterId, int orderTableId,
                 String[] orderItems, String orderStatus, LocalDateTime orderCreationTime,
                 LocalDateTime orderEstimatedCompletionTime, double orderTotalAmount) {
        this.orderId = orderId;
        this.orderVisitorId = orderVisitorId;
        this.orderWaiterId = orderWaiterId;
        this.orderTableId = orderTableId;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.orderCreationTime = orderCreationTime;
        this.orderEstimatedCompletionTime = orderEstimatedCompletionTime;
        this.orderTotalAmount = orderTotalAmount;
    }

    public void updateStatus() {}
    public void calculateTotal() {}
    public void printReceipt() {}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + orderId +
                ", status='" + orderStatus + '\'' +
                ", total=" + orderTotalAmount +
                '}';
    }
}
