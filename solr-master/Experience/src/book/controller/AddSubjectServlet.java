package book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.pojo.Subject;
import book.service.SubjectService;

/**
 * ���Ӳο������ݷ��ʽӿ�
 * 
 * @author Administrator
 *
 */
@WebServlet("/AddSubjectServlet")
public class AddSubjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// �����
		PrintWriter out = response.getWriter();

		// ����������
		String subjectName = request.getParameter("subjectName");
		String subjectDesc = request.getParameter("subjectDesc");
		

		// ��װ����
		Subject subject = new Subject(subjectName, subjectDesc);

		// ����ҵ�����
		SubjectService service = new SubjectService();

		try {
			service.addSubject(subject);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// �ɹ���ʾ
		out.print("���ӳɹ���");

	}

}
