package service;

import model.Candidate;
import model.Job;

public class EligibilityEngine {

    public static boolean checkEligibility(Candidate candidate, Job job) {
        // Check minimum percentage requirement
        if (candidate.getPercentage() < job.getMinPercentage()) {
            return false;
        }
        
        // Check maximum allowed backlogs
        if (candidate.getBacklogs() > job.getMaxBacklogsAllowed()) {
            return false;
        }
        
        // Check passing year requirement
        if (candidate.getPassingYear() != job.getRequiredPassingYear()) {
            return false;
        }
        
        // Check qualification match
        if (!candidate.getQualification().equalsIgnoreCase(job.getRequiredQualification())) {
            return false;
        }
        
        return true;
    }
}
