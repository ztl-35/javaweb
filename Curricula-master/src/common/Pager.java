package common;

public class Pager {
	//当前页码
	private int pageNo;
	//每一页显示的是多少条数据
	private int pageSize=2;
	//总页数
	private int pageCount;
	//每一个页面有多少个按钮选择页码
	private int pageBtn=4;
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
	public int getPageBtn() {
		return pageBtn;
	}
	public void setPageBtn(int pageBtn) {
		this.pageBtn = pageBtn;
	}
	
	
}
