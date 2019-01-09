package myuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Myvisit.Visitor;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CheckLogin.....");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("username");
		int id=0;
		try {
			userDAO uDao = new userDAO();
			id = uDao.getIdbyName(username);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		if (id==0) {
			pw.print("未找到该用户的信息，请先注册!");
			return;
		}
		
		ServletContext application = request.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, Visitor> map = (HashMap<String, Visitor>) application.getAttribute("ONLINE_USER");
		Set<String> key = map.keySet();
		Iterator<String> iterator = key.iterator();
		Visitor current = null;
		while(iterator.hasNext()) {
			String sid = iterator.next();
			Visitor visitor = map.get(sid);
			if (visitor.getUserID()==id) {
				current = visitor;
				break;
			}
		}
		if (current!=null) {
			pw.print("该用户已于IP地址为["+current.getIp()+"]设备登录!");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
