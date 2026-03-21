package jobmatch.jdbcDao;

import jobmatch.dto.JobMatch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobMatchRowMapper implements RowMapper<JobMatch> {

    @Override
    public JobMatch mapRow(ResultSet rs, int rowNum) throws SQLException {

        JobMatch match = new JobMatch();

        match.setJobId(rs.getInt("job_id"));
        match.setTitle(rs.getString("title"));
        match.setTotalWeight(rs.getInt("total_weight"));
        match.setMatchedWeight(rs.getInt("matched_weight"));
        match.setMatchPercentage(rs.getDouble("match_percentage"));

        return match;
    }
}
