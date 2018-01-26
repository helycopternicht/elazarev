<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add question</title>
</head>
<body>

    <h1>Add question</h1>

    <form action="addQuestion" method="post">
        <label>Title</label>
        <input type="text" name="title"> <br>

        <label>Category (; to separate)</label>
        <input type="text" name="category"> <br>

        <label>Description</label>
        <input type="text" name="description"> <br>

        <input type="submit" value="Publish">
    </form>
</body>
</html>
