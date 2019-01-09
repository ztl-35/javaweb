package answer.pojo;

import java.sql.Date;

public class Answer {
	private int answerId;
	private String answerContent;
	private String userName;
	private int questionId;
	private Date answerDate;
	
	public Answer(int answerId, String answerContent, String userName, int questionId, Date answerDate) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.userName = userName;
		this.questionId = questionId;
		this.answerDate = answerDate;
	}
	
	public Answer(String answerContent, String userName, int questionId, Date answerDate) {
		super();
		this.answerContent = answerContent;
		this.userName = userName;
		this.questionId = questionId;
		this.answerDate = answerDate;
	}

	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	
}
