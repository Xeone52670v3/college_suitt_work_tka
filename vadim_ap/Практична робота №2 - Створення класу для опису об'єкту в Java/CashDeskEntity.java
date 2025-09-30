public class CashDeskEntity {
    private double balance;

    public void addMoney(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Каса{баланс=" + balance + "}";
    }
}
