package game;

import java.util.Scanner;
import java.time.LocalDateTime;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private Board board;
    private GameSettings settings = new GameSettings();
    private char currentPlayer = 'X';
    private int moves = 0;

    public void start() {
        board = new Board(settings.getBoardSize());
        LocalDateTime startTime = LocalDateTime.now();
        boolean gameWon = false;

        while (!gameWon && moves < settings.getBoardSize() * settings.getBoardSize()) {
            board.render();
            int row = getCoord("row");
            int col = getCoord("column");

            if (board.placeMark(row, col, currentPlayer)) {
                moves++;
                gameWon = board.checkWin(currentPlayer, row, col);
                if (gameWon) {
                    board.render();
                    System.out.println("Player " + (currentPlayer == 'X' ? settings.getPlayer1() : settings.getPlayer2()) + " wins!");
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
        GameStats stats = new GameStats(startTime, settings, gameWon ? currentPlayer : ' ');
        stats.printStats();
    }

    private int getCoord(String type) {
        int coord = -1;
        while (coord < 1 || coord > settings.getBoardSize()) {
            System.out.print("Enter " + type + " (1-" + settings.getBoardSize() + "): ");
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