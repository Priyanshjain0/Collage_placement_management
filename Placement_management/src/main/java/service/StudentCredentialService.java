package service;

import org.hibernate.Session;

public interface StudentCredentialService {
	
	void credentialRegister(Session session, String studentId, String username, String password); 
    
    boolean updatePassword(Session session, String username, String newPassword);
    
    boolean deleteCredential(Session session, String username);
    
    boolean isUsernameTaken(Session session, String username);
    
    boolean authenticate(Session session, String username, String password);
    
    String getStudentIdByCredentials(Session session, String username, String password);
    
	

}
