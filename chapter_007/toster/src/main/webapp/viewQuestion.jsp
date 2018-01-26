<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Question</title>
</head>
<body>
    <h2>${question.title}</h2>

    <div class="question">
        <i>create date: ${question.createDate}</i><br>
        <i>author: ${question.author.login}</i><br>
        <strong>Solved: ${question.solution != null}</strong><br>
        <strong>Description:</strong>
        <p>${question.description}</p>
    </div>

    <div class="answers">
        <h3>Answers</h3>
        <c:forEach items="answers" var="a">
            <div class="answer">
                <i>Author: ${a.author.login}</i><br>
                <i>date: ${a.createdDate}</i><br>
                <p>${a.text}</p>
            </div>
        </c:forEach>
    </div>

    <form id="answer_form" action="addAnswer" method="post">
        <h3>Quick answer</h3>
        <input type="hidden" value="${question.id}" name="questionId">
        <label>Text:</label><br>
        <textarea name="text">

        </textarea><br>
        <input type="submit" value="send">
    </form>

</body>
</html>
