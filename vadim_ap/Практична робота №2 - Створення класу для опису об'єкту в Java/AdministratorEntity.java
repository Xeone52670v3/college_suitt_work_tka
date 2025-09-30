public class AdministratorEntity {
    private String adminName;

    public AdministratorEntity(String adminName) {
        this.adminName = adminName;
    }

    public void manage() {
        System.out.println("Адміністратор " + adminName + " керує рестораном.");
    }

    @Override
    public String toString() {
        return "AdministratorEntity{ім'я='" + adminName + "'}";
    }
}
