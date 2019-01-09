package javastudy.student_contacter;

public interface ContacterMapper {
	public void add(Contacter contacter);
	public Contacter get(int id);
	public void delete(int id);
	public void update(Contacter contacter);
}
