package javastudy.student_contacter;

public class Student {
	private int id;
	private String name;
	private String sex;
	private String pwd;
	private String phone;
	private int grade;
	private String photo;
	private Contacter contacter;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Contacter getContacter() {
		return contacter;
	}
	public void setContacter(Contacter contacter) {
		this.contacter = contacter;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", pwd=" + pwd + ", phone=" + phone
				+ ", grade=" + grade + ", photo=" + photo + ", contacter=" + contacter + "]";
	}
	
}
