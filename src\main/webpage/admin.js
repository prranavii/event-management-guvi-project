// admin.js

// Handle Logout
document.getElementById('logout').addEventListener('click', function (e) {
    e.preventDefault();
    if (confirm('Are you sure you want to log out?')) {
        window.location.href = '/logout'; // Redirect to logout endpoint
    }
});

// Display confirmation message for event approval
const form = document.querySelector('form');
form.addEventListener('submit', function (e) {
    const eventId = document.getElementById('eventId').value.trim();
    if (!eventId) {
        e.preventDefault();
        alert('Please enter a valid Event ID.');
    } else {
        alert(`Event ID ${eventId} submitted for approval!`);
    }
});
document.getElementById('logout').addEventListener('click', function (e) {
    e.preventDefault();
    if (confirm('Are you sure you want to log out?')) {
        window.location.href = '/logout'; // Replace with your logout route
    }
});
