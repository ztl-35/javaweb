package dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;

import common.HibernateUtil;
import common.Pager;
import model.Room;

public class roomDAO {
	public void update(Room room) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.saveOrUpdate(room);
		session.getTransaction().commit();
		session.close();
	}
	public void calulatePageCount(Pager pager) {
		String hql = "from Room";//���û��hibernate�Ŀ�ܣ�����ʹ��MySQL�ײ����䣬��limit���������ÿ�εļ�¼����
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Room> list = query.list();
		//������ҳ��pageCount
		pager.setPageCount(list.size()/pager.getPageSize());
		if (list.size()%pager.getPageSize()!=0) {
			pager.setPageCount(pager.getPageCount()+1);
		}
		session.getTransaction().commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<Room> getRooms(Pager pager){
		String hql = "from Room";//���û��hibernate�Ŀ�ܣ�����ʹ��MySQL�ײ����䣬��limit���������ÿ�εļ�¼����
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		//��ҳ��ʾ
		//������ʼ��λ��
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//�����յ������ʼ��λ�õļ��
		query.setMaxResults(pager.getPageSize());
		//��֮ǰ��query�������ݶε���ȡ
		List<Room> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Room> getRooms(){
		String hql = "from Room";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		List<Room> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	public void deleteRoom(Room room) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(room);
		session.getTransaction().commit();
		session.close();
	}
	public Room getRoom(Room room) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		String hql="from Room where id=:id";
		Query query = session.createQuery(hql)
				.setProperties(room);
		Room room2 = (Room) query.uniqueResult();
		session.getTransaction().commit();
		return room2;
	}
}
