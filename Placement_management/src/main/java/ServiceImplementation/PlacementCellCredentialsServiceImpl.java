package ServiceImplementation;

import org.hibernate.Session;
import org.hibernate.query.Query;
import entites.PlacementCellCredentials;
import service.PlacementCellCredentialsService;

public class PlacementCellCredentialsServiceImpl implements PlacementCellCredentialsService {

    
    public void registerPlacementCellCredentials(Session session, String username, String password) {
        PlacementCellCredentials credentials = new PlacementCellCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        session.save(credentials);
    }

   
    public boolean authenticatePlacementCellCredentials(Session session, String username, String password) {
        Query<PlacementCellCredentials> query = session.createQuery("FROM PlacementCellCredentials WHERE username = :username AND password = :password", PlacementCellCredentials.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.uniqueResult() != null;
    }

   
    public boolean updatePlacementCellPassword(Session session, String username, String newPassword) {
        PlacementCellCredentials credentials = session.get(PlacementCellCredentials.class, username);
        if (credentials != null) {
            credentials.setPassword(newPassword);
            session.update(credentials);
            return true;
        }
        return false;
    }

   
    public boolean updatePlacementCellUsername(Session session, String oldUsername, String newUsername) {
        PlacementCellCredentials credentials = session.get(PlacementCellCredentials.class, oldUsername);
        if (credentials != null) {
            credentials.setUsername(newUsername);
            session.update(credentials);
            return true;
        }
        return false;
    }

    
    public boolean changePlacementCellCredentials(Session session, String oldUsername, String newUsername, String newPassword) {
        if (updatePlacementCellUsername(session, oldUsername, newUsername)) {
            return updatePlacementCellPassword(session, newUsername, newPassword);
        }
        return false;
    }

   
    public boolean deletePlacementCellCredentials(Session session, String username) {
        PlacementCellCredentials credentials = session.get(PlacementCellCredentials.class, username);
        if (credentials != null) {
            session.delete(credentials);
            return true;
        }
        return false;
    }
    public boolean checkUsername(Session session, String username) {
        PlacementCellCredentials credentials = session.get(PlacementCellCredentials.class, username);
        return credentials != null;
    }
}
