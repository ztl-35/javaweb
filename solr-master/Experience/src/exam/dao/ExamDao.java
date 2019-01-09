package exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbUtil;
import exam.pojo.Exam;

public class ExamDao {
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
	public void insert(Exam exam) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into exam (exam_name,exam_define,exam_desc,exam_way,exam_date,exam_requirement,exam_note) values (?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, exam.getExamName());
		pstmt.setString(2, exam.getExamDefine());
		pstmt.setString(3, exam.getExamDesc());
		pstmt.setString(4, exam.getExamWay());
		pstmt.setDate(5, exam.getExamDate());
		pstmt.setString(6, exam.getExamRequirement());
		pstmt.setString(7, exam.getExamNote());
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
	public void delete(int examId) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "delete from exam where exam_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, examId);
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
	public void update(Exam exam) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "update exam set exam_name=?,exam_define=?,exam_desc=?,exam_way=?,exam_date=?,exam_requirement=?,exam_note=? where exam_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, exam.getExamName());
		pstmt.setString(2, exam.getExamDefine());
		pstmt.setString(3, exam.getExamDesc());
		pstmt.setString(4, exam.getExamWay());
		pstmt.setDate(5, exam.getExamDate());
		pstmt.setString(6, exam.getExamRequirement());
		pstmt.setString(7, exam.getExamNote());
		pstmt.setInt(8, exam.getExamId());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 查询本页数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Exam> queryPageData() throws SQLException {

		ArrayList<Exam> list = new ArrayList<Exam>();
		Connection conn = DbUtil.getConnection();
		String sql = "SELECT * FROM exam LIMIT "+(pageNo-1)*pageSize+","+pageSize;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Exam exam = new Exam(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7),rs.getString(8));
			list.add(exam);
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
	public ArrayList<Exam> queryAllData() throws SQLException {
		ArrayList<Exam> list = new ArrayList<Exam>();
		Connection conn = DbUtil.getConnection();
		String sql = "SELECT * FROM exam";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Exam exam = new Exam(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7),rs.getString(8));
			list.add(exam);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}
	/**
	 * 计算总页数
	 * @return
	 * @throws SQLException
	 */
	public void computePageCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM exam";

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
