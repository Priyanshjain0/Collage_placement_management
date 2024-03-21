package opertationals;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ServiceImplementation.JobApplicationServiceImpl;
import ServiceImplementation.JobServiceImpl;
import ServiceImplementation.StudentCredentialServiceImpl;
import ServiceImplementation.StudentEducationServiceImplement;
import ServiceImplementation.StudentPersonalServicesImpl;
import entites.Department;
import entites.EducationDetails;
import entites.Job;
import entites.Student;

public class StudentOperation {
    public static void stdmenu(String studentid) throws SQLException {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sfg = config.buildSessionFactory();
        Session session = sfg.openSession();
        Transaction tx = session.beginTransaction();

        Scanner s = new Scanner(System.in);

        // after login student get id
        String StudentID = studentid;

        boolean exit = false;
        while (!exit) {
            // Display Student menu
            System.out.println("-------------------------------");
            System.out.println("|        Student Menu          |");
            System.out.println("-------------------------------");
            System.out.println("| Options:                     |");
            System.out.println("| 1. Personal Details           |");
            System.out.println("| 2. Education Details          |");
            System.out.println("| 3. View Jobs                  |");
            System.out.println("| 4. Apply for Job              |");
            System.out.println("| 5. View Applied Job Status    |");
            System.out.println("| 6. Change Password            |");
            System.out.println("| 7. Exit                       |");
            System.out.println("-------------------------------");
            System.out.print("Enter your choice: ");

            int choice = s.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    displayPersonalDetails(session, StudentID);
                    break;
                case 2:
                    displayEducationDetails(session, StudentID);
                    break;
                case 3:
                    displayJobs(session);
                    break;
                case 4:
                    applyForJob(session, StudentID);
                    break;
                case 5:
                    viewAppliedJobStatus(session, StudentID);
                    break;
                case 6:
                    changePassword(session);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        sfg.close();
    }

    private static void displayPersonalDetails(Session session, String studentID) {
        StudentPersonalServicesImpl std = new StudentPersonalServicesImpl();
        if (std.checkStudentId(session, studentID)) {
            Student student = std.getStudentById(session, studentID);
            System.out.println("Personal Details:");
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Phone: " + student.getPhone());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Father's Name: " + student.getFatherName());
            System.out.println("Address: " + student.getAddress());
            Department department = student.getStudentDepartment();
            String departmentName = (department != null) ? department.getDepartmentName() : "N/A";
            System.out.println("Department: " + departmentName);
        } else {
            System.out.println("Error: Invalid student ID.");
        }
    }

    private static void displayEducationDetails(Session session, String studentID) {
        StudentEducationServiceImplement stdedu = new StudentEducationServiceImplement();
        if (stdedu.checkEducationByStudentId(session, studentID)) {
            EducationDetails educationDetails = stdedu.getStudentEducationById(session, studentID);
            System.out.println("Education Details:");
            System.out.println("10th Board: " + educationDetails.getTenthBoard());
            System.out.println("12th Board: " + educationDetails.getTwelfthBoard());
            System.out.println("10th Marks: " + educationDetails.getTenthMarks());
            System.out.println("12th Marks: " + educationDetails.getTwelfthMarks());
            System.out.println("Current Course: " + educationDetails.getCurrentCourse());
            System.out.println("Current Percentage: " + educationDetails.getAveragePercentage());
        } else {
            System.out.println("Error: Student education details not found.");
        }
    }

    private static void displayJobs(Session session) {
        JobServiceImpl jobService = new JobServiceImpl();
        List<Job> jobs = jobService.getAllJobs(session);
        if (jobs != null && !jobs.isEmpty()) {
            System.out.println("Jobs Available:");
            for (Job job : jobs) {
                System.out.println(job);
            }
        } else {
            System.out.println("No jobs available.");
        }
    }

    private static void applyForJob(Session session, String studentID) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Company ID: ");
        int companyId = scanner.nextInt();
        System.out.print("Enter Job ID: ");
        int jobId = scanner.nextInt();

        JobApplicationServiceImpl applicationService = new JobApplicationServiceImpl();
        if (applicationService.checkDuplicateApplication(session, studentID, jobId)) {
            System.out.println("You have already applied for this job.");
        } else {
            Date applicationDate = new Date();
            applicationService.registerJobApplication(session, studentID, jobId, applicationDate, "Applied", companyId);
            System.out.println("Application submitted successfully.");
        }
    }

    private static void viewAppliedJobStatus(Session session, String studentID) {
        JobApplicationServiceImpl jobApplicationService = new JobApplicationServiceImpl();
        List<Object[]> applicationStatusList = jobApplicationService.CompleteGetApplicationStatusByStudentId(session, studentID);
        if (applicationStatusList != null && !applicationStatusList.isEmpty()) {
            System.out.println("Applied Job Status:");
            for (Object[] row : applicationStatusList) {
                System.out.println("Company: " + row[0] + ", Job Profile: " + row[1] + ", Status: " + row[2]);
            }
        } else {
            System.out.println("No applied job status found.");
        }
    }

    private static void changePassword(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter current username: ");
        String username = scanner.next();
        StudentCredentialServiceImpl credentialService = new StudentCredentialServiceImpl();
        if (credentialService.isUsernameTaken(session, username)) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.next();
            System.out.print("Confirm new password: ");
            String confirmPassword = scanner.next();
            if (newPassword.equals(confirmPassword)) {
                credentialService.updatePassword(session, username, newPassword);
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Passwords do not match.");
            }
        } else {
            System.out.println("Error: Incorrect username.");
        }
    }
}
