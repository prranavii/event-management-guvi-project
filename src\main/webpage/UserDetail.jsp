<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
</head>
<body>
<h2>User Details</h2>

<c:if test="${not empty user}">
    <p><strong>ID:</strong> ${user.id}</p>
    <p><strong>Username:</strong> ${user.username}</p>
    <p><strong>Email:</strong> ${user.email}</p>
    <p><strong>Phone Number:</strong> ${user.phoneNumber}</p>
    <p><strong>Role:</strong> ${user.role}</p>
    <a href="UserList.jsp">Back to List</a>
</c:if>

<c:if test="${empty user}">
    <p>User not found.</p>
    <a href="UserList.jsp">Back to List</a>
</c:if>
</body>
</html>
