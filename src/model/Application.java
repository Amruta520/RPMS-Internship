package model;

import java.sql.Date;

public class Application {
    private int applicationId;
    private int candidateId;
    private int jobId;
    private Date applicationDate;
    private String status; // e.g., "APPLIED", "ELIGIBLE", "SHORTLISTED", "REJECTED", "PLACED"

    public Application() {
    }

    public Application(int applicationId, int candidateId, int jobId, Date applicationDate, String status) {
        this.applicationId = applicationId;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public int getCandidateId() { return candidateId; }
    public void setCandidateId(int candidateId) { this.candidateId = candidateId; }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
