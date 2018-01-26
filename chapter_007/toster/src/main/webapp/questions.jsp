<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>All questions</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<body>
    <h1>Toster App</h1>
    <h2>Questions</h2>

    <c:forEach items="${questions}" var="question">
        <div class="question">
            <a href="viewQuestion?id=${question.id}">${question.title}</a>
            <div>${question.description}</div>
            <c:choose>
                <c:when test="${question.solution != null}">
                    <strong>Have solution!</strong>
                </c:when>
                <c:otherwise>
                    <strong>Not resolved</strong>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>

    <a href="addQuestion">Add question</a>

</body>
</html>
