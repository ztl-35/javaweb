package article.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleService;

/**
 * 删除公告数据访问接口
 */
@WebServlet("/DelArticleServlet")
public class DelArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String articleId = request.getParameter("articleId");
		ArticleService service = new ArticleService();
		try {
			service.deleteArticle(Integer.parseInt(articleId));
		} catch (Exception e) {

			e.printStackTrace();
		}
		out.println("删除成功");
	}
		
}
