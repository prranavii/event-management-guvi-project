document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('event-form');
    const eventTable = document.getElementById('event-table').querySelector('tbody');

    // Dummy initial data including time
    const events = [
        { name: 'Tech Conference', date: '2024-12-01', time: '10:00', venue: 'New York', ticketPrice: 99.99 },
        { name: 'Startup Meetup', date: '2024-12-10', time: '15:30', venue: 'San Francisco', ticketPrice: 49.99 }
    ];

    // Load initial events
    loadEvents(events);

    // Handle form submission
    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const newEvent = {
            name: form['event-name'].value,
            date: form['event-date'].value,
            time: form['event-time'].value, // Added time input
            venue: form['event-venue'].value,
            ticketPrice: parseFloat(form['ticket-price'].value)
        };

        addEvent(newEvent);
        form.reset();
    });

    function loadEvents(events) {
        events.forEach(event => addEvent(event));
    }

    function addEvent(event) {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${event.name}</td>
            <td>${event.date}</td>
            <td>${event.time}</td> <!-- Added time to the row -->
            <td>${event.venue}</td>
            <td>$${event.ticketPrice.toFixed(2)}</td>
        `;
        eventTable.appendChild(row);
    }
});
