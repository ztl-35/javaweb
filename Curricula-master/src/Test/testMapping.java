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
		teacher.setName("张涛林");
		teacher.setPhone("15735170798");
		teacher.setSex("男");
		
		Room room = new Room();
		room.setName("101");
		room.setAddress("理科楼");
		
		Course course = new Course();
		course.setName("Java入门到精通");
		course.setHours("30");
		course.setType("实验课");
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
