public class ListOverflowException extends RuntimeException {
    private int maxSize;
    public ListOverflowException(String message, int maxSize) {
        super(message);
        this.maxSize = maxSize;
    }
    public int getMaxSize() {
        return maxSize;
    }
}