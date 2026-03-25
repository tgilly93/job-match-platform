package jobmatch.jdbcDao;

import jobmatch.dto.JobMatchDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobMatchRowMapper implements RowMapper<JobMatchDto> {

    @Override
    public JobMatchDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        JobMatchDto match = new JobMatchDto();

        match.setJobId(rs.getInt("job_id"));
        match.setTitle(rs.getString("title"));
        match.setTotalWeight(rs.getInt("total_weight"));
        match.setMatchedWeight(rs.getInt("matched_weight"));
        match.setMatchPercentage(rs.getDouble("match_percentage"));

        return match;
    }
}
