package dao;

import model.Application;
import utility.DBconnection;
import java.sql.*;

public class ApplicationDAO {

    public boolean applyForJob(Application app) {
        String query = "INSERT INTO applications (candidate_id, job_id, application_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, app.getCandidateId());
            pstmt.setInt(2, app.getJobId());
            pstmt.setDate(3, app.getApplicationDate());
            pstmt.setString(4, app.getStatus());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
