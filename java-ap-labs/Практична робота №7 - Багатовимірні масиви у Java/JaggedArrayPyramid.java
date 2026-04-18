public class JaggedArrayPyramid {
    public static void main(String[] args) {
        int[][] jaggedArray = {
                {1},
                {2, 3},
                {4, 5, 6},
                {7, 8, 9, 10}
        };
        System.out.println("Обычный порядок:");
        for (int i = 0; i < jaggedArray.length; i++) {
            for (int j = 0; j < jaggedArray[i].length; j++) {
                System.out.print(jaggedArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nОбратный порядок:");
        for (int i = jaggedArray.length - 1; i >= 0; i--) {
            for (int j = jaggedArray[i].length - 1; j >= 0; j--) {
                System.out.print(jaggedArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}
