package Login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.pojo.User;
import Login.service.LoginService;

@WebServlet("/login.action") 
public class LoginServlet extends HttpServlet {
		
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		LoginService service = new LoginService();
		try {
			User user = service.select(uname,upwd);
			PrintWriter out = response.getWriter();
			//��ѯȫ��
			if(user!=null){
				out.write(user.getUser_name());
				HttpSession session = request.getSession();
				session.setAttribute("USER", user);
				
				
//				//����ǹ���Ա,���ݿ��flag=0,����Ϊsession��Ϊ�Ժ��ж��û����ǹ���Ա��
//				if (service.checkAdmin(uname)) {
//					HttpSession session = request.getSession();
//					session.setAttribute("ADMIN", uname);
//				}
			}else{
				System.out.println("��¼ʧ��");
				out.write("");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}