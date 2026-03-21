package jobmatch.jdbcDao;

import jobmatch.dao.JobDao;
import jobmatch.model.Job;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcJobDao implements JobDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcJobDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Job> getAllJobs() {
        String sql = "SELECT * FROM jobs";
        return jdbcTemplate.query(sql, new JobRowMapper());
    }

    @Override
    public Job getJobById(int jobId) {
        String sql = "SELECT * FROM jobs WHERE job_id = ?";
        return jdbcTemplate.queryForObject(sql, new JobRowMapper(), jobId);
    }

    @Override
    public int addJob(Job job) {

        String sql = "INSERT INTO jobs (title, company_name, location, description, min_experience, max_experience, source, url) "  +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING job_id";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class,
                job.getTitle(),
                job.getCompanyName(),
                job.getLocation(),
                job.getDescription(),
                job.getMinExperience(),
                job.getMaxExperience(),
                job.getSource(),
                job.getUrl()
        );

        if (id == null) {
            throw new RuntimeException("Failed to add job. No id returned.");
        }

        return id;
    }
}
