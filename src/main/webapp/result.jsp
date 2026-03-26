<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результат</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${isWin}">
            <h2 class="result-win">🎉 ПОБЕДА! 🎉</h2>
        </c:when>
        <c:otherwise>
            <h2 class="result-loss">💀 ПОРАЖЕНИЕ 💀</h2>
        </c:otherwise>
    </c:choose>
    <div style="text-align: center; padding: 15px;">
        <p><strong>${result}</strong></p>
    </div>
    <div class="stats">
        <h3>Ваша статистика</h3>
        <p>Всего сыграно: ${gameState.gamesPlayed} игр</p>
        <p>Побед: ${gameState.totalWins}</p>
        <p>Поражений: ${gameState.totalLosses}</p>
        <c:if test="${gameState.totalWins + gameState.totalLosses > 0}">
            <c:set var="winPercent" value="${gameState.winRate * 100}"/>
            <p>Процент побед: <fmt:formatNumber value="${winPercent}" pattern="##.##"/>%</p>
        </c:if>
    </div>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/restart" class="btn">Сыграть ещё раз</a>
        <a href="${pageContext.request.contextPath}/welcome" class="btn btn-secondary">Сменить игрока</a>
    </div>
</div>
</body>
</html>
