package book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.pojo.Book;
import book.service.BookService;

/**
 * ���Ӳο������ݷ��ʽӿ�
 * 
 * @author Administrator
 *
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {

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
		String bookName = request.getParameter("bookName");
		String bookWriter = request.getParameter("bookWriter");
		String bookRank = request.getParameter("bookRank");
		String bookClassification = request.getParameter("bookClassification");
		String bookDescription = request.getParameter("bookDescription");
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		
		Book book = new Book(bookName, bookWriter, bookRank, bookClassification, bookDescription, subjectId);

		// ����ҵ�����
		BookService service = new BookService();

		try {
			service.addBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		// �ɹ���ʾ
		out.print("���ӳɹ���");

	}

}
