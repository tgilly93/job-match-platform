package jobmatch.service;

import jobmatch.model.Job;

import java.util.List;

public interface JobService {

    public List<Job> getAllJobs();

    Job getJobById(int jobId);

    int addJob(Job job);
}
