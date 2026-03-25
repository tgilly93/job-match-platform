package jobmatch.service;

import jobmatch.dao.JobDao;
import jobmatch.dto.JobMatchDto;
import jobmatch.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobDao jobDao;

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobDao.getAllJobs();
    }

    @Override
    public Job getJobById(int jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public Integer addJob(Job job) {
        return jobDao.addJob(job);
    }

    @Override
    public List<JobMatchDto> getWeightedMatches(int userId) {
        return jobDao.getWeightedMatches(userId);
    }
}
