package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DbUtil;
import notice.pojo.Notice;

public class NoticeDao {
	// 插入
	public void insert(Notice notice) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into notice(notice_header,notice_content,notice_date) values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, notice.getNoticeHeader());
		pstmt.setString(2, notice.getNoticeContent());
		pstmt.setDate(3, notice.getNoticeDate());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();

	}

	/**
	 * 修改数据
	 * 
	 * @param job
	 * @throws SQLException
	 */
	public void update(Notice notice) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "UPDATE notice SET notice_header=?,notice_content=?, notice_date=? where notice_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, notice.getNoticeHeader());
		pstmt.setString(2, notice.getNoticeContent());
		pstmt.setDate(3, notice.getNoticeDate());
		pstmt.setInt(4, notice.getNoticeId());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// 删除
	public void delete(int noticeId) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "delete from notice where notice_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, noticeId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// 根据id查询单条
	public Notice queryById(int noticeId) throws SQLException {
		Notice notice = null;

		Connection conn = DbUtil.getConnection();
		String sql = "select * from notice where notice_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, noticeId);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {

			notice = new Notice(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
		}
		rs.close();
		pstmt.close();
		conn.close();

		return notice;

	}

	// 查询所有数据
	public ArrayList<Notice> queryAll() throws SQLException {

		ArrayList<Notice> list = new ArrayList<Notice>();
		Connection conn = DbUtil.getConnection();
		String sql = "select * from notice";
		System.out.println(sql);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Notice notice = new Notice(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
			list.add(notice);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}
	
	public List<Notice> queryPageData(int currentPage,int pageSize,List<Notice> noticeList){
		int count = noticeList.size();
		int fromIndex = (currentPage - 1) * pageSize;
		int c = fromIndex + pageSize;
		int toIndex;
		if (c <= (count - 1)) {
			toIndex = c;
		} else {
			toIndex = count;
		}
		List<Notice> subList = noticeList.subList(fromIndex, toIndex);
		return subList;
	}

	//
	public int getCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM   notice";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		rs.next();

		int count = rs.getInt(1);

		rs.close();
		pstmt.close();
		conn.close();

		return count;
	}

}