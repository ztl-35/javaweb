package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import common.Pager;
import dao.courseDAO;
import dao.roomDAO;
import dao.studentDAO;
import dao.teacherDAO;
import model.Course;
import model.Room;
import model.Student;
import model.Teacher;

public class courseAction {
	private courseDAO coursedao = new courseDAO();
	private teacherDAO teacherdao = new teacherDAO();
	private roomDAO roomdao = new roomDAO();
	StringBuilder stringBuilder = new StringBuilder();
	private Course course;
	private Teacher teacher;
	private Room room;
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String main() {
		prepareData();
		return "coursemain";
	}
	private void prepareData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (page==0) {
			//如果第一次启动，page是0.导致后面的计算会出错。
			page=1;
		}
		Pager pager = new Pager();
		coursedao.calulatePageCount(pager);
		if (page>pager.getPageCount()) {
			page = pager.getPageCount();
		}
		if (ServletActionContext.getContext().get("ADD")!=null) {
			page = pager.getPageCount();
		}
		pager.setPageNo(page);
		//注意，这个地方是引用传递，所以在getcourses类中修改pager属性，在这个main方法中也会把对应的pager属性修改。
		List<Course> list = coursedao.getCourses(pager);
		//将数据存入到值栈中
		if (list.size()>0) {
			ServletActionContext.getContext().put("COURSES", list);
			ServletActionContext.getContext().put("TEACHERS", teacherdao.getTeacher());
			ServletActionContext.getContext().put("ROOMS",roomdao.getRooms());
			//设置导航条
			//1、要把当前页码放到中间
			int btncount = pager.getPageBtn();
			btncount = btncount/2;
			int start = page-btncount;
			int end = page+btncount;
			if (start<1) {
				start = 1;
				end = start+2*btncount;
				
			}
			if (end>pager.getPageCount()) {
				end = pager.getPageCount();
				start = end-2*btncount;
			}
			if (start<1) {
				start=1;
			}
			//2、从起始到终点进行页面JSP的显示。需要对每一个页码进行跳转页面的设置
			for (int i = start; i <= end; i++) {
				stringBuilder.append("<a href="+request.getContextPath()+"/course/main?page="+i+">");
				//把当前页码突出显示
				if (i==page) {
					stringBuilder.append("<font color=red><b>");
					stringBuilder.append(i);
					stringBuilder.append("</b></font>");
				}else {
					stringBuilder.append(i);
				}
				stringBuilder.append("</a>");
				stringBuilder.append("&nbsp&nbsp");
			}
			ServletActionContext.getContext().put("NVRBAR", stringBuilder.toString());
		}
	}
	public String initmain() {
		prepareData();
		return "initmain";
	}
	public String cmain() throws IOException {
		prepareData();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		//得到服务器中session的值   方法一
//		Object object =ServletActionContext.getContext().getSession().get("STUDENT");
//		if (object==null) {
//			HttpServletResponse response = ServletActionContext.getResponse();
//			HttpServletRequest request = ServletActionContext.getRequest();
//			response.sendRedirect(request.getContextPath()+"/Login.jsp");
//			return null;
//		}else
//			return "choosemain";
		
		//方法二
		Object object = request.getSession().getAttribute("STUDENT");
		if (object==null) {
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
			return null;
		}else
			return "choosemain";
		
	}
	public String update() {
		return updateIt();
	}
	private String updateIt() {
		if (course.getId()==0) {
			//表示update是添加新数据操作而不是修改页面操作
			ServletActionContext.getContext().put("ADD", true);
		}
		coursedao.update(course);
		return main();
	}
	public String modify() {
		return updateIt();
	}
	public String delete() {
		coursedao.deleteCourse(course);
		return null;
	}
	public String getCourses() throws IOException {
		course = coursedao.getCourse(course);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(course.getId()+"!"+course.getName()+"!"+course.getType()+"!"+course.getHours());
		return null;
	}
	public String updateTeacher() {
		teacher = teacherdao.getTeacher(teacher);
		course = coursedao.getCourse(course);
		course.setTeacher(teacher);
		coursedao.update(course);
		return null;
	}
	public String updateRoom() {
		room = roomdao.getRoom(room);
		course = coursedao.getCourse(course);
		course.setRoom(room);
		coursedao.update(course);
		return null;
	}
	public String checkSelect() throws IOException {
		Student student = (Student) ServletActionContext.getContext().getSession().get("STUDENT");
		Set<Course> courses = student.getCourses();
		Iterator<Course> iterator = courses.iterator();
		boolean flag = false;
		while (iterator.hasNext()) {
			 Course course1 = iterator.next();
			 if (course1.getId()==course.getId()) {
				flag = true;
				break;
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(flag);
		return null;
	}
	public String checkIt() throws IOException {
		Student student = (Student) ServletActionContext.getContext().getSession().get("STUDENT");
		studentDAO sDao = new studentDAO();
		student = sDao.getStudent(student);
		course = coursedao.getCourse(course);
		Set<Course> courses = student.getCourses();
		Iterator<Course> iterator = courses.iterator();
		boolean flag = false;
		Course currentCourse=null;
		while (iterator.hasNext()) {
			currentCourse = iterator.next();
			 if (currentCourse.getId()==course.getId()) {
				flag = true;
				break;
			}
		}
		String courseName = "";
		if (flag) {
			//它曾经选课了，现在想取消选课
			courseName = "0!"+currentCourse.getName();
			student.getCourses().remove(currentCourse);
		}else {
			//它曾经没有选课，现在想选这门课
			courseName = "1!"+course.getName();
			student.getCourses().add(course);
		}
		sDao.updateStudent(student);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(courseName);
		return null;
	}
}
