package model;

import java.util.HashMap;
import java.util.Map;

public class GameStatistics {
    private final Map<String, Integer> wins = new HashMap();
    private final Map<String, Integer> losses = new HashMap();

    public void recordWin(String playerName) {
        this.wins.put(playerName, this.wins.getOrDefault(playerName, 0) + 1);
    }

    public void recordLoss(String playerName) {
        this.losses.put(playerName, this.losses.getOrDefault(playerName, 0) + 1);
    }

    public int getWins(String playerName) {
        return this.wins.getOrDefault(playerName, 0);
    }

    public int getLosses(String playerName) {
        return this.losses.getOrDefault(playerName, 0);
    }

    public double getWinRate(String playerName) {
        int total = this.getWins(playerName) + this.getLosses(playerName);
        return total == 0 ? (double) 0.0F : (double) this.getWins(playerName) / (double) total;
    }
}
