package dao;

import model.Job;
import utility.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public List<Job> getAllOpenJobs() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs WHERE status = 'OPEN'";
        
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Job job = new Job(
                    rs.getInt("job_id"),
                    rs.getInt("company_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDouble("min_percentage"),
                    rs.getInt("max_backlogs_allowed"),
                    rs.getInt("required_passing_year"),
                    rs.getString("required_qualification"),
                    rs.getString("status")
                );
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public Job getJobById(int jobId) {
        String query = "SELECT * FROM jobs WHERE job_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, jobId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Job(
                    rs.getInt("job_id"),
                    rs.getInt("company_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDouble("min_percentage"),
                    rs.getInt("max_backlogs_allowed"),
                    rs.getInt("required_passing_year"),
                    rs.getString("required_qualification"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
