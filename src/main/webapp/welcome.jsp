<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h2>Добро пожаловать!</h2>
    <div class="game-intro">
        <p>
            В старом волшебном мире есть вещь, которой боятся не меньше, чем молний: карта, не терпящая
            лжи. Её линии меняются не от ветра и не от времени — а от того, как выбирают люди.
        </p>
        <p>
            Говорят, что карта была сделана хранителями лесной обители. Она ведёт туда, где разговоры
            кончаются делом: на развилки, где нужно выбрать одно решение вместо сотни сомнений.
        </p>
        <p>
            Вам осталось немного. Перед вами — Тёмный лес, в котором следы не стираются, а
            аккуратно запоминаются. На пути встретится путник, и одна из тропинок приведёт
            к пещере — сундук там ждёт смелых, но умеет обманывать.
        </p>
        <p>
            Если готовы — назовите своё имя. Лес услышит его и подберёт для вас испытание.
            А дальше всё просто: выбирайте шаг за шагом, и карта наконец-то скажет правду.
        </p>
    </div>

    <p>Введите ваше имя, чтобы начать игру:</p>

    <form action="${pageContext.request.contextPath}/welcome" method="post">
        <input type="text" id="playerName" name="playerName" placeholder="Ваше имя" required>
        <button type="submit" class="btn">Начать приключение</button>
    </form>

    <div style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">На главную</a>
    </div>
</div>
</body>
</html>
