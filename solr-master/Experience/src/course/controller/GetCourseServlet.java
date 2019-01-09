package course.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import course.pojo.Course;
import course.service.CourseService;

/**
 * Servlet implementation class GetCourseServlet
 */
@WebServlet("/GetCourseServlet")
public class GetCourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940326462287371131L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		输出流
		PrintWriter out = response.getWriter();

		String courseId = request.getParameter("courseId");

		CourseService service = new CourseService();

		try {
			Course course = service.queryCourseById(Integer.parseInt(courseId));
			System.out.println(course);	
			
//			产生json格式数据
			Gson gson = new GsonBuilder()  
			  .setDateFormat("yyyy-MM-dd")  
			  .create();
			
			out.print(gson.toJson(course));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}