Event Management System
A Spring Boot application for managing events, allowing organizers to create events and attendees to view and register for them.

Features
User Roles: Organizer and Attendee.
Event Management: Create, update, and delete events (Organizer); view and register for events (Attendee).
Authentication: Secure login and registration.
Database: Persistent storage using MySQL.
Frontend: JSP pages with JSTL and EL for dynamic views.
Getting Started
Prerequisites
JDK 17 or later
MySQL
Maven
Setup
Clone the repository:

bash
Copy code
git clone https://github.com/username/event-management-system.git
cd event-management-system
Configure the database in application.properties.

Build and run the application:

bash
Copy code
mvn spring-boot:run
Access the application:

Organizer: http://localhost:8080/organizer
Attendee: http://localhost:8080/attendee
License
This project is licensed under the MIT License.

