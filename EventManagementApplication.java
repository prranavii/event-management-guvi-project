package event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Main Application class
@SpringBootApplication
public class EventManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);
	}

	// User Entity (Model)
	@Entity
	public static class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String username;
		private String email;
		private String password;
		private String phoneNumber;
		private String role;

		// Getters and setters
		public Long getId() { return id; }
		public void setId(Long id) { this.id = id; }
		public String getUsername() { return username; }
		public void setUsername(String username) { this.username = username; }
		public String getEmail() { return email; }
		public void setEmail(String email) { this.email = email; }
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
		public String getPhoneNumber() { return phoneNumber; }
		public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
		public String getRole() { return role; }
		public void setRole(String role) { this.role = role; }
	}

	// User Repository (Data Access)
	public interface UserRepository extends JpaRepository<User, Long> {
		User findByUsername(String username);
	}

	// User Service (Business Logic)
	@Service
	public static class UserService {
		@Autowired
		private UserRepository userRepository;

		private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		public ResponseEntity<String> signup(User user) {
			if (userRepository.findByUsername(user.getUsername()) != null) {
				return ResponseEntity.badRequest().body("Username already exists.");
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return ResponseEntity.ok("User registered successfully.");
		}

		public ResponseEntity<String> login(User user) {
			User existingUser = userRepository.findByUsername(user.getUsername());
			if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
				return ResponseEntity.status(401).body("Invalid credentials.");
			}
			return ResponseEntity.ok("Login successful.");
		}
	}

	// User Controller (API Endpoints)
	@RestController
	@RequestMapping("/api/users")
	public static class UserController {
		@Autowired
		private UserService userService;

		@PostMapping("/signup")
		public ResponseEntity<String> signup(@RequestBody User user) {
			return userService.signup(user);
		}

		@PostMapping("/login")
		public ResponseEntity<String> login(@RequestBody User user) {
			return userService.login(user);
		}
	}

	// Security Configuration
	@Component
	public static class SecurityConfig {
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
			http.csrf().disable()
					.authorizeHttpRequests()
					.requestMatchers("/api/users/signup", "/api/users/login").permitAll()
					.anyRequest().authenticated()
					.and()
					.httpBasic();
			return http.build();
		}
	}

	// Event Controller (Additional Controller Example)
	@Controller
	public static class OrganizerController {
		@Autowired
		private EventService eventService;

		@GetMapping("/organizer")
		public String showDashboard(Model model) {
			model.addAttribute("events", eventService.getAllEvents());
			return "organizerDashboard";
		}

		@PostMapping("/organizer/addEvent")
		public String addEvent(@RequestParam String eventName,
							   @RequestParam String eventDate,
							   @RequestParam String eventTime,
							   @RequestParam String eventVenue,
							   @RequestParam double ticketPrice) {
			eventService.addEvent(new Event(eventName, eventDate, eventTime, eventVenue, ticketPrice));
			return "redirect:/organizer";
		}
	}

	// Event Service (Business Logic for Event)
	@Service
	public static class EventService {
		// This can be implemented with a real database or in-memory storage
		public void addEvent(Event event) {
			// Logic to add event
		}

		public List<Event> getAllEvents() {
			// Logic to fetch all events
			return List.of(); // Example, return an empty list
		}
	}

	// Event Entity (Model for Event)
	@Entity
	public static class Event {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private String date;
		private String time;
		private String venue;
		private double ticketPrice;

		public Event(String name, String date, String time, String venue, double ticketPrice) {
			this.name = name;
			this.date = date;
			this.time = time;
			this.venue = venue;
			this.ticketPrice = ticketPrice;
		}

		// Getters and setters
		public Long getId() { return id; }
		public void setId(Long id) { this.id = id; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		public String getDate() { return date; }
		public void setDate(String date) { this.date = date; }
		public String getTime() { return time; }
		public void setTime(String time) { this.time = time; }
		public String getVenue() { return venue; }
		public void setVenue(String venue) { this.venue = venue; }
		public double getTicketPrice() { return ticketPrice; }
		public void setTicketPrice(double ticketPrice) { this.ticketPrice = ticketPrice; }
	}
}
