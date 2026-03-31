package model;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private String playerName;
    private String currentStepId;
    private boolean isGameFinished;
    private Map<String, String> choices = new HashMap();
    private GameStatistics statistics = new GameStatistics();

    public GameState() {
        this.reset();
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        if (playerName != null && !playerName.trim().isEmpty()) {
            this.playerName = playerName.trim();
        } else {
            this.playerName = "Аноним";
        }
    }

    public int getGamesPlayed() {
        return this.getTotalWins() + this.getTotalLosses();
    }

    public String getCurrentStepId() {
        return this.currentStepId;
    }

    public void setCurrentStepId(String currentStepId) {
        this.currentStepId = currentStepId;
    }

    public void setGameFinished(boolean finished) {
        this.isGameFinished = finished;
    }

    public Map<String, String> getChoices() {
        return this.choices;
    }

    public void addChoice(String step, String choice) {
        this.choices.put(step, choice);
    }

    public int getTotalWins() {
        return this.statistics.getWins(this.resolvePlayerName());
    }

    public int getTotalLosses() {
        return this.statistics.getLosses(this.resolvePlayerName());
    }

    public double getWinRate() {
        return this.statistics.getWinRate(this.resolvePlayerName());
    }

    public void reset() {
        this.currentStepId = "start";
        this.choices.clear();
        this.isGameFinished = false;
    }

    public void updateStatistics(boolean isWin) {
        if (isWin) {
            this.statistics.recordWin(this.resolvePlayerName());
        } else {
            this.statistics.recordLoss(this.resolvePlayerName());
        }

    }

    private String resolvePlayerName() {
        return this.playerName != null && !this.playerName.trim().isEmpty() ? this.playerName : "Аноним";
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}
