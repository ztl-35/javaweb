package article.controller;

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

import article.dao.ArticleDao;
import article.pojo.Article;
import comment.pojo.Comment;
import comment.service.CommentService;

@WebServlet("/QueryArticleServlet")
public class QueryArticleServlet extends HttpServlet {

	/**
	 * 
	 */
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
		HttpSession session = request.getSession();
		ArticleDao articleDao = new ArticleDao();
		CommentService commentService = new CommentService();
		String articleId = request.getParameter("articleId");
		

			ArrayList<Article> articleList=null;
			try {
				articleList = articleDao.queryAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//设置分页参数
			String currentArticlePage = request.getParameter("currentArticlePage");
			if (currentArticlePage==null) {
				currentArticlePage = 1+"";
			}
			int pageSize = 3;
			
			//总记录数目
			int count = articleList.size();
			Integer totalPage = (int) Math.ceil(count * 1.0 / pageSize);
			List<Article> queryPageList = articleDao.queryPageData(Integer.parseInt(currentArticlePage), pageSize, articleList);
			
			session.setAttribute("ARICLELIST", queryPageList);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("pagesize", pageSize);
			session.setAttribute("currentArticlePage", currentArticlePage);
		
			List<Comment> commentList = null;
			if(articleId!=null) {
			
			try {
				commentList = commentService.queryByArticleId(Integer.parseInt(articleId));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("COMMENTLIST", commentList);
			
		}else {
			session.setAttribute("COMMENTLIST", null);
		}
		
		request.getRequestDispatcher("NewQueryArticle.jsp").forward(request, response);
	}
}