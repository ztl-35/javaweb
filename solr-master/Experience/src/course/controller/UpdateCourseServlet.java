package course.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.pojo.Course;
import course.service.CourseService;

/**
 * Servlet implementation class UpdateCourseServlet
 */
@WebServlet("/UpdateCourseServlet")
public class UpdateCourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// �����
		PrintWriter out = response.getWriter();

		// ����������
		String courseId = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String courseCredit = request.getParameter("courseCredit");
		String courseTeacher = request.getParameter("courseTeacher");
		String courseAdd = request.getParameter("courseAddress");
		String courseTime = request.getParameter("courseTime");
		String courseLong = request.getParameter("courseLong");
		String courseFeature = request.getParameter("courseFeature");
		String courseType = request.getParameter("courseType");

		// ��װ����
		Course course = new Course(Integer.parseInt(courseId), courseName, courseCredit, courseTeacher, courseAdd,
				courseTime, courseLong, courseFeature, courseType);

		// ����ҵ�����
		CourseService service = new CourseService();

		try {
			service.updateCourse(course);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// �ɹ���ʾ
		out.print("�޸ĳɹ���");

	}

}
