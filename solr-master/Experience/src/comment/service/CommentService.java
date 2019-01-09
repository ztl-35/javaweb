package comment.service;

import java.sql.SQLException;
import java.util.List;

import comment.dao.CommentDao;
import comment.pojo.Comment;

public class CommentService {
	private CommentDao dao = new CommentDao();
	
	
	public void addComment(Comment comment) throws SQLException  {
	
		dao.insert(comment);
	}
	
	public void updateComment(Comment comment) throws SQLException {
	
		dao.update(comment);
	}
	
	public void deleteComment(int commentId) throws SQLException  {
	
		dao.delete(commentId);
	}
	
	public Comment queryCommentById(int commentId) throws SQLException {
	
		return dao.queryById(commentId);
	}
	

	
	public List<Comment> queryByArticleId(int articleId) throws SQLException {
	
		return dao.queryByArticleId(articleId);
	}
}

