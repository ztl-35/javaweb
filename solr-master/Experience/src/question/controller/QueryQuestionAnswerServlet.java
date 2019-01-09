package question.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import answer.dao.AnswerDao;
import answer.pojo.Answer;
import question.dao.QuestionDao;
import question.pojo.Question;

/**
 * Servlet implementation class QueryQuestionAnswerServlet
 */
@WebServlet("/QueryQuestionAnswerServlet")
public class QueryQuestionAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();
		String questionId = request.getParameter("questionId");

			ArrayList<Question> questionList=null;
			try {
				questionList = questionDao.queryAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//设置分页参数
			String currentQuestionAnswerPage = request.getParameter("currentQuestionAnswerPage");
			if (currentQuestionAnswerPage==null) {
				currentQuestionAnswerPage = 1+"";
			}
			int pageSize = 3;
			
			//总记录数目
			int count = questionList.size();
			Integer totalPage = (int) Math.ceil(count * 1.0 / pageSize);
			List<Question> queryPageList = questionDao.queryPageData(Integer.parseInt(currentQuestionAnswerPage), pageSize, questionList);
			//问题列表放入session
			session.setAttribute("QALIST", queryPageList);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("pagesize", pageSize);
			session.setAttribute("currentQuestionAnswerPage", currentQuestionAnswerPage);
		
			List<Answer> answerList = null;
			if(questionId!=null) {
			
			try {
				answerList = answerDao.queryByQuestionId(Integer.parseInt(questionId));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//回答列表 放入session
			session.setAttribute("ANSWERLIST", answerList);
			
		}else {
			session.setAttribute("ANSWERLIST", null);
		}
		
		request.getRequestDispatcher("NewQuestionAnswer.jsp").forward(request, response);	
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
