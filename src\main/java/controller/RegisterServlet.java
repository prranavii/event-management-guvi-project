package event.servlet;

import event.model.User;
import event.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();  // Initialize the UserService, which handles business logic
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        // Create User object
        User newUser = new User(username, email, password, phoneNumber);

        // Register the user
        boolean isRegistered = userService.registerUser(newUser);

        // Redirect to appropriate page based on registration result
        if (isRegistered) {
            response.sendRedirect("/login");  // Redirect to login page after successful registration
        } else {
            response.getWriter().println("Registration failed. Try again!");
        }
    }
}
