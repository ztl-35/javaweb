package dao;

import org.hibernate.Session;

import common.HibernateUtil;
import model.Contact;

public class contactDAO {
	public void updateContact(Contact contact) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.saveOrUpdate(contact);
		session.getTransaction().commit();
		session.close();
	}
}
