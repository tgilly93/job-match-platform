package jobmatch.jdbcDao;

import jobmatch.dao.UserDao;
import jobmatch.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate)  {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), userId);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO users (name, email, years_experience, desired_role, location) VALUES (?, ?, ?, ?, ?) RETURNING user_id";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class,
                user.getName(),
                user.getEmail(),
                user.getYearsExperience(),
                user.getDesiredRole(),
                user.getLocation());

        if (id == null) {
            throw new RuntimeException("Failed to add user. No ID returned.");
        }
        return id;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, years_experience = ?, desired_role = ?, location = ? WHERE user_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getYearsExperience(),
                user.getDesiredRole(),
                user.getLocation(),
                user.getUserId()
        );

        if (rowsAffected == 0) {
            throw new RuntimeException("No user updated with ID " + user.getUserId());
        }
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, userId);

        if (rowsAffected == 0) {
            throw new RuntimeException("No user deleted with ID " + userId);
        }
    }
}
