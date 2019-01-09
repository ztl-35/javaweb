package notice.pojo;

import java.sql.Date;

public class Notice {
	private int noticeId;
	private String noticeHeader;
	private String noticeContent;
	private Date noticeDate;

	public Notice() {
		super();
	}

	public Notice(String noticeHeader, String noticeContent, Date noticeDate) {
		super();
		this.noticeHeader = noticeHeader;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
	}

	public Notice(int noticeId, String noticeHeader, String noticeContent, Date noticeDate) {
		super();
		this.noticeId = noticeId;
		this.noticeHeader = noticeHeader;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeHeader() {
		return noticeHeader;
	}

	public void setNoticeHeader(String noticeHeader) {
		this.noticeHeader = noticeHeader;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", noticeHeader=" + noticeHeader + ", noticeContent=" + noticeContent
				+ "]";
	}

	/*
	 * public static void main(String[] args){ Notice notice = new
	 * Notice(3,"����������żٵ�֪ͨ","��Ϊ8��30���Ǳ���˾����10����ļ����գ��ʷż�5�졣����ż�ʱ��Ϊ 8��30��~9��3�գ����໥ת�棡"
	 * ,Date.valueOf("2016-8-19")); System.out.println(notice); }
	 */

}
