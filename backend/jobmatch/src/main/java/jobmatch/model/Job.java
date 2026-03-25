package jobmatch.model;

public class Job {

    private Integer jobId;
    private String title;
    private String companyName;
    private String location;
    private String description;
    private Integer minExperience;
    private Integer maxExperience;
    private String source;
    private String url;

    public Job() {}

    public Job(Integer jobId, String title, String companyName, String location, String description, Integer minExperience,  Integer maxExperience, String source, String url) {
        this.jobId = jobId;
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.description = description;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        this.source = source;
        this.url = url;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", title='" + title + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", minExperience=" + minExperience +
                ", maxExperience=" + maxExperience +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
