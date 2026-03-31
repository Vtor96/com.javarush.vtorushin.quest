package model;

import org.junit.Assert;
import org.junit.Test;

public class GameStateTest {
    @Test
    public void statisticsShouldUpdateWinLossAndWinRate() {
        GameState state = new GameState();
        state.setPlayerName("Evgeniy");
        Assert.assertEquals(0L, state.getTotalWins());
        Assert.assertEquals(0L, state.getTotalLosses());
        Assert.assertEquals((double) 0.0F, state.getWinRate(), 1.0E-4);
        Assert.assertEquals(0L, state.getGamesPlayed());
        state.updateStatistics(true);
        state.updateStatistics(false);
        Assert.assertEquals(1L, state.getTotalWins());
        Assert.assertEquals(1L, state.getTotalLosses());
        Assert.assertEquals(2L, state.getGamesPlayed());
        Assert.assertEquals((double) 0.5F, state.getWinRate(), 1.0E-4);
    }

    @Test
    public void resetShouldNotClearStatistics() {
        GameState state = new GameState();
        state.setPlayerName("Evgeniy");
        state.updateStatistics(true);
        Assert.assertEquals(1L, state.getTotalWins());
        state.reset();
        Assert.assertEquals(1L, state.getTotalWins());
        Assert.assertEquals(0L, state.getTotalLosses());
        Assert.assertEquals("start", state.getCurrentStepId());
        Assert.assertFalse(state.isGameFinished());
        Assert.assertTrue(state.getChoices().isEmpty());
    }

    @Test
    public void emptyPlayerNameShouldBeMappedToAnonymous() {
        GameState state = new GameState();
        state.setPlayerName("");
        Assert.assertEquals("Аноним", state.getPlayerName());
        state.updateStatistics(true);
        Assert.assertEquals(1L, state.getTotalWins());
        Assert.assertEquals(0L, state.getTotalLosses());
    }
}
