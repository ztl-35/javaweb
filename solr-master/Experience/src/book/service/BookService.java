package book.service;

import java.sql.SQLException;
import java.util.ArrayList;

import book.dao.BookDao;
import book.pojo.Book;
import common.Pager;

public class BookService {

	private BookDao dao = new BookDao();

	/* 插入数据 */
	public void addBook(Book book) throws SQLException {

		try {
			dao.insert(book);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 修改数据 */
	public void updateBook(Book book) throws SQLException {

		dao.update(book);
	}

	/* 删除数据 */
	public void deleteBook(int bookId) throws SQLException {

		dao.delete(bookId);
	}

	/* 查询单条数据 */
	public Book queryBookById(int bookId) throws SQLException {

		return dao.queryById(bookId);

	}

	/**
	 * 分页查询所有数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Book> queryBookByPager(Pager pager) throws SQLException{
		
		return dao.queryAllBook();
	}
	

}
