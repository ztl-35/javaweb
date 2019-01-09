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
 * Servlet implementation class AddExamServlet
 */
@WebServlet("/AddExamServlet")
public class AddExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// �����
		PrintWriter out = response.getWriter();

		// ����������
		String examName = request.getParameter("examName");
		String examDefine = request.getParameter("examDefine");
		String examDesc = request.getParameter("examDesc");
		String examWay = request.getParameter("examWay");
		String examDate = request.getParameter("examDate");
		String examRequirement = request.getParameter("examRequirement");
		String examNote = request.getParameter("examNote");

		// ��װ����
		Exam exam = new Exam(examName, examDefine, examDesc, examWay, Date.valueOf(examDate), examRequirement,
				examNote);
		// ����ҵ�����
		ExamDao examDao = new ExamDao();

		try {
			examDao.insert(exam);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		// �ɹ���ʾ
		out.print("���ӳɹ���");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}