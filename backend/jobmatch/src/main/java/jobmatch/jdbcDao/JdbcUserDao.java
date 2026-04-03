package jobmatch.jdbcDao;

import jobmatch.dao.UserDao;
import jobmatch.model.User;
import jobmatch.model.UserSkill;
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

    @Override
    public int addUserSkill(UserSkill userSkill) {
        String sql = "INSERT INTO user_skills (user_id, skill_id, proficiency_level) "  +
                "VALUES (?, ?, ?) " + "ON CONFLICT (user_id, skill_id) DO UPDATE SET proficiency_level = EXCLUDED.proficiency_level";

        int rowsAffected = jdbcTemplate.update(sql,
                userSkill.getUserId(),
                userSkill.getSkillId(),
                userSkill.getProficiencyLevel()
        );

        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to add or update user skill.");
        }
        return rowsAffected;
    }

    @Override
    public List<UserSkill> getUserSkills(int userId) {
        String sql = "SELECT * FROM user_skills WHERE user_id = ?";

        return jdbcTemplate.query(sql, new UserSkillRowMapper(), userId);
    }
}
