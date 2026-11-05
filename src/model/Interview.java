package model;

import java.sql.Timestamp;

public class Interview {
    private int interviewId;
    private int applicationId;
    private String roundName; // e.g., "Aptitude", "Technical HR", "Managerial"
    private Timestamp scheduledTime;
    private String feedback;
    public String result; // e.g., "PASSED", "FAILED", "PENDING"

    public Interview() {
    }

    public Interview(int interviewId, int applicationId, String roundName, Timestamp scheduledTime, String feedback, String result) {
        this.interviewId = interviewId;
        this.applicationId = applicationId;
        this.roundName = roundName;
        this.scheduledTime = scheduledTime;
        this.feedback = feedback;
        this.result = result;
    }

    public int getInterviewId() { return interviewId; }
    public void setInterviewId(int interviewId) { this.interviewId = interviewId; }

    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public String getRoundName() { return roundName; }
    public void setRoundName(String roundName) { this.roundName = roundName; }

    public Timestamp getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(Timestamp scheduledTime) { this.scheduledTime = scheduledTime; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
}
