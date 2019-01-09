package myuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import mypicture.Picture;
import mypicture.pictureDAO;

public class userAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String add() throws SQLException {
		UserDAO userDAO = new UserDAO();
		userDAO.addUser(user);
		return "list";
	}
	public String checkExists() throws SQLException, IOException {
		UserDAO userDAO = new UserDAO();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		if (userDAO.checkExists(user)) {
			out.print("1");
		}else {
			out.print("0");
		}
		return null;
	}
	public String checkLogin() throws SQLException, IOException {
		System.out.println("function:checkLogin");
		UserDAO userDAO = new UserDAO();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (userDAO.checkUserLogin(user)) {
			//Struts1是通过action中的配置将数据注入到action类中，同时也可以设置相应的数据作用时间范围
			//Struts2没有上述的操作，是程序员自己在action代码中设置session的属性
			session.setAttribute("user", user);
			System.out.println("checkLogin:1");
			out.print("1");
		}else {
			System.out.println("checkLogin:0");
			out.print("0");
		}
		return null;
	}
	public String list() throws SQLException {
		
		System.out.println("list");
		UserDAO userDAO = new UserDAO();
		ArrayList<User> mylist = userDAO.getusers();
		
		//存放值的属性，和request是一个请求的模式,在JSP页面可以通过Struts2的标签来访问数据。(使得程序可以统一编程语言的风格。)
		//其实这个地方也是 可以通过session来进行处理，但是页面用Java来获取就不能统一编程语言的标签的风格。
		ActionContext ctx = ActionContext.getContext();
		ctx.put("USERLIST", mylist);
		return "userlist";
	}
	public String delete() throws SQLException {
		System.out.println("id::::"+user.id);
		UserDAO userDAO = new UserDAO();
		userDAO.deleteUser(user);
		return null;
	}
	public String modify() throws SQLException {
		user = new UserDAO().getUserById(user.id);
		
		//为页面获取图片进行数据的传递。
		pictureDAO dao = new pictureDAO();
		ArrayList<Picture> pictures = dao.getPictures(user.getId(),"user");
		ActionContext ctx = ActionContext.getContext();
		ctx.put("PICTURES", pictures);
						
		return "modify";
	}
	public String save() throws SQLException {
		UserDAO userDAO = new UserDAO();
		userDAO.modifyUser(user);
		return "list";
	}
}
