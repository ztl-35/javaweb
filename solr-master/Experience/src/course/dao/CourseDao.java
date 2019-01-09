package course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbUtil;
import course.pojo.Course;

/**
 * 课程DAO
 * 
 * @author Administrator
 * 
 */
public class CourseDao {
	/**
	 * 定义课程信息分页参数
	 */
	int pageNo;
	int pageSize;
	int pageCount;
	
	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 插入数据
	 * 
	 * @param course
	 * @throws SQLException
	 */
	public void insert(Course course) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into course (course_name,course_credit,course_teacher,course_address,course_time,course_long,course_feature,course_type) values (?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, course.getCourseName());
		pstmt.setString(2, course.getCourseCredit());
		pstmt.setString(3, course.getCourseTeacher());
		pstmt.setString(4, course.getCourseAdd());
		pstmt.setString(5, course.getCourseTime());
		pstmt.setString(6, course.getCourseLong());
		pstmt.setString(7, course.getCourseFeature());
		pstmt.setString(8, course.getCourseType());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 
	 * 删除数据
	 * 
	 * @param courseId
	 * @throws SQLException
	 */
	public void delete(int courseId) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "delete from course where course_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, courseId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 修改数据
	 * 
	 * @param course
	 * @throws SQLException
	 */
	public void update(Course course) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "update course set course_name=?,course_credit=?,course_teacher=?,course_address=?,course_time=?,course_long=?,course_feature=?,course_type=? where course_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, course.getCourseName());
		pstmt.setString(2, course.getCourseCredit());
		pstmt.setString(3, course.getCourseTeacher());
		pstmt.setString(4, course.getCourseAdd());
		pstmt.setString(5, course.getCourseTime());
		pstmt.setString(6, course.getCourseLong());
		pstmt.setString(7, course.getCourseFeature());
		pstmt.setString(8, course.getCourseType());
		pstmt.setInt(9, course.getCourseId());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 根据id查询单条数据
	 * 
	 * @param courseId
	 * @return
	 * @throws SQLException
	 */
	public Course queryById(int courseId) throws SQLException {

		Course course = null;

		Connection conn = DbUtil.getConnection();

		String sql = "select * from course where course_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, courseId);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			course = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return course;
	}
	
	/**
	 * 查询本页数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Course> queryPageData() throws SQLException {

		ArrayList<Course> list = new ArrayList<Course>();
		Connection conn = DbUtil.getConnection();
		String sql = "SELECT * FROM course LIMIT "+(pageNo-1)*pageSize+","+pageSize;
		System.out.println(sql);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Course course = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
			list.add(course);
		}
		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}
	/**
	 * 查询所有课程数据
	 * @throws SQLException 
	 */
	public ArrayList<Course> queryAllData() throws SQLException {
		ArrayList<Course> list = new ArrayList<Course>();
		Connection conn = DbUtil.getConnection();
		String sql = "SELECT * FROM course";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Course course = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
			list.add(course);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	/**
	 * 返回总记录数
	 * 
	 * @throws SQLException
	 */
	public int getCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM   course";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		rs.next();

		int count = rs.getInt(1);
		System.out.println("查询课程的总数为： "+count);
		rs.close();
		pstmt.close();
		conn.close();

		return count;
	}
	/**
	 * 计算总页数
	 * @return
	 * @throws SQLException
	 */
	public void computePageCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM   course";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		rs.next();

		pageCount = rs.getInt(1);
		if (pageCount%pageSize==0) {
			pageCount = pageCount/pageSize;
		}else {
			pageCount = pageCount/pageSize+1;
		}
		rs.close();
		pstmt.close();
		conn.close();
	}
	
}