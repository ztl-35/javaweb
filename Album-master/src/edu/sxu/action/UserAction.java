package edu.sxu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import edu.sxu.model.User;
import edu.sxu.service.UserService;

public class UserAction {
	
	
	private User user;
	UserService userService;
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}
	@Resource(name="UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String addUser() {
		userService.add(user);
		return "main";
	}
	public String checkUser() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		if(userService.checkUser(user)) {
			//注册不成功，在本页面继续注册
			pw.print("1");
			return null;
		}else {
			//注册成功，跳转到主页面，在主页面显示登录名
			pw.print("0");
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("USER",user);
			return "main";
		}
	}
	public String loginUser() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		if(userService.checkUser(user)) {
			pw.print("1");
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			request.getSession().setAttribute("USER",user);
			return "main";
		}else {
			pw.print("0");
			return "login";
		}
	}
	public String logout() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("USER",null);
//		request.getSession().invalidate(); 可以使得user为空，但是程序会报错
//		System.out.println(request.getSession().getAttribute("USER"));
		return "main";
	}
}