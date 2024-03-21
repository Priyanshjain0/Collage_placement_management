package entites;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;
    
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;
    
    @Column(name = "position_name")
    private String positionName;
    
    @Column(name = "package")
    private double packageAmount;
    
    @Column(name = "eligibility_criteria")
    private String eligibilityCriteria;
    
    @Column(name = "requirement_technology_description")
    private String requirementTechnologyDescription;
    
    @Column(name = "role_and_responsibilities")
    private String roleAndResponsibilities;
    
    @Column(name = "any_bond")
    private String anyBond;
    
    @Column(name = "mode_of_company_visit")
    private String modeOfCompanyVisit;

    // Getters and setters
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public double getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public String getRequirementTechnologyDescription() {
        return requirementTechnologyDescription;
    }

    public void setRequirementTechnologyDescription(String requirementTechnologyDescription) {
        this.requirementTechnologyDescription = requirementTechnologyDescription;
    }

    public String getRoleAndResponsibilities() {
        return roleAndResponsibilities;
    }

    public void setRoleAndResponsibilities(String roleAndResponsibilities) {
        this.roleAndResponsibilities = roleAndResponsibilities;
    }

    public String getAnyBond() {
        return anyBond;
    }

    public void setAnyBond(String anyBond) {
        this.anyBond = anyBond;
    }

    public String getModeOfCompanyVisit() {
        return modeOfCompanyVisit;
    }

    public void setModeOfCompanyVisit(String modeOfCompanyVisit) {
        this.modeOfCompanyVisit = modeOfCompanyVisit;
    }
}
