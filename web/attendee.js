// Sample event data
const events = [
    {
        id: 1,
        name: "Tech Conference 2024",
        date: "2024-03-15",
        time: "10:00 AM",
        venue: "Conference Hall A",
        ticketPrice: "$50",
        seatsAvailable: 100,
    },
    {
        id: 2,
        name: "Music Fest",
        date: "2024-03-20",
        time: "7:00 PM",
        venue: "City Stadium",
        ticketPrice: "$30",
        seatsAvailable: 50,
    },
    {
        id: 3,
        name: "Art Expo",
        date: "2024-04-05",
        time: "3:00 PM",
        venue: "Art Gallery",
        ticketPrice: "$20",
        seatsAvailable: 200,
    },
];

// DOM Elements
const eventTableBody = document.querySelector("#event-table tbody");
const bookingForm = document.getElementById("booking-form");
const attendeeNameInput = document.getElementById("attendee-name");
const numberOfTicketsInput = document.getElementById("number-of-tickets");
const eventIdInput = document.getElementById("event-id");

// Function to populate the event table
function populateEventTable() {
    events.forEach((event) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${event.name}</td>
            <td>${event.date}</td>
            <td>${event.time}</td>
            <td>${event.venue}</td>
            <td>${event.ticketPrice}</td>
            <td>${event.seatsAvailable}</td>
            <td><button class="book-btn" data-event-id="${event.id}">Book</button></td>
        `;

        eventTableBody.appendChild(row);
    });
}

// Function to show the booking form
function showBookingForm(eventId) {
    const event = events.find((e) => e.id === parseInt(eventId));
    if (event) {
        eventIdInput.value = event.id;
        bookingForm.style.display = "block";
    }
}

// Event listener for booking buttons
eventTableBody.addEventListener("click", (e) => {
    if (e.target.classList.contains("book-btn")) {
        const eventId = e.target.getAttribute("data-event-id");
        showBookingForm(eventId);
    }
});

// Event listener for booking form submission
bookingForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const attendeeName = attendeeNameInput.value.trim();
    const numberOfTickets = parseInt(numberOfTicketsInput.value);
    const eventId = parseInt(eventIdInput.value);

    const event = events.find((e) => e.id === eventId);

    if (event && numberOfTickets <= event.seatsAvailable) {
        event.seatsAvailable -= numberOfTickets;
        alert(`Booking successful for ${attendeeName}!\nEvent: ${event.name}\nTickets: ${numberOfTickets}`);
        eventTableBody.innerHTML = ""; // Clear and re-populate the table to update seat availability
        populateEventTable();
        bookingForm.reset();
        bookingForm.style.display = "none";
    } else {
        alert("Not enough seats available. Please reduce the number of tickets.");
    }
});

// Populate the table on page load
populateEventTable();
