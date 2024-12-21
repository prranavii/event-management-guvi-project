// Select form and input elements
const signupForm = document.querySelector('form');
const usernameInput = document.getElementById('username');
const emailInput = document.getElementById('email');
const phoneNumberInput = document.getElementById('phone_number');
const roleSelect = document.getElementById('role');

// Event listener for form submission
signupForm.addEventListener('submit', (e) => {
    e.preventDefault(); // Prevent the default form submission

    const username = usernameInput.value.trim();
    const email = emailInput.value.trim();
    const phoneNumber = phoneNumberInput.value.trim();
    const role = roleSelect.value;

    // Validate fields
    if (!username || !email || !phoneNumber || !role) {
        alert('All fields are required. Please fill out the form completely.');
        return;
    }

    // Validate email format
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Please enter a valid email address.');
        return;
    }

    // Validate phone number (basic check for numeric values)
    const phoneRegex = /^\d{10}$/; // Adjust for your region's phone format
    if (!phoneRegex.test(phoneNumber)) {
        alert('Please enter a valid 10-digit phone number.');
        return;
    }

    // Simulated signup logic
    alert(`Signup successful!\n\nUsername: ${username}\nEmail: ${email}\nPhone Number: ${phoneNumber}\nRole: ${role}`);

    // Redirect to a success page or reset the form
    signupForm.reset();
});
