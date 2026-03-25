package jobmatch.jdbcDao;

import jobmatch.model.Skills;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillRowMapper implements RowMapper<Skills> {

    @Override
    public Skills mapRow(ResultSet rs, int rowNum) throws SQLException {

        Skills skill = new Skills();

        skill.setSkillId(rs.getInt("skill_id"));
        skill.setName(rs.getString("name"));

        return skill;
    }
}
