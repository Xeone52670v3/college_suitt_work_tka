public class Administrator {
    private int adminId;
    private String adminName;
    private String adminContactExtension;
    private String[] adminResponsibilities;
    private boolean adminCashControlAccess;
    private boolean adminBookingManagement;
    private int adminAtmosphereScore;
    private String[] adminIncidentLog;

    public Administrator(int adminId, String adminName, String adminContactExtension,
                         String[] adminResponsibilities, boolean adminCashControlAccess,
                         boolean adminBookingManagement, int adminAtmosphereScore,
                         String[] adminIncidentLog) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminContactExtension = adminContactExtension;
        this.adminResponsibilities = adminResponsibilities;
        this.adminCashControlAccess = adminCashControlAccess;
        this.adminBookingManagement = adminBookingManagement;
        this.adminAtmosphereScore = adminAtmosphereScore;
        this.adminIncidentLog = adminIncidentLog;
    }

    public void handleIncident() {}
    public void manageBooking() {}
    public void controlCash() {}

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + adminId +
                ", name='" + adminName + '\'' +
                ", atmosphereScore=" + adminAtmosphereScore +
                '}';
    }
}
