package ServiceImplementation;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entites.Company;
import service.CompanyService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

	public void registerCompany(Session session, String companyName, int numberOfJobOpenings) {
		Company company = new Company();
		company.setCompanyName(companyName);
		company.setNumberOfJobOpenings(numberOfJobOpenings);

		//        session.beginTransaction();
		session.save(company);
		session.getTransaction().commit();
	}

	public void updateCompanyDetails(Session session, int companyId, String companyName, int numberOfJobOpenings) {
		Company company = session.get(Company.class, companyId);
		if (company != null) {
			company.setCompanyName(companyName);
			company.setNumberOfJobOpenings(numberOfJobOpenings);

			session.beginTransaction();
			session.update(company);
			session.getTransaction().commit();
		}
	}

	public void updateCompanyName(Session session, int companyId, String companyName) {
		Company company = session.get(Company.class, companyId);
		if (company != null) {
			company.setCompanyName(companyName);

			session.beginTransaction();
			session.update(company);
			session.getTransaction().commit();
		}
	}

	public void updateNumberOfJobOpenings(Session session, int companyId, int numberOfJobOpenings) {
		Company company = session.get(Company.class, companyId);
		if (company != null) {
			company.setNumberOfJobOpenings(numberOfJobOpenings);

			session.beginTransaction();
			session.update(company);
			session.getTransaction().commit();
		}
	}

	public void deleteCompany(Session session, int companyId) {
		Company company = session.get(Company.class, companyId);
		if (company != null) {
			session.beginTransaction();
			session.delete(company);
			session.getTransaction().commit();
		}
	}

	public Company getCompanyById(Session session, int companyId) {
		return session.get(Company.class, companyId);
	}

	public List<Company> getAllCompanies(Session session) {
		return session.createQuery("FROM Company", Company.class).list();
	}

	public int findCompanyIdByCompanyName(Session session, String companyName) {
		Query<Integer> query = session.createQuery("SELECT c.companyId FROM Company c WHERE c.companyName = :name", Integer.class);
		query.setParameter("name", companyName);
		query.setMaxResults(1); // Limit the result to one
		Integer companyId = query.uniqueResult();
		return companyId != null ? companyId : -1; // Return -1 if no company found
	}

}

