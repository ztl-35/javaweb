package article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.pojo.Article;
import article.service.ArticleService;

/**
 * Servlet implementation class UpdateDeptServlet
 */
@WebServlet("/UpdateArticleServlet")
public class UpdateArticleServlet extends HttpServlet {

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

		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// �����
		PrintWriter out = response.getWriter();

		// ����������
		String articleId = request.getParameter("articleId");
		String articleTitle = request.getParameter("articleTitle");
		String articleContent = request.getParameter("articleContent");
		//String articleDate = request.getParameter("articleDate");

		//��˲����޸Ĺ���ĵ�ǰʱ��
		Calendar now = Calendar.getInstance();
		String year = now.get(Calendar.YEAR)+"";
		String month = (now.get(Calendar.MONTH) + 1) + "";
		String day = now.get(Calendar.DAY_OF_MONTH)+"";
		if (Integer.parseInt(month)<=9) {
			month = "0"+month;
		}
		if (Integer.parseInt(day)<=9) {
			day = "0"+day;
		}
		String articleDate = year+"-"+month+"-"+day;
		
		// ��װ����
		Article article = new Article(Integer.parseInt(articleId), articleTitle, articleContent,
				Date.valueOf(articleDate));

		// ����ҵ�����
		ArticleService service = new ArticleService();

		try {
			service.updateArticle(article);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// �ɹ���ʾ
		out.print("�޸ĳɹ���");

	}

}
