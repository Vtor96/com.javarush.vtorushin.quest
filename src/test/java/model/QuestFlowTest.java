package model;

import org.junit.Assert;
import org.junit.Test;

public class QuestFlowTest {
    private boolean playPathAndUpdate(GameState state, QuestTree tree, String... choices) {
        for (String nextStepId : choices) {
            Assert.assertTrue("Шаг должен существовать: " + nextStepId, tree.hasStep(nextStepId));
            QuestStep step = tree.getStep(nextStepId);
            Assert.assertNotNull(step);
            if (step.isFinal()) {
                state.updateStatistics(step.isWin());
                state.setGameFinished(true);
                return step.isWin();
            }

            state.setCurrentStepId(nextStepId);
        }

        Assert.fail("Цепочка должна завершаться финальным шагом");
        return false;
    }

    @Test
    public void leftPathTreasureShouldBeWin() {
        QuestTree tree = new QuestTree();
        GameState state = new GameState();
        state.setPlayerName("Player");
        Assert.assertTrue(this.playPathAndUpdate(state, tree, "path_left", "cave_enter", "treasure"));
        Assert.assertEquals(1L, state.getTotalWins());
        Assert.assertEquals(0L, state.getTotalLosses());
    }

    @Test
    public void leftPathNoTreasureShouldBeLoss() {
        QuestTree tree = new QuestTree();
        GameState state = new GameState();
        state.setPlayerName("Player");
        Assert.assertFalse(this.playPathAndUpdate(state, tree, "path_left", "cave_enter", "no_treasure"));
        Assert.assertEquals(0L, state.getTotalWins());
        Assert.assertEquals(1L, state.getTotalLosses());
    }

    @Test
    public void rightPathHelpShouldBeWin() {
        QuestTree tree = new QuestTree();
        GameState state = new GameState();
        state.setPlayerName("Player");
        Assert.assertTrue(this.playPathAndUpdate(state, tree, "path_right", "help"));
        Assert.assertEquals(1L, state.getTotalWins());
        Assert.assertEquals(0L, state.getTotalLosses());
    }

    @Test
    public void rightPathIgnoreShouldBeLoss() {
        QuestTree tree = new QuestTree();
        GameState state = new GameState();
        state.setPlayerName("Player");
        Assert.assertFalse(this.playPathAndUpdate(state, tree, "path_right", "ignore"));
        Assert.assertEquals(0L, state.getTotalWins());
        Assert.assertEquals(1L, state.getTotalLosses());
    }
}
