package opertationals;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ServiceImplementation.CompanyServiceImpl;
import ServiceImplementation.DepartmentServiceImpl;
import ServiceImplementation.JobApplicationServiceImpl;
import ServiceImplementation.JobServiceImpl;
import ServiceImplementation.StatusUpdateServiceImpl;
import ServiceImplementation.StudentPersonalServicesImpl;
import entites.Department;
import entites.JobApplication;
import entites.Student;
import service.DepartmentService;

public class PlacementOperatation {
	public static void placementMenu() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sfg = config.buildSessionFactory();
		Session session = sfg.openSession();
		Transaction tx = session.beginTransaction();
		 boolean exit = false;
	        Scanner s = new Scanner(System.in);

	        while (!exit) {
	            System.out.println("-------------------------------");
	            System.out.println("+                              +");
	            System.out.println("+       Placement cell         +");
	            System.out.println("+                              +");
	            System.out.println("-------------------------------");

	            System.out.println("Placement menu");
	            System.out.println("1. Total students");
	            System.out.println("2. Create Company post ");
	            System.out.println("3. No of selected student");
	            System.out.println("4. Show all applications");
	            System.out.println("5. Update application status");
	            System.out.println("6. Update company details");
	            System.out.println("7. Number of HOD with department");
	            System.out.println("8. Change Credentials");
	            System.out.println("9. Exit");

		System.out.println("Enter choice:");

		int choice = s.nextInt();
		switch (choice) {
		case 1:
			// Handle case 1: List of Total students
			System.out.println("1. List of Total students");
			System.out.println("2. List of Student Branch wise");
			int studentChoice = s.nextInt();
			switch (studentChoice) {
			case 1:
				// List all students
				StudentPersonalServicesImpl studentService = new StudentPersonalServicesImpl();
				List<Student> students = studentService.getAllStudents(session);

				if (students != null && !students.isEmpty()) {
					System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
					System.out.println("| Student ID      | Name            | Email           | Phone           | Father's Name   | Department      |");
					System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
					for (Student student : students) {
						System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
								student.getStudentId(), student.getName(), student.getEmail(), student.getPhone(),
								student.getFatherName(), student.getStudentDepartment().getDepartmentName());
					}
					System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
				} else {
					System.out.println("No students found.");
				}
				break;
			case 2:
			    // List of Student Branch wise
				StudentPersonalServicesImpl dept = new StudentPersonalServicesImpl();
				System.out.println("Enter department name");
				String departmentname=s.next();
				
				students=dept.getStudentsByDepartment(session, departmentname);
				if (students != null && !students.isEmpty()) {
					System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
					System.out.println("| Student ID      | Name            | Email           | Phone           | Father's Name   | Department      |");
					System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
					for (Student student : students) {
						System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
								student.getStudentId(), student.getName(), student.getEmail(), student.getPhone(),
								student.getFatherName(), student.getStudentDepartment().getDepartmentName());
					}
					System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
				} else {
					System.out.println("No students found.");
				}
			    
			    break;

			default:
				System.out.println("Invalid choice");
				break;
			}
			break;
		case 2:
			System.out.print("Enter company name: ");
		    String companyName = s.next();
		    System.out.print("Enter number of job openings: ");
		    int numberOfJobOpenings = s.nextInt();
		    // campnay object
		    CompanyServiceImpl company=new CompanyServiceImpl();
		    
		    // Register company
		    company.registerCompany(session, companyName, numberOfJobOpenings);
		    
		    // Register job
		    System.out.print("Enter position name: ");
		    String positionName = s.next();
		    System.out.print("Enter package amount: ");
		    double packageAmount = s.nextDouble();
		    System.out.print("Enter eligibility criteria: ");
		    String eligibilityCriteria = s.next();
		    System.out.print("Enter requirement technology description: ");
		    String requirementTechnologyDescription = s.next();
		    System.out.print("Enter role and responsibilities: ");
		    String roleAndResponsibilities = s.next();
		    System.out.print("Enter any bond: ");
		    String anyBond = s.next();
		    System.out.print("Enter mode of company visit: ");
		    String modeOfCompanyVisit = s.next();
		    
		    
		    int companyid=company.findCompanyIdByCompanyName(session, companyName);
		    
		    // Register job
		    // create object
		    JobServiceImpl jobpost=new JobServiceImpl();
		    jobpost.registerJob(session, positionName, companyName, packageAmount, eligibilityCriteria, requirementTechnologyDescription, roleAndResponsibilities, anyBond, modeOfCompanyVisit, companyid);
		    break;
		case 3:
			// Handle case 3: No of selected student
			// Add your code here
			break;
		case 4:
		    JobApplicationServiceImpl jobApplications = new JobApplicationServiceImpl();
		    List<JobApplication> applications = jobApplications.getAllJobApplications(session);

