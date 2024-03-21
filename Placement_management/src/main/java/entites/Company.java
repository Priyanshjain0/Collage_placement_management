package entites;



import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "no_of_job_openings")
    private int numberOfJobOpenings;

    // Getters and setters

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNumberOfJobOpenings() {
        return numberOfJobOpenings;
    }

    public void setNumberOfJobOpenings(int numberOfJobOpenings) {
        this.numberOfJobOpenings = numberOfJobOpenings;
    }
}
