package course.service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Pager;
import course.dao.CourseDao;
import course.pojo.Course;

public class CourseService {

	private CourseDao dao = new CourseDao();

	/* 插入数据 */
	public void addCourse(Course course) throws SQLException {

		dao.insert(course);
	}

	/* 修改数据 */
	public void updateCourse(Course course) throws SQLException {

		dao.update(course);
	}

	/* 删除数据 */
	public void deleteCourse(int courseId) throws SQLException {

		dao.delete(courseId);
	}

	/* 查询单条数据 */
	public Course queryCourseById(int courseId) throws SQLException {

		return dao.queryById(courseId);

	}

	/**
	 * 分页查询所有数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Course> queryCourseByPager(Pager pager) throws SQLException{
		
		return dao.queryPageData();
	}
	
	/**
	 * 返回总记录数
	 * @throws SQLException 
	 */
	public int getCourseCount() throws SQLException{
		
		return dao.getCount();
	}

}
