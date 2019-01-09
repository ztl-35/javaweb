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
	 * Notice(3,"关于周年庆放假的通知","因为8月30日是本公司成立10周年的纪念日，故放假5天。具体放假时间为 8月30日~9月3日，请相互转告！"
	 * ,Date.valueOf("2016-8-19")); System.out.println(notice); }
	 */

}
