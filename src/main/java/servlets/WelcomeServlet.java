package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GameState;

public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameState gameState = (GameState)session.getAttribute("gameState");
        if (gameState == null) {
            gameState = new GameState();
            session.setAttribute("gameState", gameState);
        }

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String playerName = request.getParameter("playerName");
        HttpSession session = request.getSession();
        GameState gameState = (GameState)session.getAttribute("gameState");
        if (gameState != null && playerName != null && !playerName.trim().isEmpty()) {
            gameState.setPlayerName(playerName);
            gameState.reset();
            session.setAttribute("gameState", gameState);
        }

        response.sendRedirect(request.getContextPath() + "/game");
    }
}
