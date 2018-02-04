<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
    <h1>New user</h1>
    <form action="add" method="post">
        <input type="text" name="login" placeholder="login"> <br>
        <input type="password" name="password" placeholder="password"> <br>
        <input type="email" name="email" placeholder="email"> <br>
        <label>is Admin?</label>
        <input type="checkbox" name="isAdmin">
        <input type="submit" value="create">
    </form>
</body>
</html>
