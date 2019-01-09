package myuser;

import org.apache.struts.action.ActionForm;

public class User extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String userName;
	String pwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
