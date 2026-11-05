package model;

public class Job {
    private int jobId;
    private int companyId;
    private String title;
    private String description;
    private double minPercentage;
    private int maxBacklogsAllowed;
    private int requiredPassingYear;
    private String requiredQualification;
    private String status; // e.g., "OPEN", "CLOSED"

    public Job() {
    }

    public Job(int jobId, int companyId, String title, String description, double minPercentage, 
               int maxBacklogsAllowed, int requiredPassingYear, String requiredQualification, String status) {
        this.jobId = jobId;
        this.companyId = companyId;
        this.title = title;
        this.description = description;
        this.minPercentage = minPercentage;
        this.maxBacklogsAllowed = maxBacklogsAllowed;
        this.requiredPassingYear = requiredPassingYear;
        this.requiredQualification = requiredQualification;
        this.status = status;
    }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getMinPercentage() { return minPercentage; }
    public void setMinPercentage(double minPercentage) { this.minPercentage = minPercentage; }

    public int getMaxBacklogsAllowed() { return maxBacklogsAllowed; }
    public void setMaxBacklogsAllowed(int maxBacklogsAllowed) { this.maxBacklogsAllowed = maxBacklogsAllowed; }

    public int getRequiredPassingYear() { return requiredPassingYear; }
    public void setRequiredPassingYear(int requiredPassingYear) { this.requiredPassingYear = requiredPassingYear; }

    public String getRequiredQualification() { return requiredQualification; }
    public void setRequiredQualification(String requiredQualification) { this.requiredQualification = requiredQualification; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
