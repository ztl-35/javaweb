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
			//Struts1��ͨ��action�е����ý�����ע�뵽action���У�ͬʱҲ����������Ӧ����������ʱ�䷶Χ
			//Struts2û�������Ĳ������ǳ���Ա�Լ���action����������session������
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
		
		//���ֵ�����ԣ���request��һ�������ģʽ,��JSPҳ�����ͨ��Struts2�ı�ǩ���������ݡ�(ʹ�ó������ͳһ������Եķ��)
		//��ʵ����ط�Ҳ�� ����ͨ��session�����д�������ҳ����Java����ȡ�Ͳ���ͳһ������Եı�ǩ�ķ��
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
		
		//Ϊҳ���ȡͼƬ�������ݵĴ��ݡ�
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
