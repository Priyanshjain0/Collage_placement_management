package ServiceImplementation;



import org.hibernate.Session;
import org.hibernate.Transaction;

import entites.EducationDetails;
import entites.Student;
import service.StudentEducationService;

public class StudentEducationServiceImplement  implements StudentEducationService{

	 public void registerStudentEducation(Session session, String studentId, float tenthMarks, float twelfthMarks,
	            String tenthBoard, String twelfthBoard, String currentCourse, float averagePercentage) {
	       
	            // Create a new EducationDetails object
	            EducationDetails educationDetails = new EducationDetails();
	            educationDetails.setStudentId(studentId); // Assuming EducationDetails has studentId property
	            educationDetails.setTenthMarks(tenthMarks);
	            educationDetails.setTwelfthMarks(twelfthMarks);
	            educationDetails.setTenthBoard(tenthBoard);
	            educationDetails.setTwelfthBoard(twelfthBoard);
	            educationDetails.setCurrentCourse(currentCourse);
	            educationDetails.setAveragePercentage(averagePercentage);
	            session.save(educationDetails);

	            
	            
	    }

	 public boolean updateStudentTenthMarks(Session session, String studentId, float tenthMarks) {
	        EducationDetails educationDetails = session.get(EducationDetails.class, studentId);
	        if (educationDetails != null) {
	            educationDetails.setTenthMarks(tenthMarks);
	            session.update(educationDetails);
	            return true;
	        }
	        return false;
	    }

	    public boolean updateStudentTwelfthMarks(Session session, String studentId, float twelfthMarks) {
	        EducationDetails educationDetails = session.get(EducationDetails.class, studentId);
	        if (educationDetails != null) {
	            educationDetails.setTwelfthMarks(twelfthMarks);
	            session.update(educationDetails);
	            return true;
	        }
	        return false;
	    }

	    public boolean updateStudentTenthBoard(Session session, String studentId, String tenthBoard) {
	        EducationDetails educationDetails = session.get(EducationDetails.class, studentId);
	        if (educationDetails != null) {
	            educationDetails.setTenthBoard(tenthBoard);
	            session.update(educationDetails);
	            return true;
	        }
	        return false;
	    }

	    public boolean updateStudentTwelfthBoard(Session session, String studentId, String twelfthBoard) {
	        EducationDetails educationDetails = session.get(EducationDetails.class, studentId);
	        if (educationDetails != null) {
	            educationDetails.setTwelfthBoard(twelfthBoard);
	            session.update(educationDetails);
	            return true;
	        }
	        return false;
	    }

	    public boolean updateStudentCurrentCourse(Session session, String studentId, String currentCourse) {
	        EducationDetails educationDetails = session.get(EducationDetails.class, studentId);
	        if (educationDetails != null) {
	            educationDetails.setCurrentCourse(currentCourse);
	            session.update(educationDetails);
	            return true;
	        }
	        return false;
	    }

	    public boolean updateStudentAveragePercentage(Session session, String studentId, float averagePercentage) {
	        EducationDetails educationDetails = session.get(EducationDetails.class, studentId);
	        if (educationDetails != null) {
	            educationDetails.setAveragePercentage(averagePercentage);
	            session.update(educationDetails);
	            return true;
	        }
	        return false;
	    }

	    public boolean deleteStudentEducation(Session session, String studentId) {
	        EducationDetails educationDetails = session.get(EducationDetails.class, studentId);
	        if (educationDetails != null) {
	            session.delete(educationDetails);
	            return true;
	        }
	        return false;
	    }

	    public EducationDetails getStudentEducationById(Session session, String studentId) {
	        return session.get(EducationDetails.class, studentId);
	    }
	
	public boolean checkEducationByStudentId(Session session, String studentId) {
		EducationDetails student = session.get(EducationDetails.class, studentId);
	    
	    // Return true if the student exists (i.e., student is not null), otherwise return false
	    return student != null;
	}

	
    

   

    

	}


