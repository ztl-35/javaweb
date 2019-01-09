package answer.controller;

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
import javax.servlet.http.HttpSession;

import Login.pojo.User;
import answer.dao.AnswerDao;
import answer.pojo.Answer;

/**
 * Servlet implementation class AddAnswerController
 */
@WebServlet("/AddAnswerServlet")
public class AddAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		
		String questionId = request.getParameter("questionId");
		String answerContent = request.getParameter("answerContent");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");
		String userName = user.getUser_name();
		
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
		String answerDate = year+"-"+month+"-"+day;
		AnswerDao answerDao = new AnswerDao();
		Answer answer = new Answer(answerContent, userName, Integer.parseInt(questionId), Date.valueOf(answerDate));
		try {
			answerDao.insert(answer);
			out.print("增加成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
