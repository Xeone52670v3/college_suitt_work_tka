public class CashDesk {
    private int cashdeskId;
    private String[] cashdeskAcceptedPaymentMethods;
    private String[] cashdeskDailyTransactions;
    private boolean cashdeskIsOnline;
    private int cashdeskSecurityLevel;

    public CashDesk(int cashdeskId, String[] cashdeskAcceptedPaymentMethods,
                    String[] cashdeskDailyTransactions, boolean cashdeskIsOnline,
                    int cashdeskSecurityLevel) {
        this.cashdeskId = cashdeskId;
        this.cashdeskAcceptedPaymentMethods = cashdeskAcceptedPaymentMethods;
        this.cashdeskDailyTransactions = cashdeskDailyTransactions;
        this.cashdeskIsOnline = cashdeskIsOnline;
        this.cashdeskSecurityLevel = cashdeskSecurityLevel;
    }

    public void processPayment() {}
    public void generateReport() {}
    public void backupData() {}

    @Override
    public String toString() {
        return "CashDesk{" +
                "id=" + cashdeskId +
                ", online=" + cashdeskIsOnline +
                ", securityLevel=" + cashdeskSecurityLevel +
                '}';
    }
}
