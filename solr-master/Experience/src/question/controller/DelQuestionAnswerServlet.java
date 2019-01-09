package question.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.dao.QuestionDao;

/**
 * Servlet implementation class DelQuestionAnswerServlet
 */
@WebServlet("/DelQuestionAnswerServlet")
public class DelQuestionAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String questionId = request.getParameter("questionId");
		QuestionDao questionDao = new QuestionDao();
		try {
			questionDao.delete(Integer.parseInt(questionId));
		} catch (Exception e) {

			e.printStackTrace();
		}
		out.print("É¾³ý³É¹¦");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
