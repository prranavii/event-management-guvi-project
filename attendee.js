document.addEventListener('DOMContentLoaded', function () {
    const eventTable = document.getElementById('event-table').querySelector('tbody');
    const bookingForm = document.getElementById('booking-form');
    const attendeeNameInput = document.getElementById('attendee-name');
    const numberOfTicketsInput = document.getElementById('number-of-tickets');
    const eventIdInput = document.getElementById('event-id');

    // Dummy initial data (you can replace this with a fetch from the server)
    const events = [
        { id: 1, name: 'Tech Conference', date: '2024-12-01', time: '10:00', venue: 'New York', ticketPrice: 99.99, seatsAvailable: 50 },
        { id: 2, name: 'Startup Meetup', date: '2024-12-10', time: '15:30', venue: 'San Francisco', ticketPrice: 49.99, seatsAvailable: 20 }
    ];

    // Load initial events
    loadEvents(events);

    // Function to load events into the table
    function loadEvents(events) {
        events.forEach(event => addEvent(event));
    }

    // Function to add an event to the table
    function addEvent(event) {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${event.name}</td>
            <td>${event.date}</td>
            <td>${event.time}</td>
            <td>${event.venue}</td>
            <td>$${event.ticketPrice.toFixed(2)}</td>
            <td>${event.seatsAvailable}</td>
            <td>
                <button onclick="showBookingForm(${event.id}, '${event.name}', ${event.seatsAvailable})">Book</button>
            </td>
        `;
        eventTable.appendChild(row);
    }

    // Show booking form for the selected event
    window.showBookingForm = function (eventId, eventName, seatsAvailable) {
        eventIdInput.value = eventId;
        bookingForm.style.display = 'block';
    };

    // Handle booking form submission
    bookingForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const numberOfTickets = parseInt(numberOfTicketsInput.value);
        const eventId = parseInt(eventIdInput.value);
        const event = events.find(e => e.id === eventId);

        if (event && event.seatsAvailable >= numberOfTickets) {
            event.seatsAvailable -= numberOfTickets;  // Update available seats
            alert(`Successfully booked ${numberOfTickets} ticket(s) for ${event.name}.`);
            bookingForm.reset();
            bookingForm.style.display = 'none';
            updateEventTable();  // Refresh the event table to reflect changes
        } else {
            alert(`Not enough seats available. Only ${event.seatsAvailable} seats left.`);
        }
    });

    // Function to update the event table after booking
    function updateEventTable() {
        eventTable.innerHTML = '';  // Clear the table
        loadEvents(events);  // Reload events with updated seats
    }
});
