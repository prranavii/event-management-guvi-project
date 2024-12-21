package event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);
	}
}

---

		package event.controller;

import event.model.User;
import event.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

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

---

		package event.model;

import jakarta.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	private String role;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

---

		package event.repository;

import event.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}

---

		package event.service;

import event.model.User;
import event.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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

---

		package event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/api/users/signup", "/api/users/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
		return http.build();
	}
	@Controller
	public class OrganizerController {

		@GetMapping("/organizer")
		public String showDashboard(Model model) {
			model.addAttribute("events", eventService.getAllEvents()); // Add events to the model
			return "organizerDashboard"; // The JSP name (organizerDashboard.jsp)
		}

		@PostMapping("/organizer/addEvent")
		public String addEvent(@RequestParam String eventName,
							   @RequestParam String eventDate,
							   @RequestParam String eventTime,
							   @RequestParam String eventVenue,
							   @RequestParam double ticketPrice) {
			// Add event to database or in-memory list
			eventService.addEvent(new Event(eventName, eventDate, eventTime, eventVenue, ticketPrice));
			return "redirect:/organizer"; // Redirect back to dashboard
		}
	}
