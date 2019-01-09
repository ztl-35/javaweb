package book.pojo;

public class Subject {

	private int subjectId;

	private String subjectName;

	private String subjectDesc;

	public Subject() {
		super();
	}

	public Subject(int subjectId, String subjectName, String subjectDesc) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		
	}

	public Subject(String subjectName, String subjectDesc) {
		super();
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", subjectDesc=" + subjectDesc
				+ "]";
	}

}