package Anudip_project.Placement_management;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.Scanner;

import ServiceImplementation.DepartmentCredentialsServiceImpl;
import ServiceImplementation.PlacementCellCredentialsServiceImpl;
import ServiceImplementation.StudentCredentialServiceImpl;
import opertationals.HodOperatation;
import opertationals.PlacementOperatation;
import opertationals.StudentOperation;



public class App 
{
	public static void main(String[] args) {
		//
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sfg = config.buildSessionFactory();
		Session session = sfg.openSession();
		Transaction tx = session.beginTransaction();
		boolean exitProgram = false;
		while (!exitProgram) {
			System.out.println("-------------------------------");
			System.out.println("|        Placement Management  |");
			System.out.println("-------------------------------");
			System.out.println("| Options:                     |");
			System.out.println("| 1. Student                   |");
			System.out.println("| 2. HoD                       |");
			System.out.println("| 3. Placement Cell            |");
			System.out.println("| 4. Admin                     |");
			System.out.println("| 5. Exit                      |");
			System.out.println("-------------------------------");
			System.out.print("Enter your choice: ");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("\n------------------------");
				System.out.println("|       Student        |");
				System.out.println("------------------------");
				System.out.print("Enter Username: ");
				String studentUsername = scanner.next();
				StudentCredentialServiceImpl studentCredentialService = new StudentCredentialServiceImpl();

				if (studentCredentialService.isUsernameTaken(session, studentUsername)) {
					System.out.print("Enter Password: ");
					String password = scanner.next();
					if (studentCredentialService.authenticate(session, studentUsername, password)) {
						System.out.println("You are an authorized user.");
						String studentId = studentCredentialService.getStudentIdByCredentials(session, studentUsername, password);
						StudentOperation studentOperation = new StudentOperation();
						try {
							studentOperation.stdmenu(studentId);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Incorrect credentials.");
					}
				} else {
					System.out.println("Error: Invalid username.");
				}
				break;
			case 2:
				System.out.println("\n------------------------");
				System.out.println("|          HoD          |");
				System.out.println("------------------------");
				System.out.print("Enter Username: ");
				String hodUsername = scanner.next();
				DepartmentCredentialsServiceImpl hodService = new DepartmentCredentialsServiceImpl();

				if (hodService.usernameExists(session, hodUsername)) {
					System.out.print("Enter Password: ");
					String hodPassword = scanner.next();
					if (hodService.authenticate(session, hodUsername, hodPassword)) {
						String departmentName = hodService.getDepartmentNameByUsername(session, hodUsername);
						HodOperatation hodOperation = new HodOperatation();
						hodOperation.hodmenu(departmentName);
					} else {
						System.out.println("Error: Incorrect password.");
					}
				} else {
					System.out.println("Invalid username.");
				}
				break;
			case 3:
				System.out.println("\n------------------------");
				System.out.println("|     Placement Cell    |");
				System.out.println("------------------------");
				System.out.print("Enter Username: ");
				String placementUsername = scanner.next();
				PlacementCellCredentialsServiceImpl placementCellService = new PlacementCellCredentialsServiceImpl();

				if (placementCellService.checkUsername(session, placementUsername)) {
					System.out.print("Enter Password: ");
					String placementPassword = scanner.next();
					if (placementCellService.authenticatePlacementCellCredentials(session, placementUsername, placementPassword)) {
						System.out.println("Login successful.");
						PlacementOperatation placementOperation = new PlacementOperatation();
						placementOperation.placementMenu();
					}
				} else {
					System.out.println("Error: Invalid username.");
				}
				break;
			case 4:
				System.out.println("\n------------------------");
				System.out.println("|         Admin         |");
				System.out.println("------------------------");
				// Add admin functionality here
				break;
			default:
				System.out.println("\nInvalid choice.");
			}
		}
	}
}
