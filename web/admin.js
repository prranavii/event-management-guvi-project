// Logout functionality
const logoutLink = document.getElementById('logout');

logoutLink.addEventListener('click', (e) => {
    e.preventDefault(); // Prevent the default anchor behavior
    const confirmLogout = confirm('Are you sure you want to logout?');
    if (confirmLogout) {
        alert('You have been logged out.');
        window.location.href = 'login.html'; // Redirect to the login page
    }
});

// Highlight active link
const navLinks = document.querySelectorAll('.sidebar ul li a');
navLinks.forEach((link) => {
    link.addEventListener('click', () => {
        navLinks.forEach((link) => link.classList.remove('active'));
        link.classList.add('active');
    });
});
