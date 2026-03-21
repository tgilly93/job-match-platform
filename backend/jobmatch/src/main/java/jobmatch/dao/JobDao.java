package jobmatch.dao;

import jobmatch.model.Job;

import java.util.List;

public interface JobDao {

    List<Job> getAllJobs();

    Job getJobById(int jobId);

    int addJob(Job job);
}
