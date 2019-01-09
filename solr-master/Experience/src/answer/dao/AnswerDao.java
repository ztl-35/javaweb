package answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import answer.pojo.Answer;
import common.DbUtil;

public class AnswerDao {
	// 插入
		public void insert(Answer answer) throws SQLException {

			Connection conn = DbUtil.getConnection();

			String sql = "insert into answer(answer_content,user_name,question_id,answer_date) values (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer.getAnswerContent());
			pstmt.setString(2, answer.getUserName());
			pstmt.setInt(3, answer.getQuestionId());
			pstmt.setDate(4, answer.getAnswerDate());
			
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
		public void update(Answer answer) throws SQLException {
			Connection conn = DbUtil.getConnection();
			String sql = "UPDATE comment SET answer_content=?,user_name=?,question_id=?,comment_date=? where answer_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer.getAnswerContent());
			pstmt.setString(2, answer.getUserName());
			pstmt.setInt(3, answer.getQuestionId());
			pstmt.setDate(4, answer.getAnswerDate());
			pstmt.setInt(5,answer.getAnswerId());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}

		// 删除
		public void delete(int answerId) throws SQLException {
			Connection conn = DbUtil.getConnection();
			String sql = "delete from answer where answer_id=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answerId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}

		
		/**
		 * 根据articleId查询所有评论
		 * @param articleId
		 * @return List<Comment>
		 * @throws SQLException
		 */
		public List<Answer> queryByQuestionId(int questionId) throws SQLException {
			Answer answer = null;

			Connection conn = DbUtil.getConnection();
			String sql = "select * from answer where question_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questionId);
			List<Answer> list=new ArrayList<Answer>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				answer = new Answer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
				list.add(answer);
			}
			rs.close();
			pstmt.close();
			conn.close();

			return list;

		}
}
