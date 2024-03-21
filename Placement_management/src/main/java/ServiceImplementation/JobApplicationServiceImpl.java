package ServiceImplementation;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entites.Job;
import entites.JobApplication;
import entites.Student;
import service.JobApplicationService;

public class JobApplicationServiceImpl implements JobApplicationService {

	public void registerJobApplication(Session session, String studentId, int jobId, Date applicationDate, String status,
			int companyId) {
		Student student = session.get(Student.class, studentId);
		Job job = session.get(Job.class, jobId);

		if (student != null && job != null) {
			JobApplication jobApplication = new JobApplication();
			jobApplication.setStudent(student);
			jobApplication.setJob(job);
			jobApplication.setApplicationDate(applicationDate);
			jobApplication.setStatus(status);
			jobApplication.setCompanyId(companyId);


			session.save(jobApplication);
			session.getTransaction().commit();
		}
	}

	public boolean updateJobApplicationDetails(Session session, int applicationId, int studentId, int jobId,
			Date applicationDate, String status, int companyId) {
		JobApplication jobApplication = session.get(JobApplication.class, applicationId);
		if (jobApplication != null) {
			Student student = session.get(Student.class, studentId);
			Job job = session.get(Job.class, jobId);
			if (student != null && job != null) {
				jobApplication.setStudent(student);
				jobApplication.setJob(job);
				jobApplication.setApplicationDate(applicationDate);
				jobApplication.setStatus(status);
				jobApplication.setCompanyId(companyId);

				session.beginTransaction();
				session.update(jobApplication);
				session.getTransaction().commit();
				return true;
			}
		}
		return false;
	}

	public boolean updateJobApplicationStatus(Session session, int applicationId, String status) {
		JobApplication jobApplication = session.get(JobApplication.class, applicationId);
		if (jobApplication != null) {
			jobApplication.setStatus(status);
			session.beginTransaction();
			session.update(jobApplication);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobApplicationCompany(Session session, int applicationId, int companyId) {
		JobApplication jobApplication = session.get(JobApplication.class, applicationId);
		if (jobApplication != null) {
			jobApplication.setCompanyId(companyId);
			session.beginTransaction();
			session.update(jobApplication);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean deleteJobApplication(Session session, int applicationId) {
		JobApplication jobApplication = session.get(JobApplication.class, applicationId);
		if (jobApplication != null) {
			session.beginTransaction();
			session.delete(jobApplication);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public JobApplication getJobApplicationById(Session session, int applicationId) {
		return session.get(JobApplication.class, applicationId);
	}

	public List<JobApplication> getAllJobApplications(Session session) {
		return session.createQuery("FROM JobApplication", JobApplication.class).getResultList();
	}

	public List<JobApplication> getJobApplicationsByStudentId(Session session, int studentId) {
		return session.createQuery("FROM JobApplication WHERE studentId = :studentId", JobApplication.class)
				.setParameter("studentId", studentId).getResultList();
	}

	public List<JobApplication> getJobApplicationsByJobId(Session session, int jobId) {
		return session.createQuery("FROM JobApplication WHERE jobId = :jobId", JobApplication.class)
				.setParameter("jobId", jobId).getResultList();
	}

	public boolean checkDuplicateApplication(Session session, String studentId, int jobId) {
		Long count = (Long) session.createQuery(
				"SELECT COUNT(*) FROM JobApplication WHERE student.studentId = :studentId AND job.jobId = :jobId")
				.setParameter("studentId", studentId)
				.setParameter("jobId", jobId)
				.uniqueResult();
		return count > 0;
	}
	public List<Object[]> CompleteGetApplicationStatusByStudentId(Session session, String studentId) {
	    try {
	    	String hql =  "SELECT " +
	    		    "s.name AS student_name, " +
	    		    "s.studentId, " +
	    		    "j.company.companyName, " +  // Replace ja.company with j.company
	    		    "j.positionName AS job_profile, " +
	    		    "ja.status AS application_status, " +
	    		    "su.applicationStatus AS status_update " +
	    		    "FROM " +
	    		    "JobApplication AS ja " +
	    		    "JOIN " +
	    		    "ja.student AS s " +
	    		    "JOIN " +
	    		    "ja.job AS j " +
	    		    "LEFT JOIN " +
	    		    "ja.statusUpdates AS su " +
	    		    "LEFT JOIN " +
	    		    "j.company AS c " +  // Replace ja.company with j.company
	    		    "WHERE " +
	    		    "ja.student.studentId = :studentId";
    Query<Object[]> query = session.createQuery(hql);
    query.setParameter("studentId", studentId);
    return query.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // Handle the exception appropriately
	    }
	}

	}
	

