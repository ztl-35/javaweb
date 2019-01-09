package comment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentService;

/**
 * 删除公告数据访问接口
 */
@WebServlet("/DelCommentServlet")
public class DelCommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String commentId = request.getParameter("commentId");
		String currentArticlePage = request.getParameter("currentArticlePage");
		String articleId = request.getParameter("articleId");
		request.setAttribute("currentArticlePage", currentArticlePage);
		request.setAttribute("articleId", articleId);
		CommentService service = new CommentService();
		try {
			service.deleteComment(Integer.parseInt(commentId));
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		request.getRequestDispatcher("QueryArticleServlet").forward(request, response);
		out.println("删除成功");
	}
		
}
