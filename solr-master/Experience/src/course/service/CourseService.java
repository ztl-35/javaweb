package course.service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Pager;
import course.dao.CourseDao;
import course.pojo.Course;

public class CourseService {

	private CourseDao dao = new CourseDao();

	/* �������� */
	public void addCourse(Course course) throws SQLException {

		dao.insert(course);
	}

	/* �޸����� */
	public void updateCourse(Course course) throws SQLException {

		dao.update(course);
	}

	/* ɾ������ */
	public void deleteCourse(int courseId) throws SQLException {

		dao.delete(courseId);
	}

	/* ��ѯ�������� */
	public Course queryCourseById(int courseId) throws SQLException {

		return dao.queryById(courseId);

	}

	/**
	 * ��ҳ��ѯ��������
	 * 
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Course> queryCourseByPager(Pager pager) throws SQLException{
		
		return dao.queryPageData();
	}
	
	/**
	 * �����ܼ�¼��
	 * @throws SQLException 
	 */
	public int getCourseCount() throws SQLException{
		
		return dao.getCount();
	}

}
