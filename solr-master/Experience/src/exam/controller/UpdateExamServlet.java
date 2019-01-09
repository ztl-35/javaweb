package exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.dao.ExamDao;
import exam.pojo.Exam;

/**
 * Servlet implementation class UpdateExamServlet
 */
@WebServlet("/UpdateExamServlet")
public class UpdateExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 输出流
		PrintWriter out = response.getWriter();

		// 获得请求参数
		String examId = request.getParameter("examId");
		String examName = request.getParameter("examName");
		String examDefine = request.getParameter("examDefine");
		String examDesc = request.getParameter("examDesc");
		String examWay = request.getParameter("examWay");
		String examDate = request.getParameter("examDate");
		String examRequirement = request.getParameter("examRequirement");
		String examNote = request.getParameter("examNote");
		

		// 封装数据
		Exam exam = new Exam(Integer.parseInt(examId), examName, examDefine, examDesc, examWay,
				Date.valueOf(examDate), examRequirement, examNote);

		// 创建业务对象
		ExamDao examDao = new ExamDao();

		try {
			examDao.update(exam);
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
		doGet(request, response);
	}

}
