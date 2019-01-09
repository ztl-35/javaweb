package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DbUtil;
import article.pojo.Article;

public class ArticleDao {
	// 插入
	public void insert(Article article) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into article(article_title,article_content,article_date) values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, article.getArticleTitle());
		pstmt.setString(2, article.getArticleContent());
		pstmt.setDate(3, article.getArticleDate());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();

	}

	// 修改

	public void update(Article article) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "UPDATE article SET article_title=?,article_content=?, article_date=? where article_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, article.getArticleTitle());
		pstmt.setString(2, article.getArticleContent());
		pstmt.setDate(3, article.getArticleDate());
		pstmt.setInt(4, article.getArticleId());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// 删除
	public void delete(int articleId) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "delete from article where article_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, articleId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	// 根据id查询单条
	public Article queryById(int articleId) throws SQLException {
		Article article = null;

		Connection conn = DbUtil.getConnection();
		String sql = "select * from article where article_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, articleId);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			article = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
		}
		rs.close();
		pstmt.close();
		conn.close();

		return article;

	}

	// 查询所有数据
	public ArrayList<Article> queryAll() throws SQLException {

		ArrayList<Article> list = new ArrayList<Article>();
		Connection conn = DbUtil.getConnection();
		String sql = "select * from article";
		System.out.println(sql);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Article article = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
			list.add(article);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}
	
	public List<Article> queryPageData(int currentPage,int pageSize,List<Article> articleList){
		int count = articleList.size();
		int fromIndex = (currentPage - 1) * pageSize;
		int c = fromIndex + pageSize;
		int toIndex;
		if (c <= (count - 1)) {
			toIndex = c;
		} else {
			toIndex = count;
		}
		List<Article> subList = articleList.subList(fromIndex, toIndex);
		return subList;
	}

	//
	public int getCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM   article";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		rs.next();

		int count = rs.getInt(1);

		rs.close();
		pstmt.close();
		conn.close();

		return count;
	}

}