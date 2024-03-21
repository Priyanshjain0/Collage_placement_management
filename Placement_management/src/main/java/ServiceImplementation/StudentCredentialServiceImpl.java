package ServiceImplementation;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entites.Student;
import entites.StudentCredential;
import service.StudentCredentialService;

public class StudentCredentialServiceImpl implements StudentCredentialService {

	 public void credentialRegister(Session session, String studentId, String username, String password ) {
	        try {
	            Student student = session.get(Student.class, studentId); // Retrieve the student entity using the provided student ID
	            
	            if (student != null) {
	                StudentCredential studentCredential = new StudentCredential();
	                studentCredential.setStudent(student);
	                studentCredential.setUsername(username);
	                studentCredential.setPassword(password);
	                session.save(studentCredential);
	            } else {
	                System.out.println("Error: Student with ID " + studentId + " does not exist.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	public boolean updatePassword(Session session, String username, String newPassword) {
		try {
			StudentCredential studentCredential = session.createQuery(
					"FROM StudentCredential WHERE username = :username",
					StudentCredential.class
					).setParameter("username", username)
					.uniqueResult();
			if (studentCredential != null) {
				studentCredential.setPassword(newPassword);
				session.update(studentCredential);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteCredential(Session session, String username) {
		try {
			Query<StudentCredential> query = session.createQuery(
					"FROM StudentCredential WHERE username = :username",
					StudentCredential.class
					);
			query.setParameter("username", username);
			StudentCredential studentCredential = query.uniqueResult();
			if (studentCredential != null) {
				session.delete(studentCredential);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isUsernameTaken(Session session, String username) {
		try {
			Long count = session.createQuery(
					"SELECT COUNT(*) FROM StudentCredential WHERE username = :username",
					Long.class
					).setParameter("username", username)
					.uniqueResult();
			return count != null && count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean authenticate(Session session, String username, String password) {
		try {
			Query<StudentCredential> query = session.createQuery(
					"FROM StudentCredential WHERE username = :username AND password = :password",
					StudentCredential.class
					);
			query.setParameter("username", username);
			query.setParameter("password", password);
			return query.uniqueResult() != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getStudentIdByCredentials(Session session, String username, String password) {
		try {
			Query<String> query = session.createQuery(
					"SELECT sc.student.studentId FROM StudentCredential sc WHERE sc.username = :username AND sc.password = :password",
					String.class
					);
			query.setParameter("username", username);
			query.setParameter("password", password);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


	}
}

