<!DOCTYPE html>
<html>
<head>
    <title>Attendee Dashboard</title>
</head>
<body>
<h1>Available Events</h1>
<table>
    <tr>
        <th>Event Name</th>
        <th>Date</th>
        <th>Time</th>
        <th>Venue</th>
        <th>Price</th>
    </tr>
    <c:forEach var="event" items="${events}">
        <tr>
            <td>${event.name}</td>
            <td>${event.date}</td>
            <td>${event.time}</td>
            <td>${event.venue}</td>
            <td>${event.ticketPrice}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
