package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import common.HibernateUtil;
import model.Student;

public class studentDAO {
	Session session;
	public void updateStudent(Student student) {
		session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		//对于插入语句的用法
		session.merge(student);
		transaction.commit();
		session.close();
	}
	public Student check(Student student) {
		session = HibernateUtil.openSession();
		String hql = "from Student where name=:name and pwd=:pwd";
		//方法一 对于查询语句的用法
//		Query query = session.createQuery(hql);
//		query.setString(1, student.getName());
//		query.setString(2, student.getPwd());
//		List<Student> list = query.list();
		
		//方法二 因为属性的变量名与student中一样，可以直接采用注入的方式
		Query query = session.createQuery(hql)
				.setProperties(student);
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();
		student = null;
		if (list.size()>0) {
			student = list.get(0);
		}
		session.close();
		return student;
	}
	public Student getStudent(Student student) {
		session = HibernateUtil.openSession();
		String hql = "from Student where id=:id";
		Query query = session.createQuery(hql)
				.setProperties(student);
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();
		student = null;
		if (list.size()>0) {
			student = list.get(0);
		}
		session.close();
		return student;
	}
	public boolean checkOldPassword(Student student) {
		session = HibernateUtil.openSession();
		String hql="from Student where id=:id and pwd=:pwd";
		Query query = session.createQuery(hql)
						.setProperties(student);
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();
		session.close();
		return list.size()>0?true:false;
	}
}
