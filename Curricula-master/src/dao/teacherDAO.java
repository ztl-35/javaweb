package dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;

import common.HibernateUtil;
import common.Pager;
import model.Teacher;

public class teacherDAO {
	public void update(Teacher teacher) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.saveOrUpdate(teacher);
		session.getTransaction().commit();
		session.close();
	}
	public void calulatePageCount(Pager pager) {
		String hql = "from Teacher";//如果没有hibernate的框架，这里使用MySQL底层的语句，用limit语句来限制每次的记录数。
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Teacher> list = query.list();
		//计算总页数pageCount
		pager.setPageCount(list.size()/pager.getPageSize());
		if (list.size()%pager.getPageSize()!=0) {
			pager.setPageCount(pager.getPageCount()+1);
		}
		session.getTransaction().commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<Teacher> getTeachers(Pager pager){
		String hql = "from Teacher";//如果没有hibernate的框架，这里使用MySQL底层的语句，用limit语句来限制每次的记录数。
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		//分页显示
		//设置起始的位置
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//设置终点距离起始点位置的间隔
		query.setMaxResults(pager.getPageSize());
		//将之前的query进行数据段的提取
		List<Teacher> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Teacher> getTeacher(){
		String hql = "from Teacher";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		List<Teacher> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	public void deleteTeacher(Teacher teacher) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(teacher);
		session.getTransaction().commit();
		session.close();
	}
	public Teacher getTeacher(Teacher teacher) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		String hql="from Teacher where id=:id";
		Query query = session.createQuery(hql)
				.setProperties(teacher);
		Teacher teacher2 = (Teacher) query.uniqueResult();
		session.getTransaction().commit();
		return teacher2;
	}
}
