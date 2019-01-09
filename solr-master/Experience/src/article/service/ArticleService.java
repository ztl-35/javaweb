package article.service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Pager;
import article.dao.ArticleDao;
import article.pojo.Article;

public class ArticleService {

	private ArticleDao dao = new ArticleDao();

	
	public void addArticle(Article article) throws SQLException {

		dao.insert(article);
	}

	public void updateArticle(Article article) throws SQLException {

		dao.update(article);
	}

	public void deleteArticle(int articleId) throws SQLException {

		dao.delete(articleId);
	}

	public Article queryArticleById(int articleId) throws SQLException {

		return dao.queryById(articleId);
	}

	public int getCountArticle() throws SQLException {

		return dao.getCount();
	}

	public ArrayList<Article> queryAllArticle(Pager pager) throws SQLException {

		return dao.queryAll();
	}

}
