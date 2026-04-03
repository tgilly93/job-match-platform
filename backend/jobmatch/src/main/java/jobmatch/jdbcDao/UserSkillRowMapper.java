package jobmatch.jdbcDao;

import jobmatch.model.UserSkill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSkillRowMapper implements RowMapper<UserSkill> {

    @Override
    public UserSkill mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserSkill userSkill = new UserSkill();

        userSkill.setUserId(rs.getInt("user_id"));
        userSkill.setSkillId(rs.getInt("skill_id"));
        userSkill.setProficiencyLevel(rs.getInt("proficiency_level"));

        return userSkill;
    }
}
