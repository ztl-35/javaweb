package common;

public class Pager {
	//��ǰҳ��
	private int pageNo;
	//ÿһҳ��ʾ���Ƕ���������
	private int pageSize=2;
	//��ҳ��
	private int pageCount;
	//ÿһ��ҳ���ж��ٸ���ťѡ��ҳ��
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
