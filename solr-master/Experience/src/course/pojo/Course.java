package course.pojo;

public class Course {

	private int courseId;

	private String courseName;

	private String courseCredit;

	private String courseTeacher;

	private String courseAdd;

	private String courseTime;

	private String courseLong;

	private String courseFeature;

	private String courseType;

	public Course() {
		super();
	}

	public Course(int courseId, String courseName, String courseCredit, String courseTeacher, String courseAdd,
			String courseTime, String courseLong, String courseFeature, String courseType) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCredit = courseCredit;
		this.courseTeacher = courseTeacher;
		this.courseAdd = courseAdd;
		this.courseTime = courseTime;
		this.courseLong = courseLong;
		this.courseFeature = courseFeature;
		this.courseType = courseType;
	}

	public Course(String courseName, String courseCredit, String courseTeacher, String courseAdd, String courseTime,
			String courseLong, String courseFeature, String courseType) {
		super();
		this.courseName = courseName;
		this.courseCredit = courseCredit;
		this.courseTeacher = courseTeacher;
		this.courseAdd = courseAdd;
		this.courseTime = courseTime;
		this.courseLong = courseLong;
		this.courseFeature = courseFeature;
		this.courseType = courseType;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}

	public String getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}

	public String getCourseAdd() {
		return courseAdd;
	}

	public void setCourseAdd(String courseAdd) {
		this.courseAdd = courseAdd;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getCourseLong() {
		return courseLong;
	}

	public void setCourseLong(String courseLong) {
		this.courseLong = courseLong;
	}

	public String getCourseFeature() {
		return courseFeature;
	}

	public void setCourseFeature(String courseFeature) {
		this.courseFeature = courseFeature;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseCredit=" + courseCredit
				+ ", courseTeacher=" + courseTeacher + ", courseAdd=" + courseAdd + ", courseTime=" + courseTime
				+ ", courseLong=" + courseLong + ", courseFeature=" + courseFeature + ", courseType=" + courseType
				+ "]";
	}

}