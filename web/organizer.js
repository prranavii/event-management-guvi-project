// DOM Elements
const eventForm = document.getElementById('event-form');
const eventTableBody = document.querySelector('#event-table tbody');

// Sample events array to hold added events
let events = [];

// Function to populate the event list in the table
function populateEventList() {
    eventTableBody.innerHTML = ""; // Clear the table body before adding new events

    events.forEach((event, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${event.name}</td>
            <td>${event.date}</td>
            <td>${event.time}</td>
            <td>${event.venue}</td>
            <td>$${event.ticketPrice}</td>
        `;
        eventTableBody.appendChild(row);
    });
}

// Function to handle form submission
eventForm.addEventListener('submit', (e) => {
    e.preventDefault(); // Prevent the form from submitting

    const eventName = document.getElementById('event-name').value;
    const eventDate = document.getElementById('event-date').value;
    const eventTime = document.getElementById('event-time').value;
    const eventVenue = document.getElementById('event-venue').value;
    const ticketPrice = parseFloat(document.getElementById('ticket-price').value);

    // Basic form validation
    if (!eventName || !eventDate || !eventTime || !eventVenue || isNaN(ticketPrice) || ticketPrice <= 0) {
        alert('Please fill in all fields with valid information.');
        return;
    }

    // Create a new event object
    const newEvent = {
        name: eventName,
        date: eventDate,
        time: eventTime,
        venue: eventVenue,
        ticketPrice: ticketPrice,
    };

    // Add the new event to the events array
    events.push(newEvent);

    // Clear the form fields
    eventForm.reset();

    // Update the event list in the table
    populateEventList();
});

// Initial population of the event list (if any events already exist)
populateEventList();
