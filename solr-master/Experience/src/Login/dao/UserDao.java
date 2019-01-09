package Login.dao;

import java.sql.SQLException;
import Login.pojo.User;
 
public interface UserDao {

	public User select(String uname,String upwd) throws SQLException;

	public Boolean register(String rtel, String rname,String rpwd) throws SQLException;
	public boolean checkUserByUserName(String userName);
	public boolean checkAdmin(String userName);
}
