package book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import book.pojo.Book;
import book.service.BookService;

/**
 * Servlet implementation class GetBookServlet
 */
@WebServlet("/GetBookServlet")
public class GetBookServlet extends HttpServlet {
	private static final long serialVersionUID = 3940326462287371131L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		输出流
		PrintWriter out = response.getWriter();

		String bookId = request.getParameter("bookId");
		
		BookService service = new BookService();
			
		try {
			Book book = service.queryBookById(Integer.parseInt(bookId));
			
			System.out.println(book);	
			
//			产生json格式数据
			Gson gson = new GsonBuilder()  
			  .setDateFormat("yyyy-MM-dd")  
			  .create();
			
			out.print(gson.toJson(book));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
