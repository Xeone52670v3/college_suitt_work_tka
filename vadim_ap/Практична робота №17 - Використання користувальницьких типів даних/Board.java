package game;

public class Board {
    private char[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
        grid = new char[2 * size + 1][2 * size + 1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = (i % 2 == 0) ? ((j % 2 == 0) ? ' ' : '|') : ((j % 2 == 0) ? '-' : '+');
            }
        }
    }

    public void render() {
        for (char[] row : grid) {
            System.out.println(row);
        }
    }

    public boolean placeMark(int row, int col, char symbol) {
        if (grid[2 * row][2 * col] == ' ') {
            grid[2 * row][2 * col] = symbol;
            return true;
        }
        return false;
    }

    public boolean checkWin(char symbol, int row, int col) {
        int[][] directions = {{0,1},{1,0},{1,1},{1,-1}};
        for (int[] dir : directions) {
            int count = 1;
            for (int sign = -1; sign <= 1; sign += 2) {
                for (int i = 1; i < 3; i++) {
                    int newRow = row + dir[0] * i * sign;
                    int newCol = col + dir[1] * i * sign;
                    if (newRow < 1 || newCol < 1 || newRow > size || newCol > size ||
                        grid[2 * newRow][2 * newCol] != symbol) {
                        break;
                    }
                    count++;
                }
            }
            if (count >= 3) return true;
        }
        return false;
    }
}