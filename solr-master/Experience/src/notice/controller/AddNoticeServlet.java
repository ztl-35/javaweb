package notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.pojo.Notice;
import notice.service.NoticeService;

@WebServlet("/AddNoticeServlet")
public class AddNoticeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddNoticeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// �����
		PrintWriter out = response.getWriter();
		// ����������
		String noticeHeader = request.getParameter("noticeHeader");
		String noticeContent = request.getParameter("noticeContent");
		String noticeDate = request.getParameter("noticeDate");
		System.out.println("��˻�ȡ�������ڣ�"+noticeDate);
		// ��װ����
		Notice notice = new Notice(noticeHeader, noticeContent, Date.valueOf(noticeDate));

		// ����ҵ�����
		NoticeService service = new NoticeService();
		try {
			service.addNotice(notice);
		} catch (SQLException e) {

			e.printStackTrace();
			return;
		}

		// �ɹ���ʾ
		out.print("<h1>�����ɹ�</h1>");
	}

}
