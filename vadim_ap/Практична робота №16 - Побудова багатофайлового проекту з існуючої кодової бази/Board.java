package game;

public class Board {
    public static char[][] initBoard(int size) {
        char[][] board = new char[2 * size + 1][2 * size + 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = (i % 2 == 0) ? ((j % 2 == 0) ? ' ' : '|') : ((j % 2 == 0) ? '-' : '+');
            }
        }
        return board;
    }

    public static void renderBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(row);
        }
    }

    public static boolean checkWin(char[][] board, char symbol, int row, int col, int size) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] dir : directions) {
            int count = 1;
            for (int sign = -1; sign <= 1; sign += 2) {
                for (int i = 1; i < 3; i++) {
                    int newRow = row + dir[0] * i * sign;
                    int newCol = col + dir[1] * i * sign;
                    if (newRow < 1 || newCol < 1 || newRow > size || newCol > size ||
                        board[2 * newRow][2 * newCol] != symbol) {
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