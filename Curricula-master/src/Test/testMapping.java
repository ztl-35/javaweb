package Test;

import org.hibernate.Session;
import org.junit.Test;

import common.HibernateUtil;
import model.Course;
import model.Room;
import model.Teacher;

public class testMapping {
	@Test
	public void testmap(){
		Teacher teacher = new Teacher();
		teacher.setName("������");
		teacher.setPhone("15735170798");
		teacher.setSex("��");
		
		Room room = new Room();
		room.setName("101");
		room.setAddress("���¥");
		
		Course course = new Course();
		course.setName("Java���ŵ���ͨ");
		course.setHours("30");
		course.setType("ʵ���");
		course.setRoom(room);
		course.setTeacher(teacher);
		
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.save(teacher);
		session.save(room);
		session.save(course);
		session.getTransaction().commit();
		session.close();
	}
}
