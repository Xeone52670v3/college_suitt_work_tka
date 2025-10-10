public class Cook {
    private int cookId;
    private String cookName;
    private int cookExperienceYears;
    private int cookExperienceLevel;
    private String[] cookSpecialityDishes;
    private String cookWorkstation;
    private int cookDisciplineScore;
    private int cookSpeedScore;
    private int[] cookCurrentOrders;

    public Cook(int cookId, String cookName, int cookExperienceYears, int cookExperienceLevel,
                String[] cookSpecialityDishes, String cookWorkstation, int cookDisciplineScore,
                int cookSpeedScore, int[] cookCurrentOrders) {
        this.cookId = cookId;
        this.cookName = cookName;
        this.cookExperienceYears = cookExperienceYears;
        this.cookExperienceLevel = cookExperienceLevel;
        this.cookSpecialityDishes = cookSpecialityDishes;
        this.cookWorkstation = cookWorkstation;
        this.cookDisciplineScore = cookDisciplineScore;
        this.cookSpeedScore = cookSpeedScore;
        this.cookCurrentOrders = cookCurrentOrders;
    }

    public void prepareDish() {}
    public void updateOrderStatus() {}
    public void cleanWorkstation() {}

    @Override
    public String toString() {
        return "Cook{" +
                "id=" + cookId +
                ", name='" + cookName + '\'' +
                ", expYears=" + cookExperienceYears +
                ", level=" + cookExperienceLevel +
                ", workstation='" + cookWorkstation + '\'' +
                '}';
    }
}
