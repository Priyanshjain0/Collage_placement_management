package service;

import org.hibernate.Session;


public interface PlacementCellCredentialsService {

    // Method to register placement cell credentials
    void registerPlacementCellCredentials(Session session, String username, String password);

    // Method to authenticate placement cell credentials
    boolean authenticatePlacementCellCredentials(Session session, String username, String password);

    // Method to update placement cell password
    boolean updatePlacementCellPassword(Session session, String username, String newPassword);

    // Method to update placement cell username
    boolean updatePlacementCellUsername(Session session, String oldUsername, String newUsername);

    // Method to change placement cell username and password
    boolean changePlacementCellCredentials(Session session, String oldUsername, String newUsername, String newPassword);

    // Method to delete placement cell credentials
    boolean deletePlacementCellCredentials(Session session, String username);
    boolean checkUsername(Session session, String username);
}
