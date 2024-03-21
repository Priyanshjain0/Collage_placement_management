package service;

import org.hibernate.Session;
import entites.StatusUpdate;
import java.util.List;

public interface StatusUpdateService {
    
    // Method for registering a status update
    void registerStatusUpdate(Session session, int applicationId, String applicationStatus, String roundName);
    
    // Method for updating application status with round name
    void updateApplicationStatusWithRoundName(Session session, int applicationId, String applicationStatus, String roundName);
    
    // Method for retrieving a status update by ID
    StatusUpdate getStatusUpdateById(Session session, int statusUpdateId);

    // Method for retrieving status updates by application ID
    List<StatusUpdate> getStatusUpdatesByApplicationId(Session session, int applicationId);
    
    // Method for retrieving all status updates by company ID
    List<StatusUpdate> getStatusUpdatesByCompanyId(Session session, int companyId);
    
   boolean applicationExists(Session session, int applicationid);
}
