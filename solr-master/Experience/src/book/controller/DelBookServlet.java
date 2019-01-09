package book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.service.BookService;

/*
/**
 * ɾ���ο������ݷ��ʽӿ�
 */
@WebServlet("/DelBookServlet")
public class DelBookServlet extends HttpServlet {

	// private static final long serialVersionUID = 8642656559598059856L;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookId = request.getParameter("bookId");
		System.out.println("ɾ���鱾Id:"+bookId);
		BookService service = new BookService();

		try {
			service.deleteBook(Integer.parseInt(bookId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out =response.getWriter();
		out.print("1");
	}

}
