package notice.controller;

import java.util.*;

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

/**
 * Servlet implementation class UpdateDeptServlet
 */
@WebServlet("/UpdateNoticeServlet")
public class UpdateNoticeServlet extends HttpServlet {

	/**
	 * 
	 */
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
		response.setCharacterEncoding("utf-8");
		// �����
		PrintWriter out = response.getWriter();

		// ����������
		String noticeId = request.getParameter("noticeId");
		String noticeHeader = request.getParameter("noticeHeader");
		String noticeContent = request.getParameter("noticeContent");
		
		//��˲����޸Ĺ���ĵ�ǰʱ��
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
		String noticeDate = year+"-"+month+"-"+day;
		
		
		// ��װ����
		Notice notice = new Notice(Integer.parseInt(noticeId), noticeHeader, noticeContent,Date.valueOf(noticeDate));

		// ����ҵ�����
		NoticeService service = new NoticeService();

		try {
			service.updateNotice(notice);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// �ɹ���ʾ
		out.print("�޸ĳɹ���");

	}

}
