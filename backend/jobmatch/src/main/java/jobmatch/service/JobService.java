package jobmatch.service;

import jobmatch.dto.JobMatchDto;
import jobmatch.model.Job;

import java.util.List;

public interface JobService {

    List<Job> getAllJobs();

    Job getJobById(int jobId);

    Integer addJob(Job job);

    List<JobMatchDto> getWeightedMatches(int userId);

    void addJobSkill(int jobId, int skillId, int importanceLevel);

    List<String> getMissingSkills(int userId, int jobId);
}
