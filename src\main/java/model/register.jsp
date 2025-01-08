<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register an Account</h2>
<form action="register" method="POST">
    <label for="username">Username: </label><input type="text" name="username" required><br>
    <label for="email">Email: </label><input type="email" name="email" required><br>
    <label for="password">Password: </label><input type="password" name="password" required><br>
    <button type="submit">Register</button>
</form>
</body>
</html>
