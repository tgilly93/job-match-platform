package jobmatch.controller;

import jobmatch.dto.JobMatchDto;
import jobmatch.model.Job;
import jobmatch.service.JobIngestionService;
import jobmatch.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final JobIngestionService jobIngestionService;

    public JobController(JobService jobService, JobIngestionService jobIngestionService)  {
        this.jobService = jobService;
        this.jobIngestionService = jobIngestionService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable("id") int id) {
        return jobService.getJobById(id);
    }

    @PostMapping
    public Integer addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    @GetMapping("/ingest")
    public String ingestJobs() {
        int count = jobIngestionService.fetchAndSaveJobs();
        return count + " jobs ingested!";
    }

    @GetMapping("/match/{userId}")
    public List<JobMatchDto> getMatches(@PathVariable("userId") int userId) {
        return jobService.getWeightedMatches(userId);
    }

    @GetMapping("/{jobId}/missing-skills")
    public List<String> getMissingSkills(@PathVariable("jobId") int jobId, @RequestParam("userId") int userId) {
        return jobService.getMissingSkills(userId, jobId);
    }
}
