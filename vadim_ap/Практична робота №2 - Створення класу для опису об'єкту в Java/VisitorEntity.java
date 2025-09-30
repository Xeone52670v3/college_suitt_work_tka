public class VisitorEntity {
    private String visitorName;
    private String visitorGoal;

    public VisitorEntity(String visitorName, String visitorGoal) {
        this.visitorName = visitorName;
        this.visitorGoal = visitorGoal;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public String getVisitorGoal() {
        return visitorGoal;
    }

    @Override
    public String toString() {
        return "Відвідувач{ім'я='" + visitorName + "', мета='" + visitorGoal + "'}";
    }
}
