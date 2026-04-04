package jobmatch.jdbcDao;

import jobmatch.dao.JobDao;
import jobmatch.dto.JobMatchDto;
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
    public Integer addJob(Job job) {
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

    @Override
    public List<JobMatchDto> getWeightedMatches(int userId) {
        String sql = """
                SELECT
                    j.job_id,
                    j.title,
                    SUM(js.importance_level) AS total_weight,
                    SUM(
                        CASE
                            WHEN us.proficiency_level IS NOT NULL THEN js.importance_level * us.proficiency_level
                            ELSE 0
                        END
                    ) AS matched_weight,
                    ROUND(
                        SUM(
                            CASE
                                WHEN us.proficiency_level IS NOT NULL THEN js.importance_level * us.proficiency_level
                                ELSE 0
                            END
                        ) * 100.0 / (SUM(js.importance_level) * 5),
                        2
                    ) AS match_percentage
                FROM jobs j
                JOIN job_skills js ON j.job_id = js.job_id
                LEFT JOIN user_skills us
                    ON js.skill_id = us.skill_id
                    AND us.user_id = ?
                GROUP BY j.job_id, j.title
                ORDER BY match_percentage DESC
                """;

        return jdbcTemplate.query(sql, new JobMatchRowMapper(), userId);
    }

    @Override
    public void addJobSkill(int jobId, int skillId, int importanceLevel) {
        String sql = "INSERT INTO job_skills (job_id, skill_id, importance_level) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, jobId, skillId, importanceLevel);
    }

    @Override
    public List<String> getMissingSkills(int userId, int jobId) {
        String sql = """
                SELECT
                    s.name AS missing_skill
                FROM jobs j
                JOIN job_skills js  ON j.job_id = js.job_id
                JOIN skills s ON js.skill_id = s.skill_id
                LEFT JOIN user_skills us
                    ON  js.skill_id = us.skill_id
                    AND us.user_id = ?
                WHERE j.job_id = ?
                AND us.skill_id IS NULL
                ORDER BY js.importance_level DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("missing_skill"), userId, jobId);
    }
}
