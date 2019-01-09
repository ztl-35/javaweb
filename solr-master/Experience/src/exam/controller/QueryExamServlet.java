package exam.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.dao.ExamDao;

/**
 * Servlet implementation class QueryExamServlet
 */
@WebServlet("/QueryExamServlet")
public class QueryExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding ("UTF-8");
		String op = request.getParameter("op");
		HttpSession session = request.getSession();
		//在session中获得当前页码
		int page = Integer.parseInt(session.getAttribute("Exampage").toString());
		if (op.equals("prev")) {
			if (page>1) {
				page--;
			}
		}else if (op.equals("indexPage")) {
			page=1;
		}else if (op.equals("lastPage")) {
			ExamDao examDao = new ExamDao();
			try {
				examDao.setPageSize(4);
				examDao.computePageCount();
				page = examDao.getPageCount();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else {
			ExamDao examDao = new ExamDao();
			try {
				examDao.setPageSize(4);
				examDao.computePageCount();
				
				if (page<examDao.getPageCount()) {
					page++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		session.setAttribute("Exampage", page);
		response.sendRedirect("http://localhost:8080/Experience/NewQueryExam.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
