package ServiceImplementation;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entites.JobApplication;
import entites.StatusUpdate;
import service.StatusUpdateService;
import java.util.List;

public class StatusUpdateServiceImpl implements StatusUpdateService {

	public void registerStatusUpdate(Session session, int applicationId, String applicationStatus, String roundName) {
	    JobApplication application = session.get(JobApplication.class, applicationId);
	    
	    StatusUpdate statusUpdate = new StatusUpdate();
	    statusUpdate.setJobApplication(application); // Set the JobApplication directly
	    statusUpdate.setApplicationStatus(applicationStatus);
	    statusUpdate.setRoundName(roundName);
	    
	    
	    session.save(statusUpdate);
	    session.getTransaction().commit();
	}

    public void updateApplicationStatusWithRoundName(Session session, int applicationId, String applicationStatus, String roundName) {
        StatusUpdate statusUpdate = getStatusUpdateById(session, applicationId);
        if (statusUpdate != null) {
            statusUpdate.setApplicationStatus(applicationStatus);
            statusUpdate.setRoundName(roundName);
            
            session.beginTransaction();
            session.update(statusUpdate);
            session.getTransaction().commit();
        }
    }

    public StatusUpdate getStatusUpdateById(Session session, int statusUpdateId) {
        return session.get(StatusUpdate.class, statusUpdateId);
    }

    public List<StatusUpdate> getStatusUpdatesByApplicationId(Session session, int applicationId) {
        return session.createQuery("FROM StatusUpdate WHERE applicationId = :applicationId", StatusUpdate.class)
                .setParameter("applicationId", applicationId)
                .getResultList();
    }

    public List<StatusUpdate> getStatusUpdatesByCompanyId(Session session, int companyId) {
        return session.createQuery("SELECT s FROM StatusUpdate s JOIN JobApplication j ON s.applicationId = j.applicationId WHERE j.companyId = :companyId", StatusUpdate.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }
    public boolean applicationExists(Session session, int applicationId) {
        // Create the HQL query to count the number of status updates for the given application ID
        String hql = "SELECT COUNT(*) FROM StatusUpdate su WHERE su.jobApplication.applicationId = :applicationId";
        
        // Execute the query
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("applicationId", applicationId);
        
        // Get the count of status updates
        long count = query.uniqueResult();
        
        // If count is greater than 0, application exists
        return count > 0;
    }
}
