package service;

import org.hibernate.Session;
import entites.Department;
import java.util.List;

public interface DepartmentService {
    
    // Method for registering a department without HOD
    void registerDepartment(Session session, String departmentName);
    
    // Method for registering a department with HOD
    boolean registerDepartmentHOD(Session session, String departmentName, String hodName);

    // Method for updating the department name
    boolean updateDepartmentName(Session session, String oldDepartmentName, String newDepartmentName);

    // Method for updating the HOD of a department
    boolean updateDepartmentHOD(Session session, String departmentName, String newHodName);

    // Method for deleting a department
    boolean deleteDepartment(Session session, String departmentName);
    
    // Method for deleting the HOD of a department
    boolean deleteDepartmentHOD(Session session, String departmentName);

    // Method for retrieving the HOD name by department name
    String getHODNameByDepartment(Session session, String departmentName);

    // Method for retrieving all departments
    List<Department> getAllDepartments(Session session);

    // Method for retrieving all HODs
    List<String> getAllHODs(Session session);
}
