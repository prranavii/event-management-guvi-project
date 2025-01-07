package event.repository;

import event.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
    }

    @Test
    void testFindByUsername() {
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        User foundUser = userRepository.findByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void testFindByUsername_UserNotFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(null);

        User foundUser = userRepository.findByUsername("testUser");

        assertNull(foundUser);
        verify(userRepository, times(1)).findByUsername("testUser");
    }
}
