package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GameState;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class RestartServletTest {
    @Test
    public void restartShouldResetCurrentStepButKeepStatistics() throws Exception {
        GameState state = new GameState();
        state.setPlayerName("Evgeniy");
        state.updateStatistics(true);
        state.setCurrentStepId("help");
        state.setGameFinished(true);
        state.addChoice("help", "ignore");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("gameState")).thenReturn(state);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getContextPath()).thenReturn("/quest");
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RestartServlet servlet = new RestartServlet();
        servlet.doGet(request, response);
        (Mockito.verify(session)).setAttribute("gameState", state);
        (Mockito.verify(response)).sendRedirect("/quest/game");
        Assert.assertEquals(1L, state.getTotalWins());
        Assert.assertEquals(0L, state.getTotalLosses());
        Assert.assertEquals("start", state.getCurrentStepId());
        Assert.assertFalse(state.isGameFinished());
        Assert.assertTrue(state.getChoices().isEmpty());
    }

    @Test
    public void restartWithNullGameStateShouldNotCrash() throws Exception {
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("gameState")).thenReturn(null);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getContextPath()).thenReturn("/quest");
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RestartServlet servlet = new RestartServlet();
        servlet.doGet(request, response);
        (Mockito.verify(response)).sendRedirect("/quest/game");
    }
}
