@WebServlet("/organizer")
public class OrganizerDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/organizerDashboard.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventName = request.getParameter("event-name");
        String eventDate = request.getParameter("event-date");
        String eventTime = request.getParameter("event-time");
        String eventVenue = request.getParameter("event-venue");
        double ticketPrice = Double.parseDouble(request.getParameter("ticket-price"));

        // Save event data to the database or in-memory list
        // For simplicity, we'll assume it gets saved.

        // Redirect to the dashboard after saving the event
        response.sendRedirect("organizer");
    }
}
