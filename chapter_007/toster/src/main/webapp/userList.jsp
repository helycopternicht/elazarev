<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
    <h1>Users list</h1>
    <table border="1">
        <thead>
            <tr>
                <td>id</td>
                <td>login</td>
                <td>email</td>
                <td></td>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td><<a href="users/remove?id=${user.id}">remove</a>></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="users/add">Add user</a>

</body>
</html>