		    if (applications != null && !applications.isEmpty()) {
		        // Print table header
		        System.out.println("--------------------------------------------------------------------------------------------------");
		        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Student Name", "Student ID", "Company Name", "Job Profile", "Application Status", "Status Update");
		        System.out.println("--------------------------------------------------------------------------------------------------");
		        
		        // Print job applications in table format
		        for (JobApplication application : applications) {
		            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n",
		                    application.getStudent().getName(),
		                    application.getStudent().getStudentId(),
		                    application.getJob().getCompany().getCompanyName(),
		                    application.getJob().getPositionName(),
		                    application.getStatus(),
		                    application.getStatusUpdates() != null && !application.getStatusUpdates().isEmpty() ? application.getStatusUpdates().get(0).getApplicationStatus() : "");
		        }
		        System.out.println("--------------------------------------------------------------------------------------------------");
		    } else {
		        System.out.println("No job applications found.");
		    }
		    break;

		case 5:
		    // Update application status
			StatusUpdateServiceImpl statusUpdateService = new StatusUpdateServiceImpl();
		    System.out.println("Enter the number of status updates:");
		    int numberOfUpdates = s.nextInt();

		    for (int i = 0; i < numberOfUpdates; i++) {
		        System.out.println("Status Update " + (i + 1));
		        
		        // Enter application ID
		        System.out.println("Enter Application ID:");
		        int appIdToUpdate = s.nextInt();
//		        chek application no exit or not
		        
//		        
		        statusUpdateService.applicationExists(session, appIdToUpdate);
		        System.out.println("Enter Which Round:");
		        String round = s.next();
		        
		        // Check if the application ID exists
		        JobApplication applicationToUpdate = session.get(JobApplication.class, appIdToUpdate);
		        if (applicationToUpdate != null) {
		            System.out.println("Enter New Application Status (CAPITAL)-- option (CLEAR)/(REJECT):");
		            String newStatus = s.next();
		            
		            // Register the status update
		            
		            statusUpdateService.registerStatusUpdate(session, appIdToUpdate, newStatus, round);
		            
		            System.out.println("Application Status Updated Successfully!");
		            
		            // Start transaction for updating the status directly in JobApplication entity
		            Transaction tranx = session.beginTransaction();
		            try {
		            	// update application status
		                String hql = "UPDATE JobApplication SET status = :newStatus WHERE applicationId = :appId";
		                String applicationStatus=null;
		                
		                if(newStatus!="REJECT")
		                {
		                	applicationStatus="Active";
		                }
		                else
		                { 
		                	applicationStatus="Close";
		                }
		                Query query = session.createQuery(hql);
		                query.setParameter("newStatus", applicationStatus);
		                query.setParameter("appId", appIdToUpdate);
		                int rowCount = query.executeUpdate();
		                
		                // Commit the transaction
		                tranx.commit();
		                
		                // Notify student portal
		            } catch (Exception e) {
		                // Rollback the transaction if an exception occurs
		                tranx.rollback();
		                e.printStackTrace();
		            }
		        } else {
		            System.out.println("Application ID not found!");
		        }
		    }
		    break;

		case 6:
			// Handle case 6: Update company details
			// Add your code here
			break;
		case 7:
			DepartmentServiceImpl departmentService = new DepartmentServiceImpl(); // Instantiate DepartmentService
		    List<Department> departments = departmentService.getAllDepartments(session); // Retrieve all departments

		    if (departments != null && !departments.isEmpty()) {
		        // Print table header
		        System.out.println("-----------------------------------------------------------------");
		        System.out.printf("| %-20s | %-20s | %-20s |%n", "Department Name", "HOD Name", "Number of HODs");
		        System.out.println("-----------------------------------------------------------------");

		        // Iterate over departments
		        for (Department department : departments) {
		            // Get the HOD name for the current department
		            String hodName = departmentService.getHODNameByDepartment(session, department.getDepartmentName());
		            int numOfHODs = hodName != null ? 1 : 0;

		            // Print department details and number of HODs
		            System.out.printf("| %-20s | %-20s | %-20d |%n", department.getDepartmentName(), hodName != null ? hodName : "-", numOfHODs);
		        }
		        System.out.println("-----------------------------------------------------------------");
		    } else {
		        System.out.println("No departments found.");
		    }
		    break;

		case 8:
            // Handle Change Credentials
            break;
        case 9:
            exit = true;
            System.out.println("Exiting...");
            break;
		default:
			System.out.println("Invalid choice");
			break;
		}
	}
	}

}
