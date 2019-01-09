package javastudy.student_contacter;

public class Contacter {
	private int id;
	private String name;
	private String sex;
	private String relation;
	private String phone;
	private Student student;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Contacter [id=" + id + ", name=" + name + ", sex=" + sex + ", relation=" + relation + ", phone=" + phone
				+ ", student=" + student + "]";
	}
	
}
