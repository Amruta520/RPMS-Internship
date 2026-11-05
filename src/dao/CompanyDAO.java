package dao;

import model.Company;
import utility.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    // Insert a new company into the database
    public boolean addCompany(Company company) {
        String query = "INSERT INTO companies (company_name, industry, location, contact_email, contact_phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, company.getCompanyName());
            pstmt.setString(2, company.getIndustry());
            pstmt.setString(3, company.getLocation());
            pstmt.setString(4, company.getContactEmail());
            pstmt.setString(5, company.getContactPhone());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all registered companies
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM companies";
        
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Company company = new Company(
                    rs.getInt("company_id"),
                    rs.getString("company_name"),
                    rs.getString("industry"),
                    rs.getString("location"),
                    rs.getString("contact_email"),
                    rs.getString("contact_phone")
                );
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    // Retrieve a single company by its ID
    public Company getCompanyById(int companyId) {
        String query = "SELECT * FROM companies WHERE company_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, companyId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Company(
                    rs.getInt("company_id"),
                    rs.getString("company_name"),
                    rs.getString("industry"),
                    rs.getString("location"),
                    rs.getString("contact_email"),
                    rs.getString("contact_phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
