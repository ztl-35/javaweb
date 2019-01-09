package Test;

import org.hibernate.Session;
import org.junit.Test;

import common.HibernateUtil;
import dao.studentDAO;
import model.Course;
import model.Student;

public class mytest {
	@Test
	public void myain() {
		Student student = new Student();
		student.setId(2);
		
		Course course1 = new Course();
		course1.setId(1);
		
		Course course2 = new Course();
		course2.setId(2);
		
		student.getCourses().add(course1);
		student.getCourses().add(course2);
		
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		studentDAO studentDAO = new studentDAO();
		studentDAO.updateStudent(student);
		session.getTransaction().commit();
		session.close();
	}
}
