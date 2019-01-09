package dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;

import common.HibernateUtil;
import common.Pager;
import model.Course;

public class courseDAO {
	public void update(Course course) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.saveOrUpdate(course);
		session.getTransaction().commit();
		session.close();
	}
	public void calulatePageCount(Pager pager) {
		String hql = "from Course";//如果没有hibernate的框架，这里使用MySQL底层的语句，用limit语句来限制每次的记录数。
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Course> list = query.list();
		//计算总页数pageCount
		pager.setPageCount(list.size()/pager.getPageSize());
		if (list.size()%pager.getPageSize()!=0) {
			pager.setPageCount(pager.getPageCount()+1);
		}
		session.getTransaction().commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<Course> getCourses(Pager pager){
		String hql = "from Course";//如果没有hibernate的框架，这里使用MySQL底层的语句，用limit语句来限制每次的记录数。
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		//分页显示
		//设置起始的位置
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//设置终点距离起始点位置的间隔
		query.setMaxResults(pager.getPageSize());
		//将之前的query进行数据段的提取
		List<Course> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	public void deleteCourse(Course course) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(course);
		session.getTransaction().commit();
		session.close();
	}
	public Course getCourse(Course course) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		String hql="from Course where id=:id";
		Query query = session.createQuery(hql)
				.setProperties(course);
		Course course2 = (Course) query.uniqueResult();
		session.getTransaction().commit();
		return course2;
	}
}
