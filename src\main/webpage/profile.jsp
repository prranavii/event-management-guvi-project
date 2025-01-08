<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
<h2>User Profile</h2>
<p>Username: ${user.username}</p>
<p>Email: ${user.email}</p>
<p>Phone Number: ${user.phoneNumber}</p>

<form action="profile" method="POST">
    <label for="username">Update Username: </label><input type="text" name="username" value="${user.username}" required><br>
    <label for="email">Update Email: </label><input type="email" name="email" value="${user.email}" required><br>
    <label for="phone">Update Phone: </label><input type="text" name="phone" value="${user.phoneNumber}" required><br>
    <button type="submit">Update Profile</button>
</form>
</body>
</html>
