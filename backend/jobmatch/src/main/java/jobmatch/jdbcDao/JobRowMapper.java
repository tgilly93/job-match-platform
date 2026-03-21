package jobmatch.jdbcDao;

import jobmatch.model.Job;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobRowMapper implements RowMapper<Job> {

    @Override
    public Job mapRow(ResultSet rs, int rowNum) throws SQLException {

        Job job = new Job();

        job.setJobId(rs.getInt("job_id"));
        job.setTitle(rs.getString("title"));
        job.setDescription(rs.getString("description"));
        job.setCompanyName(rs.getString("company_name"));
        job.setLocation(rs.getString("location"));
        job.setMaxExperience(rs.getInt("max_experience"));
        job.setMinExperience(rs.getInt("min_experience"));
        job.setSource(rs.getString("source"));
        job.setUrl(rs.getString("url"));

        return job;
    }
}
