package edu.sxu.dao.UserDaoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import edu.sxu.dao.UserDao;
import edu.sxu.model.User;

@Component(value="sqlDao")
@Scope(value="singleton")
public class userDaoMysqlImpl implements UserDao {

	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void save(User user) {
		hibernateTemplate.merge(user);
	}
	@Override
	public boolean isExistUser(User user) {
		return hibernateTemplate.findByExample(user).size()>0;
	}
	@Override
	public User getUser(User user) {
		List<User> userList = hibernateTemplate.findByExample(user);
		if (hibernateTemplate.findByExample(user).size()>0) {
			return userList.get(0);
		}
		return null;
	}
	
}
