package TestCase;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import MyUilts.MyBatisUtils;
import javastudy.student_contacter.*;
import javastudy.student_course.student_course;
import javastudy.student_course.student_courseMapper;
import javastudy.teacher_course.Course;
import javastudy.teacher_course.CourseMapper;
import javastudy.teacher_course.Teacher;
import javastudy.teacher_course.TeacherMapper;

public class mytest {
	@Test
	public void testInsert() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setName("zhangtaolin");
		student.setPhone("15735170798");
		student.setPhoto("/images/zhang.jsp");
		student.setPwd("420519");
		student.setSex("ÄÐ");
		student.setGrade(95);
		studentMapper.add(student);
		System.out.println(student.getId());
		
		ContacterMapper contacterMapper = session.getMapper(ContacterMapper.class);
		Contacter contacter = new Contacter();
		contacter.setName("zhangjiabing");
		contacter.setPhone("13695532729");
		contacter.setRelation("fuzi");
		contacter.setSex("ÄÐ");
		contacter.setStudent(student);
		contacterMapper.add(contacter);
		
		session.commit();
		session.close();
	}
	@Test
	public void testSelect() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
//		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
//		Student student = studentMapper.get(4);
//		System.out.println(student);
		
		ContacterMapper contacterMapper = session.getMapper(ContacterMapper.class);
		Contacter contacter = contacterMapper.get(1);
		System.out.println(contacter.getStudent());
	}
	@Test
	public void testDelete() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
//		ContacterMapper contacterMapper = session.getMapper(ContacterMapper.class);
//		contacterMapper.delete(4);
//		session.commit();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		studentMapper.delete(4);
		session.commit();
		session.close();
	}
	@Test
	public void testUpdate() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
		ContacterMapper contacterMapper = session.getMapper(ContacterMapper.class);
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Contacter contacter = new Contacter();
		contacter.setId(2);
		contacter.setStudent(studentMapper.get(8));
		contacterMapper.update(contacter);
		session.commit();
	}
	@Test
	public void testTeacherInsert() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
		TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
		Teacher teacher = new Teacher();
		teacher.setName("ÐìÄÁÖÞ");
		teacher.setPhone("15735151223");
		teacher.setSex("ÄÐ");
		teacherMapper.add(teacher);
		System.out.println("id::::"+teacher.getId());
		
		CourseMapper courseMapper = session.getMapper(CourseMapper.class);
		Course course = new Course();
		course.setHours(64);
		course.setName("Êý¾Ý½á¹¹");
		course.setTeacher(teacher);
		course.setType("¿¼ÊÔ¿Î");
		courseMapper.add(course);
		
		session.commit();
		session.close();
	}
	@Test
	public void testCourseSelect() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
		CourseMapper courseMapper = session.getMapper(CourseMapper.class);
		Course course = courseMapper.get(1);
		System.out.println(course);
		session.commit();
		session.close();
	}
	@Test
	public void testTeacherSelect() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
		TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
		Teacher teacher = teacherMapper.get(6);
		System.out.println(teacher);
		session.commit();
		session.close();
	}
	@Test
	public void testSelectM2M() throws IOException {
		SqlSession session = MyBatisUtils.openSession();
		javastudy.student_course.CourseMapper courseMapper = session.getMapper(javastudy.student_course.CourseMapper.class);
		javastudy.student_course.Course course = courseMapper.get(7);
		System.out.println(course);
	}
	@Test
	public void testInsertM2M() throws IOException {
		
		SqlSession session = MyBatisUtils.openSession();
		student_courseMapper scMapper = session.getMapper(student_courseMapper.class);
		javastudy.student_course.CourseMapper courseMapper = session.getMapper(javastudy.student_course.CourseMapper.class);
		javastudy.student_course.StudentMapper studentMapper = session.getMapper(javastudy.student_course.StudentMapper.class);
		student_course sc = new student_course();
		sc.setStudent(studentMapper.get(9));
		sc.setCourse(courseMapper.get(7));
		scMapper.add(sc);
		session.commit();
		
		sc=new student_course();
		sc.setStudent(studentMapper.get(9));
		sc.setCourse(courseMapper.get(6));
		scMapper.add(sc);
		session.commit();
	}
}
