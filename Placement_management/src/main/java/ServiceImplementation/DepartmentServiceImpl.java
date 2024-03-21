package ServiceImplementation;

import org.hibernate.Session;
import org.hibernate.query.Query;
import entites.Department;
import service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    
    public void registerDepartment(Session session, String departmentName) {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        session.save(department);
    }
    
    public boolean registerDepartmentHOD(Session session, String departmentName, String hodName) {
        Department department = session.get(Department.class, departmentName);
        if (department != null) {
            department.setHodName(hodName);
            session.update(department);
            return true;
        }
        return false;
    }

    public boolean updateDepartmentName(Session session, String oldDepartmentName, String newDepartmentName) {
        Department department = session.get(Department.class, oldDepartmentName);
        if (department != null) {
            department.setDepartmentName(newDepartmentName);
            session.update(department);
            return true;
        }
        return false;
    }

    public boolean updateDepartmentHOD(Session session, String departmentName, String newHodName) {
        Department department = session.get(Department.class, departmentName);
        if (department != null) {
            department.setHodName(newHodName);
            session.update(department);
            return true;
        }
        return false;
    }

    public boolean deleteDepartment(Session session, String departmentName) {
        Department department = session.get(Department.class, departmentName);
        if (department != null) {
            session.delete(department);
            return true;
        }
        return false;
    }
    
    public boolean deleteDepartmentHOD(Session session, String departmentName) {
        Department department = session.get(Department.class, departmentName);
        if (department != null) {
            department.setHodName(null);
            session.update(department);
            return true;
        }
        return false;
    }

    public String getHODNameByDepartment(Session session, String departmentName) {
        Department department = session.get(Department.class, departmentName);
        if (department != null) {
            return department.getHodName();
        }
        return null;
    }

    public List<Department> getAllDepartments(Session session) {
        return session.createQuery("FROM Department", Department.class).getResultList();
    }

    public List<String> getAllHODs(Session session) {
        Query<String> query = session.createQuery("SELECT hodName FROM Department WHERE hodName IS NOT NULL", String.class);
        return query.getResultList();
    }
}
