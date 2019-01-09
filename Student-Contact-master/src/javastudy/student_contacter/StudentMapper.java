package javastudy.student_contacter;

public interface StudentMapper {
	public void add(Student student);
	public Student get(int id);
	public void delete(int id);
}