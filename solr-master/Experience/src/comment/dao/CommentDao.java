package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comment.pojo.Comment;
import common.DbUtil;

public class CommentDao {
	// 插入
	public void insert(Comment comment) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into comment(comment_content,article_id,user_name,comment_date) values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, comment.getCommentContent());
		pstmt.setInt(2, comment.getArticleId());
		pstmt.setString(3, comment.getUserName());
		pstmt.setDate(4, comment.getCommentDate());
		
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
	public void update(Comment comment) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "UPDATE comment SET comment_content=?,article_id=?,user_name=?,comment_date=? where comment_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, comment.getCommentContent());
		pstmt.setInt(2, comment.getArticleId());
		pstmt.setString(3, comment.getUserName());
		pstmt.setDate(4, comment.getCommentDate());
		pstmt.setInt(5, comment.getCommentId());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// 删除
	public void delete(int commentId) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "delete from comment where comment_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, commentId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// 根据id查询单条
	public Comment queryById(int commentId) throws SQLException {
		Comment comment = null;

		Connection conn = DbUtil.getConnection();
		String sql = "select * from comment where comment_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, commentId);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {

			comment = new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(2));
		}
		rs.close();
		pstmt.close();
		conn.close();

		return comment;

	}
	
	
	/**
	 * 根据articleId查询所有评论
	 * @param articleId
	 * @return List<Comment>
	 * @throws SQLException
	 */
	public List<Comment> queryByArticleId(int articleId) throws SQLException {
		Comment comment = null;

		Connection conn = DbUtil.getConnection();
		String sql = "select * from comment where article_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, articleId);
		List<Comment> list=new ArrayList<Comment>();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			comment = new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5));
			list.add(comment);
		}
		rs.close();
		pstmt.close();
		conn.close();

		return list;

	}

}
