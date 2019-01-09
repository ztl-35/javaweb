package BookInfoSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class BookDAO {
	Connection conn;
	Statement st;
	public BookDAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "420519";
		conn = DriverManager.getConnection(url, user, password);
		st = (Statement) conn.createStatement();
	}
	public int addBook(Book book) throws SQLException {
		String sql1 = "use book;";
		st.executeUpdate(sql1);
		String sql="insert into books(Name,Author,Price,Publisher) values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, book.getBookName());
		ps.setString(2, book.getAuthor());
		ps.setDouble(3, book.getBookPrice());
		ps.setString(4, book.getBookPublisher());
		return ps.executeUpdate();
	}
	public int deleteBook(int id) throws SQLException {
		String sql1 = "use book;";
		st.executeUpdate(sql1);
		String sql="delete from books where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		return ps.executeUpdate();
	}
	public int modifyBook(int id,Book book) throws SQLException {
		String sql1 = "use book;";
		st.executeUpdate(sql1);
		String sql = "update books set Name=?,Author=?,Price=?,Publisher=? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, book.getBookName());
		ps.setString(2, book.getAuthor());
		ps.setDouble(3, book.getBookPrice());
		ps.setString(4, book.getBookPublisher());
		ps.setInt(5, id);
		return ps.executeUpdate();
	}
	//获取所有图书信息
	public ArrayList<Book> getBooks() throws SQLException {
		String sql1 = "use book;";
		st.executeUpdate(sql1);
		String sql = "select * from books";
		ResultSet rs = st.executeQuery(sql);
		ArrayList<Book> arrayList = new ArrayList<Book>();
		while(rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt(1));
			book.setBookName(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setBookPrice(rs.getDouble(4));
			book.setBookPublisher(rs.getString(5));
			arrayList.add(book);
		}
		return arrayList;
	}
	//为了用户搜索查询书名
	public ArrayList<Book> getBooks(String bookName) throws SQLException {
		//这样写，感觉有点啰嗦。
//		String sql = "select * from books";
//		ResultSet rs = st.executeQuery(sql);
//		ArrayList<Book> arrayList = new ArrayList<Book>();
//		while(rs.next()) {
//			if (rs.getString(2).equals(bookName)) {
//				Book book = new Book();
//				book.setId(rs.getInt(1));
//				book.setBookName(rs.getString(2));
//				book.setAuthor(rs.getString(3));
//				book.setBookPrice(rs.getDouble(4));
//				book.setBookPublisher(rs.getString(5));
//				arrayList.add(book);
//			}else {
//				continue;
//			}
//		}
		String sql1 = "use book;";
		st.executeUpdate(sql1);
		ArrayList<Book> arrayList = new ArrayList<Book>();
		String sql = "select * from books where Name=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, bookName);
		ResultSet rs = ps.executeQuery(sql);//PreparedStatement  executeQuery 联合使用
		while(rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt(1));
			book.setBookName(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setBookPrice(rs.getDouble(4));
			book.setBookPublisher(rs.getString(5));
			arrayList.add(book);
		}
		return arrayList;
	}
	public Book getBookById(int id) throws SQLException {
		String sql1 = "use book;";
		st.executeUpdate(sql1);
		Book book = new Book();
		String sql ="select * from books where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		book.setId(rs.getInt(1));
		book.setBookName(rs.getString(2));
		book.setAuthor(rs.getString(3));
		book.setBookPrice(rs.getDouble(4));
		book.setBookPublisher(rs.getString(5));
		return book;
	}
}
