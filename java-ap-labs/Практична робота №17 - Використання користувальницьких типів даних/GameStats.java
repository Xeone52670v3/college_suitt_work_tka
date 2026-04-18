package game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameStats {
    private LocalDateTime startTime;
    private GameSettings settings;
    private char winnerSymbol;

    public GameStats(LocalDateTime startTime, GameSettings settings, char winnerSymbol) {
        this.startTime = startTime;
        this.settings = settings;
        this.winnerSymbol = winnerSymbol;
    }

    public void printStats() {
        String time = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String result = (winnerSymbol != ' ') ? (winnerSymbol + " wins!") : "Draw";
        System.out.println("|-------Game Statistics-------|");
        System.out.println("Time: " + time);
        System.out.println("Board size: " + settings.getBoardSize() + "x" + settings.getBoardSize());
        System.out.println("Players: " + settings.getPlayer1() + " vs " + settings.getPlayer2());
        System.out.println("Result: " + result);
        System.out.println("|-----------------------------|");
    }
}