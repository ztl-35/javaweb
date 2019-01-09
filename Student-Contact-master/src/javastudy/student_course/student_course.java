package javastudy.student_course;

public class student_course {
	private Course course;
	private Student student;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "student_course [course=" + course + ", student=" + student + "]";
	}
	
}
