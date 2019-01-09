package javastudy.teacher_course;

public class Course {
	private int id;
	private String name;
	private int hours;
	private String type;
	private Teacher teacher;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", hours=" + hours + ", type=" + type + ", teacher=" + teacher
				+ "]";
	}
	
}
