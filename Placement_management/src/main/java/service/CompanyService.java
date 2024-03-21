package service;

import org.hibernate.Session;
import entites.Company;
import java.util.List;

public interface CompanyService {
    
    // Method for registering a company
    void registerCompany(Session session, String companyName, int numberOfJobOpenings);

    // Method for updating the company details
    void updateCompanyDetails(Session session, int companyId, String companyName, int numberOfJobOpenings);

    // Method for updating the company name
    void updateCompanyName(Session session, int companyId, String companyName);

    // Method for updating the number of job openings in the company
    void updateNumberOfJobOpenings(Session session, int companyId, int numberOfJobOpenings);

    // Method for deleting a company
    void deleteCompany(Session session, int companyId);

    // Method for retrieving a company by ID
    Company getCompanyById(Session session, int companyId);

    // Method for retrieving all companies
    List<Company> getAllCompanies(Session session);
    
    // Method for finding company ID by company name
    int findCompanyIdByCompanyName(Session session, String companyName);
    
    
}