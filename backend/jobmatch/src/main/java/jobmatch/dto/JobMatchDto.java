package jobmatch.dto;

public class JobMatchDto {

    private int jobId;
    private String title;
    private int totalWeight;
    private int matchedWeight;
    private double matchPercentage;

    public JobMatchDto() {}

    public JobMatchDto(int jobId, String title, int totalWeight, int matchedWeight, double matchPercentage) {
        this.jobId = jobId;
        this.title = title;
        this.totalWeight = totalWeight;
        this.matchedWeight = matchedWeight;
        this.matchPercentage = matchPercentage;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getMatchedWeight() {
        return matchedWeight;
    }

    public void setMatchedWeight(int matchedWeight) {
        this.matchedWeight = matchedWeight;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    @Override
    public String toString() {
        return "JobMatchDto{" +
                "jobId=" + jobId +
                ", title='" + title + '\'' +
                ", totalWeight=" + totalWeight +
                ", matchedWeight=" + matchedWeight +
                ", matchPercentage=" + matchPercentage +
                '}';
    }
}
