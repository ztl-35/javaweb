package book.service;

import java.sql.SQLException;
import java.util.ArrayList;

import book.pojo.Subject;
import book.dao.SubjectDao;

public class SubjectService {

	private SubjectDao dao = new SubjectDao();

	//
	public void addSubject(Subject subject) throws SQLException {

		dao.insert(subject);
	}

	public void updateSubject(Subject subject) throws SQLException {

		dao.update(subject);
	}

	public void deleteSubject(int subjectId) throws SQLException {

		dao.delete(subjectId);
	}

	public Subject querySubjectById(int subjectId) throws SQLException {

		return dao.queryById(subjectId);
	}

	/**
	 * 查询所有部门
	 * @throws SQLException 
	 */
	public ArrayList<Subject> queryAllSubject() throws SQLException {
		
		return dao.queryAll();
	}

}
