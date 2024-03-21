package service;

import org.hibernate.Session;

import entites.EducationDetails;

public interface StudentEducationService {
	// Method for registering education details for a student
	 public void registerStudentEducation(Session session, String studentId, float tenthMarks, float twelfthMarks,
	            String tenthBoard, String twelfthBoard, String currentCourse, float averagePercentage);
 // Method for updating the student's tenth marks
     boolean updateStudentTenthMarks(Session session, String studentId, float tenthMarks);

    // Method for updating the student's twelfth marks
     boolean updateStudentTwelfthMarks(Session session, String studentId, float twelfthMarks);

    // Method for updating the student's tenth board
     boolean updateStudentTenthBoard(Session session, String studentId, String tenthBoard);

    // Method for updating the student's twelfth board
     boolean updateStudentTwelfthBoard(Session session, String studentId, String twelfthBoard);

    // Method for updating the student's current course
     boolean updateStudentCurrentCourse(Session session, String studentId, String currentCourse);

    // Method for updating the student's average percentage
     boolean updateStudentAveragePercentage(Session session, String studentId, float averagePercentage);

    // Method for deleting education details of a student
     boolean deleteStudentEducation(Session session, String studentId);
     
     boolean checkEducationByStudentId(Session session, String studentId);

    // Method for retrieving education details of a student by student ID
    EducationDetails getStudentEducationById(Session session, String studentId);


}
