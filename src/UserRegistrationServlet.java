package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try {
            Connection conn = DatabaseUtils.getConnection();
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.getWriter().write("<h1>Registration Successful!</h1>");
            } else {
                response.getWriter().write("<h1>Registration Failed!</h1>");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            response.getWriter().write("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
