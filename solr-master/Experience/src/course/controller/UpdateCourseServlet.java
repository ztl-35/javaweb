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

		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 输出流
		PrintWriter out = response.getWriter();

		// 获得请求参数
		String courseId = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String courseCredit = request.getParameter("courseCredit");
		String courseTeacher = request.getParameter("courseTeacher");
		String courseAdd = request.getParameter("courseAddress");
		String courseTime = request.getParameter("courseTime");
		String courseLong = request.getParameter("courseLong");
		String courseFeature = request.getParameter("courseFeature");
		String courseType = request.getParameter("courseType");

		// 封装数据
		Course course = new Course(Integer.parseInt(courseId), courseName, courseCredit, courseTeacher, courseAdd,
				courseTime, courseLong, courseFeature, courseType);

		// 创建业务对象
		CourseService service = new CourseService();

		try {
			service.updateCourse(course);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// 成功提示
		out.print("修改成功！");

	}

}
