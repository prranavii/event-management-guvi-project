package event.servlet;

import event.model.User;
import event.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/userList", "/userDetails"})
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(); // Initialize user service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/userList".equals(action)) {
            List<User> userList = userService.getAllUsers(); // Fetch all users
            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userList.jsp");
            dispatcher.forward(request, response);
        } else if ("/userDetails".equals(action)) {
            String id = request.getParameter("id");
            User user = userService.getUserById(Long.parseLong(id)); // Fetch user by ID
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userDetails.jsp");
            dispatcher.forward(request, response);
        }
    }
}
package event.servlet;

import event.model.User;
import event.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserControllerServlet", urlPatterns = {"/userList", "/userDetails", "/registerUser"})
public class UserControllerServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            switch (action) {
                case "/userList":
                    listUsers(request, response);
                    break;
                case "/userDetails":
                    showUserDetails(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page Not Found");
            }
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            if ("/registerUser".equals(action)) {
                registerUser(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page Not Found");
            }
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
        request.setAttribute("errorMessage", e.getMessage());
        e.printStackTrace(); // Log the error for debugging
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/error.jsp");
        dispatcher.forward(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userList", userService.getAllUsers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userList.jsp");
        dispatcher.forward(request, response);
    }

    private void showUserDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = request.getParameter("id");
            User user = userService.getUserById(Long.parseLong(userId));
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userDetails.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid user ID format.");
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String role = request.getParameter("role");

            User user = new User(username, email, password, phoneNumber, role);
            userService.registerUser(user);

            response.sendRedirect("userList");
        } catch (Exception e) {
            throw new RuntimeException("Error while registering the user: " + e.getMessage());
        }
    }
}
