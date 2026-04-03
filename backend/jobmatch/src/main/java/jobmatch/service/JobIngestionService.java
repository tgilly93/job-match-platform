package jobmatch.service;

import jobmatch.model.Job;
import jobmatch.model.Skills;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
        String url = "https://api.adzuna.com/v1/api/jobs/us/search/1" + "?app_id=" + app_id + "&app_key=" + app_key + "&category=it-jobs" + "&where=ohio" + "&sort_by=date";

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

            System.out.println("Processing job: " + job.getTitle());  //DEBUG
            System.out.println("Description: " + job.getDescription()); //DEBUG

            int jobId = jobService.addJob(job);

            String description = job.getDescription();

            if(description != null) {

                String lowerDescription = description.toLowerCase();

                Map<String, List<String>> skillMap = Map.ofEntries(
                        Map.entry("java", List.of("java")),
                        Map.entry("spring", List.of("spring", "spring boot", "spring framework")),
                        Map.entry("javascript", List.of("javascript", "js", "typescript", "node", "node.js")),
                        Map.entry("sql", List.of("sql", "postgres", "mysql", "postgresql")),
                        Map.entry("cloud", List.of("aws", "teradata", "gcp", "google cloud", "amazon web services")),
                        Map.entry("api", List.of("api", "rest", "restful", "microservices")),
                        Map.entry("react", List.of("react", "react.js", "redux")),
                        Map.entry("jira", List.of("jira")),
                        Map.entry("git", List.of("git", "github")),
                        Map.entry("agile", List.of("agile", "waterfall", "scrum", "kanban")),
                        Map.entry("ci/cd", List.of("ci/cd", "pipeline", "end-to-end", "github actions")),
                        Map.entry("tools", List.of("vscode", "intellij", "postman")),
                        Map.entry("data", List.of("data", "etl", "analysis", "analytics")),
                        Map.entry("microsoft", List.of("microsoft suite", "microsoft word", "excel", "microsoft office")),
                        Map.entry("oop", List.of("oop", "object oriented")),
                        Map.entry("teradata", List.of("teradata")),
                        Map.entry("testing", List.of("junit", "testing", "unit test", "integration test")),
                        Map.entry("database", List.of("database", "db", "data storage"))
                );

                List<String> matchedKeywords = new ArrayList<>();
                Set<String> matchedSkills = new HashSet<>();

                for(Map.Entry<String, List<String>> entry : skillMap.entrySet()) {

                    String normalizedSkill = entry.getKey();
                    List<String> variations  = entry.getValue();

                    for(String keyword : variations) {

                        if(lowerDescription.matches(".*\\b" + keyword + ".*\\b")) {

                            if(matchedSkills.contains(normalizedSkill)) {
                                break;
                            }
                            matchedSkills.add(normalizedSkill);

                            matchedKeywords.add(keyword);
                            System.out.println("MATCH FOUND: " + keyword + " -> " + normalizedSkill);

                            int frequency = countOccurrences(lowerDescription, keyword);
                            int baseImportance = 2;
                            int frequencyBoost = Math.min(2, frequency);
                            int positionBoost = lowerDescription.indexOf(keyword) < 150 ? 1 : 0;

                            int importance = Math.min(5, baseImportance + frequencyBoost + positionBoost);

                            Skills skills = skillsService.getOrCreateSkill(normalizedSkill);

                            System.out.println("Frequency:" + frequency + " | Importance: "  + importance);
                            System.out.println("SkillID: " + skills.getSkillId());

                            jobService.addJobSkill(jobId, skills.getSkillId(), importance);

                            break;
                        }
                    }
                }
                System.out.println("Matched keywords for job " + job.getTitle() +  ": " + matchedKeywords);
            }
            count++;
        }

        return count;
    }

    private int countOccurrences(String text, String keyword) {
        int count = 0;
        int index = 0;

        while((index = text.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }

        return count;

    }

}
