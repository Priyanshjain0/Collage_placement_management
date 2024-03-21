package service;


import org.hibernate.Session;
import entites.JobApplication;
import java.util.List;
import java.util.Date;

public interface JobApplicationService {
    
    // Method for registering a job application
    void registerJobApplication(Session session, String studentId, int jobId, Date applicationDate, String status, int companyId);

    // Method for updating the job application details
    boolean updateJobApplicationDetails(Session session, int applicationId, int studentId, int jobId, Date applicationDate, String status, int companyId);

    // Method for updating the job application status
    boolean updateJobApplicationStatus(Session session, int applicationId, String status);

    // Method for updating the job application company
    boolean updateJobApplicationCompany(Session session, int applicationId, int companyId);

    // Method for deleting a job application
    boolean deleteJobApplication(Session session, int applicationId);

    // Method for retrieving a job application by ID
    JobApplication getJobApplicationById(Session session, int applicationId);

    // Method for retrieving all job applications
    List<JobApplication> getAllJobApplications(Session session);

    // Method for retrieving job applications by student ID
    List<JobApplication> getJobApplicationsByStudentId(Session session, int studentId);

    // Method for retrieving job applications by job ID
    List<JobApplication> getJobApplicationsByJobId(Session session, int jobId);
    boolean checkDuplicateApplication(Session session, String studentId, int jobId);

}
