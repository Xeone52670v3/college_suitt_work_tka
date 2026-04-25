import java.util.Comparator;

public class ListItemLengthComparator implements Comparator<ListItem> {
    @Override
    public int compare(ListItem a, ListItem b) {
        return Integer.compare(a.getValue().length(), b.getValue().length());
    }
}
