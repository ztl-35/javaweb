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
		String hql = "from Course";//���û��hibernate�Ŀ�ܣ�����ʹ��MySQL�ײ����䣬��limit���������ÿ�εļ�¼����
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Course> list = query.list();
		//������ҳ��pageCount
		pager.setPageCount(list.size()/pager.getPageSize());
		if (list.size()%pager.getPageSize()!=0) {
			pager.setPageCount(pager.getPageCount()+1);
		}
		session.getTransaction().commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<Course> getCourses(Pager pager){
		String hql = "from Course";//���û��hibernate�Ŀ�ܣ�����ʹ��MySQL�ײ����䣬��limit���������ÿ�εļ�¼����
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		//��ҳ��ʾ
		//������ʼ��λ��
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//�����յ������ʼ��λ�õļ��
		query.setMaxResults(pager.getPageSize());
		//��֮ǰ��query�������ݶε���ȡ
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
