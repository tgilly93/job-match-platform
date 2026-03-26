package jobmatch.service;

import jobmatch.model.Job;
import jobmatch.model.Skills;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobIngestionService {

    @Value("${adzuna.app.id}")
    private String app_id;
    @Value("${adzuna.app.key}")
    private String app_key;

    private final JobService jobService;
    private final SkillsService skillsService;
    private final RestTemplate restTemplate = new RestTemplate();

    public JobIngestionService(JobService jobService, SkillsService skillsService)  {
        this.jobService = jobService;
        this.skillsService = skillsService;
    }

    @SuppressWarnings("unchecked")
    public int fetchAndSaveJobs() {
        String url = "https://api.adzuna.com/v1/api/jobs/us/search/1" + "?app_id=" + app_id + "&app_key=" + app_key;

        Map<String,Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String,Object>> results = Optional.ofNullable((List<Map<String,Object>>)response.get("results")).orElse(Collections.emptyList());

        int count = 0;

        for (Map<String,Object> jobData : results) {

            Job job = new Job();

            job.setTitle((String) jobData.get("title"));

            Map<String,Object> company = (Map<String, Object>) jobData.get("company");
            if(company != null) {
                job.setCompanyName((String) company.get("display_name"));
            }

            Map<String,Object> location = (Map<String, Object>) jobData.get("location");
            if(location != null) {
                job.setLocation((String) location.get("display_name"));
            }

            job.setDescription((String) jobData.get("description"));
            job.setSource("Adzuna");
            job.setUrl((String) jobData.get("redirect_url"));
            job.setMinExperience(0);
            job.setMaxExperience(10);

            int jobId = jobService.addJob(job);

            String description = job.getDescription();

            if(description != null) {

                List<String> keywords = List.of("java", "sql", "react", "spring", "spring boot", "teradata", "jira");

                for(String keyword : keywords) {

                    if(description.toLowerCase().contains(keyword)) {

                        Skills skills = skillsService.getOrCreateSkill(keyword);
                        int importance = description.toLowerCase().indexOf(keyword) < 200 ? 5 : 3;

                        jobService.addJobSkill(jobId, skills.getSkillId(), importance);
                    }

                }
            }

            count++;
        }

        return count;
    }

}
