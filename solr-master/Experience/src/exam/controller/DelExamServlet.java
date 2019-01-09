package exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.dao.ExamDao;

@WebServlet("/DelExamServlet")
public class DelExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String examId = request.getParameter("examId");
		System.out.println("删除的等级考试ID:"+examId);
		ExamDao examDao = new ExamDao();
		HttpSession session = request.getSession();
		try {
			examDao.delete(Integer.parseInt(examId));
			//删除后，跳转到首页
			examDao.setPageSize(4);
			session.setAttribute("Exampage", 1);
			PrintWriter out = response.getWriter();
			out.print("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
