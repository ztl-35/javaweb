package edu.sxu.dao.UserDaoImpl;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.sxu.dao.UserDao;
import edu.sxu.model.User;

@Component(value="fileDao")
@Scope(value="singleton")
public class userDaoFileImpl implements UserDao {
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		try {
			FileWriter fs = new FileWriter("E:\\users.txt",true);
			fs.write("username:"+user.getUserName()+" "+"userPwd:"+user.getUserPwd());
			fs.write("\r\n");
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	public void start() {
//		System.out.println("start dao...");
//	}
//	public void end() {
//		System.out.println("end dao...");
//	}

	@Override
	public boolean isExistUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
