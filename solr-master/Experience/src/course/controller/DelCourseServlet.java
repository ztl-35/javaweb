package course.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.service.CourseService;

/**
 * 删除课程数据访问接口
 */
@WebServlet("/DelCourseServlet")
public class DelCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseId = request.getParameter("courseId");
		System.out.println("删除的课程ID:"+courseId);
		CourseService service = new CourseService();

		try {
			service.deleteCourse(Integer.parseInt(courseId));
			PrintWriter out = response.getWriter();
			out.print("1");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
