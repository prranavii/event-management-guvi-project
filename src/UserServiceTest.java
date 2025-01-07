package event.service;

import event.model.User;
import event.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
    }

    @Test
    void testSignup_Success() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null); // No existing user

        ResponseEntity<String> response = userService.signup(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully.", response.getBody());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testSignup_UsernameAlreadyExists() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user); // User already exists

        ResponseEntity<String> response = userService.signup(user);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Username already exists.", response.getBody());
        verify(userRepository, never()).save(user); // User should not be saved
    }

    @Test
    void testLogin_Success() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(user.getPassword()).thenReturn("password123"); // Correct password

        ResponseEntity<String> response = userService.login(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Login successful.", response.getBody());
    }

    @Test
    void testLogin_Failure_InvalidCredentials() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null); // User does not exist

        ResponseEntity<String> response = userService.login(user);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials.", response.getBody());
    }
}
