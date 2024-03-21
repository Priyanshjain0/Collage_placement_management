package service;

import org.hibernate.Session;
import entites.Job;
import java.util.List;

public interface JobService {
    
    // Method for registering a job
    void registerJob(Session session, String positionName, String companyName, double Package, String eligibilityCriteria, String requirementTechnologyDescription, String roleResponsibilities, String anyBond, String modeOfCompanyVisit, int companyId);
    
    // Method for updating the job details
    boolean updateJobDetails(Session session, int jobId, String positionName, String companyName, double Package, String eligibilityCriteria, String requirementTechnologyDescription, String roleResponsibilities, boolean anyBond, String modeOfCompanyVisit, int companyId);

    // Method for updating the job position name
    boolean updateJobPositionName(Session session, int jobId, String positionName);

    // Method for updating the job company name
    boolean updateJobCompanyName(Session session, int jobId, String companyName);

    // Method for updating the job package
    boolean updateJobPackage(Session session, int jobId, double Package);

    // Method for updating the job eligibility criteria
    boolean updateJobEligibilityCriteria(Session session, int jobId, String eligibilityCriteria);

    // Method for updating the job requirement technology description
    boolean updateJobRequirementTechnologyDescription(Session session, int jobId, String requirementTechnologyDescription);

    // Method for updating the job role responsibilities
    boolean updateJobRoleResponsibilities(Session session, int jobId, String roleResponsibilities);

    // Method for updating the job bond status
    boolean updateJobAnyBond(Session session, int jobId, boolean anyBond);

    // Method for updating the job mode of company visit
    boolean updateJobModeOfCompanyVisit(Session session, int jobId, String modeOfCompanyVisit);

    // Method for deleting a job
    boolean deleteJob(Session session, int jobId);

    // Method for retrieving a job by ID
    Job getJobById(Session session, int jobId);

    // Method for retrieving all jobs
    List<Job> getAllJobs(Session session);

    // Method for retrieving jobs by company ID
    List<Job> getJobsByCompanyId(Session session, int companyId);
    
    // Method for finding a job by its ID
    Job findJobById(Session session, int jobId);

	
}
