package notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.service.NoticeService;

/**
 * 删除公告数据访问接口
 */
@WebServlet("/DelNoticeServlet")
public class DelNoticeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String noticeId = request.getParameter("noticeId");
		NoticeService service = new NoticeService();
		try {
			service.deleteNotice(Integer.parseInt(noticeId));
		} catch (Exception e) {

			e.printStackTrace();
		}
		out.print("删除成功");
	}

}
