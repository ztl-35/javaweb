package answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answer.dao.AnswerDao;

/**
 * Servlet implementation class AddAnswerController
 */
@WebServlet("/DelAnswerServlet")
public class DelAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String questionId = request.getParameter("questionId");
		String currentQuestionAnswerPage = request.getParameter("currentQuestionAnswerPage");
		String answerId = request.getParameter("answerId");
		request.setAttribute("questionId", questionId);
		request.setAttribute("currentQuestionAnswerPage", currentQuestionAnswerPage);
		
		AnswerDao answerDao = new AnswerDao();
		try {
			answerDao.delete(Integer.parseInt(answerId));
			out.print("É¾³ý³É¹¦");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("QueryQuestionAnswerServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
