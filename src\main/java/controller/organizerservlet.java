package com.event.organizer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/organizer")
public class OrganizerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>My Events</h1>");
        response.getWriter().println("<p>1. Tech Conference 2025</p>");
        response.getWriter().println("<p>2. AI Workshop</p>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eventName = request.getParameter("eventName");
        String eventDate = request.getParameter("eventDate");
        response.setContentType("text/html");
        response.getWriter().println("<h1>Event Created Successfully</h1>");
        response.getWriter().println("<p>Event Name: " + eventName + "</p>");
        response.getWriter().println("<p>Event Date: " + eventDate + "</p>");
    }
}
