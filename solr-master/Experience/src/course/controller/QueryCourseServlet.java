package course.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import course.dao.CourseDao;
import exam.dao.ExamDao;

/**
 * 分页显示课程信息
 */
@WebServlet("/QueryCourseServlet")
public class QueryCourseServlet extends HttpServlet {

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
		response.setCharacterEncoding ("UTF-8");
		String op = request.getParameter("op");
		HttpSession session = request.getSession();
		//在session中获得当前页码
		int page = Integer.parseInt(session.getAttribute("Coursepage").toString());
		if (op.equals("prev")) {
			if (page>1) {
				page--;
			}
		}else if (op.equals("indexPage")) {
			page=1;
		}else if (op.equals("lastPage")) {
			CourseDao courseDao = new CourseDao();
			try {
				courseDao.setPageSize(4);
				courseDao.computePageCount();
				page = courseDao.getPageCount();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else {
			CourseDao courseDao = new CourseDao();
			try {
				courseDao.setPageSize(4);
				courseDao.computePageCount();
				
				if (page<courseDao.getPageCount()) {
					page++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		session.setAttribute("Coursepage", page);
		response.sendRedirect("http://localhost:8080/Experience/NewQueryCourse.jsp");
	}
}