package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        try {
            Connection conn = DatabaseUtils.getConnection();
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                out.write("<h1>User Profile</h1>");
                out.write("<p>Username: " + rs.getString("username") + "</p>");
                out.write("<p>Email: " + rs.getString("email") + "</p>");
            } else {
                out.write("<h1>No user found with username: " + username + "</h1>");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            response.getWriter().write("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
