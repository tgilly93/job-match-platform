package jobmatch.controller;

import jobmatch.model.Job;
import jobmatch.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService)  {
        this.jobService = jobService;
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
    public int addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }
}
