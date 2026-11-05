package model;

import java.sql.Date;

public class Candidate {
    private int candidateId;
    private String fullName;
    private String email;
    private String phone;
    private String qualification;
    private int passingYear;
    private double percentage;
    private int backlogs;
    private String skills;
    private Date registrationDate;

    // Default Constructor
    public Candidate() {
    }

    // Parameterized Constructor
    public Candidate(int candidateId, String fullName, String email, String phone, 
                     String qualification, int passingYear, double percentage, 
                     int backlogs, String skills, Date registrationDate) {
        this.candidateId = candidateId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.passingYear = passingYear;
        this.percentage = percentage;
        this.backlogs = backlogs;
        this.skills = skills;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(int backlogs) {
        this.backlogs = backlogs;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Candidate [candidateId=" + candidateId + ", fullName=" + fullName + 
               ", email=" + email + ", qualification=" + qualification + "]";
    }
}
