package opertationals;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ServiceImplementation.DepartmentCredentialsServiceImpl;
import ServiceImplementation.JobServiceImpl;
import ServiceImplementation.StudentCredentialServiceImpl;
import ServiceImplementation.StudentEducationServiceImplement;
import ServiceImplementation.StudentPersonalServicesImpl;
import entites.Department;
import entites.Job;
import entites.Student;
import entites.StudentCredential;

public class HodOperatation {

	public void hodmenu(String departmentName)
	{
		System.out.println("-------------------------------");
		System.out.println("+                              +");
		System.out.println("+         HoD menu             +");
		System.out.println("+                              +");
		System.out.println("-------------------------------");
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sfg = config.buildSessionFactory();
		Session session = sfg.openSession();
		Transaction tx = session.beginTransaction();

		Scanner s = new Scanner(System.in);
		 boolean exit = false;

	        while (!exit) {
	            System.out.println("-------------------------------");
	            System.out.println("+                              +");
	            System.out.println("+         HoD menu             +");
	            System.out.println("+                              +");
	            System.out.println("-------------------------------");

	            System.out.println("HOD menu");
	            System.out.println("1. Student Registration");
	            System.out.println("2. Student details Update");
	            System.out.println("3. All students department wise");
	            System.out.println("4. All Jobs");
	            System.out.println("5. Job application department wise");
	            System.out.println("6. Password change");
	            System.out.println("7. Exit");

	            System.out.println("Enter choice:");


		System.out.println("Enter choice");
		StudentPersonalServicesImpl std = new StudentPersonalServicesImpl();
		int choice = s.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Student Registration selected");
			System.out.println("Enter Student ID:");
			String studentID = s.next();

			System.out.println("1. Enter Personal Details");
			System.out.println("2. Enter Education Details");
			System.out.println("Enter choice:");


			int c = s.nextInt();
			switch (c) {
			case 1:
				if (!std.checkStudentId(session, studentID)) {
					System.out.print("Name: ");
					String name = s.next();
					System.out.print("Phone: ");
					String phone = s.next();
					System.out.print("Email: ");
					String email = s.next();
					System.out.print("Father's Name: ");
					String fatherName = s.next();
					System.out.print("Address: ");
					String address = s.next();
					System.out.print("Department: ");
					String departmentname = s.next();

					Department department = (Department) session.createQuery("FROM Department d WHERE d.departmentName = :departmentName")
							.setParameter("departmentName", departmentname)
							.uniqueResult();

					if (department != null) {
						std.registerStudent(session, studentID, name, phone, email, fatherName, address, department);
						System.out.println("Student personal details successfully entered");

						// Automatic insertion of student credential with username as name+studentID and password as phone
						StudentCredentialServiceImpl credentialService = new StudentCredentialServiceImpl();
						credentialService.credentialRegister(session, studentID, name + studentID, phone);

						tx.commit();
						System.out.println("Student user ID and password generated");
						System.out.println("Username: " + name + studentID);
						System.out.println("Password: " + phone);
					} else {
						System.out.println("Error: Department not registered. Please choose a valid department.");
					}
				} else {
					System.out.println("Error: You are already registered");
				}
				break;

			case 2:
				if (std.checkStudentId(session, studentID)) {
					System.out.println("Enter Education Details");
					System.out.print("Enter Tenth Marks: ");
					float tenthMarks = s.nextFloat();
					System.out.print("Enter Twelfth Marks: ");
					float twelfthMarks = s.nextFloat();
					System.out.print("Enter Tenth Board: ");
					String tenthBoard = s.next();
					System.out.print("Enter Twelfth Board: ");
					String twelfthBoard = s.next();
					System.out.print("Enter Current Course: ");
					String currentCourse = s.next();
					System.out.print("Enter Average Percentage: ");
					float averagePercentage = s.nextFloat();
					StudentEducationServiceImplement stdedu = new StudentEducationServiceImplement();
					stdedu.registerStudentEducation(session, studentID, tenthMarks, twelfthMarks, tenthBoard, twelfthBoard, currentCourse, averagePercentage);
					System.out.println("Student registered successfully!");
					tx.commit();
					session.close();
				} else {
					System.out.println("Error: Must insert Personal Details before you enter");
				}
				break;
			}
			break;

		case 2:
			System.out.println("Enter choice");
			System.out.println("1.Update Persional Details");
			System.out.println("2.Update Persional Details");


			int updationchoice = s.nextInt();
			System.out.println("Enter new StudentID: ");
			String StudentID = s.next();
			switch (updationchoice) {
			case 1:
				// update update persional menu
				System.out.println("1 updateStudentName");
				System.out.println("2 updateStudentPhone");
				System.out.println("3 updateStudentEmail");
				System.out.println("4 updateStudentFatherName");
				System.out.println("5 updateStudentAddress");
				System.out.println("Enter your choice");
				int persionalupdate=s.nextInt();


				switch (persionalupdate) {
				case 1:
					System.out.println("Enter new name: ");
					String name = s.next();


					std.updateStudentName(session, StudentID, name);
					break;
				case 2:
					System.out.println("Enter new phone: ");
					String phone = s.next();

					std.updateStudentPhone(session, StudentID, phone);
					break;
				case 3:
					System.out.print("Enter new email: ");
					String email = s.next();

					std.updateStudentEmail(session, StudentID, email);
					break;
				case 4:
					System.out.print("Enter new father's name: ");
					s.nextLine(); // Consume newline
					String fatherName = s.nextLine();
					std.updateStudentFatherName(session, StudentID, fatherName);
					break;
				case 5:
					System.out.print("Enter new address: ");
					s.nextLine(); // Consume newline
					String address = s.nextLine();
					std.updateStudentAddress(session, StudentID, address);
					break;


				default:
					System.out.println("Invalid choice. Please enter a valid option.");
				}
			case 2:
				// update update education menu
				StudentEducationServiceImplement stdedu =new StudentEducationServiceImplement();
				System.out.println("1 updateStudentTenthMarks");
				System.out.println("2 updateStudentTwelfthMarks");
				System.out.println("3 updateStudentTenthBoard");
				System.out.println("4 updateStudentTwelfthBoard");
				System.out.println("5 updateStudentCurrentCourse");
				System.out.println("5 updateStudentAveragePercentage");

				System.out.println("Enter your choice");
				int educationlupdate=s.nextInt();


				switch (educationlupdate) {
				case 1:
					System.out.println("Enter new 10th Marks: ");
					float tenthmarks = s.nextFloat();
					if (stdedu.updateStudentTenthMarks(session, StudentID, tenthmarks)) {
						System.out.println("Successfully changed 10th Marks");
					} else {
						System.out.println("Failed to change 10th Marks");
					}
					break;
				case 2:
					System.out.println("Enter 12th Marks: ");
					float twelth = s.nextFloat();
					if (stdedu.updateStudentTwelfthMarks(session, StudentID, twelth)) {
						System.out.println("Successfully changed 12th Marks");
					} else {
						System.out.println("Failed to change 12th Marks");
					}
					break;
				case 3:
					System.out.print("Enter new 10th board: ");
					String tenthBoard = s.next();
					if (stdedu.updateStudentTenthBoard(session, StudentID, tenthBoard)) {
						System.out.println("Successfully updated 10th board");
					} else {
						System.out.println("Failed to update 10th board");
					}
					break;
				case 4:
					System.out.print("Enter new 12th board: ");
					String twelfthBoard = s.next();
					if (stdedu.updateStudentTwelfthBoard(session, StudentID, twelfthBoard)) {
						System.out.println("Successfully updated 12th board");
					} else {
						System.out.println("Failed to update 12th board");
					}
					break;
				case 5:
					System.out.print("Enter new current course: ");
					String currentCourse = s.next();
					if (stdedu.updateStudentCurrentCourse(session, StudentID, currentCourse)) {
						System.out.println("Successfully updated current course");
					} else {
						System.out.println("Failed to update current course");
					}
					break;
				case 6:
					System.out.print("Enter new average percentage: ");
					float averagePercentage = s.nextFloat();
					if (stdedu.updateStudentAveragePercentage(session, StudentID, averagePercentage)) {
						System.out.println("Successfully updated average percentage");
					} else {
						System.out.println("Failed to update average percentage");
					}
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
				}

			case 3:
				//
				//	                	getStudentsByDepartment
				List<Student> studentsInDepartment = std.getStudentsByDepartment(session, departmentName);
			    if (studentsInDepartment != null && !studentsInDepartment.isEmpty()) {
			        System.out.println("Students in department " + departmentName + ":");
			        System.out.println("-------------------------------------------------------------");
			        System.out.printf("| %-15s | %-20s |\n", "Student ID", "Name");
			        System.out.println("-------------------------------------------------------------");
			        for (Student student : studentsInDepartment) {
			            System.out.printf("| %-15s | %-20s |\n", student.getStudentId(), student.getName());
			        }
			        System.out.println("-------------------------------------------------------------");
			    } else {
			        System.out.println("No students found in department " + departmentName);
			    }
				break;
			case 4:
				//			   
				// Instantiate JobServiceImpl
				JobServiceImpl jobService = new JobServiceImpl();

				// Call getAllJobs method
				List<Job> jobs = jobService.getAllJobs(session);

				// Check if the list of jobs is not empty
				if (jobs != null && !jobs.isEmpty()) {
					// Print table header with separators
					System.out.println("+--------+-------------------+------------------------------+-----------------+-----------------------+------------------------------------------+--------------------------------+---------+-----------------+------------+");
					System.out.println("| Job ID | Position Name     | Company Name                 | Package Amount  | Eligibility Criteria | Requirement Tech Description            | Role & Responsibilities       | Any Bond| Company Visit   | Company ID |");
					System.out.println("+--------+-------------------+------------------------------+-----------------+-----------------------+------------------------------------------+--------------------------------+---------+-----------------+------------+");

					// Iterate over the list and print details of each job in table format
					for (Job job : jobs) {
						System.out.printf("| %-6d | %-17s | %-28s | %-15.2f | %-21s | %-40s | %-30s | %-7s | %-15s | %-10d |\n", 
								job.getJobId(), job.getPositionName(), job.getCompany().getCompanyName(), job.getPackageAmount(), 
								job.getEligibilityCriteria(), job.getRequirementTechnologyDescription(), job.getRoleAndResponsibilities(), 
								job.getAnyBond(), job.getModeOfCompanyVisit(), job.getCompany().getCompanyId());
					}
					// Print table footer with separators
					System.out.println("+--------+-------------------+------------------------------+-----------------+-----------------------+------------------------------------------+--------------------------------+---------+-----------------+------------+");
				} else {
					System.out.println("No jobs found.");
				}


				break;
			case 5:
                System.out.println("Job application department wise selected");
                // Implement job application department wise logic
                break;
            case 6:
                System.out.println("Password change selected");
                // Implement password change logic
                break;
            case 7:
                exit = true;
                System.out.println("Exiting...");
                break;
			default:
				System.out.println("Invalid choice");
			}

			sfg.close();
		}

	}
	}
}