package book.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.pojo.Book;
import book.pojo.Subject;
import common.DbUtil;


public class BookDao {
	
	/**
	 * 定义课程信息分页参数
	 */
	int pageNo;
	int pageSize;
	int pageCount;
	
	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
	/**
	 * 插入数据
	 * 
	 * @param book
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public void insert(Book book) throws SQLException, ClassNotFoundException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into book(book_name,book_writer,book_rank,book_classification,book_description,subject_id) values (?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getBookWriter());
		pstmt.setString(3, book.getBookRank());
		pstmt.setString(4, book.getBookClassification());
		pstmt.setString(5, book.getBookDescription());
		pstmt.setInt(6, book.getSubjectId());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 
	 * 删除数据
	 * 
	 * @param bookId
	 * @throws SQLException
	 */
	public void delete(int bookId) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "delete from book where book_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bookId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 修改数据
	 * 
	 * @param book
	 * @throws SQLException
	 */
	public void update(Book book) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "update book set book_name=?,book_writer=?,book_rank=?,book_classification=?,book_description=?,subject_id=? where book_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getBookWriter());
		pstmt.setString(3, book.getBookRank());
		pstmt.setString(4, book.getBookClassification());
		pstmt.setString(5, book.getBookDescription());
		pstmt.setInt(6, book.getSubjectId());
		pstmt.setInt(7, book.getBookId());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 根据id查询单条数据
	 * 
	 * @param bookId
	 * @return
	 * @throws SQLException
	 */
	public Book queryById(int bookId) throws SQLException {

		Book book = null;

		Connection conn = DbUtil.getConnection();

		String sql = "select b.book_id,b.book_name,b.book_writer,b.book_rank,b.book_classification,b.book_description,b.subject_id,s.subject_name from book b inner join subject s on s.subject_id = b.subject_id where book_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, bookId);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			
			Subject subject = new Subject();
			subject.setSubjectId(rs.getInt(7));
			subject.setSubjectName(rs.getString(8));
			
			book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6),rs.getInt(7));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return book;
	}
		
		/**
		 * 分页查询所有数据
		 * 
		 * @return
		 * @throws SQLException
		 */
		public ArrayList<Book> queryAllBook() throws SQLException {

			ArrayList<Book> list = new ArrayList<Book>();

			Connection conn = DbUtil.getConnection();
			
			String sql = "select * from book";
			System.out.println(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6),rs.getInt(7));
				list.add(book);
			}

			rs.close();
			pstmt.close();
			conn.close();

			return list;
		}
		
		public ArrayList<Book> queryBookBy_SubjectId(int sunjectId) throws SQLException{

			ArrayList<Book> list = new ArrayList<Book>();

			Connection conn = DbUtil.getConnection();
			
			String sql = "select * from book where subject_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sunjectId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6),rs.getInt(7));
				list.add(book);
			}
			rs.close();
			pstmt.close();
			conn.close();
			return list;
		}
		
		public List<Book> queryBookByPage(int currentPage,int pageSize,List<Book> bookList){
			int count = bookList.size();
			int fromIndex = (currentPage - 1) * pageSize;
			int c = fromIndex + pageSize;
			int toIndex;
			if (c <= (count - 1)) {
				toIndex = c;
			} else {
				toIndex = count;
			}
			List<Book> subList = bookList.subList(fromIndex, toIndex);
			return subList;
		}
		
		/**
		 * 查询本页数据
		 * 
		 * @return
		 * @throws SQLException
		 */
		public ArrayList<Book> queryPageData() throws SQLException {

			ArrayList<Book> list = new ArrayList<Book>();
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM book LIMIT "+(pageNo-1)*pageSize+","+pageSize;
			System.out.println(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
				list.add(book);
			}
			rs.close();
			pstmt.close();
			conn.close();

			return list;
		}

		/**
		 * 返回总记录数
		 * @throws SQLException 
		 */
		public void computePageCount() throws SQLException {
			

			Connection conn = DbUtil.getConnection();

			String sql = "SELECT COUNT(*) FROM book";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			pageCount = rs.getInt(1);
			if (pageCount%pageSize==0) {
				pageCount = pageCount/pageSize;
			}else {
				pageCount = pageCount/pageSize+1;
			}
			rs.close();
			pstmt.close();
			conn.close();	
		}
}


