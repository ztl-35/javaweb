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
		String hql = "from Room";//如果没有hibernate的框架，这里使用MySQL底层的语句，用limit语句来限制每次的记录数。
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Room> list = query.list();
		//计算总页数pageCount
		pager.setPageCount(list.size()/pager.getPageSize());
		if (list.size()%pager.getPageSize()!=0) {
			pager.setPageCount(pager.getPageCount()+1);
		}
		session.getTransaction().commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<Room> getRooms(Pager pager){
		String hql = "from Room";//如果没有hibernate的框架，这里使用MySQL底层的语句，用limit语句来限制每次的记录数。
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
	    Query query = (Query) session.createQuery(hql);
		//分页显示
		//设置起始的位置
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//设置终点距离起始点位置的间隔
		query.setMaxResults(pager.getPageSize());
		//将之前的query进行数据段的提取
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
