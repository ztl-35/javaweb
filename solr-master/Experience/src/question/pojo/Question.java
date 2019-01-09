package question.pojo;

import java.sql.Date;

/**
 * @author m1395
 *
 */
public class Question {
	private int questionId;
	private String questionOwner;
	private String questionContent;
	private Date questionDate;
	
	public Question(int questionId,String questionOwner, String questionContent, Date questionDate) {
		super();
		this.questionId = questionId;
		this.questionOwner = questionOwner;
		this.questionContent = questionContent;
		this.questionDate = questionDate;
	}
	
	public Question(String questionOwner, String questionContent, Date questionDate) {
		super();
		this.questionOwner = questionOwner;
		this.questionContent = questionContent;
		this.questionDate = questionDate;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionOwner() {
		return questionOwner;
	}
	public void setQuestionOwner(String questionOwner) {
		this.questionOwner = questionOwner;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public Date getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}
	
}
