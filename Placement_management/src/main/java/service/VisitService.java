package service;

import org.hibernate.Session;
import entites.Visits;
import java.util.Date;
import java.util.List;

public interface VisitService {
    
    // Method for registering a visit
    void registerVisit(Session session, int companyId, Date scheduleDate, String companyStatus);

    // Method for updating the visit details
    void updateVisitDetails(Session session, int visitId, int companyId, Date scheduleDate, String companyStatus);

    // Method for updating the visit schedule date
    void updateVisitScheduleDate(Session session, int visitId, Date scheduleDate);

    // Method for updating the company status in the visit
    void updateCompanyStatusInVisit(Session session, int visitId, String companyStatus);

    // Method for deleting a visit
    void deleteVisit(Session session, int visitId);

    // Method for retrieving a visit by ID
    Visits getVisitById(Session session, int visitId);

    // Method for retrieving all visits
    List<Visits> getAllVisits(Session session);

    // Method for retrieving visits by company ID
    List<Visits> getVisitsByCompanyId(Session session, int companyId);
}
