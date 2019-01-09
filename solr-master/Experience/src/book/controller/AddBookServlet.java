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
 * 增加参考书数据访问接口
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

		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 输出流
		PrintWriter out = response.getWriter();

		// 获得请求参数
		String bookName = request.getParameter("bookName");
		String bookWriter = request.getParameter("bookWriter");
		String bookRank = request.getParameter("bookRank");
		String bookClassification = request.getParameter("bookClassification");
		String bookDescription = request.getParameter("bookDescription");
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		
		Book book = new Book(bookName, bookWriter, bookRank, bookClassification, bookDescription, subjectId);

		// 创建业务对象
		BookService service = new BookService();

		try {
			service.addBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		// 成功提示
		out.print("增加成功！");

	}

}
