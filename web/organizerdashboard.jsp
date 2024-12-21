<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Organizer Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/organizer.css">
</head>
<body>
<div class="container">
    <h1>Organizer Dashboard</h1>
    <form id="event-form" action="${pageContext.request.contextPath}/organizer/addEvent" method="POST">
        <label for="event-name">Event Name:</label>
        <input type="text" id="event-name" name="event-name" required>

        <label for="event-date">Date:</label>
        <input type="date" id="event-date" name="event-date" required>

        <label for="event-time">Time:</label>
        <input type="time" id="event-time" name="event-time" required>

        <label for="event-venue">Venue:</label>
        <input type="text" id="event-venue" name="event-venue" required>

        <label for="ticket-price">Ticket Price:</label>
        <input type="number" id="ticket-price" name="ticket-price" step="0.01" required>

        <button type="submit">Add Event</button>
    </form>

    <h2>Event List</h2>
    <table id="event-table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Time</th>
            <th>Venue</th>
            <th>Ticket Price</th>
        </tr>
        </thead>
        <tbody>
        <!-- Dynamic events rows will be injected here -->
        </tbody>
    </table>
</div>

<script src="${pageContext.request.contextPath}/js/organizer.js"></script>
</body>
</html>
