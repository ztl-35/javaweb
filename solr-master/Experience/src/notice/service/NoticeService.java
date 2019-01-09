package notice.service;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Pager;
import notice.dao.NoticeDao;
import notice.pojo.Notice;

public class NoticeService {

	private NoticeDao dao = new NoticeDao();

	//
	public void addNotice(Notice notice) throws SQLException {

		dao.insert(notice);
	}

	public void updateNotice(Notice notice) throws SQLException {

		dao.update(notice);
	}

	public void deleteNotice(int noticeId) throws SQLException {

		dao.delete(noticeId);
	}

	public Notice queryNoticeById(int noticeId) throws SQLException {

		return dao.queryById(noticeId);
	}

	public int getCountNotice() throws SQLException {

		return dao.getCount();
	}

	public ArrayList<Notice> queryAllNotice(Pager pager) throws SQLException {

		return dao.queryAll();
	}

}
