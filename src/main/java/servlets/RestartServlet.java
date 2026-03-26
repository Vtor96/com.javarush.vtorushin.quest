package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GameState;

public class RestartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameState gameState = (GameState)session.getAttribute("gameState");
        if (gameState == null) {
            gameState = new GameState();
        }

        gameState.reset();
        session.setAttribute("gameState", gameState);
        response.sendRedirect(request.getContextPath() + "/game");
    }
}
