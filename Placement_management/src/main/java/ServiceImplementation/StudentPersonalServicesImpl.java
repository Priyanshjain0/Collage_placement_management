package ServiceImplementation;

import service.StudentPersionalServices;
import org.hibernate.Session;
import org.hibernate.query.Query;

import entites.Department;
import entites.Student;
import entites.StudentCredential;

import java.util.List;

public class StudentPersonalServicesImpl implements StudentPersionalServices {




	public boolean updateStudentName(Session session, String studentId, String name) {
		Student student = session.get(Student.class, studentId);
		if (student != null) {
			student.setName(name);
			session.update(student);
			return true;
		}
		return false;
	}

	public boolean updateStudentPhone(Session session, String studentId, String phone) {
		Student student = session.get(Student.class, studentId);
		if (student != null) {
			student.setPhone(phone);
			session.update(student);
			return true;
		}
		return false;
	}

	public boolean updateStudentEmail(Session session, String studentId, String email) {
		Student student = session.get(Student.class, studentId);
		if (student != null) {
			student.setEmail(email);
			session.update(student);
			return true;
		}
		return false;
	}

	public boolean updateStudentFatherName(Session session, String studentId, String fatherName) {
		Student student = session.get(Student.class, studentId);
		if (student != null) {
			student.setFatherName(fatherName);
			session.update(student);
			return true;
		}
		return false;
	}

	public boolean updateStudentAddress(Session session, String studentId, String address) {
		Student student = session.get(Student.class, studentId);
		if (student != null) {
			student.setAddress(address);
			session.update(student);
			return true;
		}
		return false;
	}



	public boolean deleteStudent(Session session, String studentId) {
		Student student = session.get(Student.class, studentId);
		if (student != null) {
			session.delete(student);
			return true;
		}
		return false;
	}

	public Student getStudentById(Session session, String studentId) {
		return session.get(Student.class, studentId);
	}

	public List<Student> getAllStudents(Session session) {
		return session.createQuery("FROM Student", Student.class).getResultList();
	}

	public void registerStudent(Session session,String StudentID, String name, String phone, String email, String fatherName,
            String address, Department department) {
		// TODO Auto-generated method stub

		Student student = new Student();
		student.setStudentId(StudentID);
		student.setName(name);
		student.setPhone(phone);
		student.setEmail(email);
		student.setFatherName(fatherName);
		student.setAddress(address);
		student.setStudentDepartment(department);
		System.out.println(" ------------------------save blow----------");
		StudentCredential cd =new StudentCredential();
		cd.setStudent(student);//set in credintial
		Student st=cd.getStudent();
		System.out.println(st.getStudentId()+"--------------d-----------");
		session.save(student);
	}

	public boolean updateStudentDepartment(Session session, int studentId, Department department) {
	    Student student = session.get(Student.class, studentId);
	    if (student != null) {
	        student.setStudentDepartment(department); // Corrected method name
	        session.update(student);
	        return true;
	    }
	    return false;
	}

	public boolean checkStudentId(Session session, String studentId) {
	    // Attempt to retrieve the student with the provided ID from the database
	    Student student = session.get(Student.class, studentId);
	    
	    // Return true if the student exists (i.e., student is not null), otherwise return false
	    return student != null;
	}
	public List<Student> getStudentsByDepartment(Session session, String departmentName) {
        try {
            // Create query to retrieve students by department name
            Query<Student> query = session.createQuery("FROM Student WHERE studentDepartment.departmentName = :deptName", Student.class);
            query.setParameter("deptName", departmentName);
            List<Student> students = query.getResultList();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

	public boolean updateStudentDepartment(Session session, String studentId, Department department) {
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            student.setStudentDepartment(department);
            session.update(student);
            return true;
        }
        return false;
    }
	}

