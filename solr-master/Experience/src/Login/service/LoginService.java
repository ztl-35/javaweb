package Login.service;

import java.sql.SQLException;

import Login.dao.UserDao;
import Login.daoimpl.UserDaoImpl;
import Login.pojo.User;

public class LoginService {
	private UserDao dao = new UserDaoImpl();
	
	public User select(String uname,String upwd) throws SQLException{
		return dao.select(uname,upwd); 
	}
	public boolean checkAdmin(String userName) {
		return dao.checkAdmin(userName);
	}
}
