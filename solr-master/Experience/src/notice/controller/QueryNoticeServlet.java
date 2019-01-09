package notice.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.dao.NoticeDao;
import notice.pojo.Notice;

@WebServlet("/QueryNoticeServlet")
public class QueryNoticeServlet extends HttpServlet {

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

		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		NoticeDao noticeDao = new NoticeDao();
		
		ArrayList<Notice> noticeList=null;
		try {
			noticeList = noticeDao.queryAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//设置分页参数
		String currentNoticePage = request.getParameter("currentNoticePage");
		if (currentNoticePage==null) {
			currentNoticePage = 1+"";
		}
		int pageSize = 3;
		
		//总记录数目
		int count = noticeList.size();
		Integer totalPage = (int) Math.ceil(count * 1.0 / pageSize);
		List<Notice> queryPageList = noticeDao.queryPageData(Integer.parseInt(currentNoticePage), pageSize, noticeList);
		
		request.setAttribute("NOTICELIST", queryPageList);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pagesize", pageSize);
		request.setAttribute("currentNoticePage", currentNoticePage);
		request.getRequestDispatcher("NewQueryNotice.jsp").forward(request, response);

	}

}
