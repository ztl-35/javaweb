package comment.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.pojo.Comment;
import comment.service.CommentService;

@WebServlet("/QueryCommentServlet")
public class QueryCommentServlet extends HttpServlet {

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
		// 创建业务对象
		CommentService service = new CommentService();
		// 获得请求参数
		String articleId = request.getParameter("articleId");
		
		
		List<Comment> commentList = null;
		try {
			commentList = service.queryByArticleId(Integer.parseInt(articleId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("COMMENTLIST", commentList);
		request.getRequestDispatcher("NewQueryArticle.jsp").forward(request, response);
	}
}