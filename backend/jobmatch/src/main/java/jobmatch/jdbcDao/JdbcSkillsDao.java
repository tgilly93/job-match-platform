package jobmatch.jdbcDao;

import jobmatch.dao.SkillsDao;
import jobmatch.model.Skills;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcSkillsDao implements SkillsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSkillsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Skills> getAllSkills() {
        String sql = "SELECT * FROM skills";
        return jdbcTemplate.query(sql, new SkillRowMapper());
    }

    @Override
    public Skills getSkillById(int skillId) {
        String sql = "SELECT * FROM skills WHERE skill_id = ?";
        return jdbcTemplate.queryForObject(sql, new SkillRowMapper(), skillId);
    }

    @Override
    public Integer addSkill(Skills skill) {
        String sql = "INSERT INTO skills (name) VALUES (?) RETURNING skill_id";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, skill.getName());
        return id != null ? id: -1;
    }
}
