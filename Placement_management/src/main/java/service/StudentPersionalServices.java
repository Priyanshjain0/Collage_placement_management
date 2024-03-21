package service;

import java.util.List;
import org.hibernate.Session;
import entites.Department;
import entites.Student;

public interface StudentPersionalServices {
    void registerStudent(Session session,String StudentID, String name, String phone, String email, String fatherName,
            String address, Department department);

    boolean updateStudentName(Session session, String studentId, String name);

    boolean updateStudentPhone(Session session, String studentId, String phone);

    boolean updateStudentEmail(Session session, String studentId, String email);

    boolean updateStudentFatherName(Session session, String studentId, String fatherName);

    boolean updateStudentAddress(Session session, String studentId, String address);

    boolean updateStudentDepartment(Session session, String studentId, Department department);

    boolean deleteStudent(Session session, String studentId);

    Student getStudentById(Session session, String studentId);
   
    boolean checkStudentId(Session session, String studentId);

    List<Student> getAllStudents(Session session);
 // Method to retrieve all students department-wise
    List<Student> getStudentsByDepartment(Session session, String departmentName);
}
