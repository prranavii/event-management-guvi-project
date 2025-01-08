package event.servlet;

import event.model.User;
import event.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(); // Initialize the UserService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For demonstration purposes, retrieve the user from the "database"
        // In a real-world scenario, you would retrieve the logged-in user from the session
        String username = request.getParameter("username");  // Assume username is passed for simplicity
        User user = userService.getUserByUsername(username);  // Fetch user details

        if (user != null) {
            // Show user profile
            response.getWriter().println("<h1>User Profile</h1>");
            response.getWriter().println("Username: " + user.getUsername() + "<br>");
            response.getWriter().println("Email: " + user.getEmail() + "<br>");
            response.getWriter().println("Phone Number: " + user.getPhoneNumber() + "<br>");
        } else {
            response.getWriter().println("User not found.");
        }
    }
}
