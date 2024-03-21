package entites;



import javax.persistence.*;

@Entity
@Table(name = "status_update")
public class StatusUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_update_id")
    private int statusUpdateId;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    private JobApplication jobApplication;

    @Column(name = "Round_status")
    private String applicationStatus;

    @Column(name = "round_name")
    private String roundName;
    private String Department_name;

    // Getters and setters

    public int getStatusUpdateId() {
        return statusUpdateId;
    }

    public void setStatusUpdateId(int statusUpdateId) {
        this.statusUpdateId = statusUpdateId;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

	public String getDepartment_name() {
		return Department_name;
	}

	public void setDepartment_name(String department_name) {
		Department_name = department_name;
	}
}
