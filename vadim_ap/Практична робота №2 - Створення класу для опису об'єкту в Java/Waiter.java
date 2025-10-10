public class Waiter {
    private int waiterId;
    private String waiterName;
    private int waiterSkillLevel;
    private int waiterSpeedScore;
    private int waiterPolitenessScore;
    private int waiterAttentivenessScore;
    private String[] waiterCurrentTasks;
    private int[] waiterAssignedTables;
    private String[] waiterSpecializations;

    public Waiter(int waiterId, String waiterName, int waiterSkillLevel, int waiterSpeedScore,
                  int waiterPolitenessScore, int waiterAttentivenessScore, String[] waiterCurrentTasks,
                  int[] waiterAssignedTables, String[] waiterSpecializations) {
        this.waiterId = waiterId;
        this.waiterName = waiterName;
        this.waiterSkillLevel = waiterSkillLevel;
        this.waiterSpeedScore = waiterSpeedScore;
        this.waiterPolitenessScore = waiterPolitenessScore;
        this.waiterAttentivenessScore = waiterAttentivenessScore;
        this.waiterCurrentTasks = waiterCurrentTasks;
        this.waiterAssignedTables = waiterAssignedTables;
        this.waiterSpecializations = waiterSpecializations;
    }

    public void takeOrder() {}
    public void serveTable() {}
    public void completeTask() {}

    @Override
    public String toString() {
        return "Waiter{" +
                "id=" + waiterId +
                ", name='" + waiterName + '\'' +
                ", skillLevel=" + waiterSkillLevel +
                ", speed=" + waiterSpeedScore +
                ", politeness=" + waiterPolitenessScore +
                ", attentiveness=" + waiterAttentivenessScore +
                '}';
    }
}
