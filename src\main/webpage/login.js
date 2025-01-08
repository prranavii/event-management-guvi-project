// Select form elements
const loginForm = document.querySelector('form');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

// Event listener for form submission
loginForm.addEventListener('submit', (e) => {
    e.preventDefault(); // Prevent default form submission

    const username = usernameInput.value.trim();
    const password = passwordInput.value.trim();

    // Basic validation
    if (!username || !password) {
        alert('Please fill out both fields.');
        return;
    }

    // Simulated login logic (Replace this with actual server-side validation)
    if (username === 'admin' && password === 'password123') {
        alert('Login successful!');
        window.location.href = 'dashboard.html'; // Redirect to the dashboard page
    } else {
        alert('Invalid username or password.');
    }
});
