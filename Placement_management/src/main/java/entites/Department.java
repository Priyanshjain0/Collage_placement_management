package entites; // Corrected package name

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    
    @Id
    @Column(name = "department_name")
    private String departmentName;
    
    public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getHodName() {
		return hodName;
	}

	public void setHodName(String hodName) {
		this.hodName = hodName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Column(name = "hod_name")
    private String hodName;

    @OneToMany(mappedBy = "studentDepartment")
    private List<Student> students; // Corrected mappedBy value to reference studentDepartment

    // Getters and setters...
}
