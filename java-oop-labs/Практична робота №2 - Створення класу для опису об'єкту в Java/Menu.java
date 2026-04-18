public class Menu {
    private int menuId;
    private String menuName;
    private String[] menuItems;
    private String menuStyle;
    private boolean menuHasPhotos;
    private boolean menuHasDetailedDescriptions;
    private boolean menuIsFlexible;
    private int[] menuSeasonalOffers;

    public Menu(int menuId, String menuName, String[] menuItems, String menuStyle,
                boolean menuHasPhotos, boolean menuHasDetailedDescriptions,
                boolean menuIsFlexible, int[] menuSeasonalOffers) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuItems = menuItems;
        this.menuStyle = menuStyle;
        this.menuHasPhotos = menuHasPhotos;
        this.menuHasDetailedDescriptions = menuHasDetailedDescriptions;
        this.menuIsFlexible = menuIsFlexible;
        this.menuSeasonalOffers = menuSeasonalOffers;
    }

    public void addItem() {}
    public void removeItem() {}
    public void showMenu() {}

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + menuId +
                ", name='" + menuName + '\'' +
                ", style='" + menuStyle + '\'' +
                ", hasPhotos=" + menuHasPhotos +
                '}';
    }
}
