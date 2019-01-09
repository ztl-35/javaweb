package question.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.dao.QuestionDao;
import question.pojo.Question;

/**
 * Servlet implementation class UpdateQuestionAnswerServlet
 */
@WebServlet("/UpdateQuestionAnswerServlet")
public class UpdateQuestionAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 输出流
		PrintWriter out = response.getWriter();

		// 获得请求参数
		String questionId = request.getParameter("questionId");
		String questionOwner = request.getParameter("questionOwner");
		String questionContent = request.getParameter("questionContent");
		//String articleDate = request.getParameter("articleDate");

		//后端补充修改公告的当前时间
		Calendar now = Calendar.getInstance();
		String year = now.get(Calendar.YEAR)+"";
		String month = (now.get(Calendar.MONTH) + 1) + "";
		String day = now.get(Calendar.DAY_OF_MONTH)+"";
		if (Integer.parseInt(month)<=9) {
			month = "0"+month;
		}
		if (Integer.parseInt(day)<=9) {
			day = "0"+day;
		}
		String questionDate = year+"-"+month+"-"+day;
		
		// 封装数据
		Question question = new Question(Integer.parseInt(questionId), questionOwner, questionContent,
				Date.valueOf(questionDate));

		// 创建业务对象
		QuestionDao questionDao = new QuestionDao();

		try {
			questionDao.update(question);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// 成功提示
		out.print("修改成功！");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
