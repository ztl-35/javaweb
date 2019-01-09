package book.pojo;

public class Book {
	private int bookId;

	private String bookName;

	private String bookWriter;

	private String bookRank;

	private String bookClassification;

	private String bookDescription;
	
	private int subjectId;
	public Book() {
		super();
	}

	public Book(int bookId, String bookName, String bookWriter, String bookRank, String bookClassification,
			String bookDescription,int subjectId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookRank = bookRank;
		this.bookClassification = bookClassification;
		this.bookDescription = bookDescription;
		this.subjectId = subjectId;
	}

	public Book(String bookName, String bookWriter, String bookRank, String bookClassification,
			String bookDescription,int subjectId) {
		super();
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookRank = bookRank;
		this.bookClassification = bookClassification;
		this.bookDescription = bookDescription;
		this.subjectId = subjectId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public String getBookRank() {
		return bookRank;
	}

	public void setBookRank(String bookRank) {
		this.bookRank = bookRank;
	}

	public String getBookClassification() {
		return bookClassification;
	}

	public void setBookClassification(String bookClassification) {
		this.bookClassification = bookClassification;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookWriter=" + bookWriter + ", bookRank="
				+ bookRank + ", bookClassification=" + bookClassification + ", bookDescription=" + bookDescription
				+", subjectId=" + subjectId+ "]";
	}


}
