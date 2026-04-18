import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Base {
    private static final String CONFIG_FILE = "config.txt";
    private static final String STATS_FILE = "stats.txt";
    private static String player1 = "Player 1";
    private static String player2 = "Player 2";
    private static int boardSize = 3;

    public static void main(String[] args) {
        char[][] board;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        loadConfig();

        while (true) {
            showMenu();
            choice = getInput(scanner);

            if (choice == 1) {
                board = initBoard(boardSize);
                char playerSymbol = 'X';
                int moves = 0;
                boolean win = false;
                LocalDateTime startTime = LocalDateTime.now();

                while (!win && moves < boardSize * boardSize) {
                    renderBoard(board);

                    int row = getCoords(scanner, "row", playerSymbol, boardSize);
                    if (row == 88) break;

                    int col = getCoords(scanner, "column", playerSymbol, boardSize);
                    if (col == 88) break;

                    if (board[2 * row][2 * col] == ' ') {
                        board[2 * row][2 * col] = playerSymbol;
                        moves++;
                    } else {
                        System.out.println("This cell is already occupied. Choose another one.");
                        continue;
                    }

                    win = checkWin(board, playerSymbol, row, col, boardSize);

                    if (win) {
                        renderBoard(board);
                        String winnerName = (playerSymbol == 'X') ? player1 : player2;
                        System.out.println("Player " + winnerName + " (" + playerSymbol + ") won!");
                        saveStats(winnerName, playerSymbol, startTime);
                    } else if (moves == boardSize * boardSize) {
                        System.out.println("It's a draw!");
                        saveStats("Draw", ' ', startTime);
                    } else {
                        playerSymbol = (playerSymbol == 'X') ? 'O' : 'X';
                    }
                }
            } else if (choice == 2) {
                settingsMenu(scanner);
            } else if (choice == 3) {
                showStats();
            } else if (choice == 4) {
                break;
            }
        }
    }

    private static void showMenu() {
        System.out.println("|------------------|");
        System.out.println("| Menu:            |");
        System.out.println("| 1. Play          |");
        System.out.println("| 2. Settings      |");
        System.out.println("| 3. Statistics    |");
        System.out.println("| 4. Exit          |");
        System.out.println("|------------------|");
        System.out.print("Choose an option: ");
    }

    private static void settingsMenu(Scanner scanner) {
        System.out.println("|-----Settings----------|");
        System.out.println("| 1. Change board size  |");
        System.out.println("| 2. Change Player X    |");
        System.out.println("| 3. Change Player O    |");
        System.out.println("| 4. Save settings      |");
        System.out.println("| 5. Back to main menu  |");
        System.out.println("|------------------------|");
        System.out.print("Choose an option: ");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boardSize = changeBoardSize(scanner);
                    break;
                case 2:
                    System.out.print("Enter new name for Player X: ");
                    player1 = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter new name for Player O: ");
                    player2 = scanner.nextLine();
                    break;
                case 4:
                    saveConfig();
                    System.out.println("Settings saved.");
                    break;
            }
        } else {
            System.out.println("You must enter a number.");
            scanner.next();
        }
    }

    private static int changeBoardSize(Scanner scanner) {
        System.out.println("|----Board Size-----|");
        System.out.println("| Enter an odd number|");
        System.out.println("| from 3 to 9:        |");
        System.out.println("|--------------------|");
        System.out.print("Enter size: ");

        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            if (size >= 3 && size <= 9 && size % 2 == 1) {
                return size;
            } else {
                System.out.println("Invalid size. Default 3x3 will be used.");
            }
        } else {
            System.out.println("You must enter a number.");
            scanner.next();
        }
        return 3;
    }

    private static void saveConfig() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE))) {
            writer.println(boardSize);
            writer.println(player1);
            writer.println(player2);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    private static void loadConfig() {
        try (Scanner fileScanner = new Scanner(new File(CONFIG_FILE))) {
            if (fileScanner.hasNextInt()) {
                boardSize = fileScanner.nextInt();
                fileScanner.nextLine();
            }
            if (fileScanner.hasNextLine()) {
                player1 = fileScanner.nextLine();
            }
            if (fileScanner.hasNextLine()) {
                player2 = fileScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found!");
        }
    }

    private static void saveStats(String winner, char symbol, LocalDateTime startTime) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STATS_FILE, true))) {
            String time = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println(time + ";" + boardSize + "x" + boardSize + ";" +
                           player1 + " vs " + player2 + ";" +
                           (symbol != ' ' ? symbol + ":" + winner : winner));
        } catch (IOException e) {
            System.out.println("Error saving statistics: " + e.getMessage());
        }
    }

    private static void showStats() {
        System.out.println("|-------Statistics---------|");
        System.out.println("| Date       | Size | Players        | Result    |");

        try (Scanner fileScanner = new Scanner(new File(STATS_FILE))) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(";");
                if (parts.length >= 4) {
                    System.out.printf("| %s | %5s | %-15s | %-9s |\n",
                                      parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("| No statistics data available |");
        }
        System.out.println("|------------------------------|");
    }

    private static int getInput(Scanner scanner) {
        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("You must enter a number.");
            scanner.next();
        }
        return choice;
    }

    private static char[][] initBoard(int boardSize) {
        char[][] board = new char[2 * boardSize + 1][2 * boardSize + 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = (i % 2 == 0) ? ((j % 2 == 0) ? ' ' : '|') : ((j % 2 == 0) ? '-' : '+');
            }
        }
        for (int i = 1; i <= boardSize; i++) {
            board[0][2 * i] = board[2 * i][0] = (char) ('0' + i);
        }
        return board;
    }

    private static void renderBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(row);
        }
    }

    private static int getCoords(Scanner scanner, String type, char playerSymbol, int boardSize) {
        int coord = -1;
        while (coord < 1 || coord > boardSize) {
            System.out.print("Player " + playerSymbol + ", enter " + type + " number (or '88' to exit): ");
            if (scanner.hasNextInt()) {
                coord = scanner.nextInt();
                if (coord == 88) break;
            } else {
                System.out.println("You must enter a number.");
                scanner.next();
            }
        }
        return coord;
    }

    private static boolean checkWin(char[][] board, char playerSymbol, int row, int col, int boardSize) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] dir : directions) {
            int count = 1;
            for (int sign = -1; sign <= 1; sign += 2) {
                for (int i = 1; i < 3; i++) {
                    int newRow = row + dir[0] * i * sign;
                    int newCol = col + dir[1] * i * sign;
                    if (newRow < 1 || newCol < 1 || newRow > boardSize || newCol > boardSize || board[2 * newRow][2 * newCol] != playerSymbol) {
                        break;
                    }
                    count++;
                }
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }
}
