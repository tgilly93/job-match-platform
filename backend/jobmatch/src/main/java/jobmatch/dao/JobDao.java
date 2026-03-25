package jobmatch.dao;

import jobmatch.dto.JobMatchDto;
import jobmatch.model.Job;

import java.util.List;

public interface JobDao {

    List<Job> getAllJobs();

    Job getJobById(int jobId);

    Integer addJob(Job job);

    List<JobMatchDto> getWeightedMatches(int userId);
}
