import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        try (Scanner con = new Scanner(System.in)) {
            int b = 3;
            while (true) {
                System.out.println("Tik Tak Toe Game");
                System.out.println("      Menu");
                System.out.println("1. Start Game");
                System.out.println("2. Settings");
                System.out.println("0. Exit");
                int a = 0;
                int d;
                char[][] game;
                while (true) {
                    if (con.hasNextInt()) {
                        a = con.nextInt();
                        break;
                    } else {
                        System.out.println("Please enter a number of menu:");
                        con.next();
                    }
                }
                if (a == 1) {
                    game = generateBoard(b);
                    char player = 'X';
                    boolean end = false;
                    boolean returnToMenu = false;
                    while (!end && !returnToMenu) {
                        printBoard(game);
                        System.out.println("Player " + player + ", enter your move (enter 0 to back to menu)");
                        System.out.println("First coordinate is X (row):");
                        int r;
                        int c;
                        while (true) {
                            if (con.hasNextInt()) {
                                r = con.nextInt();
                                if (r == 0) {
                                    returnToMenu = true;
                                    break;
                                }
                                break;
                            } else {
                                System.out.println("Please enter a coord of row by using only numbers:");
                                con.next();
                            }
                        }
                        if (returnToMenu) break;
                        System.out.println("Second coordinate Y (column):");
                        while (true) {
                            if (con.hasNextInt()) {
                                c = con.nextInt();
                                if (c == 0) {
                                    returnToMenu = true;
                                    break;
                                }
                                break;
                            } else {
                                System.out.println("Please enter a coord of column by using only numbers:");
                                con.next();
                            }
                        }
                        if (returnToMenu) break;
                        if (r < 1 || r > b || c < 1 || c > b) {
                            System.out.println("This move is out of bounds");
                        } else if (game[r * 2][c * 2] == ' ') {
                            game[r * 2][c * 2] = player;
                            if (checkWin(game, player, b)) {
                                printBoard(game);
                                System.out.println("Player " + player + " wins!");
                                end = true;
                            } else if (checkTie(game, b)) {
                                printBoard(game);
                                System.out.println("The game is a tie!");
                                end = true;
                            } else {
                                player = (player == 'X') ? 'O' : 'X';
                            }
                        } else {
                            System.out.println("This move is not valid");
                        }
                    }
                } else if (a == 2) {
                    System.out.println("Settings");
                    System.out.println("1. 3x3 game zone");
                    System.out.println("2. 5x5 game zone");
                    System.out.println("3. 7x7 game zone");
                    System.out.println("4. 9x9 game zone");
                    System.out.println("0. Main Menu");
                    while (true) {
                        if (con.hasNextInt()) {
                            d = con.nextInt();
                            break;
                        } else {
                            System.out.println("Please enter a number of settings:");
                            con.next();
                        }
                    }
                    switch (d) {
                        case 1:
                            b = 3;
                            break;
                        case 2:
                            b = 5;
                            break;
                        case 3:
                            b = 7;
                            break;
                        case 4:
                            b = 9;
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Please enter a valid number of settings");
                    }
                } else if (a == 0) {
                    System.out.println("Bye");
                    break;
                }
            }
        }
    }

    private static char[][] generateBoard(int b) {
        char[][] game = new char[2 * b + 1][2 * b + 1];
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    game[i][j] = ' ';
                } else if (i % 2 == 0) {
                    game[i][j] = '|';
                } else if (j % 2 == 0) {
                    game[i][j] = '-';
                } else {
                    game[i][j] = '+';
                }
            }
        }
        for (int i = 1; i <= b; i++) {
            game[0][2 * i] = (char) ('0' + i);
            game[2 * i][0] = (char) ('0' + i);
        }
        return game;
    }

    private static void printBoard(char[][] game) {
        for (char[] row : game) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static boolean checkWin(char[][] game, char player, int b) {
        for (int k = 1; k <= b; k++) {
            if ((game[2 * k][2] == player && game[2 * k][4] == player && game[2 * k][6] == player) ||
                (game[2][2 * k] == player && game[4][2 * k] == player && game[6][2 * k] == player)) {
                return true;
            }
        }
        for (int k = 1; k <= b - 2; k++) {
            if ((game[2 * k][2 * k] == player && game[2 * (k + 1)][2 * (k + 1)] == player && game[2 * (k + 2)][2 * (k + 2)] == player) ||
                (game[2 * k][2 * (b - k + 1)] == player && game[2 * (k + 1)][2 * (b - (k + 1) + 1)] == player && game[2 * (k + 2)][2 * (b - (k + 2) + 1)] == player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTie(char[][] game, int b) {
        for (int x = 2; x <= 6; x += 2) {
            for (int y = 2; y <= 6; y += 2) {
                if (game[x][y] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

