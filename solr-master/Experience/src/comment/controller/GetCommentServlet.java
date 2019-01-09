package comment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import article.pojo.Article;
import article.service.ArticleService;

/**
 * Servlet implementation class GetArticleServlet
 */
@WebServlet("/GetCommentServlet")
public class GetCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940326462287371131L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		输出流
		PrintWriter out = response.getWriter();

		String articleId = request.getParameter("articleId");

		ArticleService service = new ArticleService();

		try {
			Article article = service.queryArticleById(Integer.parseInt(articleId));
			System.out.println(article);	
			
//			产生json格式数据
			Gson gson = new GsonBuilder()  
			  .setDateFormat("yyyy-MM-dd")  
			  .create();
			
			out.print(gson.toJson(article));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}