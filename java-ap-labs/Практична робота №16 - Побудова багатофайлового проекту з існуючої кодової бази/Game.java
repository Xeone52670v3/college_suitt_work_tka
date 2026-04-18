package game;

import java.util.Scanner;
import java.time.LocalDateTime;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private char[][] board;
    private char currentPlayer = 'X';
    private int moves = 0;

    public void start() {
        board = Board.initBoard(Config.boardSize);
        LocalDateTime startTime = LocalDateTime.now();
        boolean gameWon = false;

        while (!gameWon && moves < Config.boardSize * Config.boardSize) {
            Board.renderBoard(board);
            int row = getCoord("row");
            int col = getCoord("column");

            if (board[2 * row][2 * col] == ' ') {
                board[2 * row][2 * col] = currentPlayer;
                moves++;
                gameWon = Board.checkWin(board, currentPlayer, row, col, Config.boardSize);
                if (gameWon) {
                    Board.renderBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Cell occupied. Try again.");
            }
        }
        if (!gameWon) {
            System.out.println("It's a draw!");
        }
    }

    private int getCoord(String type) {
        int coord = -1;
        while (coord < 1 || coord > Config.boardSize) {
            System.out.print("Enter " + type + " (1-" + Config.boardSize + "): ");
            if (scanner.hasNextInt()) {
                coord = scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Invalid input.");
            }
        }
        return coord;
    }
}