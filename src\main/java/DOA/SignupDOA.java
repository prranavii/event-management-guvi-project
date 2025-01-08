import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/event_mgt";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Database connection method
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Method to add a new user
    public void addUser(Signup signup) {
        String query = "INSERT INTO signup (username, email, phone_number, role, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, signup.getUsername());
            stmt.setString(2, signup.getEmail());
            stmt.setString(3, signup.getPhoneNumber());
            stmt.setString(4, signup.getRole());
            stmt.setString(5, signup.getPassword());

            stmt.executeUpdate();
            System.out.println("User added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a user by username
    public Signup getUserByUsername(String username) {
        String query = "SELECT * FROM signup WHERE username = ?";
        Signup signup = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                signup = new Signup();
                signup.setId(rs.getLong("id"));
                signup.setUsername(rs.getString("username"));
                signup.setEmail(rs.getString("email"));
                signup.setPhoneNumber(rs.getString("phone_number"));
                signup.setRole(rs.getString("role"));
                signup.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return signup;
    }

    // Method to update a user's details
    public void updateUser(Signup signup) {
        String query = "UPDATE signup SET email = ?, phone_number = ?, role = ?, password = ? WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, signup.getEmail());
            stmt.setString(2, signup.getPhoneNumber());
            stmt.setString(3, signup.getRole());
            stmt.setString(4, signup.getPassword());
            stmt.setString(5, signup.getUsername());

            stmt.executeUpdate();
            System.out.println("User updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a user by username
    public void deleteUser(String username) {
        String query = "DELETE FROM signup WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
            System.out.println("User deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

