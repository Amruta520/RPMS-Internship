package dao;

import java.sql.*;
import java.util.*;
import utility.DBconnection;

public class CandidateDAO {

    public List<Map<String, Object>> getAllCandidates() {

        List<Map<String, Object>> list = new ArrayList<>();

        String sql = "SELECT candidate_id, candidate_code, full_name, email, mobile, " +
                "qualification, college, passing_year, percentage, current_location, " +
                "placement_status, primary_technology FROM candidates ORDER BY candidate_id";

        try (
            Connection con = DBconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Map<String, Object> candidate = new LinkedHashMap<>();

                candidate.put("candidate_id", rs.getInt("candidate_id"));
                candidate.put("candidate_code", rs.getString("candidate_code"));
                candidate.put("full_name", rs.getString("full_name"));
                candidate.put("email", rs.getString("email"));
                candidate.put("mobile", rs.getString("mobile"));
                candidate.put("qualification", rs.getString("qualification"));
                candidate.put("primary_technology", rs.getString("primary_technology"));
                candidate.put("college", rs.getString("college"));
                candidate.put("passing_year", rs.getInt("passing_year"));
                candidate.put("percentage", rs.getBigDecimal("percentage"));
                candidate.put("current_location", rs.getString("current_location"));
                candidate.put("placement_status", rs.getString("placement_status"));

                list.add(candidate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public String addCandidate(Map<String, String> data) throws Exception {

        String sql = "INSERT INTO candidates " +
            "(candidate_code, full_name, email, mobile, date_of_birth, gender, " +
            "qualification, college, university, passing_year, percentage, " +
            "backlog_count, primary_technology, current_location, " +
            "preferred_location, profile_status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String fullName = required(data, "fullName");
        String email = required(data, "email");
        String mobile = required(data, "mobile");
        String qualification = required(data, "qualification");
        String passingYear = required(data, "passingYear");

        try (Connection con = DBconnection.getConnection()) {

            if (con == null) {
                throw new SQLException("Database connection failed");
            }

            int nextId;

            try (
                PreparedStatement ps = con.prepareStatement(
                    "SELECT COALESCE(MAX(candidate_id), 0) + 1 FROM candidates"
                );
                ResultSet rs = ps.executeQuery()
            ) {
                rs.next();
                nextId = rs.getInt(1);
            }

            String candidateCode = String.format(
                "CAN-%d-%04d",
                java.time.Year.now().getValue(),
                nextId
            );

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                int i = 1;

                ps.setString(i++, candidateCode);
                ps.setString(i++, fullName);
                ps.setString(i++, email);
                ps.setString(i++, mobile);

                String dob = data.get("dob");

                if (dob == null || dob.trim().isEmpty()) {
                    ps.setNull(i++, Types.DATE);
                } else {
                    ps.setDate(i++, java.sql.Date.valueOf(dob));
                }

                setNullable(ps, i++, data.get("gender"));
                ps.setString(i++, qualification);
                setNullable(ps, i++, data.get("college"));
                setNullable(ps, i++, data.get("university"));

                ps.setInt(i++, Integer.parseInt(passingYear));

                String percentage = data.get("percentage");

                if (percentage == null || percentage.trim().isEmpty()) {
                    ps.setNull(i++, Types.DECIMAL);
                } else {
                    ps.setBigDecimal(
                        i++,
                        new java.math.BigDecimal(percentage)
                    );
                }

                String backlog = data.get("backlog");

                ps.setInt(
                    i++,
                    (backlog == null || backlog.trim().isEmpty())
                        ? 0 : Integer.parseInt(backlog)
                );

                setNullable(ps, i++, data.get("technology"));
                setNullable(ps, i++, data.get("currentLocation"));
                setNullable(ps, i++, data.get("preferredLocation"));

                String status = data.get("profileStatus");

                ps.setString(
                    i++,
                    (status == null || status.trim().isEmpty())
                        ? "ACTIVE" : status.trim().toUpperCase()
                );

                ps.executeUpdate();
            }

            return candidateCode;
        }
    }

    private static String required(
        Map<String, String> data, String key
    ) {

        String value = data.get(key);

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Missing required field: " + key
            );
        }

        return value.trim();
    }

    private static void setNullable(
        PreparedStatement ps, int index, String value
    ) throws SQLException {

        if (value == null || value.trim().isEmpty()) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, value.trim());
        }
    }
}