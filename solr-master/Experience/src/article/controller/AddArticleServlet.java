package article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.pojo.Article;
import article.service.ArticleService;

@WebServlet("/AddArticleServlet")
public class AddArticleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddArticleServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 输出流
		PrintWriter out = response.getWriter();
		// 获得请求参数
		String articleTitle = request.getParameter("articleTitle");
		String articleContent = request.getParameter("articleContent");
		String articleDate = request.getParameter("articleDate");

		// 封装数据
		Article article = new Article(articleTitle, articleContent, Date.valueOf(articleDate));

		// 创建业务对象
		ArticleService service = new ArticleService();
		try {
			service.addArticle(article);
		} catch (SQLException e) {

			e.printStackTrace();
			return;
		}

		// 成功提示
		out.print("<h1>发布成功</h1>");
	}

}
