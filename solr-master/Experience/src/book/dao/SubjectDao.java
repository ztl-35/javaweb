package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbUtil;
import book.pojo.Subject;

public class SubjectDao {
	// ����
	public void insert(Subject subject) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into subject values (subject_seq.nextval,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, subject.getSubjectName());
		pstmt.setString(2, subject.getSubjectDesc());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();

	}

	// �޸�

	public void update(Subject subject) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "UPDATE subject SET subject_name=?,subject_desc=? where subject_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, subject.getSubjectName());
		pstmt.setString(2, subject.getSubjectDesc());
		pstmt.setInt(3, subject.getSubjectId());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// ɾ��
	public void delete(int subjectId) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "delete from subject where subject_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, subjectId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// ����id��ѯ����
	public Subject queryById(int subjectId) throws SQLException {
		Subject subject = null;

		Connection conn = DbUtil.getConnection();
		String sql = "select * from subject where subject_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, subjectId);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			subject = new Subject(rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		rs.close();
		pstmt.close();
		conn.close();

		return subject;

	}

/*	// ��ѯ��������
	public ArrayList<Subject> queryAll(Pager pager) throws SQLException {

		ArrayList<Subject> list = new ArrayList<Subject>();
		Connection conn = DbUtil.getConnection();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM (SELECT rownum rnum,");
		sb.append("      subject_id,");
		sb.append("      subject_name,");
		sb.append("      subject_desc");
		sb.append("FROM subject ");
		sb.append("ORDER BY subject_id)");
		sb.append("where rnum between ? and ?");

		String sql = sb.toString();
		System.out.println(sql);

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getEnd());

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Subject subject = new Subject(rs.getInt(2), rs.getString(3), rs.getString(4));
			list.add(subject);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}

	//
	public int getCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM  subject";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		rs.next();

		int count = rs.getInt(1);

		rs.close();
		pstmt.close();
		conn.close();

		return count;
	}
*/
	/**
	 * ��ѯ��������
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Subject> queryAll() throws SQLException {

		ArrayList<Subject> list = new ArrayList<Subject>();

		Connection conn = DbUtil.getConnection();

		String sql = "select * from subject";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			Subject subject = new Subject(rs.getInt(1), rs.getString(2), rs.getString(3));
			list.add(subject);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}
}