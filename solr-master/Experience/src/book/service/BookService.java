package book.service;

import java.sql.SQLException;
import java.util.ArrayList;

import book.dao.BookDao;
import book.pojo.Book;
import common.Pager;

public class BookService {

	private BookDao dao = new BookDao();

	/* �������� */
	public void addBook(Book book) throws SQLException {

		try {
			dao.insert(book);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* �޸����� */
	public void updateBook(Book book) throws SQLException {

		dao.update(book);
	}

	/* ɾ������ */
	public void deleteBook(int bookId) throws SQLException {

		dao.delete(bookId);
	}

	/* ��ѯ�������� */
	public Book queryBookById(int bookId) throws SQLException {

		return dao.queryById(bookId);

	}

	/**
	 * ��ҳ��ѯ��������
	 * 
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Book> queryBookByPager(Pager pager) throws SQLException{
		
		return dao.queryAllBook();
	}
	

}
