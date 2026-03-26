<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Игра</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <c:if test="${not empty gameState.playerName}">
        <div class="player-info">
            <p><strong>Игрок:</strong> ${gameState.playerName}</p>
            <p><strong>Сыграно игр:</strong> ${gameState.gamesPlayed}</p>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${empty question}">
            <div class="question-box">
                <h3>Игра завершена</h3>
                <p>Пожалуйста, начните новую игру.</p>
                <div style="text-align: center;">
                    <a href="${pageContext.request.contextPath}/restart" class="btn btn-secondary">Начать заново</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="question-box">
                <h3>Что будете делать?</h3>
                <p>${question}</p>

                <c:choose>
                    <c:when test="${not empty choices}">
                        <form action="${pageContext.request.contextPath}/game" method="post">
                            <c:forEach items="${choices}" var="entry">
                                <div class="choice-option">
                                    <label>
                                        <input type="radio" name="choice" value="${entry.key}" required>
                                            ${entry.value}
                                    </label>
                                </div>
                            </c:forEach>
                            <button type="submit" class="btn">Сделать выбор</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p style="color: #e74c3c; font-weight: bold;">
                            Нет доступных вариантов выбора. Игра завершена.
                        </p>
                        <div style="text-align: center; margin-top: 20px;">
                            <a href="${pageContext.request.contextPath}/restart" class="btn btn-secondary">Начать
                                заново</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:otherwise>
    </c:choose>

    <div style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/restart" class="btn btn-secondary">Начать заново</a>
    </div>

    <c:if test="${not empty result}">
        <div class="error-message">
            <strong>Сообщение:</strong> ${result}
        </div>
    </c:if>
</div>
</body>
</html>
