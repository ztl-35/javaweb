package question.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.pojo.User;
import notice.pojo.Notice;
import notice.service.NoticeService;
import question.dao.QuestionDao;
import question.pojo.Question;

@WebServlet("/AddQuestionAnswerServlet")
public class AddQuestionAnswerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddQuestionAnswerServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 输出流
		PrintWriter out = response.getWriter();
		//获取登录名
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");
		String questionOwner = user.getUser_name();
		// 获得请求参数
		String questionContent = request.getParameter("questionContent");
		String questionDate = request.getParameter("questionDate");
		System.out.println("后端获取提问日期："+questionDate);
		// 封装数据
		Question question = new Question(questionOwner, questionContent, Date.valueOf(questionDate));

		// 创建业务对象
		QuestionDao questionDao = new QuestionDao();
		try {
			questionDao.insert(question);
		} catch (SQLException e) {

			e.printStackTrace();
			return;
		}

		// 成功提示
		out.print("<h1>发布成功</h1>");
	}

}
