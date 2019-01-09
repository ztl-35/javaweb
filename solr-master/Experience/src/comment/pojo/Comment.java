package comment.pojo;

import java.sql.Date;

public class Comment {
	private int commentId;
	private String commentContent;
	private int articleId;
	private String userName;
	private Date commentDate;
	



	public Comment(String commentContent, int articleId, String userName, Date commentDate) {
		super();
		this.commentContent = commentContent;
		this.articleId = articleId;
		this.userName = userName;
		this.commentDate = commentDate;
	}

	public Comment(int commentId, String commentContent, int articleId, String userName, Date commentDate) {
		super();
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.articleId = articleId;
		this.userName = userName;
		this.commentDate = commentDate;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}


	
}
