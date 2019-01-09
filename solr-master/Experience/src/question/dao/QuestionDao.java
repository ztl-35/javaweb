package question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DbUtil;
import question.pojo.Question;

public class QuestionDao {
	// 插入
		public void insert(Question question) throws SQLException {

			Connection conn = DbUtil.getConnection();

			String sql = "insert into question(question_owner,question_content,question_date) values (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question.getQuestionOwner());
			pstmt.setString(2, question.getQuestionContent());
			pstmt.setDate(3, question.getQuestionDate());
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
		public void update(Question question) throws SQLException {
			Connection conn = DbUtil.getConnection();
			String sql = "UPDATE question SET question_owner=?,question_content=?,question_date=? where question_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question.getQuestionOwner());
			pstmt.setString(2, question.getQuestionContent());
			pstmt.setDate(3, question.getQuestionDate());
			pstmt.setInt(4, question.getQuestionId());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}

		// 删除
		public void delete(int questionId) throws SQLException {
			Connection conn = DbUtil.getConnection();
			String sql = "delete from question where question_id=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questionId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}

		// 查询所有数据
		public ArrayList<Question> queryAll() throws SQLException {

			ArrayList<Question> list = new ArrayList<Question>();
			Connection conn = DbUtil.getConnection();
			String sql = "select * from question";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Question question = new Question(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
				list.add(question);
			}
			rs.close();
			pstmt.close();
			conn.close();
			return list;
		}
		
		public List<Question> queryPageData(int currentPage,int pageSize,List<Question> questionList){
			int count = questionList.size();
			int fromIndex = (currentPage - 1) * pageSize;
			int c = fromIndex + pageSize;
			int toIndex;
			if (c <= (count - 1)) {
				toIndex = c;
			} else {
				toIndex = count;
			}
			List<Question> subList = questionList.subList(fromIndex, toIndex);
			return subList;
		}


		public int getCount() throws SQLException {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM question";
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
