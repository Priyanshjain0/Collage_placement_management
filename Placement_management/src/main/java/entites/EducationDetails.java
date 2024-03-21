package entites;
import javax.persistence.*;

@Entity
@Table(name = "education_details")
public class EducationDetails {
    
    @Id
    @Column(name = "student_id")
    private String studentId;
    
    @Column(name = "10th_marks")
    private float tenthMarks;
    
    @Column(name = "12th_marks")
    private float twelfthMarks;
    
    @Column(name = "10th_board")
    private String tenthBoard;
    
    public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public float getTenthMarks() {
		return tenthMarks;
	}

	public void setTenthMarks(float tenthMarks) {
		this.tenthMarks = tenthMarks;
	}

	public float getTwelfthMarks() {
		return twelfthMarks;
	}

	public void setTwelfthMarks(float twelfthMarks) {
		this.twelfthMarks = twelfthMarks;
	}

	public String getTenthBoard() {
		return tenthBoard;
	}

	public void setTenthBoard(String tenthBoard) {
		this.tenthBoard = tenthBoard;
	}

	public String getTwelfthBoard() {
		return twelfthBoard;
	}

	public void setTwelfthBoard(String twelfthBoard) {
		this.twelfthBoard = twelfthBoard;
	}

	public String getCurrentCourse() {
		return currentCourse;
	}

	public void setCurrentCourse(String currentCourse) {
		this.currentCourse = currentCourse;
	}

	public float getAveragePercentage() {
		return averagePercentage;
	}

	public void setAveragePercentage(float averagePercentage) {
		this.averagePercentage = averagePercentage;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Column(name = "12th_board")
    private String twelfthBoard;
    
    @Column(name = "current_course")
    private String currentCourse;
    
    @Column(name = "average_percentage")
    private float averagePercentage;
    
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;
    
    // Getters and setters
}
