package entites;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job_application")
public class JobApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;
    
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;
    
    @Column(name = "application_date")
    private Date applicationDate;
    
    @Column(name = "Application_status")
    private String status;
    
    @Column(name = "company_id")
    private int companyId; // Add this field for company ID
    
    // Getters and setters
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    @OneToMany(mappedBy = "jobApplication")
    private List<StatusUpdate> statusUpdates;

	public List<StatusUpdate> getStatusUpdates() {
		return statusUpdates;
	}

	public void setStatusUpdates(List<StatusUpdate> statusUpdates) {
		this.statusUpdates = statusUpdates;
	}
}
