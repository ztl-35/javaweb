package edu.sxu.service.userServiceImpl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.sxu.dao.UserDao;
import edu.sxu.model.User;
import edu.sxu.service.UserService;

@Component(value="UserService")
@Scope(value="singleton")
public class UserSericeImpl implements UserService {
	//如果直接在dao后面加上new 一个对象，相当于把这个对象写死了(比如有些需要写入文件，有些需要写入数据库)。
	//应该从spring中动态配置（spring IOC=控制反转   把程序员的定义权限给到spring配置文件中）
	//如果要运行这个文件，需要把dao数据注入进来，从spring配置文件和下面的setDao进行注入（DI = 依赖注入 = denpendence injection）
	UserDao dao;
	public UserDao getDao() {
		return dao;
	}
//	注解的方式注入数据
	@Resource(name="sqlDao")
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
//	这是构造函数方式来注入数据
//	public UserSericeImpl(UserDao dao) {
//		super();
//		this.dao = dao;
//	}
	//这个地方对应于UserService，Userservice对应于UserDao方法
	//如果在userdao中有多个实现方法，比如有的要写到文件中，有的要写到数据库中，那么在userService的实现中也要实现多个方法
	//这个add应该有两个implement实现，一个对应于userdaoMysqlImpl  一个对应与userdaoFileImpl
	//所以在userServic包下面应该有两个Java文件，但是在action调用的时候，只能new一个实例化，那么用户怎么去动态的修改调用哪个Java文件呢？
	//这里用到了spring的配置

	@Override
	public void add(User user) {
		// dao的封装
		dao.save(user);
	}
	@Override
	public boolean checkUser(User user) {
		return dao.isExistUser(user);
	}
	@Override
	public User getUser(User user) {
		return dao.getUser(user);
	}

	
}
