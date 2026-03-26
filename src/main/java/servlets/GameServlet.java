package servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GameState;
import model.QuestStep;
import model.QuestTree;

public class GameServlet extends HttpServlet {
    private final QuestTree questTree = new QuestTree();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("choice");
        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");
        if (choice != null && gameState != null) {
            String currentStepId = gameState.getCurrentStepId();
            QuestStep currentStep = this.questTree.getStep(currentStepId);
            if (currentStep != null && this.questTree.hasStep(currentStepId)) {
                if (currentStep.isFinal()) {
                    this.renderFinalResult(request, response, session, gameState, currentStep, true);
                } else {
                    Map<String, String> availableChoices = currentStep.getChoices();
                    if (availableChoices != null && !availableChoices.isEmpty()) {
                        if (choice != null && this.questTree.hasStep(choice)) {
                            gameState.setCurrentStepId(choice);
                            gameState.addChoice(currentStepId, choice);
                            session.setAttribute("gameState", gameState);
                            QuestStep nextStep = this.questTree.getStep(choice);
                            if (nextStep != null && nextStep.isFinal()) {
                                this.renderFinalResult(request, response, session, gameState, nextStep, true);
                                return;
                            }

                            this.processRequest(request, response);
                        } else {
                            this.handleError(request, response, "Неверный выбор или следующий шаг не найден. Попробуйте снова.");
                        }

                    } else {
                        this.handleError(request, response, "Нет доступных вариантов выбора.");
                    }
                }
            } else {
                this.handleError(request, response, "Текущий шаг не найден. Начните заново.");
            }
        } else {
            this.handleError(request, response, "Ошибка: данные не получены. Начните заново.");
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState == null) {
            gameState = new GameState();
            session.setAttribute("gameState", gameState);
        }

        String currentStepId = gameState.getCurrentStepId();
        QuestStep currentStep = this.questTree.getStep(currentStepId);
        if (!this.questTree.hasStep(currentStepId)) {
            this.handleError(request, response, "Текущий шаг не найден: " + currentStepId + ". Начните заново.");
        } else {
            request.setAttribute("gameState", gameState);
            if (currentStep != null && !currentStep.isFinal()) {
                request.setAttribute("question", currentStep.getText());
                request.setAttribute("choices", currentStep.getChoices());
                this.forwardToJsp(request, response, "/game.jsp");
            } else if (currentStep != null) {
                request.setAttribute("result", currentStep.getText());
                request.setAttribute("isWin", currentStep.isWin());
                this.forwardToJsp(request, response, "/result.jsp");
            } else {
                this.handleError(request, response, "Шаг не найден. Начните заново.");
            }

        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState == null) {
            gameState = new GameState();
            session.setAttribute("gameState", gameState);
        }

        request.setAttribute("gameState", gameState);
        request.setAttribute("result", message);
        this.forwardToJsp(request, response, "/result.jsp");
    }

    private void renderFinalResult(HttpServletRequest request, HttpServletResponse response, HttpSession session, GameState gameState, QuestStep finalStep, boolean updateStatistics) throws ServletException, IOException {
        if (updateStatistics && !gameState.isGameFinished()) {
            gameState.updateStatistics(finalStep.isWin());
        }

        gameState.setGameFinished(true);
        session.setAttribute("gameState", gameState);
        request.setAttribute("result", finalStep.getText());
        request.setAttribute("isWin", finalStep.isWin());
        request.setAttribute("gameState", gameState);
        this.forwardToJsp(request, response, "/result.jsp");
    }

    private void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String jspPath) throws ServletException, IOException {
        request.getRequestDispatcher(jspPath).forward(request, response);
    }
}
