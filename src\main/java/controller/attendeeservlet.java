package com.event.attendee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/attendee")
public class AttendeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Available Events</h1>");
        response.getWriter().println("<p>1. Tech Conference 2025</p>");
        response.getWriter().println("<p>2. Startup Networking Event</p>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String attendeeName = request.getParameter("attendeeName");
        String eventId = request.getParameter("eventId");
        response.setContentType("text/html");
        response.getWriter().println("<h1>Registration Successful</h1>");
        response.getWriter().println("<p>Name: " + attendeeName + "</p>");
        response.getWriter().println("<p>Event ID: " + eventId + "</p>");
    }
}
