package BookInfoSystem;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class modifyBookServlet
 */
@WebServlet("/modifyBookServlet")
public class modifyBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int id =Integer.parseInt(request.getParameter("id"));
		String bookName = request.getParameter("bookName");
		String Author = request.getParameter("Author");
		double bookPrice = Double.parseDouble(request.getParameter("bookPrice"));
		String bookPublisher = request.getParameter("bookPublisher");
		Book book = new Book();
		book.setBookName(bookName);
		book.setAuthor(Author);
		book.setBookPrice(bookPrice);
		book.setBookPublisher(bookPublisher);
		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.modifyBook(id, book);
			response.sendRedirect("BookInfoMain.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
