package book.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.dao.BookDao;
import book.pojo.Book;


/**
 * ��ҳ��ʾ�ο�����Ϣ
 */
@WebServlet("/QueryBookServlet")
public class QueryBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// ���ñ���
			HttpSession session = request.getSession();
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding ("UTF-8");
			String subjectId = request.getParameter("subjectId");
			
			ArrayList<Book> bookList = null;
			BookDao bookDao = new BookDao();
			//����ǰ�����󴫵ݵ�subjectId�жϿ�Ŀ
			if (subjectId==null) {
				if (session.getAttribute("SUBJECTID")==null) {
					try {
						bookList = bookDao.queryAllBook();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					subjectId = (String)session.getAttribute("SUBJECTID");
					int subjectID = Integer.parseInt(subjectId);
					try {
						bookList = bookDao.queryBookBy_SubjectId(subjectID);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else if ("100".equals(subjectId)) {
				try {
					session.setAttribute("SUBJECTID", null);
					bookList = bookDao.queryAllBook();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				session.setAttribute("SUBJECTID", subjectId);
				//����ǰ�˴��ݹ��������ֵĲ�ͬ�жϵ���˲�ͬ�Ŀ�Ŀ
				int subjectID = Integer.parseInt(subjectId);
				try {
					bookList = bookDao.queryBookBy_SubjectId(subjectID);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String currentPage = request.getParameter("currentPage");
			if (currentPage==null) {
				currentPage = 1+"";
			}
			int pageSize = 3;
			
			int count = bookList.size();
			Integer totalPage = (int) Math.ceil(count * 1.0 / pageSize);
			List<Book> subList = bookDao.queryBookByPage(Integer.parseInt(currentPage), pageSize, bookList);
			request.setAttribute("BOOKLIST", subList);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pagesize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("NewNewQueryBook.jsp").forward(request, response);
			
			
			
			//����ר����ǰһ�汾�ĺ���������
	/*
			String op = request.getParameter("op");
			HttpSession session = request.getSession();
			//��session�л�õ�ǰҳ��
			int page = Integer.parseInt(session.getAttribute("Bookpage").toString());
			if (op.equals("prev")) {
				if (page>1) {
					page--;
				}
			}else {
				BookDao bookDao = new BookDao();
				try {
					bookDao.setPageSize(4);
					bookDao.computePageCount();
					
					if (page<bookDao.getPageCount()) {
						page++;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			session.setAttribute("Bookpage", page);
			response.sendRedirect("http://localhost:8080/Experience/NewQueryBook.jsp");*/
	}

}