public class Visitor {
    private int visitorId;
    private String visitorName;
    private String visitorContactPhone;
    private String visitorGoal;
    private String[] visitorOrder;
    private String[] visitorPreferences;
    private String visitorPaymentMethod;
    private String visitorFeedback;
    private int visitorLoyaltyPoints;

    public Visitor(int visitorId, String visitorName, String visitorContactPhone, String visitorGoal,
                   String[] visitorOrder, String[] visitorPreferences, String visitorPaymentMethod,
                   String visitorFeedback, int visitorLoyaltyPoints) {
        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.visitorContactPhone = visitorContactPhone;
        this.visitorGoal = visitorGoal;
        this.visitorOrder = visitorOrder;
        this.visitorPreferences = visitorPreferences;
        this.visitorPaymentMethod = visitorPaymentMethod;
        this.visitorFeedback = visitorFeedback;
        this.visitorLoyaltyPoints = visitorLoyaltyPoints;
    }

    // Порожні методи-заглушки
    public void makeOrder() {}
    public void giveFeedback() {}
    public void useLoyaltyPoints() {}

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + visitorId +
                ", name='" + visitorName + '\'' +
                ", phone='" + visitorContactPhone + '\'' +
                ", goal='" + visitorGoal + '\'' +
                ", paymentMethod='" + visitorPaymentMethod + '\'' +
                ", feedback='" + visitorFeedback + '\'' +
                ", loyaltyPoints=" + visitorLoyaltyPoints +
                '}';
    }
}
//

