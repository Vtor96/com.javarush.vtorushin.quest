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
        HttpSession session = (HttpSession) Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("gameState")).thenReturn(state);
        HttpServletRequest request = (HttpServletRequest) Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getContextPath()).thenReturn("/quest");
        HttpServletResponse response = (HttpServletResponse) Mockito.mock(HttpServletResponse.class);
        RestartServlet servlet = new RestartServlet();
        servlet.doGet(request, response);
        ((HttpSession) Mockito.verify(session)).setAttribute("gameState", state);
        ((HttpServletResponse) Mockito.verify(response)).sendRedirect("/quest/game");
        Assert.assertEquals(1L, (long) state.getTotalWins());
        Assert.assertEquals(0L, (long) state.getTotalLosses());
        Assert.assertEquals("start", state.getCurrentStepId());
        Assert.assertFalse(state.isGameFinished());
        Assert.assertTrue(state.getChoices().isEmpty());
    }

    @Test
    public void restartWithNullGameStateShouldNotCrash() throws Exception {
        HttpSession session = (HttpSession) Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("gameState")).thenReturn((Object) null);
        HttpServletRequest request = (HttpServletRequest) Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getContextPath()).thenReturn("/quest");
        HttpServletResponse response = (HttpServletResponse) Mockito.mock(HttpServletResponse.class);
        RestartServlet servlet = new RestartServlet();
        servlet.doGet(request, response);
        ((HttpServletResponse) Mockito.verify(response)).sendRedirect("/quest/game");
    }
}
