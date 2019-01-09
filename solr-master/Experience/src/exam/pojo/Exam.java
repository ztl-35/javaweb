package exam.pojo;

import java.sql.Date;

public class Exam {
	private int examId;
	private String examName;
	private String examDefine;
	private String examDesc;
	private String examWay;
	private Date examDate;
	private String examRequirement;
	private String examNote;
	
	public Exam(int examId, String examName, String examDefine, String examDesc, String examWay, Date examDate,
			String examRequirement, String examNote) {
		super();
		this.examId = examId;
		this.examName = examName;
		this.examDefine = examDefine;
		this.examDesc = examDesc;
		this.examWay = examWay;
		this.examDate = examDate;
		this.examRequirement = examRequirement;
		this.examNote = examNote;
	}
	public Exam( String examName, String examDefine, String examDesc, String examWay, Date examDate,
			String examRequirement, String examNote) {
		super();
		this.examName = examName;
		this.examDefine = examDefine;
		this.examDesc = examDesc;
		this.examWay = examWay;
		this.examDate = examDate;
		this.examRequirement = examRequirement;
		this.examNote = examNote;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getExamDefine() {
		return examDefine;
	}
	public void setExamDefine(String examDefine) {
		this.examDefine = examDefine;
	}
	public String getExamDesc() {
		return examDesc;
	}
	public void setExamDesc(String examDesc) {
		this.examDesc = examDesc;
	}
	public String getExamWay() {
		return examWay;
	}
	public void setExamWay(String examWay) {
		this.examWay = examWay;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getExamRequirement() {
		return examRequirement;
	}
	public void setExamRequirement(String examRequirement) {
		this.examRequirement = examRequirement;
	}
	public String getExamNote() {
		return examNote;
	}
	public void setExamNote(String examNote) {
		this.examNote = examNote;
	}
	@Override
	public String toString() {
		return "Exam [examId=" + examId + ", examName=" + examName + ", examDefine=" + examDefine + ", examDesc="
				+ examDesc + ", examWay=" + examWay + ", examDate=" + examDate + ", examRequirement=" + examRequirement
				+ ", examNote=" + examNote + "]";
	}
}
