package model;

public class Company {
    private int companyId;
    private String companyName;
    private String industry;
    private String location;
    private String contactEmail;
    private String contactPhone;

    public Company() {
    }

    public Company(int companyId, String companyName, String industry, String location, String contactEmail, String contactPhone) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.industry = industry;
        this.location = location;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    @Override
    public String toString() {
        return "Company [companyId=" + companyId + ", companyName=" + companyName + "]";
    }
}
