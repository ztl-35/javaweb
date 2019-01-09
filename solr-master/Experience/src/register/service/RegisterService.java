package register.service;

import java.sql.SQLException;
import Login.dao.UserDao;
import Login.daoimpl.UserDaoImpl;

public class RegisterService {
	private UserDao dao = new UserDaoImpl();

	public Boolean register(String remail, String rname,String rpwd) throws SQLException{
		return dao.register(remail,rname,rpwd); 
	}
	public boolean checkUserByUserName(String userName){
		//true代表有这个用户名了
		return dao.checkUserByUserName(userName);
	}
}
