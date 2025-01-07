package com.event.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Pending Event Requests</h1>");
        response.getWriter().println("<p>1. Tech Conference 2025</p>");
        response.getWriter().println("<p>2. Startup Networking Event</p>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eventId = request.getParameter("eventId");
        response.setContentType("text/html");
        response.getWriter().println("<h1>Event Approved</h1>");
        response.getWriter().println("<p>Event ID: " + eventId + " has been approved.</p>");
    }
}
