package jobmatch.dto;

public class JobMatch {

    private int jobId;
    private String title;
    private int totalWeight;
    private int matchedWeight;
    private double matchPercentage;

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

}
