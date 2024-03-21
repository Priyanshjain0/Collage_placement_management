package service;


import org.hibernate.Session;

import entites.Department;
import entites.DepartmentCredentials;

public interface DepartmentCredentialsService {
	

     //  Create a new department credentials entry
    void createDepartmentCredentials(Session session,Department department, String username, String password);

    // Retrieve department credentials by department name
    DepartmentCredentials getDepartmentCredentialsByDepartmentName(Session session,String departmentName);

    // Retrieve department name by username
    String getDepartmentNameByUsername(Session session,String username);

    // Check if username exists in department credentials
    boolean usernameExists(Session session,String username);

    // Authenticate a user
    boolean authenticate(Session session,String username, String password);

    // Update username and/or password
    boolean updateCredentials(Session session,String username, String newUsername, String newPassword);
    
   // chnage username    
    boolean changeUsername(Session session,String username);
 // change password
    boolean changePassword(Session session,String username);
    
    
    
    
    
}