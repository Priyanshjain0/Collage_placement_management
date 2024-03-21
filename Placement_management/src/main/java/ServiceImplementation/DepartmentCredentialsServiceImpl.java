package ServiceImplementation;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entites.Department;
import entites.DepartmentCredentials;
import service.DepartmentCredentialsService;

public class DepartmentCredentialsServiceImpl implements DepartmentCredentialsService {

	public void createDepartmentCredentials(Session session, Department department, String username, String password) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			DepartmentCredentials credentials = new DepartmentCredentials();
			credentials.setDepartment(department);
			credentials.setUsername(username);
			credentials.setPassword(password);

			session.save(credentials);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public DepartmentCredentials getDepartmentCredentialsByDepartmentName(Session session, String departmentName) {
		return session.createQuery("FROM DepartmentCredentials WHERE department.departmentName = :departmentName", DepartmentCredentials.class)
				.setParameter("departmentName", departmentName)
				.uniqueResult();
	}

	public String getDepartmentNameByUsername(Session session, String username) {
		DepartmentCredentials credentials = session.createQuery("FROM DepartmentCredentials WHERE username = :username", DepartmentCredentials.class)
				.setParameter("username", username)
				.uniqueResult();
		if (credentials != null) {
			return credentials.getDepartment().getDepartmentName();
		}
		return null;
	}

	public boolean usernameExists(Session session, String username) {
		Long count = session.createQuery("SELECT COUNT(*) FROM DepartmentCredentials WHERE username = :username", Long.class)
				.setParameter("username", username)
				.uniqueResult();
		return count != null && count > 0;
	}

	public boolean authenticate(Session session, String username, String password) {
		DepartmentCredentials credentials = session.createQuery("FROM DepartmentCredentials WHERE username = :username AND password = :password", DepartmentCredentials.class)
				.setParameter("username", username)
				.setParameter("password", password)
				.uniqueResult();
		return credentials != null;
	}

	public boolean updateCredentials(Session session, String username, String newUsername, String newPassword) {
		Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            DepartmentCredentials credentials = session.createQuery("FROM DepartmentCredentials WHERE username = :username", DepartmentCredentials.class)
                                                      .setParameter("username", username)
                                                      .uniqueResult();
            if (credentials != null) {
                credentials.setUsername(newUsername);
                credentials.setPassword(newPassword);
                session.update(credentials);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
	}

	public boolean changeUsername(Session session, String newUsername) {
		 Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            
	            DepartmentCredentials credentials = session.createQuery("FROM DepartmentCredentials WHERE username = :username", DepartmentCredentials.class)
	                                                      .setParameter("username", newUsername)
	                                                      .uniqueResult();
	            if (credentials != null) {
	                credentials.setUsername(newUsername);
	                session.update(credentials);
	                transaction.commit();
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            return false;
	        }
	}

	public boolean changePassword(Session session, String newPassword) {
		Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            DepartmentCredentials credentials = session.createQuery("FROM DepartmentCredentials WHERE username = :username", DepartmentCredentials.class)
                                                      .setParameter("username", newPassword)
                                                      .uniqueResult();
            if (credentials != null) {
                credentials.setPassword(newPassword);
                session.update(credentials);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
	}










