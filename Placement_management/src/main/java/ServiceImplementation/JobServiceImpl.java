package ServiceImplementation;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;

import entites.Company;
import entites.Job;
import service.JobService;

public class JobServiceImpl implements JobService {

	public void registerJob(Session session, String positionName, String companyName, double packageAmount,
	        String eligibilityCriteria, String requirementTechnologyDescription, String roleResponsibilities,
	        String anyBond, String modeOfCompanyVisit, int companyId) {
	    // Retrieve the company from the database using the companyId
	    Company company = session.get(Company.class, companyId);
	    if (company == null) {
	        System.out.println("Company with ID " + companyId + " does not exist.");
	        return;
	    }

	    // Create a new job
	    Job job = new Job();
	    job.setPositionName(positionName);
	    job.setCompany(company); // Set the company object instead of companyId
	    job.setPackageAmount(packageAmount);
	    job.setEligibilityCriteria(eligibilityCriteria);
	    job.setRequirementTechnologyDescription(requirementTechnologyDescription);
	    job.setRoleAndResponsibilities(roleResponsibilities);
	    job.setAnyBond(anyBond );
	    job.setModeOfCompanyVisit(modeOfCompanyVisit);

	    // Begin transaction, save the job, and commit transaction
	    session.beginTransaction();
	    session.save(job);
	    session.getTransaction().commit();
	}

	public boolean updateJobDetails(Session session, int jobId, String positionName, String companyName, double packageAmount,
	        String eligibilityCriteria, String requirementTechnologyDescription, String roleResponsibilities,
	        boolean anyBond, String modeOfCompanyVisit, int companyId) {
	    Job job = session.get(Job.class, jobId);
	    if (job != null) {
	        // Retrieve the company from the database using the companyId
	        Company company = session.get(Company.class, companyId);
	        if (company == null) {
	            System.out.println("Company with ID " + companyId + " does not exist.");
	            return false;
	        }

	        // Update job details
	        job.setPositionName(positionName);
	        job.setCompany(company); // Set the company object instead of companyId
	        job.setPackageAmount(packageAmount);
	        job.setEligibilityCriteria(eligibilityCriteria);
	        job.setRequirementTechnologyDescription(requirementTechnologyDescription);
	        job.setRoleAndResponsibilities(roleResponsibilities);
	        job.setAnyBond(anyBond ? "Yes" : "No");
	        job.setModeOfCompanyVisit(modeOfCompanyVisit);

	        // Begin transaction, update the job, and commit transaction
	        session.beginTransaction();
	        session.update(job);
	        session.getTransaction().commit();
	        return true;
	    }
	    return false;
	}

	public boolean updateJobPositionName(Session session, int jobId, String positionName) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			job.setPositionName(positionName);
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobCompanyName(Session session, int companyId, String companyName) {
		
		 Company company = session.get(Company.class, companyId);
		if (company != null) {
			
			company.setCompanyName(companyName);
			session.beginTransaction();
			session.update(company);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobPackage(Session session, int jobId, double Package) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			job.setPackageAmount(Package);
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobEligibilityCriteria(Session session, int jobId, String eligibilityCriteria) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			job.setEligibilityCriteria(eligibilityCriteria);
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobRequirementTechnologyDescription(Session session, int jobId,
			String requirementTechnologyDescription) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			job.setRequirementTechnologyDescription(requirementTechnologyDescription);
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobRoleResponsibilities(Session session, int jobId, String roleResponsibilities) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			job.setRoleAndResponsibilities(roleResponsibilities);
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobAnyBond(Session session, int jobId, boolean anyBond) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			job.setAnyBond(anyBond ? "Yes" : "No");
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean updateJobModeOfCompanyVisit(Session session, int jobId, String modeOfCompanyVisit) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			job.setModeOfCompanyVisit(modeOfCompanyVisit);
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean deleteJob(Session session, int jobId) {
		Job job = session.get(Job.class, jobId);
		if (job != null) {
			session.beginTransaction();
			session.delete(job);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public Job getJobById(Session session, int jobId) {
		return session.get(Job.class, jobId);
	}

	public List<Job> getAllJobs(Session session) {
		 try {
		        List<Job> jobs = session.createQuery("FROM Job", Job.class).list();
		        System.out.println("Number of jobs retrieved: " + jobs.size()); // Log the number of jobs retrieved
		        return jobs;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
	}

	public List<Job> getJobsByCompanyId(Session session, int companyId) {
		Query<Job> query = session.createQuery("FROM Job WHERE companyId = :companyId", Job.class);
		query.setParameter("companyId", companyId);
		return query.list();
	}
	public Job findJobById(Session session, int jobId) {
		return session.find(Job.class, jobId);
	}
}