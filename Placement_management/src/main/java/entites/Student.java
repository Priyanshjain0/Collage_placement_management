package entites; // Corrected package name

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    
    @Id
    @Column(name = "student_id")
    private String studentId;
    
    public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Department getStudentDepartment() {
		return studentDepartment;
	}

	public void setStudentDepartment(Department studentDepartment) {
		this.studentDepartment = studentDepartment;
	}

	@Column(name = "name")
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "father_name")
    private String fatherName;
    
    @Column(name = "address")
    private String address;
    
    @ManyToOne
    @JoinColumn(name = "department_name", referencedColumnName = "department_name")
    private Department studentDepartment; // Corrected to reference Department entity

    // Getters and setters...
//    ----------------------------------------------
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private EducationDetails educationDetails;
    
    

    public EducationDetails getEducationDetails() {
        return educationDetails;
    }

    public void setEducationDetails(EducationDetails educationDetails) {
        this.educationDetails = educationDetails;
    }
}
